package com.download.download.combo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class repofunctions {
	static String connectionURL = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String password = "Root1234";
	String s1;
	String s2;
	String s3;
	private String s4;

	protected static Connection connection = null;

	protected static Connection getconnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=Root1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return connection;
	}

	public repofunctions(Connection conn) throws IOException {
		String path = "C:\\Users\\shiva\\OneDrive\\Desktop\\partFile\\";
		Path repo_path = Path.of(path + "repo.txt");
		String str1 = Files.readString(repo_path.toAbsolutePath());
		this.s1 = str1;

		Path fileName1 = Path.of(path + "repoPackage.txt");
		String str2 = Files.readString(fileName1.toAbsolutePath());
		this.s2 = str2;

		Path fileName2 = Path.of(path + "repoImport.txt");
		String str3 = Files.readString(fileName2.toAbsolutePath());
		this.s3 = str3;

		Path fileName3 = Path.of(path + "repoFieldFragment.txt");
		String str4 = Files.readString(fileName3.toAbsolutePath());
		this.s4 = str4;
	}

	public String get_repoPackage(Connection conn) throws IOException {
		// Creating a path choosing file from local
		// directory by creating an object of Path class
		repofunctions obj1 = new repofunctions(conn);
		return obj1.s2;
	}

	public static String get_import(Connection conn) throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
		repofunctions obj1 = new repofunctions(conn);
		StringBuffer str = functions.sub(obj1.s3, "#tableName#", functions.get_tableName(conn));
		return str.toString();
	}

	public static String get_repoFieldFragment(Connection conn)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
		repofunctions obj1 = new repofunctions(conn);

		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs1 = meta.getPrimaryKeys(null, null, functions.get_tableName(conn));
		String season = null;
		while (rs1.next())
			season = rs1.getString("COLUMN_NAME");

		StringBuffer str = functions.sub(obj1.s4, "#tableName#", functions.get_tableName(conn));
		String repostr = str.toString();

		StringBuffer str2 = functions.sub(repostr, "#primaryKey#", season);
		return str2.toString();

	}

}

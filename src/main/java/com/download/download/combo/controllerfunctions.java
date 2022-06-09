package com.download.download.combo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class controllerfunctions {
	static String connectionURL = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String password = "Root1234";
	String s1;
	String s2;
	String s3;
	private String s4;
	private String s5;
	private String s6;
	private String s7;
	private String s8;
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

	public controllerfunctions(Connection conn) throws IOException {
		String path = "C:\\Users\\shiva\\OneDrive\\Desktop\\partFile\\";
		Path controller_path = Path.of(path + "controller.txt");
		String str1 = Files.readString(controller_path.toAbsolutePath());
		this.s1 = str1;

		Path fileName1 = Path.of(path + "controllerPackage.txt");
		String str2 = Files.readString(fileName1.toAbsolutePath());
		this.s2 = str2;

		Path fileName2 = Path.of(path + "controllerImport.txt");
		String str3 = Files.readString(fileName2.toAbsolutePath());
		this.s3 = str3;

		Path fileName3 = Path.of(path + "controllerPost1.txt");
		String str4 = Files.readString(fileName3.toAbsolutePath());
		this.s4 = str4;

		Path fileName4 = Path.of(path + "controllerPost2.txt");
		String str5 = Files.readString(fileName4.toAbsolutePath());
		this.s5 = str5;

		Path fileName5 = Path.of(path + "controllerPost3.txt");
		String str6 = Files.readString(fileName5.toAbsolutePath());
		this.s6 = str6;

		Path fileName6 = Path.of(path + "controllerDelete.txt");
		String str7 = Files.readString(fileName6.toAbsolutePath());
		this.s7 = str7;

		Path fileName7 = Path.of(path + "controllerGet.txt");
		String str8 = Files.readString(fileName7.toAbsolutePath());
		this.s8 = str8;
	}

	public String get_package(Connection conn) throws IOException {
		// Creating a path choosing file from local
		// directory by creating an object of Path class
		functions obj = new functions(conn, connectionURL);
		return obj.s2;
	}

	public String get_import(Connection conn) throws IOException {
		functions obj = new functions(conn, connectionURL);
		return obj.s3;
	}

	public String get_controllerPostFieldFragment(Connection conn)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
		controllerfunctions obj1 = new controllerfunctions(conn);
		StringBuffer str1 = functions.sub(obj1.s4, "#tableName#", functions.get_tableName(conn));
		String post1 = str1.toString();

		StringBuffer str = functions.sub(obj.columnQuery, "#tableName#", functions.get_tableName(conn));
		String query = str.toString();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();

		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs1 = meta.getPrimaryKeys(null, null, functions.get_tableName(conn));
		String season = null;
		while (rs1.next())
			season = rs1.getString("COLUMN_NAME");

		String allCol = "";
		for (int i = 1; i <= columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			if (!season.equals(columnName)) {
				allCol = get_columnName(conn, columnName);
			}
			post1 = post1 + '\n' + allCol;
		}
		StringBuffer str2 = functions.sub(obj1.s6, "#tableName#", functions.get_tableName(conn));
		String postFinal = post1 + str2.toString();
		return postFinal;
	}

	public String get_columnName(Connection conn, String columnName) throws IOException {
		functions obj = new functions(conn, connectionURL);
		controllerfunctions obj1 = new controllerfunctions(conn);
		StringBuffer str = functions.sub(obj1.s5, "#columnName#", columnName);
		String column = str.toString();
		return column;
	}

	public String get_controllerDeleteFieldFragment(Connection conn)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
		controllerfunctions obj1 = new controllerfunctions(conn);
		StringBuffer str = functions.sub(obj1.s7, "#tableName#", functions.get_tableName(conn));
		return str.toString();
	}

	public String get_controllerGetFieldFragment(Connection conn)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
		controllerfunctions obj1 = new controllerfunctions(conn);
		StringBuffer str = functions.sub(obj1.s8, "#tableName#", functions.get_tableName(conn));
		return str.toString();
	}

}

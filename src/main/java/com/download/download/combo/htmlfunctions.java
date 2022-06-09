package com.download.download.combo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class htmlfunctions {
	static String connectionURL = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String password = "Root1234";
	String s1;
	private static String s4;
	private static String s3;
	private static String s2;

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

	public htmlfunctions(Connection conn) throws IOException {
		String path = "C:\\Users\\shiva\\OneDrive\\Desktop\\partFile\\";
		Path html_path = Path.of(path + "html.txt");
		String str1 = Files.readString(html_path.toAbsolutePath());
		this.s1 = str1;

		Path fileName1 = Path.of(path + "htmlFieldFragment1.txt");
		String str2 = Files.readString(fileName1.toAbsolutePath());
		this.s2 = str2;

		Path fileName2 = Path.of(path + "htmlFieldFragment2.txt");
		String str3 = Files.readString(fileName2.toAbsolutePath());
		this.s3 = str3;

		Path fileName3 = Path.of(path + "htmlFieldFragment3.txt");
		String str4 = Files.readString(fileName3.toAbsolutePath());
		this.s4 = str4;
	}

	public static String get_htmlFieldFragment1(Connection conn)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
		StringBuffer s = functions.sub(s2, "#tableName#", obj.get_tableName(conn));
		String new_s2 = s.toString();
		StringBuffer str = functions.sub(obj.columnQuery, "#tableName#", functions.get_tableName(conn));
		String query = str.toString();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		String tstr = "";
		for (int i = 1; i <= columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			int columnType = metadata.getColumnType(i);
			StringBuffer str1 = functions.sub(new_s2, "#columnName#", columnName);
			String str2 = str1.toString();
			StringBuffer str3 = functions.sub(str2, "#vartype#", get_vartype(columnType));

			tstr = tstr + '\n' + str3.toString();
		}
		return tstr;
	}

	public static String get_htmlFieldFragment2(Connection conn)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
		StringBuffer s = functions.sub(s3, "#tableName#", obj.get_tableName(conn));
		String new_s2 = s.toString();
		StringBuffer str = functions.sub(obj.columnQuery, "#tableName#", functions.get_tableName(conn));
		String query = str.toString();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		String tstr = "";
		for (int i = 1; i <= columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			StringBuffer str1 = functions.sub(new_s2, "#columnName#", columnName);
			tstr = tstr + '\n' + str1.toString();
		}
		return tstr;
	}

	public static String get_htmlFieldFragment3(Connection conn)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
		StringBuffer s = functions.sub(s4, "#tableName#", obj.get_tableName(conn));
		String new_s2 = s.toString();
		StringBuffer str = functions.sub(obj.columnQuery, "#tableName#", functions.get_tableName(conn));
		String query = str.toString();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		String tstr = "";
		for (int i = 1; i <= columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			StringBuffer str1 = functions.sub(new_s2, "#columnName#", columnName);
			tstr = tstr + '\n' + str1.toString();
		}
		return tstr;
	}

	public static String get_vartype(int columnType) {
		if (columnType == 12)
			return "text";
		if (columnType == 3)
			return "BigDecimal";
		if (columnType == 91)
			return "Date";
		if (columnType == 16)
			return "boolean";
		else
			return "number";
	}

}

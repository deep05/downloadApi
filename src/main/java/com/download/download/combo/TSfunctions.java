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

public class TSfunctions {
	static String connectionURL = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String password = "Root1234";
	String s1;
	String s2;
	private static String s5;
	private static String s4;
	private static String s3;

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

	public TSfunctions(Connection conn) throws IOException {
		String path = "C:\\Users\\shiva\\OneDrive\\Desktop\\partFile\\";
		Path ts_path = Path.of(path + "ts_file.txt");
		String str1 = Files.readString(ts_path.toAbsolutePath());
		this.s1 = str1;

		Path fileName1 = Path.of(path + "ts_import_file.txt");
		String str2 = Files.readString(fileName1.toAbsolutePath());
		this.s2 = str2;

		Path fileName2 = Path.of(path + "ts_file_field_fragment.txt");
		String str3 = Files.readString(fileName2.toAbsolutePath());
		this.s3 = str3;

		Path fileName3 = Path.of(path + "ts_field_fragment_initial_value.txt");
		String str4 = Files.readString(fileName3.toAbsolutePath());
		this.s4 = str4;

		Path fileName4 = Path.of(path + "ts_addModelOneArray.txt");
		String str5 = Files.readString(fileName4.toAbsolutePath());
		this.s5 = str5;
	}

	public static String get_ts_file_field_fragment(Connection conn)
			throws SQLException, ClassNotFoundException, IOException {
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
			int columnType = metadata.getColumnType(i);
			String columnVal;
			if (columnType == 4 || columnType == 3) {
				columnVal = "null";
			} else
				columnVal = "\"\"";
			StringBuffer str1 = functions.sub(s4, "#columnName#", columnName);
			String new_s3 = str1.toString();
			StringBuffer str2 = functions.sub(new_s3, "#columnValue#", columnVal);
			tstr = tstr + '\n' + str2.toString();
		}
		StringBuffer new_s4 = functions.sub(new_s2, "#ts_field_fragment_initial_value#", tstr);
		String new_s5 = new_s4.toString();
		StringBuffer new_s6 = functions.sub(new_s5, "#pkColumnName#", get_pkColumnName(conn));
		String new_s7 = new_s6.toString();
		StringBuffer new_s8 = functions.sub(new_s7, "#ts_addModelOneArray_fragment#",
				get_ts_addModelOneArray_fragment(conn));
		String new_s9 = new_s8.toString();
		StringBuffer new_s10 = functions.sub(new_s9, "#secondColumn#", metadata.getColumnName(2));
		return new_s10.toString();
	}

	public static String get_pkColumnName(Connection conn) throws SQLException, ClassNotFoundException, IOException {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getPrimaryKeys(null, null, functions.get_tableName(conn));
		String season = null;
		while (rs.next())
			season = rs.getString(4);
		return season;
	}

	public static String get_ts_addModelOneArray_fragment(Connection conn)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);
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
		String post1 = "";
		for (int i = 1; i <= columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			if (!season.equals(columnName)) {
				allCol = get_columnName(conn, columnName);
			}
			post1 = post1 + '\n' + allCol;
		}
		StringBuffer str1 = functions.sub(post1, "#tableName#", functions.get_tableName(conn));
		return str1.toString();
	}

	public static String get_columnName(Connection conn, String columnName) throws IOException {
		functions obj = new functions(conn, connectionURL);
		StringBuffer str = functions.sub(s5, "#npkColumnName#", columnName);
		String column = str.toString();
		return column;
	}

}

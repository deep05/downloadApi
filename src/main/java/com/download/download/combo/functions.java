package com.download.download.combo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class functions {
	static Connection conn;
	String s1;
	String s2;
	String s3;
	private String tableQuery;
	static String columnQuery;
	private String fieldFragment;
	private static String lastRow;

	public functions(Connection conn, String s1) throws IOException {
		this.conn = conn;
		String path = "C:\\Users\\shiva\\OneDrive\\Desktop\\partFile\\";

		Path entity_path = Path.of(path + "entity.txt");
		String str1 = Files.readString(entity_path.toAbsolutePath());
		this.s1 = str1;

		Path fileName1 = Path.of(path + "package.txt");
		String str2 = Files.readString(fileName1.toAbsolutePath());
		this.s2 = str2;

		Path fileName2 = Path.of(path + "import.txt");
		String str3 = Files.readString(fileName2.toAbsolutePath());
		this.s3 = str3;

		Path fileName4 = Path.of(path + "tableQuery.txt");
		String str4 = Files.readString(fileName4.toAbsolutePath());
		this.tableQuery = str4;

		Path fileName5 = Path.of(path + "columnQuery.txt");
		String str5 = Files.readString(fileName5.toAbsolutePath());
		this.columnQuery = str5;

		Path fileName6 = Path.of(path + "field_fragment.txt");
		String str6 = Files.readString(fileName6.toAbsolutePath());
		this.fieldFragment = str6;

		Path fileName7 = Path.of(path + "firstFromLastQuery.txt");
		String str7 = Files.readString(fileName7.toAbsolutePath());
		this.lastRow = str7;

		// Path fileName5 = Path.of(path + "field_fragment.txt");

	}

	static String connectionURL = "jdbc:mysql://localhost:3306/test";
	String user = "root";
	String password = "Root1234";
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

	public static StringBuffer sub(String str, String sourcestring, String targetstring) {
		StringBuffer strbfr = new StringBuffer(str);
		int origlen = strbfr.length();
		int newlen = 0;
		int diff = 0;
		int k = 0;
		int j = 0;
		while (k != -1) {
			k = str.indexOf(sourcestring, j);
			if (k != -1)
				strbfr.replace(k - diff, k + sourcestring.length() - diff, targetstring);
			j = k + sourcestring.length() + 1;
			newlen = strbfr.length();
			diff = origlen - newlen;
		}
		return (strbfr);
	}

	public static String readLineByLineJava8(String s) {

		try {
			return new String(Files.readAllBytes(Paths.get(s)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String get_package(Connection conn) throws IOException {
		// Creating a path choosing file from local
		// directory by creating an object of Path class
		functions obj = new functions(conn, connectionURL);
		return obj.s2;
	}

	public String get_import() throws IOException {
		functions obj = new functions(conn, connectionURL);
		return obj.s3;
	}

	public static String get_tableName(Connection conn) throws ClassNotFoundException, SQLException, IOException {
		functions obj = new functions(conn, connectionURL);
		StringBuffer str = sub(obj.tableQuery, "#screenid#", get_screenid(conn));
		String query = str.toString();
		Statement st = conn.createStatement();
		st.execute(query);
		java.sql.PreparedStatement preparedStatement = null;
		preparedStatement = conn.prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery();
		String season = null;
		if (rs.next())
			season = rs.getString(1);
		return season;
	}

	public static String get_screenid(Connection conn) throws SQLException {
		String query2 = lastRow;
		java.sql.PreparedStatement preparedStatement = null;
		preparedStatement = conn.prepareStatement(query2);
		ResultSet rs = preparedStatement.executeQuery();
		String season = null;
		if (rs.next())
			season = rs.getString(1);
		return season;
	}

	public static String get_file_field_list_fragment(Connection conn)
			throws SQLException, IOException, ClassNotFoundException {
		functions obj = new functions(conn, connectionURL);
		StringBuffer str = sub(obj.columnQuery, "#tableName#", get_tableName(conn));
		String query = str.toString();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		String tstr;
		String s = "";
		for (int i = 1; i <= columnCount; i++) {
			String columnName = metadata.getColumnName(i);
			tstr = get_field_fragment(columnName, i);
			// System.out.println("------" + columnName + "------");
			s = s + tstr + '\n';
		}
		return s;
	}

	public static String get_field_fragment(String fieldvalue, int columnNumber)
			throws IOException, ClassNotFoundException, SQLException {
		functions obj = new functions(conn, connectionURL);

		StringBuffer s = sub(obj.fieldFragment, "#flag#", flag(fieldvalue, conn));
		String s1 = s.toString();
		StringBuffer s2 = sub(s1, "#keyflag#", keyflag(fieldvalue, conn));
		String s3 = s2.toString();
		StringBuffer s4 = sub(s3, "#Id#", Id(fieldvalue, conn));
		String s5 = s4.toString();
		StringBuffer s6 = sub(s5, "#GeneratedValue#", genaratedValue(fieldvalue, conn));
		String s7 = s6.toString();
		StringBuffer s8 = sub(s7, "#vartype#", vartype(columnNumber, conn));
		String s9 = s8.toString();
		StringBuffer s10 = sub(s9, "#fieldvalue#", fieldvalue);
		String s11 = s10.toString();

		return s11;

	}

	public static String flag(String fieldvalue, Connection conn)
			throws SQLException, ClassNotFoundException, IOException {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getPrimaryKeys(null, null, get_tableName(conn));
		String season = null;
		while (rs.next())
			season = rs.getString("COLUMN_NAME");
		// System.out.println("-----" + season + "-------" + fieldvalue);
		if (season.equals(fieldvalue))
			return "false";
		else
			return "true";
	}

	public static String keyflag(String fieldvalue, Connection conn)
			throws SQLException, ClassNotFoundException, IOException {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getPrimaryKeys(null, null, get_tableName(conn));
		String season = null;
		while (rs.next())
			season = rs.getString(4);
		// System.out.println("-----" + season + "-------" + fieldvalue);
		if (season.equals(fieldvalue))
			return "Primary Key";
		else
			return "(size = 500)(required)";
	}

	public static String Id(String fieldvalue, Connection conn)
			throws SQLException, ClassNotFoundException, IOException {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getPrimaryKeys(null, null, get_tableName(conn));
		String season = null;
		while (rs.next())
			season = rs.getString(4);
		// System.out.println("-----" + season + "-------" + fieldvalue);
		if (season.equals(fieldvalue))
			return "@Id";
		else
			return "";
	}

	public static String vartype(int i, Connection conn) throws SQLException, ClassNotFoundException, IOException {
		StringBuffer str = sub(columnQuery, "#tableName#", get_tableName(conn));
		String query = str.toString();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData metadata = rs.getMetaData();
		int columnType = metadata.getColumnType(i);
		if (columnType == 12)
			return "String";
		if (columnType == 3)
			return "BigDecimal";
		if (columnType == 91)
			return "Date";
		if (columnType == 16)
			return "boolean";
		else
			return "Integer";
	}

	public static String genaratedValue(String fieldvalue, Connection conn)
			throws SQLException, ClassNotFoundException, IOException {
		DatabaseMetaData meta = conn.getMetaData();
		ResultSet rs = meta.getPrimaryKeys(null, null, get_tableName(conn));
		String season = null;
		while (rs.next())
			season = rs.getString(4);
		// System.out.println("-----" + season + "-------" + fieldvalue);
		if (season.equals(fieldvalue))
			return "@GeneratedValue(strategy=GenerationType.IDENTITY)";
		else
			return "";
	}

}

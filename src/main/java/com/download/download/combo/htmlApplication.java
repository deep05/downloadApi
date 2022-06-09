package com.download.download.combo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class htmlApplication {
	public static ResponseFile main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		Connection conn = htmlfunctions.getconnection();
		htmlfunctions f1 = new htmlfunctions(conn);
		functions f = new functions(conn, functions.connectionURL);

		StringBuffer str = functions.sub(f1.s1, "#tableName#", f.get_tableName(conn));
		String str_new = str.toString();

		StringBuffer str2 = functions.sub(str_new, "#html_field_fragment1#",
				htmlfunctions.get_htmlFieldFragment1(conn));
		String str_new2 = str2.toString();

		StringBuffer str3 = functions.sub(str_new2, "#html_field_fragment2#",
				htmlfunctions.get_htmlFieldFragment2(conn));
		String str_new3 = str3.toString();

		StringBuffer str4 = functions.sub(str_new3, "#html_field_fragment3#",
				htmlfunctions.get_htmlFieldFragment3(conn));
		String str_new4 = str4.toString();

		System.out.println(str_new4);

		String fileName = f.get_tableName(conn) + ".html";
		PrintWriter out = new PrintWriter(fileName);
		out.println(str_new4);
		out.close();

		ResponseFile response = new ResponseFile();

		return response.withName(fileName).withContents(str_new4);

	}

}

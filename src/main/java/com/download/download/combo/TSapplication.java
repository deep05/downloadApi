package com.download.download.combo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class TSapplication {
	public static ResponseFile main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		Connection conn = TSfunctions.getconnection();
		TSfunctions f1 = new TSfunctions(conn);
		functions f = new functions(conn, functions.connectionURL);

		StringBuffer str = functions.sub(f1.s1, "#tsImportFile#", f1.s2);
		String str_new = str.toString();

		StringBuffer str2 = functions.sub(str_new, "#tableName#", f.get_tableName(conn));
		String str_new2 = str2.toString();

		StringBuffer str3 = functions.sub(str_new2, "#ts_file_field_fragment#", f1.get_ts_file_field_fragment(conn));
		String str_new3 = str3.toString();

		System.out.println(str_new3);

		String fileName = f.get_tableName(conn) + ".ts";
		PrintWriter out = new PrintWriter(fileName);
		out.println(str_new3);
		out.close();

		ResponseFile response = new ResponseFile();

		return response.withName(fileName).withContents(str_new3);

	}

}

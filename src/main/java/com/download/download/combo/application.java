package com.download.download.combo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class application {

	public static ResponseFile main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		Connection conn = functions.getconnection();
		functions f = new functions(conn, functions.connectionURL);
		StringBuffer str = functions.sub(f.s1, "#packageName#", f.s2);
		String str_new = str.toString();

		StringBuffer str2 = functions.sub(str_new, "#import_packagefile_fragment#", f.s3);
		String str_new2 = str2.toString();
		// System.out.println(str_new2);

		StringBuffer str3 = functions.sub(str_new2, "#tableName#", f.get_tableName(conn));
		String str_new3 = str3.toString();
		// System.out.println(str_new3);

		StringBuffer str4 = functions.sub(str_new3, "#filefieldlistfragment#", f.get_file_field_list_fragment(conn));
		String str_new4 = str4.toString();
		System.out.println(str_new4);

		String fileName = f.get_tableName(conn) + "Mst.java";
		PrintWriter out = new PrintWriter(fileName);
		out.println(str_new4);
		out.close();

		ResponseFile response = new ResponseFile();

		return response.withName(fileName).withContents(str_new4);
	}

}

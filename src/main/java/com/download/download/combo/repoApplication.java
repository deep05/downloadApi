package com.download.download.combo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class repoApplication {
	public static ResponseFile main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		Connection conn = repofunctions.getconnection();
		repofunctions f1 = new repofunctions(conn);
		functions f = new functions(conn, functions.connectionURL);

		StringBuffer str = functions.sub(f1.s1, "#repoPackage#", f1.s2);
		String str_new = str.toString();

		StringBuffer str2 = functions.sub(str_new, "#repoImport#", repofunctions.get_import(conn));
		String str_new2 = str2.toString();

		StringBuffer str3 = functions.sub(str_new2, "#tableName#", functions.get_tableName(conn));
		String str_new3 = str3.toString();

		StringBuffer str4 = functions.sub(str_new3, "#repoFieldFragment#", repofunctions.get_repoFieldFragment(conn));
		String str_new4 = str4.toString();

		System.out.println(str_new4);

		String fileName = f.get_tableName(conn) + "Repo.java";
		PrintWriter out = new PrintWriter(fileName);
		out.println(str_new4);
		out.close();

		ResponseFile response = new ResponseFile();

		return response.withName(fileName).withContents(str_new4);

	}

}

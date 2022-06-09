package com.download.download.combo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class controllerApplication {
	public static ResponseFile main(String args[]) throws IOException, ClassNotFoundException, SQLException {
		Connection conn = controllerfunctions.getconnection();
		controllerfunctions f1 = new controllerfunctions(conn);
		functions f = new functions(conn, functions.connectionURL);

		StringBuffer str = functions.sub(f1.s1, "#packageController#", f1.s2);
		String str_new = str.toString();

		StringBuffer str1 = functions.sub(f1.s3, "#tableName#", functions.get_tableName(conn));

		StringBuffer str2 = functions.sub(str_new, "#imoprt_controller_file#", str1.toString());
		String str_new2 = str2.toString();

		StringBuffer str3 = functions.sub(str_new2, "#tableName#", functions.get_tableName(conn));
		String str_new3 = str3.toString();

		StringBuffer str4 = functions.sub(str_new3, "#controllerPostFieldFragment#",
				f1.get_controllerPostFieldFragment(conn));
		String str_new4 = str4.toString();

		StringBuffer str5 = functions.sub(str_new4, "#controllerDeleteFieldFragment#",
				f1.get_controllerDeleteFieldFragment(conn));
		String str_new5 = str5.toString();

		StringBuffer str6 = functions.sub(str_new5, "#controllerGetFieldFragment#",
				f1.get_controllerGetFieldFragment(conn));
		String str_new6 = str6.toString();

		System.out.println(str_new6);

		String fileName = f.get_tableName(conn) + "Controller.java";
		PrintWriter out = new PrintWriter(fileName);
		out.println(str_new6);
		out.close();

		ResponseFile response = new ResponseFile();

		return response.withName(fileName).withContents(str_new6);

	}

}

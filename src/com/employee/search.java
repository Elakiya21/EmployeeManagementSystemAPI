package com.employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String attr = request.getParameter("attributes");
		String type = request.getParameter("type");
		String ch = request.getParameter("ch");
		String query = null;
		ArrayList<Employee> employee = null;
		if (type.equals("contains")) {
			if (!attr.equals("id") || !attr.equals("age"))
				query = " like '%" + ch + "%'";

		} else if (type.equals("StartsWith")) {
			if (!attr.equals("id") || !attr.equals("age"))
				query = " like '" + ch + "%'";

		} else if (type.equals("EndsWith")) {
			if (!attr.equals("id") || !attr.equals("age"))
				query = " like '%" + ch + "'";

		} else if (type.equals("Equal")) {
			if (!attr.equals("id") || !attr.equals("age"))
				query = "='" + ch + "'";
			else
				query = "=" + ch + "";
		} else if (type.equals("NotEqual")) {
			if (!attr.equals("id") || !attr.equals("age"))
				query = "<>'" + ch + "'";
			else
				query = "<>" + ch + "";
		}

		try {
			employee = DbUtil.searchEmployee(attr, query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String employeeJSON = gson.toJson(employee);
		PrintWriter out = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.write(employeeJSON);
		out.close();
	}

}

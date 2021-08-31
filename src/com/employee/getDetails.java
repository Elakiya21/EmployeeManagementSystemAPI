package com.employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/getDetails")
public class getDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			String username = request.getParameter("id");
			try {
				id = DbUtil.getEmployeeId(username);
			} catch (ClassNotFoundException | SQLException e1) {

				e1.printStackTrace();
			}
		}
		Employee employee = null;
		try {
			employee = DbUtil.getDetails(id);
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

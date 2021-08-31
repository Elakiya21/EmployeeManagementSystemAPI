package com.employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

@WebServlet("/sort")
public class sort extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String attr = request.getParameter("attributes");
		String type = request.getParameter("type");
		ArrayList<Employee> employee = null;
		try {
			employee = DbUtil.sortEmployee(attr, type);
		} catch (ClassNotFoundException | SQLException | IOException e) {
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

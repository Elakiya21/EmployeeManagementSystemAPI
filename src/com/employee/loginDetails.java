package com.employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/loginDetails")
public class loginDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<String> employee = new ArrayList<String>();
		HttpSession session = request.getSession();
		String name=String.valueOf(session.getAttribute("username"));
		employee.add(name);
		try {
			employee.add(DbUtil.getCategoryName(name));			
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

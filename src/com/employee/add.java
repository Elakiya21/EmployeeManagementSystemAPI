package com.employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String mail = request.getParameter("mail");
		String mobile = request.getParameter("mobileNum");
		String dep = request.getParameter("dep");
		String category = request.getParameter("category");
		Employee employee = new Employee(name, age, address, gender, mail, mobile, dep, category);
		try {
			DbUtil.addEmployee(employee);
			out.write("Successfull");
		} catch (Exception e) {
			out.write("Unsuccessfull");
		}		
		out.close();
	}

}

package com.employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int Id = Integer.parseInt(request.getParameter("id"));
		try {
			DbUtil.deleteStudent(Id);
			PrintWriter out = response.getWriter();
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("atext/html");
			response.setCharacterEncoding("UTF-8");
			out.write("deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

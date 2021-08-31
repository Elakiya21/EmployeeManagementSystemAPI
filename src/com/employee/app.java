package com.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import com.employee.DbUtil;
import com.employee.Employee;




public class app {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//addEmployee("elaki",1);
		//Update();
		//list();
		try {
			System.out.println("hai");
			listEmployee();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		//	e.printStackTrace();
		}
	}
	public static Connection dbUtil() throws ClassNotFoundException, SQLException {
		String constr="jdbc:sqlserver://employee121.database.windows.net:1433;database=EmployeeDB;user=elakiya@employee121;password=Elakiy@21;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	//	Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(constr);
		System.out.println(con);
		return con;
	}
	public static void addEmployee(String name,int id) throws ClassNotFoundException, SQLException {
		Connection con = dbUtil();
		String sql = "insert into trail values(?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2,id);
		ps.executeUpdate();
		con.close();
	}
	public static void list() throws SQLException, ClassNotFoundException {
		Connection con = dbUtil();
		Statement stmt = null;
	stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("select * from employee");

	while (rs.next()) {
		String name = rs.getString("name");
		int id = rs.getInt("id");
		System.out.println("name :"+name+" id :"+id);
	}
	}
	public static void Update() throws SQLException, ClassNotFoundException {
		Connection con = dbUtil();
		Statement stmt = con.createStatement();
		String sql = "update employee "
				+ "set id=0 where name='admin'";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.executeUpdate();
		
		con.close();
	}
	public static void listEmployee() throws Exception {

		ArrayList<Employee> Emplist = new ArrayList<>();
		String c="jdbc:sqlserver://employee121.database.windows.net:1433;database=EmployeeDB;user=elakiya@employee121;password=Elakiy@21;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		String constr="jdbc:sqlserver://employee121.database.windows.net:1433;database=EmployeeDB;user=elakiya@employee121;password=Elakiy@21;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		System.out.println("hai2");
			Connection con = DriverManager.getConnection(c);
			System.out.println("hello");
			System.out.println("con "+con);
			Statement stmt = null;
		try {
			
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employee");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String mail = rs.getString("mail");
				String mobileNum = rs.getString("mobilenumber");
				String dep = rs.getString("department");
				String catg = DbUtil.getCategoryName(mail);
				//System.out.println(id+" "+name+" "+age+" "+address+" "+gender+" "+mail+" "+mobileNum+" "+dep+" "+catg);
				if (id != 0) {
					Employee emp = new Employee(id, name, age, address, gender, mail, mobileNum, dep, catg);
					Emplist.add(emp);
				}
			}
			System.out.println("jjjj");
			System.out.println(Emplist);
		} finally {
			stmt.close();
			con.close();

		}
	}


}

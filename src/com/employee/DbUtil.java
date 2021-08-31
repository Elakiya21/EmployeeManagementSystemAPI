package com.employee;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

public class DbUtil {
	public static Connection dbUtil() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MyFirstDb", "postgres",
				"Elakiya21");
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		String constr="jdbc:sqlserver://employee121.database.windows.net:1433;database=EmployeeDB;user=elakiya@employee121;password=Elakiy@21;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
//		Connection con = DriverManager.getConnection(constr);
//		System.out.println(con);
		return con;
	}
	public static boolean isMatch(String username, String password) throws SQLException, ClassNotFoundException {
		Connection con = dbUtil();
		Statement stmt = con.createStatement();
		String pasw;
		String sql = "Select password from login where username='" + username + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			pasw = rs.getString("password");
			if (pasw.equals(password))
				return true;
		}
		stmt.close();
		con.close();
		return false;

	}

	public static void addEmployee(Employee e) throws ClassNotFoundException, SQLException {
		Connection con = dbUtil();
		try {
		String name = e.getName();
		int age = e.getAge();
		String address = e.getAddress();
		String gender = e.getGender();
		String mail = e.getMail();
		String mobile = e.getMobileNum();
		String dep = e.getDep();
		String sql = "insert into employee values(?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setString(3, address);
		ps.setString(4, gender);
		ps.setString(5, mail);
		ps.setString(6, mobile);
		ps.setString(7, dep);
		ps.executeUpdate();
		try {
			String mail1 = e.getMail();
			String password = generatePasswor();
			String type = e.getCategory();
			String sql1 = "insert into login values(?,?,?)";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, mail1);
			ps1.setString(2, password);
			ps1.setString(3, type);
			ps1.executeUpdate();
		} catch (Exception ex) {
			ex.getMessage();
		}
		}catch(SQLIntegrityConstraintViolationException ex)
		{
			System.out.println(ex.getMessage());
		}
		con.close();

	}

	public static String generatePasswor() {
		int n = 10;
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	public static ArrayList<Employee> listEmployee() throws Exception {

		ArrayList<Employee> Emplist = new ArrayList<>();

		Connection con = null;
		Statement stmt = null;

		try {
			con = DbUtil.dbUtil();
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

			return Emplist;
		} finally {
			stmt.close();
			con.close();

		}
	}

	public static void deleteStudent(int id) throws ClassNotFoundException, SQLException {
		Connection con = DbUtil.dbUtil();
		Statement stmt = con.createStatement();

		String sql = "delete from employee where id=" + id;
		stmt.executeUpdate(sql);
		stmt.close();
		con.close();
	}

	public static void UpdateEmployee(Employee e) throws SQLException, ClassNotFoundException {
		Connection con = dbUtil();
		Statement stmt = con.createStatement();
		
		int id1 = e.getId();
		String existMail = null;
		String sql2 = "Select mail from employee where id="+id1;
		ResultSet rs = stmt.executeQuery(sql2);
		if (rs.next())
			existMail = rs.getString("mail");
		
		int id = e.getId();
		String name = e.getName();
		int age = e.getAge();
		String address = e.getAddress();
		String gender = e.getGender();
		String mail = e.getMail();
		String mobile = e.getMobileNum();
		String dep = e.getDep();
		String cat = e.getCategory();
		String sql = "update employee "
				+ "set name=?,age=?,address=?,gender=?,mail=?,mobilenumber=?,department=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setString(3, address);
		ps.setString(4, gender);
		ps.setString(5, mail);
		ps.setString(6, mobile);
		ps.setString(7, dep);
		ps.setInt(8, id);
		ps.executeUpdate();
		String sql1 = "update login set category='" + cat + "'" + " where username='" + mail + "'";
		stmt.executeUpdate(sql1);
		if(!mail.equals(existMail))
		{
			String password=generatePasswor();
			String sql4 = "update login set password='" + password + "'" + " where username='" + mail + "'";
			stmt.executeUpdate(sql4);
		}
		stmt.close();
		con.close();
	}

	public static ArrayList<Employee> sortEmployee(String attr, String type)
			throws SQLException, ClassNotFoundException, IOException {
		Connection con = DbUtil.dbUtil();
		Statement stmt = con.createStatement();
		ArrayList<Employee> sortlist = new ArrayList<>();
		String sql = "Select * from employee order by " + attr + " " + type + "";
		ResultSet rs = stmt.executeQuery(sql);
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
			if (id != 0) {
				Employee emp = new Employee(id, name, age, address, gender, mail, mobileNum, dep, catg);
				sortlist.add(emp);
			}
		}
		stmt.close();
		con.close();
		return sortlist;

	}

	public static String getCategoryName(String username) throws SQLException, ClassNotFoundException {
		Connection con = DbUtil.dbUtil();
		Statement stmt = con.createStatement();
		String category = null;
		String sql = "Select category from login where username='" + username + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
			category = rs.getString("category");
		stmt.close();
		con.close();
		return category;

	}

	public static ArrayList<Employee> searchEmployee(String attr, String ch)
			throws SQLException, ClassNotFoundException, IOException {
		Connection con = DbUtil.dbUtil();
		Statement stmt = con.createStatement();
		ArrayList<Employee> searchlist = new ArrayList<>();
		String sql;
		sql = "select * from employee where " + attr + ch;
		ResultSet rs = stmt.executeQuery(sql);
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
			if (id != 0) {
				Employee emp = new Employee(id, name, age, address, gender, mail, mobileNum, dep, catg);
				searchlist.add(emp);
			}
		}
		stmt.close();
		con.close();
		return searchlist;

	}

	public static Employee getDetails(int id) throws SQLException, ClassNotFoundException {
		Connection con = dbUtil();
		Statement stmt = con.createStatement();
		Employee emp = null;
		String sql = "select * from employee where id=" + id;
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
		String name = rs.getString("name");
		int age = rs.getInt("age");
		String address = rs.getString("address");
		String gender = rs.getString("gender");
		String mail = rs.getString("mail");
		String mobileNum = rs.getString("mobilenumber");
		String dep = rs.getString("department");
		String catg = DbUtil.getCategoryName(mail);
		String pass = getCurrentPassword(id);
		emp = new Employee(id, name, age, address, gender, mail, mobileNum, dep, catg, pass);
		}
		stmt.close();
		con.close();
		return emp;
	}

	public static int getEmployeeId(String mail) throws SQLException, ClassNotFoundException {
		Connection con = dbUtil();
		Statement stmt = con.createStatement();
		int id = 0;
		String sql = "Select id from employee where mail='" + mail + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next())
		id = rs.getInt("id");
		stmt.close();
		con.close();
		return id;

	}

	public static void UpdateEmployeeProfile(Employee e) throws ClassNotFoundException, SQLException {
		Connection con = dbUtil();
		Statement stmt = con.createStatement();
		int id = e.getId();
		String name = e.getName();
		int age = e.getAge();
		String address = e.getAddress();
		String mobile = e.getMobileNum();
		String password = e.getPassword();
		String sql = "update employee " + "set name=?,age=?,address=?,mobilenumber=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ps.setInt(2, age);
		ps.setString(3, address);
		ps.setString(4, mobile);
		ps.setInt(5, id);
		ps.executeUpdate();
		String sql1 = "Select mail from employee where id=" + id;
		ResultSet rs = stmt.executeQuery(sql1);
		rs.next();
		String username = rs.getString("mail");
		String sql2 = "UPDATE login set password=? where username='" + username + "'";
		PreparedStatement ps1 = con.prepareStatement(sql2);
		ps1.setString(1, password);
		ps1.executeUpdate();
		stmt.close();
		con.close();
	}

	public static String getCurrentPassword(int id) throws SQLException, ClassNotFoundException {
		Connection con = dbUtil();
		Statement stmt = con.createStatement();
		String sql1 = "Select mail from employee where id=" + id;
		ResultSet rs = stmt.executeQuery(sql1);
		rs.next();
		String username = rs.getString("mail");
		String sql2 = "select password from login where username='" + username + "'";
		ResultSet rs1 = stmt.executeQuery(sql2);
		rs1.next();
		String pass = rs1.getString("password");
		stmt.close();
		con.close();
		return pass;

	}

	public static String getPassword(String username) throws SQLException, ClassNotFoundException {
		Connection con = dbUtil();
		String pass = null;
		Statement stmt = con.createStatement();
		String sql2 = "select password from login where username='" + username + "'";
		ResultSet rs1 = stmt.executeQuery(sql2);
		if(rs1.next())
			pass = rs1.getString("password");
		stmt.close();
		con.close();
		return pass;
	}
	public static String getRole(String username) throws SQLException, ClassNotFoundException {
		Connection con = DbUtil.dbUtil();
		Statement stmt = con.createStatement();
		String category = null;
		String sql = "Select category from login where username='" + username + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next())
			category = rs.getString("category");
		stmt.close();
		con.close();
		return category;

	}
}

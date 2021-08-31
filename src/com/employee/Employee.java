package com.employee;

public class Employee {
	int id;
	String name;
	int age;
	String address;
	String gender;
	String mail;
	String mobileNum;
	String dep;
	String attr;
	String Sort;
	String category;
	String password;

	public Employee(int id, String name, int age, String address, String gender, String mail, String mobileNum,
			String dep, String category) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.gender = gender;
		this.mail = mail;
		this.mobileNum = mobileNum;
		this.dep = dep;
		this.category = category;
	}

	public Employee(int id, String name, int age, String address, String gender, String mail, String mobileNum,
			String dep, String category, String pass) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
		this.gender = gender;
		this.mail = mail;
		this.mobileNum = mobileNum;
		this.dep = dep;
		this.category = category;
		this.password = pass;
	}

	public Employee(String name, int age, String address, String gender, String mail, String mobileNum, String dep,
			String category) {

		this.name = name;
		this.age = age;
		this.address = address;
		this.gender = gender;
		this.mail = mail;
		this.mobileNum = mobileNum;
		this.dep = dep;
		this.category = category;
	}

	public Employee(int id2, String name2, int age2, String address2, String mobile, String password2) {
		this.id = id2;
		this.name = name2;
		this.age = age2;
		this.address = address2;
		this.mobileNum = mobile;
		this.password = password2;

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setSort(String Sort) {
		this.Sort = Sort;
	}

	public String getSort() {
		return Sort;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

	public String getAttr() {
		return attr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", gender=" + gender
				+ ", mail=" + mail + ", mobileNum=" + mobileNum + ", dep=" + dep + "]";
	}

}

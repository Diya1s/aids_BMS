package BankingSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class User {
	private Scanner scanner;
	private Connection connection;
public User(Scanner scanner, Connection connection) {//local variable
		// TODO Auto-generated constructor stub
	this.scanner=scanner;
	this.connection=connection;
	}
public User(Connection connection2, Scanner scanner2) {
	// TODO Auto-generated constructor stub
}
public void register() {
	scanner.nextLine();
	System.out.println("Full name: ");
	String full_name=scanner.nextLine();
	
	System.out.println("Email: ");
	String email=scanner.nextLine();
	
	System.out.println("Password: ");
	String password=scanner.nextLine();
	
	String query="INSERT INTO user(fullname,email,password) VALUES(?,?,?)";
try {
	PreparedStatement preparedStatement=connection.prepareStatement(query);
	preparedStatement.setString(1, full_name);
	preparedStatement.setString(2, email);
	preparedStatement.setString(3, password);
	int affectedRows=preparedStatement.executeUpdate();
	if(affectedRows >0) {
		System.out.println("Registration Successfull !!!");
	}else {
		System.out.println("Registration Failed !!!");
	}
}catch(Exception e) {
	e.printStackTrace();
}
}
private boolean user_exist(String email) {
	
	String user_query="SELECT * FROM User WHERE email=?";
try {
	PreparedStatement preparedStatement=connection.prepareStatement(user_query);
	preparedStatement.setString(1, email);
	ResultSet resultSet=preparedStatement.executeQuery();
	if(resultSet.next()) {
		return true;
	}else {
		return false;
	}
}catch(Exception e) {
	e.printStackTrace();
}return false;
}
	public String login() {
		scanner.nextLine();
		System.out.println("Email: ");
		String email=scanner.nextLine();
		System.out.println("Password: ");
		String password=scanner.nextLine();
		if(user_exist(email)) {
			System.out.println("user already exist for this  email address !!!");
			return null;
		}

		String login_query="SELECT * FROM User WHERE email=? AND password=?";
		try {
			String query;
			PreparedStatement preparedStatement=connection.prepareStatement(login_query);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				System.out.println("Home page");
				//return email;
			}else {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	
}
}

package org.jdbcpractice;

import java.sql.*;
import java.util.Scanner;

public class DemoJDBC {
	static String url = "jdbc:postgresql://localhost:5432/hibpractice";
	static String uname = "postgres";
	static String password = "1234";
	static String driver = "org.postgresql.Driver";

	public static void main(String[] args) {
		fetchDataFromDB();
		if (updateToTable() > 0) {
			fetchDataFromDB();
		}
	}

	private static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, uname, password);
			connection.setAutoCommit(true);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}


	private static void fetchDataFromDB() {
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver"); // optional
			connection = DriverManager.getConnection(url, uname, password);
			System.out.println("Connection Established.");
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM student;";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("rollno") + " - ");
				System.out.print(resultSet.getInt("age") + " - ");
				System.out.println(resultSet.getString("name"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}

		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	private static int insertToDB() {
		// CRUD operation
		Connection connection = null;
		try {
			Class.forName(driver); // optional
			connection = DriverManager.getConnection(url, uname, password);
			System.out.println("Connection Established.");
			String query = "insert into student values(?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Input rollno : > ");
			statement.setInt(1, scanner.nextInt());
			System.out.println("Input age : > ");
			statement.setInt(2, scanner.nextInt());
			System.out.println("Input name : > ");
			statement.setString(3, scanner.nextLine());
			return statement.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}


	private static int deleteFromTable() {
		try (Connection connection = getConnection()){
			String query = "delete from student where rollno = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			Scanner scanner = new Scanner(System.in);
			System.out.println("Input roll no to delete: ");
			statement.setInt(1, scanner.nextInt());
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static int updateToTable() {
		try (Connection connection = getConnection()) {
			try {
				String query = "update student set name = ? where rollno = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter Name: ");
				statement.setString(1, scanner.nextLine());
				System.out.println("Enter rollno: ");
				statement.setInt(2, scanner.nextInt());
				return statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}

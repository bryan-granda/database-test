package com.database.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PreDestroy;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class DatabaseApplication implements CommandLineRunner{
	Connection connection;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		DataSource dataSource = SqlConnection.createSqlConnection();
		connection = dataSource.getConnection();
		System.out.println("Connected to database");
		
		String query = "SELECT * FROM client";
		ResultSet resultSet = connection.createStatement().executeQuery(query);
		
		while (resultSet.next()) {
			Client client = new Client();
			client.setId(resultSet.getInt("id"));
			client.setName(resultSet.getString("name"));
			System.out.println(client);
		}
	}
	
	@PreDestroy
	public void onDestroy() throws SQLException {
		System.out.println("Closing connection");
		connection.close();
	}

}

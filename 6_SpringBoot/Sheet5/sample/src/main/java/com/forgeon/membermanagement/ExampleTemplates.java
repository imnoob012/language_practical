package com.forgeon.membermanagement;													
													
import java.math.BigDecimal;													
import java.sql.Connection;													
import java.sql.DriverManager;													
import java.sql.PreparedStatement;													
import java.sql.ResultSet;													
import java.sql.SQLException;													
import java.util.ArrayList;													
import java.util.List;													
													
import org.springframework.stereotype.Controller;													
import org.springframework.ui.Model;													
import org.springframework.web.bind.annotation.GetMapping;

import com.forgeon.membermanagement.entity.Users;													
													
@Controller													
public class ExampleTemplates {													
													
	@GetMapping("/users")													
	public String users(Model model) throws SQLException {													
														
	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres2", "root",													
	"root");													
	PreparedStatement statement = connection.prepareStatement("SELECT id, name FROM users");													
	ResultSet resultSet = statement.executeQuery();													
														
	List<Users> userList = new ArrayList<>();													
														
	while (resultSet.next()) {													
													
	BigDecimal id = resultSet.getBigDecimal("id");													
	String name = resultSet.getString("name");													
													
	Users user = new Users(id, name);													
													
	userList.add(user);													
	}													
													
	model.addAttribute("userList", userList);													
													
	return "users";													
}													
													
}													
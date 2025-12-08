package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.AllTableData;

@Controller
public class ViewController {
	
	@GetMapping("/")
	public String getAllTableData(Model model) throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root")) {
			
			AllTableData data = new AllTableData();
			// ユーザーテーブルを取得
			try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users;")) {
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					data.getUserIds().add(resultSet.getInt("id"));
					data.getUserNames().add(resultSet.getString("name"));
				}
			}
			// ページに表示させる名前とスキルを取得
			try (PreparedStatement statement = connection.prepareStatement("SELECT s.id, u.name, s.skill FROM skills AS s INNER JOIN users AS u ON s.user_id = u.id")) {
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					data.getSkillNames().add(resultSet.getString("name"));
					data.getSkills().add(resultSet.getString("skill"));
					data.getSkillIds().add(resultSet.getInt("id"));
				}
			}
			model.addAttribute("allData", data);
		} catch (SQLException e) {
			throw e;
		}
		return "index";
	}
}
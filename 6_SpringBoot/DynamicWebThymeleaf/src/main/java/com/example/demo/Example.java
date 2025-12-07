package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.SkillDto;
import com.example.demo.dto.SkillListDto;
import com.example.demo.dto.UserResponse;

@RestController
public class Example {
	
//	⭐︎ユーザーテーブルの処理
//	全件取得
	@GetMapping("/api/users")
	public UserResponse getUsers() throws SQLException {
	
		List<String> userNames = new ArrayList<>();
		List<Integer> userIds = new ArrayList<>();
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root");
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
		ResultSet resultSet = statement.executeQuery();
	
		while(resultSet.next()) {
			userNames.add(resultSet.getString("name"));
			userIds.add(resultSet.getInt("id"));
		}
		
		return new UserResponse(userNames, userIds);
	}
//	絞り込み検索
	@PostMapping("/api/users/filter")
	public UserResponse filterUsers(@RequestBody String filterName) throws SQLException {
		
		String regexPattern = ".*" + filterName + ".*";
		
		List<String> userNames = new ArrayList<>();
		List<Integer> userIds = new ArrayList<>();
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root");
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE name ~ ?");
		statement.setString(1, regexPattern);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			userNames.add(resultSet.getString("name"));
			userIds.add(resultSet.getInt("id"));
		}
		
		
		return new UserResponse(userNames, userIds);
	}
	
	
//	ユーザー追加
	@PostMapping("/api/users/add")
	public void addUser(@RequestBody String name) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root");
		PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name) VALUES(?)");
		// プレースホルダー（１番の?）に名前を設定する（インデックスは１から始まる）
		statement.setString(1, name);
		statement.executeUpdate();
	}
	
//	ユーザー削除
	@PostMapping("/api/users/delete/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) throws SQLException {
		
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root")) {
			// 子テーブル(skills)のレコードを削除する処理
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM skills WHERE user_id = ?")) {
				statement.setInt(1, userId);
				statement.executeUpdate();
			}
			
			// 親テーブル(users)のレコードを削除する処理
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
				statement.setInt(1, userId);
				statement.executeUpdate();				
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	
//	⭐︎スキルテーブルの処理
//	全件取得処理
	@GetMapping("/api/skills")
	public SkillListDto getSkills() throws SQLException {
		List<String> userNames = new ArrayList<>();
		List<String> userSkills = new ArrayList<>();
		List<Integer> skillIds = new ArrayList<>();
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root");
		PreparedStatement statement = connection.prepareStatement("SELECT s.id, u.name, s.skill FROM skills AS s INNER JOIN users AS u ON s.user_id = u.id");
		ResultSet resultSet = statement.executeQuery();
	
		while(resultSet.next()) {
			userNames.add(resultSet.getString("name"));
			userSkills.add(resultSet.getString("skill"));
			skillIds.add(resultSet.getInt("id"));
		}
		
		return new SkillListDto(userNames, userSkills, skillIds);
	}
//  絞り込み検索処理
	@PostMapping("/api/skills/filter")
	public SkillListDto filterSkills(@RequestBody String filterSkill) throws SQLException {

		String regexPattern = ".*" + filterSkill + ".*";
		
		List<String> userNames = new ArrayList<>();
		List<String> userSkills = new ArrayList<>();
		List<Integer> skillIds = new ArrayList<>();
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root");
		PreparedStatement statement = connection.prepareStatement("SELECT s.id, u.name, s.skill FROM skills AS s INNER JOIN users AS u ON s.user_id = u.id WHERE skill ~ ?");
		statement.setString(1, regexPattern);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			userNames.add(resultSet.getString("name"));
			userSkills.add(resultSet.getString("skill"));
			skillIds.add(resultSet.getInt("id"));
		}
		
		return new SkillListDto(userNames, userSkills, skillIds);
	}
	
//  スキルを追加する処理
	@PostMapping("/api/skills/add")
	public void addSkill(@RequestBody SkillDto skillDto) throws SQLException {
		try (
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root");
			PreparedStatement statement = connection.prepareStatement("INSERT INTO skills(user_id, skill) VALUES(?, ?)");
			) {
			statement.setInt(1, skillDto.getUserId());
			statement.setString(2, skillDto.getSkill());
			statement.executeUpdate();
		} catch (PSQLException e){
//			System.err.println("SQLState Code: " + e.getSQLState());
//			外部キー制約違反（コード：23503）を捕捉
			if ("23503".equals(e.getSQLState())) {
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST,
						"入力されたユーザーID（" + skillDto.getUserId() + "）は存在しません"
				);
			}
		} catch (SQLException e) {
//			データベース接続失敗を捕捉
				throw new ResponseStatusException(
						HttpStatus.INTERNAL_SERVER_ERROR,
						"データベース接続に失敗しました。"
				);
			}
	}
	
//	スキルを削除する処理
	@PostMapping("/api/skills/delete/{skillId}")
	public void deleteSkill(@PathVariable("skillId") int skillId) throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "root", "root")) {
			
			// レコードを削除する処理
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM skills WHERE id = ?")) {
				statement.setInt(1, skillId);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
}
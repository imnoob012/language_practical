package com.forgeon.membermanagement.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Repository;


import com.forgeon.membermanagement.model.dto.UserListDto;

@Repository
public class JdbcUserRepositoryImpl implements UserRepository {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	
	//全件取得
	@Override
	public UserListDto findAll() {
		
		List<String> userNames = new ArrayList<>();
		List<Integer> userIds = new ArrayList<>();
		
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
			ResultSet resultSet = statement.executeQuery();) {
			
			while(resultSet.next()) {
				userNames.add(resultSet.getString("name"));
				userIds.add(resultSet.getInt("id"));
			}
		} catch (SQLException e) {
			// SQLExceptionをDataAccessExceptionにラップ
			String errorMessage = "エラーが発生しました。";
			System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
			
			throw new DataAccessResourceFailureException(errorMessage, e);
		}
	
		
		return new UserListDto(userNames, userIds);
	}
	//絞り込み検索
	@Override
	public UserListDto findByNameContaining(String input){
		
		String regexPattern = ".*" + input + ".*";
		
		List<String> userNames = new ArrayList<>();
		List<Integer> userIds = new ArrayList<>();
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE name ~ ?");
				
		) {
			statement.setString(1, regexPattern);
			
			try (ResultSet resultSet = statement.executeQuery()){
				
				while(resultSet.next()) {
					userNames.add(resultSet.getString("name"));
					userIds.add(resultSet.getInt("id"));
				}
			}
			
		
		} catch (SQLException e) {
			// SQLExceptionをDataAccessExceptionにラップ
			String errorMessage = "エラーが発生しました。";
			System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
			
			throw new DataAccessResourceFailureException(errorMessage, e);
		}
		
		return new UserListDto(userNames, userIds);
	}
	
	
	//ユーザー追加
	@Override
	public void save(String name) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name) VALUES(?)");
		) {
			// プレースホルダー（１番の?）に名前を設定する（インデックスは１から始まる）
			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			// SQLExceptionをDataAccessExceptionにラップ
			String errorMessage = "エラーが発生しました。";
			System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
			
			throw new DataAccessResourceFailureException(errorMessage, e);
		}
	}
	
	//ユーザー削除
	@Override
	public void delete(int userId) {
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			
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
			// SQLExceptionをDataAccessExceptionにラップ
			String errorMessage = "エラーが発生しました。";
			System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
			
			throw new DataAccessResourceFailureException(errorMessage, e);
		}
	}
}
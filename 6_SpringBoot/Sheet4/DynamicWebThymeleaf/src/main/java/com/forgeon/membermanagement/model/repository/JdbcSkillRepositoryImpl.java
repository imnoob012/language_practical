package com.forgeon.membermanagement.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.forgeon.membermanagement.model.dto.SkillDto;
import com.forgeon.membermanagement.model.dto.SkillListDto;

@Repository
public class JdbcSkillRepositoryImpl implements SkillRepository {
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	
	// 全件取得
	@Override
	public SkillListDto findAll() {
		List<String> userNames = new ArrayList<>();
		List<String> userSkills = new ArrayList<>();
		List<Integer> skillIds = new ArrayList<>();
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("SELECT s.id, u.name, s.skill FROM skills AS s INNER JOIN users AS u ON s.user_id = u.id");
		) {
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while(resultSet.next()) {
					userNames.add(resultSet.getString("name"));
					userSkills.add(resultSet.getString("skill"));
					skillIds.add(resultSet.getInt("id"));
				}
			}
			
		} catch (SQLException e) {
			// SQLExceptionをDataAccessResourceFailureExceptionにラップ
			String errorMessage = "エラーが発生しました。";
			System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
			
			throw new DataAccessResourceFailureException(errorMessage, e);
		}
			
		return new SkillListDto(userNames, userSkills, skillIds);
	}
	
	// 絞り込み検索
	@Override
	public SkillListDto findByNameContaining(String filterSkill) {

		String regexPattern = ".*" + filterSkill + ".*";
		
		List<String> userNames = new ArrayList<>();
		List<String> userSkills = new ArrayList<>();
		List<Integer> skillIds = new ArrayList<>();
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("SELECT s.id, u.name, s.skill FROM skills AS s INNER JOIN users AS u ON s.user_id = u.id WHERE skill ~ ?");
		) {
			statement.setString(1, regexPattern);
			try (ResultSet resultSet = statement.executeQuery()) {
				
				while(resultSet.next()) {
					userNames.add(resultSet.getString("name"));
					userSkills.add(resultSet.getString("skill"));
					skillIds.add(resultSet.getInt("id"));
				}
			}
		} catch (SQLException e) {
			// SQLExceptionをDataAccessResourceFailureExceptionにラップ
			String errorMessage = "エラーが発生しました。";
			System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
			
			throw new DataAccessResourceFailureException(errorMessage, e);
		}
		
		return new SkillListDto(userNames, userSkills, skillIds);
	}
	
//  スキルを追加する処理
	@Override
	public void save(SkillDto skillDto) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("INSERT INTO skills(user_id, skill) VALUES(?, ?)");
		) {
			statement.setInt(1, skillDto.getUserId());
			statement.setString(2, skillDto.getSkill());
			statement.executeUpdate();
		} catch (PSQLException e) {
			// System.err.println("SQLState Code: " + e.getSQLState());
			// 外部キー制約違反（コード：23503）を捕捉
			if ("23503".equals(e.getSQLState())) {
				String errorMessage = "入力されたユーザーID（" + skillDto.getUserId() + "）は存在しません";
				System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
				
				throw new DataIntegrityViolationException(errorMessage, e);
			}
		} catch (SQLException e) {
			// SQLExceptionをDataAccessResourceFailureExceptionにラップ
			String errorMessage = "エラーが発生しました。";
			System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
			
			throw new DataAccessResourceFailureException(errorMessage, e);
		}
	}
	
//	スキルを削除する処理
	@Override
	public void delete(int skillId) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
			
			// レコードを削除する処理
			try (PreparedStatement statement = connection.prepareStatement("DELETE FROM skills WHERE id = ?")) {
				statement.setInt(1, skillId);
				statement.executeUpdate();
			}
			
		} catch (SQLException e) {
			// SQLExceptionをDataAccessResourceFailureExceptionにラップ
			String errorMessage = "エラーが発生しました。";
			System.err.println("JBDCエラーコード:" + e.getErrorCode() + ", SQL State: " + e.getSQLState());
			
			throw new DataAccessResourceFailureException(errorMessage, e);
		}
	}
}
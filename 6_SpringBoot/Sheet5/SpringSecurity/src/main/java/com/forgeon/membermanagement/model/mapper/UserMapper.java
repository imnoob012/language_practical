package com.forgeon.membermanagement.model.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.forgeon.membermanagement.model.entity.User;


@Mapper
public interface UserMapper {
	//詳細はXMLに記述
	List<User> findAllOrSearch(String i);
	
	@Select("SELECT name, password, role FROM users WHERE name = #{name}")
	User findByName(String name);
	
	//フィールド名をかけば、getterを自動参照
	@Insert("INSERT INTO users(name, password, role) VALUES(#{name}, #{password}, #{role})")
	void save(User user);
	
	@Update("UPDATE users SET name = #{name}, role = #{role} WHERE id = #{id}")
	void update(User user);
	
	//サービス層でトランザクション管理させる
	@Delete("DELETE FROM skills WHERE user_id = #{userId}")
	void deleteSkillsByUserId(int userId);
	
	@Delete("DELETE FROM users WHERE id = #{id}")
	void deleteUserById(int id);
}



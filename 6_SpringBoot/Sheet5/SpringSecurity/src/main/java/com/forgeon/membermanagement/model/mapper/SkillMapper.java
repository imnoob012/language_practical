package com.forgeon.membermanagement.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import com.forgeon.membermanagement.model.entity.Skill;
import com.forgeon.membermanagement.model.entity.User;

@Mapper
public interface SkillMapper {
	// 詳細はXMLに記述
	List<User> findAllOrSearchWithUsers(String i);
	
	@Insert("INSERT INTO skills(user_id, skill) VALUES(#{userId}, #{skill})")
	void save(Skill skill);
	
	@Update("UPDATE skills SET skill = #{skill} WHERE id = #{id}")
	void update(Skill skill);
	
	@Delete("DELETE FROM skills WHERE id = #{skillId}")
	void delete(int skillId);
}








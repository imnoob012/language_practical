package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class SkillDto {
	@JsonProperty("user_id")
	private int userId;
	
	private String skill;
	
	public String getSkill() {
		return skill;
	}
	public int getUserId() {
		return userId;
	}
}




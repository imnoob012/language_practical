package com.example.demo.dto;

import java.util.List;

public class SkillListDto {
	private List<String> userNames;
	private List<String> userSkills;
	
	public SkillListDto(List<String> userNames, List<String> userSkills) {
		this.userNames = userNames;
		this.userSkills = userSkills;
	}
	
	public List<String> getUserNames() {
		return userNames;
	}
	
	public List<String> getUserSkills() {
		return userSkills;
	}
}
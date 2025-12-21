package com.forgeon.membermanagement.model.dto;

import java.util.List;

public class SkillListDto {
	private List<String> userNames;
	private List<String> userSkills;
	private List<Integer> skillIds;
	
	public SkillListDto(List<String> userNames, List<String> userSkills, List<Integer> skillIds) {
		this.userNames = userNames;
		this.userSkills = userSkills;
		this.skillIds = skillIds;
	}
	
	public List<String> getUserNames() {
		return userNames;
	}
	
	public List<String> getUserSkills() {
		return userSkills;
	}
	
	public List<Integer> getSkillIds() {
		return skillIds;
	}
}
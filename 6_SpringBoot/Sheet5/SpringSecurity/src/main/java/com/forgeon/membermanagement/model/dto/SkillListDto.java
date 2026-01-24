package com.forgeon.membermanagement.model.dto;

import java.util.List;

import com.forgeon.membermanagement.model.entity.User;

import lombok.Data;

@Data
public class SkillListDto {
	private List<String> userNames;
	private List<String> userSkills;
	private List<Integer> skillIds;
	
	// ENTITYからDTOへ変換
	public SkillListDto(List<User> entity) {
    	this.userNames = entity.stream()
                .map(User::getName)
                .toList();

        this.userSkills = entity.stream()
                .map(User::getSkillName)
                //.map(user -> user.getSkill().getSkill())と同じ意味
                .toList();

        this.skillIds = entity.stream()
        		.map(User::getSkillId)
        		//.map(user -> user.getSkill().getId())
                .toList();
	}
	
}
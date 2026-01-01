package com.forgeon.membermanagement.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SkillDto {
	@JsonProperty("user_id")
	private Integer userId;
	private Integer skillId;
	private String skill;
	
	
}




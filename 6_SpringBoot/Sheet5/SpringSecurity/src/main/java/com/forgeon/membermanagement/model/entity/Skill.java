package com.forgeon.membermanagement.model.entity;

import lombok.Data;

@Data
public class Skill {
	private Integer id;
	private Integer userId;
	private String skill;
}
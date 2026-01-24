package com.forgeon.membermanagement.model.entity;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String name;
	private String password;
	private String role;
	// ⭐︎子オブジェクトを保持
	private Skill skill;
	
	// 親オブジェクトにスキル名を橋渡しするためのメソッド
	public String getSkillName() {
		return this.skill.getSkill();
	}
	public Integer getSkillId() {
		return this.skill.getId();
	}
}
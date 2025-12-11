package com.forgeon.membermanagement.model.repository;

import com.forgeon.membermanagement.model.dto.SkillDto;
import com.forgeon.membermanagement.model.dto.SkillListDto;

public interface SkillRepository {
	SkillListDto findAll();
	SkillListDto findByNameContaining(String skill);
	void save(SkillDto skillDto);
	void delete(int skillId);
}








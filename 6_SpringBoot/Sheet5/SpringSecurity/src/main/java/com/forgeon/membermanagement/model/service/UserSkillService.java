package com.forgeon.membermanagement.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forgeon.membermanagement.model.dto.SkillDto;
import com.forgeon.membermanagement.model.dto.SkillListDto;
import com.forgeon.membermanagement.model.dto.UserDto;
import com.forgeon.membermanagement.model.dto.UserListDto;
import com.forgeon.membermanagement.model.entity.Skill;
import com.forgeon.membermanagement.model.entity.User;
import com.forgeon.membermanagement.model.mapper.SkillMapper;
import com.forgeon.membermanagement.model.mapper.UserMapper;

@Service
public class UserSkillService {
    
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SkillMapper skillMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;


    // --- ユーザー関連のサービス ---
    public UserListDto findAllOrSearch(String input) {
    	List<User> entity = userMapper.findAllOrSearch(input);
        return new UserListDto(entity);
    }

    public void saveUser(UserDto dto) {
        User entity = new User();
        entity.setName(dto.getName());
        entity.setRole(dto.getRole());
        // ハッシュ化
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(encodedPassword);
    	userMapper.save(entity);
    }
    
    public void updateUser(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setRole(dto.getRole());
    	userMapper.update(entity);
    }
    
    @Transactional
    public void deleteUser(int userId) {
        // 子を削除
    	userMapper.deleteSkillsByUserId(userId);
    	// 親を削除
        userMapper.deleteUserById(userId);
    }

    // --- スキル関連のサービス ---

    public SkillListDto findAllOrSearchSkills(String i) {
    	List<User> entity = skillMapper.findAllOrSearchWithUsers(i);
        return new SkillListDto(entity);
    }

    
    public void saveSkill(SkillDto skillDto) {
        // DTOからENTITYへ
    	Skill entity = new Skill();
        entity.setUserId(skillDto.getUserId());
        entity.setSkill(skillDto.getSkill());
    	skillMapper.save(entity);
    }
    
    public void updateSkill(SkillDto dto) {
        Skill entity = new Skill();
        entity.setId(dto.getSkillId());
        entity.setSkill(dto.getSkill());
    	skillMapper.update(entity);
    }

    public void deleteSkill(int skillId) {
        skillMapper.delete(skillId);
    }
}
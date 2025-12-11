package com.forgeon.membermanagement.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forgeon.membermanagement.model.dto.SkillDto;
import com.forgeon.membermanagement.model.dto.SkillListDto;
import com.forgeon.membermanagement.model.dto.UserListDto;
import com.forgeon.membermanagement.model.repository.SkillRepository;
import com.forgeon.membermanagement.model.repository.UserRepository;

@Service
public class UserSkillService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SkillRepository skillRepository;


    // --- ユーザー関連のサービス ---
    public UserListDto findAllUsers(){
        return userRepository.findAll();
    }

    public UserListDto findByNameContaining(String input) {
        return userRepository.findByNameContaining(input);
    }

    public void saveUser(String name) {
        userRepository.save(name);
    }

    public void deleteUser(int userId) {
        userRepository.delete(userId);
    }

    // --- スキル関連のサービス ---

    public SkillListDto findAllSkills() {
        return skillRepository.findAll();
    }

    public SkillListDto findBySkillNameContaining(String input) {
        return skillRepository.findByNameContaining(input);
    }

    public void saveSkill(SkillDto skillDto) {
        skillRepository.save(skillDto);
    }

    public void deleteSkill(int skillId) {
        skillRepository.delete(skillId);
    }
}
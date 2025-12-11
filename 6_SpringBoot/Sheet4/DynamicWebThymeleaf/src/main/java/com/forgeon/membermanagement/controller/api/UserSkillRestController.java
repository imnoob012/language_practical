package com.forgeon.membermanagement.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.forgeon.membermanagement.model.dto.SkillDto;
import com.forgeon.membermanagement.model.dto.SkillListDto;
import com.forgeon.membermanagement.model.dto.UserListDto;
import com.forgeon.membermanagement.model.service.UserSkillService;

@RestController
public class UserSkillRestController {
	
	@Autowired
	private UserSkillService userSkillService;
	
//	--- ︎ユーザーテーブルの処理 ---
	
	//全件取得
	@GetMapping("/api/users")
	public UserListDto findAllUsers() {
		return userSkillService.findAllUsers();
	}
	
	//絞り込み検索
	@PostMapping("/api/users/filter")
	public UserListDto findByNameContaning(@RequestBody String input) {
		return userSkillService.findByNameContaining(input);
	}
	
	//ユーザー追加
	@PostMapping("/api/users/add")
	public void saveUser(@RequestBody String name) {
		userSkillService.saveUser(name);
	}
	
	//ユーザー削除
	@PostMapping("/api/users/delete/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		userSkillService.deleteUser(userId);
	}
	
//	--- ︎スキルテーブルの処理 ---
	
	//全件取得
	@GetMapping("/api/skills")
	public SkillListDto findAllSkills() {
		return userSkillService.findAllSkills();
	}
	
	//絞り込み検索処理
	@PostMapping("/api/skills/filter")
	public SkillListDto findBySkillNameContaining(@RequestBody String input) {
		return userSkillService.findBySkillNameContaining(input);
	}
	
	//スキルを追加する処理
	@PostMapping("/api/skills/add")
	public void saveSkill(@RequestBody SkillDto skillDto) {
		userSkillService.saveSkill(skillDto);
	}
	
	//スキルを削除する処理
	@PostMapping("/api/skills/delete/{skillId}")
	public void deleteSkill(@PathVariable("skillId") int skillId) {
		userSkillService.deleteSkill(skillId);
	}
	
}
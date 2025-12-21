package com.forgeon.membermanagement.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
		try {
			return userSkillService.findAllUsers();
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//絞り込み検索
	@PostMapping("/api/users/filter")
	public UserListDto findByNameContaning(@RequestBody String input) {
		try {
			return userSkillService.findByNameContaining(input);
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//ユーザー追加
	@PostMapping("/api/users/add")
	public void saveUser(@RequestBody String name) {
		try {
			userSkillService.saveUser(name);
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//ユーザー削除
	@PostMapping("/api/users/delete/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		try {
			userSkillService.deleteUser(userId);
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
//	--- ︎スキルテーブルの処理 ---
	
	//全件取得
	@GetMapping("/api/skills")
	public SkillListDto findAllSkills() {
		try {
			return userSkillService.findAllSkills();
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//絞り込み検索処理
	@PostMapping("/api/skills/filter")
	public SkillListDto findBySkillNameContaining(@RequestBody String input) {
		try {
			return userSkillService.findBySkillNameContaining(input);
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//スキルを追加する処理
	@PostMapping("/api/skills/add")
	public void saveSkill(@RequestBody SkillDto skillDto) {
		try {
			userSkillService.saveSkill(skillDto);
		} catch (DataIntegrityViolationException e) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,
					e.getMessage()
			);
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//スキルを削除する処理
	@PostMapping("/api/skills/delete/{skillId}")
	public void deleteSkill(@PathVariable("skillId") int skillId) {
		try {
			userSkillService.deleteSkill(skillId);
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
}
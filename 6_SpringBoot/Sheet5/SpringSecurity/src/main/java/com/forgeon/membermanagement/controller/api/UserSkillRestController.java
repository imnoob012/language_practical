package com.forgeon.membermanagement.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.forgeon.membermanagement.model.dto.SkillDto;
import com.forgeon.membermanagement.model.dto.SkillListDto;
import com.forgeon.membermanagement.model.dto.UserDto;
import com.forgeon.membermanagement.model.dto.UserListDto;
import com.forgeon.membermanagement.model.service.UserSkillService;


@RestController
public class UserSkillRestController {
	
	@Autowired
	private UserSkillService userSkillService;
	
//	--- ︎ユーザーテーブルの処理 ---
	
	//全件取得と絞り込み検索
	@GetMapping("/api/users")
	public UserListDto findAllUsers(@RequestParam(name="input", required = false) String input) {
		try {
			return userSkillService.findAllOrSearch(input);
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//ユーザー追加
	@PostMapping("/api/users/add")
	public void saveUser(@RequestBody UserDto userDto) {
		try {
			userSkillService.saveUser(userDto);
		} catch (DuplicateKeyException e) {
			//一意制約違反
	        throw new ResponseStatusException(
	                HttpStatus.BAD_REQUEST,
	                "そのユーザー名は既に使用されている為、登録できません"
	        );
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//ユーザー更新
	@PostMapping("/api/users/update")
	public void updateUser(@RequestBody UserDto userDto) {
		try {
			userSkillService.updateUser(userDto);
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
	
	//全件取得と絞り込み検索
	@GetMapping("/api/skills")
	public SkillListDto findAllOrSearchSkills(@RequestParam(name="input", required = false) String input) {
		try {
			return userSkillService.findAllOrSearchSkills(input);
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
		} catch (DuplicateKeyException e) {
	        // 重複エラー（一意制約違反）をキャッチ
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "そのスキルは既に登録されています");
	    } catch (DataIntegrityViolationException e) {
	    	// 外部キー制約違反をキャッチ
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,
					"存在しないユーザーIDの為、登録できませんでした"
			);
		} catch (DataAccessResourceFailureException e) {
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage()
			);
		}
	}
	
	//スキルを更新する処理
	@PostMapping("/api/skills/update")
	public void updateSkill(@RequestBody SkillDto skillDto) {
		try {
			userSkillService.updateSkill(skillDto);
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
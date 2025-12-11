package com.forgeon.membermanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.forgeon.membermanagement.model.dto.AllTableDto;
import com.forgeon.membermanagement.model.dto.SkillListDto;
import com.forgeon.membermanagement.model.dto.UserListDto;
import com.forgeon.membermanagement.model.service.UserSkillService;

@Controller
public class ViewController {
	@Autowired
	private UserSkillService userSkillService;
	
	@GetMapping("/")
	public String getAllTableData(Model model) {
		AllTableDto data = new AllTableDto();
		// ユーザーテーブルを取得
		UserListDto userList = userSkillService.findAllUsers();
		// ページに表示させる名前とスキルを取得
		SkillListDto skillList = userSkillService.findAllSkills();
		
		data.setUserIds(userList.getUserIds());
		data.setUserNames(userList.getUserNames());
		data.setSkillIds(skillList.getSkillIds());
		data.setSkillNames(skillList.getUserNames());
		data.setSkills(skillList.getUserSkills());
		model.addAttribute("allData", data);
		return "index";
	}
}
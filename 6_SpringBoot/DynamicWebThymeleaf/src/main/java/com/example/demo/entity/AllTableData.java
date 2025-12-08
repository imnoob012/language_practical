package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
// lombokがうまく動作しない為、グレーアウト中・・・
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//public class AllTableData {
//	    // ユーザーテーブル
//	    private List<Integer> userIds = new ArrayList<>();
//	    private List<String> userNames = new ArrayList<>();
//	    // スキルテーブル
//	    private List<String> skillNames = new ArrayList<>(); 
//	    private List<String> skills = new ArrayList<>();     
//}

public class AllTableData {

    // ユーザーテーブル
    private List<Integer> userIds = new ArrayList<>();
    private List<String> userNames = new ArrayList<>();

    // スキルテーブル
    private List<String> skillNames = new ArrayList<>();
    private List<String> skills = new ArrayList<>();
    private List<Integer> skillIds = new ArrayList<>();
    
    // コンストラクタ
    public AllTableData() {}

    
    public List<Integer> getUserIds() {
        return userIds;
    }
    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
    public List<String> getUserNames() {
        return userNames;
    }
    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }
    
    
    public List<String> getSkillNames() {
        return skillNames;
    }
    public void setSkillNames(List<String> skillNames) {
        this.skillNames = skillNames;
    }
    public List<String> getSkills() {
        return skills;
    }
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

	public List<Integer> getSkillIds() {
        return skillIds;
    }
    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }
}
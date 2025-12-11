package com.forgeon.membermanagement.model.dto;

import java.util.List;

public class UserListDto {
	private List<String> userNames;
	private List<Integer> userIds;
	
//	コンストラクタ
	public UserListDto(List<String> userNames, List<Integer> userIds) {
		this.userNames = userNames;
		this.userIds = userIds;
	}
//	Jsonに変換するためにGetterを定義
	public List<String> getUserNames() {
		return userNames;
	}
	
	public List<Integer> getUserIds() {
		return userIds;
	}
	
}
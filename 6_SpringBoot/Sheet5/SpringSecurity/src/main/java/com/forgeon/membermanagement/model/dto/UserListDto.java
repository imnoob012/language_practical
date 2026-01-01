package com.forgeon.membermanagement.model.dto;

import java.util.List;

import com.forgeon.membermanagement.model.entity.User;

import lombok.Data;

@Data
public class UserListDto {
	private List<String> userNames;
	private List<Integer> userIds;
	private List<String> roles;
	
//	ENTITYからDTOに変換
	public UserListDto(List<User> entity) {
		this.userNames = entity.stream()
				.map(User::getName)
				.toList();
		this.userIds = entity.stream()
				.map(User::getId)
				.toList();
		this.roles = entity.stream()
				.map(User::getRole)
				.toList();
	}
//	Jsonに変換するためにGetterを定義
	public List<String> getUserNames() {
		return userNames;
	}
	
	public List<Integer> getUserIds() {
		return userIds;
	}
	
}
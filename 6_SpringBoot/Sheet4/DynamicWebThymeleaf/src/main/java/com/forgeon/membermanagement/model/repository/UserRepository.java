package com.forgeon.membermanagement.model.repository;
import com.forgeon.membermanagement.model.dto.UserListDto;


public interface UserRepository {
	UserListDto findAll();
	UserListDto findByNameContaining(String name);
	void save(String name);
	void delete(int userId);
}

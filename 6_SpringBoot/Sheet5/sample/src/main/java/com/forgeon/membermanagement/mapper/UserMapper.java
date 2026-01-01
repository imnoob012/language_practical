package com.forgeon.membermanagement.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.forgeon.membermanagement.entity.Users;

@Mapper
public interface UserMapper {
Users findByName(String name);
}
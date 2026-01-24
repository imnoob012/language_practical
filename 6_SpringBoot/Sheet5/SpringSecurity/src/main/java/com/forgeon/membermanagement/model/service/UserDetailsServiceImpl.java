package com.forgeon.membermanagement.model.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.forgeon.membermanagement.model.entity.User;
import com.forgeon.membermanagement.model.mapper.UserMapper;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		// DBから該当レコードを取得
		User user = userMapper.findByName(name);
		if (user == null) {
			throw new UsernameNotFoundException("ユーザーが見つかりませんでした");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
	}
}
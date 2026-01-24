package com.forgeon.membermanagement;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.forgeon.membermanagement.entity.Users;
import com.forgeon.membermanagement.mapper.UserMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

@Autowired
private UserMapper userMapper;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Users user = userMapper.findByName(username);
if (user == null) {
throw new UsernameNotFoundException("User not found");
}
return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
Collections.singleton(new SimpleGrantedAuthority("USER")));
}
}
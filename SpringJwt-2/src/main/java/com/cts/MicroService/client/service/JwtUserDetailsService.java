package com.cts.MicroService.client.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.MicroService.client.dao.UserDao;
import com.cts.MicroService.client.model.UserModel;
import com.cts.MicroService.client.model.UserEntity;
@Service
public class JwtUserDetailsService implements UserDetailsService{
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = userDao.getuser(username);
		
	
	
		if (user == null) {
		
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
	
		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),
			new ArrayList<>());
	
	}
	public boolean check(String username) {
		return userDao.existsByUserName(username);
	}
	public UserEntity save(UserEntity user) {
		//UserEntity newUser = new UserEntity();
		//newUser.setUserName(user.getUserName());
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		//newUser.setRole(user.getRole());
		return userDao.save(user);
	}
}

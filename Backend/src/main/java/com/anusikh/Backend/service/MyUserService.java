package com.anusikh.Backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anusikh.Backend.entity.MyUser;
import com.anusikh.Backend.repository.MyUserRepository;

@Service
public class MyUserService implements UserDetailsService {

	@Autowired
	MyUserRepository myUserRepository;

	public MyUser getUser(String username) throws UsernameNotFoundException {
		MyUser user = myUserRepository.findByUserName(username);
		if (null == user) {
			throw new UsernameNotFoundException("User Not Found with userName " + username);
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = myUserRepository.findByUserName(username);
		if (null == user) {
			throw new UsernameNotFoundException("User Not Found with userName " + username);
		}
		return user;
	}

}

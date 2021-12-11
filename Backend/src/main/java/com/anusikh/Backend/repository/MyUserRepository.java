package com.anusikh.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anusikh.Backend.entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
	MyUser findByUserName(String userName);
}

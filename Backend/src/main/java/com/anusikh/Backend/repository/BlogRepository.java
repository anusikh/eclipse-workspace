package com.anusikh.Backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anusikh.Backend.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

}

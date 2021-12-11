package com.anusikh.Backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anusikh.Backend.entity.Blog;
import com.anusikh.Backend.repository.BlogRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;

	public Blog saveBlog(Blog blog) {
		// TODO: add user saving function
		return blogRepository.save(blog);
	}

	public List<Blog> getBlogs() {
		return blogRepository.findAll();
	}

	public Blog getBlogById(int id) {
		return blogRepository.findById(id).orElse(null);
	}

	public String deleteBlog(int id) {
		blogRepository.deleteById(id);
		return "product removed !!! " + id;
	}

}

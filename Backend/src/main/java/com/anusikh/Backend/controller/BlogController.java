package com.anusikh.Backend.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anusikh.Backend.entity.AuthenticationRequest;
import com.anusikh.Backend.entity.AuthenticationResponse;
import com.anusikh.Backend.entity.Blog;
import com.anusikh.Backend.entity.MyUser;
import com.anusikh.Backend.service.BlogService;
import com.anusikh.Backend.service.MyUserService;
import com.anusikh.Backend.util.JwtUtil;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:3000")
public class BlogController {

	private String jwt;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	MyUserService userService;

	@Autowired
	BlogService blogService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@PostMapping("/addBlog")
	public Blog addBlog(@RequestBody Blog blog) {
		System.out.println(jwtTokenUtil.extractUsername(jwt));
		MyUser user = userService.getUser(jwtTokenUtil.extractUsername(jwt));
		blog.setUser(user);
		return blogService.saveBlog(blog);
	}

	@GetMapping("/blog/{blogId}")
	public Blog findBlogById(@PathVariable int blogId) {
		return blogService.getBlogById(blogId);
	}

	@GetMapping("/blogs")
	public List<Blog> findAllBlogs() {
		return blogService.getBlogs();
	}

	@DeleteMapping("/deleteBlog/{blogId}")
	public String deleteBlog(@PathVariable int blogId) {
		return blogService.deleteBlog(blogId);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponse res) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());

		System.out.println("Anusikh -- " + userDetails.getAuthorities());

		jwt = jwtTokenUtil.generateToken(userDetails);

		Cookie cookie = new Cookie("jwtToken", jwt);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 10);
		// cookie.setSecure(true); // True Only while deploying for https connections
		cookie.setHttpOnly(true);
		res.addCookie(cookie);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
}

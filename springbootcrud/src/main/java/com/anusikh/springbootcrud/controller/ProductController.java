package com.anusikh.springbootcrud.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.anusikh.springbootcrud.entity.AuthenticationRequest;
import com.anusikh.springbootcrud.entity.AuthenticationResponse;
import com.anusikh.springbootcrud.entity.Product;
import com.anusikh.springbootcrud.service.IpRequestService;
import com.anusikh.springbootcrud.service.ProductService;
import com.anusikh.springbootcrud.util.JwtUtil;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ProductService service;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private IpRequestService ipRequestService;

	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}

	@PostMapping("/addProducts")
	public List<Product> addProducts(@RequestBody List<Product> products) {
		return service.saveProducts(products);
	}

	@GetMapping("/products")
	public List<Product> findAllProducts() {
		return service.getProducts();
	}

	@GetMapping("/productById/{id}")
	public Product findProductById(@PathVariable int id) {
		return service.getProductById(id);
	}

	@GetMapping("/product/{name}")
	public Product findProductByName(@PathVariable String name) {
		return service.getProductByName(name);
	}

	@PutMapping("/update")
	public Product updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		return service.deleteProduct(id);
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponse res) throws Exception {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		Cookie cookie = new Cookie("jwtToken", jwt);
		cookie.setPath("/");
		cookie.setMaxAge(60 * 60 * 10);
		// cookie.setSecure(true); // True Only while deploying for https connections
		cookie.setHttpOnly(true);
		res.addCookie(cookie);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}

	@GetMapping("/ipAddress") // Can be used to IP ban Users
	public String getIpAddress(HttpServletRequest request) {
		String ip = ipRequestService.getClientIp(request);
		return ip;
	}

}

package com.anusikh.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anusikh.Backend.repository.MyUserRepository;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // When u have not implemented security yet
@SpringBootApplication
public class BackendApplication {

	@Autowired
	MyUserRepository myUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@PostConstruct
//	protected void init() {
//
//		List<Authority> authorityList = new ArrayList<>();
//
//		authorityList.add(createAuthority("ROLE_ADMIN", "Admin Role"));
//
//		MyUser user = new MyUser();
//
//		user.setUserName("anusikh");
//		user.setFirstName("anusikh");
//		user.setLastName("P");
//
//		user.setPassword("Malaya143!");
//		user.setEnabled(true);
//		user.setAuthorities(authorityList);
//
//		myUserRepository.save(user);
//	}
//
//	private Authority createAuthority(String roleCode, String roleDescription) {
//		Authority authority = new Authority();
//		authority.setRoleCode(roleCode);
//		authority.setRoleDescription(roleDescription);
//		return authority;
//	}
}

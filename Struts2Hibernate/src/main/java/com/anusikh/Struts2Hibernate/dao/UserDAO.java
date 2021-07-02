package main.java.com.anusikh.Struts2Hibernate.dao;

import main.java.com.anusikh.Struts2Hibernate.model.User;

public interface UserDAO {
	User getUserByCredentials(String userId, String password);

	int registerCredentials(String userId, String password, String name, String email);
}

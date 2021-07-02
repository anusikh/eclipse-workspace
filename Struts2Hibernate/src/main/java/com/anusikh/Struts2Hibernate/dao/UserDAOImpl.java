package main.java.com.anusikh.Struts2Hibernate.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import main.java.com.anusikh.Struts2Hibernate.model.User;

public class UserDAOImpl implements UserDAO {
	private SessionFactory sf;

	public UserDAOImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public User getUserByCredentials(String userId, String password) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User where id=:id and pwd=:pwd");
		query.setString("id", userId);
		query.setString("pwd", password);
		User user = (User) query.uniqueResult();
		if (user != null) {
			System.out.println("User Retrieved from DB::" + user);
		}
		tx.commit();
		session.close();
		return user;
	}

	public int registerCredentials(String userId, String password, String name, String email) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		User user = new User();
		user.setId(userId);
		user.setPwd(password);
		user.setEmail(email);
		user.setName(name);
		session.save(user);
		tx.commit();
		session.close();
		if (user.getId() != null) // Done this because by default session.save() returns the newly formed object
									// itself
			return 1;
		else
			return 0;
	}
}

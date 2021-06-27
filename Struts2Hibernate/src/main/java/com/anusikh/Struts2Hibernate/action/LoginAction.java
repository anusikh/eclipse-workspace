package main.java.com.anusikh.Struts2Hibernate.action;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import main.java.com.anusikh.Struts2Hibernate.dao.UserDAO;
import main.java.com.anusikh.Struts2Hibernate.dao.UserDAOImpl;
import main.java.com.anusikh.Struts2Hibernate.model.User;

public class LoginAction implements Action, ModelDriven<User>, ServletContextAware {

	@Override
	public String execute() throws Exception {

		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		UserDAO userDAO = new UserDAOImpl(sf);
		User userDB = userDAO.getUserByCredentials(user.getId(), user.getPwd());
		if (userDB == null)
			return ERROR;
		else {
			user.setEmail(userDB.getEmail());
			user.setName(userDB.getName());
			return SUCCESS;
		}
	}

	@Override
	public User getModel() {
		return user;
	}

	private User user = new User();

	private ServletContext ctx;

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

}

package main.java.com.anusikh.Struts2Hibernate.action;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

import main.java.com.anusikh.Struts2Hibernate.dao.UserDAO;
import main.java.com.anusikh.Struts2Hibernate.dao.UserDAOImpl;
import main.java.com.anusikh.Struts2Hibernate.model.User;

public class RegisterAction implements Action, ModelDriven<User>, ServletContextAware {

	public String register() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		UserDAO userDAO = new UserDAOImpl(sf);
		int res = userDAO.registerCredentials(user.getId(), user.getPwd(), user.getName(), user.getEmail());

		if (res == 0)
			return ERROR;
		else {
			return SUCCESS;
		}
	}

	private ServletContext ctx;
	private User user = new User();

	@Override
	public void setServletContext(ServletContext sc) {
		this.ctx = sc;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
}

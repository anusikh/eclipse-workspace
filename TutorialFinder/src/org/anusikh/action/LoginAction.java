package org.anusikh.action;

import org.anusikh.model.User;
import org.anusikh.service.LoginService;
import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void validate() {
		if (StringUtils.isEmpty(user.getUserId()))
			addFieldError("userId", "User ID cannot be blank");
		if (StringUtils.isEmpty(user.getPassword()))
			addFieldError("password", "Password cannot be blank");
	}

	private User user = new User();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {

		LoginService loginService = new LoginService();

		if (loginService.verifyLogin(user))
			return "success";
		else
			return "failure";
	}

	@Override
	public Object getModel() {

		return user;
	}
}

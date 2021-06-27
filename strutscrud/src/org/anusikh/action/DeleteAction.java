package org.anusikh.action;

import org.anusikh.dao.Admin;

import com.opensymphony.xwork2.ActionSupport;

public class DeleteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String uemail, msg;
	Admin dao = new Admin();

	@Override
	public String execute() throws Exception {
		try {
			int isDeleted = dao.deleteUserDetails(uemail);
			if (isDeleted > 0) {
				msg = "Record deleted successfully";
			} else {
				msg = "Some error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Delete";
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}

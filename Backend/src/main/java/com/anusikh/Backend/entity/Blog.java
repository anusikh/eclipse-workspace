package com.anusikh.Backend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "BLOGS_TBL")
public class Blog {

	@Id
	@GeneratedValue
	private int blogId;
	private String heading;
	private String subHeading;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private String body;

	@ManyToOne
	@JoinColumn(name = "id")
	private MyUser user;
	// Im Storing the userId of the user from MyUser table

	Blog() {
	}

	public Blog(int blogId, String heading, String subHeading, Date date, String body, MyUser user) {
		super();
		this.blogId = blogId;
		this.heading = heading;
		this.subHeading = subHeading;
		this.date = date;
		this.body = body;
		this.user = user;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getSubHeading() {
		return subHeading;
	}

	public void setSubHeading(String subHeading) {
		this.subHeading = subHeading;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public MyUser getUser() {
		return user;
	}

	public void setUser(MyUser user) {
		this.user = user;
	}

}

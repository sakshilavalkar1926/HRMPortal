package com.hrmportal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * @author sakshilavalkar
 * This class defines User entity.
 */
@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="user_id")
	private String userId;
	@Column(name="pass_word")
	private String password;
	
	/*
	 * constructors
	 */
	public User(){
	}
	
	public User(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	
	
	/*
	 * getters and setters
	 */
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + "]";
	}
	
	

}


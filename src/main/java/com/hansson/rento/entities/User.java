package com.hansson.rento.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mId;
	@Column(name = "MUSERNAME", length = 64)
	private String mUsername;
	@Column(name = "MPASSWORD", length = 64)
	private String mPassword;
	@Column(name = "MROLE")
	private Role mRole;

	public User() {
	}

	public Integer getId() {
		return mId;
	}

	public String getUsername() {
		return mUsername;
	}

	public void setId(Integer id) {
		mId = id;
	}

	public void setUsername(String username) {
		mUsername = username;
	}

	public String getPassword() {
		return mPassword;
	}

	public void setPassword(String password) {
		mPassword = password;
	}

	public Role getRole() {
		return mRole;
	}

	public void setRole(Role role) {
		mRole = role;
	}

}

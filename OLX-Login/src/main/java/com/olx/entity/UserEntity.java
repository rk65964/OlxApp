package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UserEntity {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String roles;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", userName=" + userName + ", password=" + password + ", roles=" + roles
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	//enum
	//private String email;
	//private String phone;

	

}

package com.nagarro.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "User_Details")
public class User {
	
	@Id
	@Column(name = "User_Name")
	private String userName;
	
	@Column(name = "Full_Name")
	private String fullName;
	
	@Column(name = "Pass_Word")
	private String passWord;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Image> images = new HashSet<>();
	
	public User() {
    }
	
	public User(String userName, String fullName, String passWord, Set<Image> images) {
		this.userName = userName;
		this.fullName = fullName;
		this.passWord = passWord;
		this.images = images;
	}
	
	
	public User(String userName, String fullName, String passWord) {
		this.userName = userName;
		this.fullName = fullName;
		this.passWord = passWord;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public Set<Image> getImages() {
		return images;
	}


	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
	
	
}

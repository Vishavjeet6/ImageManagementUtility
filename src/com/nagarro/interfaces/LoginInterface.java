package com.nagarro.interfaces;

import com.nagarro.models.User;

public interface LoginInterface {
	
	boolean userAuthentication(String userName, String passWord);
	
	User getUserDetails(String userName);
}

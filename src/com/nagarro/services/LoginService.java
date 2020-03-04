package com.nagarro.services;

import org.hibernate.Session;

import com.nagarro.interfaces.LoginInterface;
import com.nagarro.models.User;
import com.nagarro.utils.HibernateUtilities;

public class LoginService implements LoginInterface {

	public boolean userAuthentication(String userName, String passWord) {
		try(Session session = HibernateUtilities.getSessionInstance()){
			session.getTransaction().begin();
			User user = session.get(User.class, userName);
			System.out.println(user.getFullName());
			if(user.getUserName() != null && user.getPassWord().equals(passWord)) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public User getUserDetails(String userName) {
		User user = null;
		try(Session session = HibernateUtilities.getSessionInstance()){
			session.getTransaction().begin();
			user = session.get(User.class, userName);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public void updatePassword(String userName, String passWord) {
		User user = null;
		try(Session session = HibernateUtilities.getSessionInstance()){
			session.getTransaction().begin();
			user = session.get(User.class, userName);
			user.setPassWord(passWord);
			session.update(user);
			session.getTransaction().commit();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

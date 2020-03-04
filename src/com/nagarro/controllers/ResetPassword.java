package com.nagarro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.models.User;
import com.nagarro.services.LoginService;
import com.nagarro.utils.Constants;

/**
 * Servlet implementation class ResetPassword
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPassword() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		request.getSession().setAttribute("message", "");
		LoginService login = new LoginService();
		String userName = request.getParameter("username");
        String fullName = request.getParameter("fullname");
        String passWord = request.getParameter("passwordnew");
        String passwordConfirm = request.getParameter("passwordnewconfirm");
        
        User user = login.getUserDetails(userName);
        if(user != null) {
        	if(passWord.equals(passwordConfirm)) {
        		if(user.getFullName().equalsIgnoreCase(fullName)) {
        			login.updatePassword(userName, passwordConfirm);
        			message = "Success";
        		} else {
        			request.getSession().setAttribute("passwordmessage", "Some info is Wrong");
        			message = "Some info is Wrong";
        		}
        	} else {
        		request.getSession().setAttribute("passwordmessage", "No user exists with such username");
        		message = "No user exists with such username";
        	}
        	System.out.println(message);
        	response.sendRedirect(Constants.homePage);
        }
		
	}

}

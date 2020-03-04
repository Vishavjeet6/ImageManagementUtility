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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Login() {
    }
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginService login = new LoginService();
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		if(login.userAuthentication(userName, passWord)) {
			User user = login.getUserDetails(userName);
			request.getSession().setAttribute("authorized", "true");
			request.getSession().setAttribute("user", user);
			response.sendRedirect(Constants.redirectPage);
		} else {
			request.getSession().setAttribute("authorized", "false");
			response.sendRedirect(Constants.homePage);
		}
		
	}

}

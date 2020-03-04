package com.nagarro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.models.User;
import com.nagarro.services.ImageService;
import com.nagarro.services.LoginService;
import com.nagarro.utils.Constants;

/**
 * Servlet implementation class ImageDelete
 */
@WebServlet("/ImageDelete")
public class ImageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageDelete() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect(Constants.homePage);
		}else {
			LoginService login = new LoginService();
			ImageService imageService = new ImageService();
			String imageId = request.getParameter("imageid").toString();
			imageService.deleteImage(imageId);
			System.out.println(imageId + "deleted");
			User userUpdated = login.getUserDetails(((User)request.getSession().getAttribute("user")).getUserName());
			request.getSession().setAttribute("user", userUpdated);
			response.sendRedirect(Constants.userPage);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

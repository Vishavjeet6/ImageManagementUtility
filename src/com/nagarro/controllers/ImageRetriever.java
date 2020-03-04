package com.nagarro.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.models.Image;
import com.nagarro.services.ImageService;
import com.nagarro.utils.Constants;

/**
 * Servlet implementation class ImageRetriever
 */
@WebServlet("/ImageRetriever")
public class ImageRetriever extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageRetriever() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect(Constants.homePage);
		} else {
			ImageService imageService = new ImageService();
			String imageId = request.getParameter("imageId");
			Image image = imageService.getImage(imageId);
			if(image != null) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				try {
					response.getOutputStream().flush();
					response.getOutputStream().write(image.getImage());
					response.getOutputStream().close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

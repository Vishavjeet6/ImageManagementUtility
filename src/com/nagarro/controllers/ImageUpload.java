package com.nagarro.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.nagarro.models.Image;
import com.nagarro.models.User;
import com.nagarro.services.ImageService;
import com.nagarro.services.LoginService;
import com.nagarro.utils.Constants;
/**
 * Servlet implementation class ImageUpload
 */
@WebServlet("/ImageUpload")
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUpload() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect(Constants.homePage);
		} else {
			String imageName = null;
			byte bytes[] = null;
			double imageSize = 0;
			if(ServletFileUpload.isMultipartContent(request)) {
				try {
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
					
					for(FileItem item : multiparts) {
						if(item.isFormField()) {
							imageName = item.getString();
						} else {
							imageSize = item.getSize()/Constants.imageSize;
							bytes = item.get();
						}
					}
					
					
					request.setAttribute("message", "File Uploaded Successfully");
					User user = (User) request.getSession().getAttribute("user");
					Image image = new Image(imageName, imageSize, bytes);
					image.setUser(user);
					
					if(user != null) {
						if(image.getImageSize() < Constants.imageSize) {
							if(GetImagesSize.getImagesSize(user.getUserName()) + image.getImageSize() < Constants.totalSize) {
								ImageService imageService = new ImageService();
								imageService.addImage(image);
								try {
									LoginService login = new LoginService();
									
									User userUpdated = login.getUserDetails(((User)request.getSession().getAttribute("user")).getUserName());
									request.getSession().setAttribute("user", userUpdated);
									response.sendRedirect(Constants.userPage);
								} catch(Exception e) {
									e.printStackTrace();
								}	
							}else {
									System.out.println("Images size exceeded > 10 MB");
									request.getSession().setAttribute("message", "Images size exceeded > 10 MB");
									response.sendRedirect(Constants.userPage);
								}
						} else {
							System.out.println("Image Size exceeded");
							request.getSession().setAttribute("message", "Image size exceeded > 10 MB");
							response.sendRedirect(Constants.userPage);
						}
					}
				} catch(Exception e) {
					request.setAttribute("message", "File Upload Failed due to " + e);
				}
			} else {
				request.setAttribute("message", "Sorry image could not be uploaded");
			}
		}
	}
}

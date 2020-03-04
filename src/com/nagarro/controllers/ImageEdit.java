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
 * Servlet implementation class ImageEdit
 */
@WebServlet("/ImageEdit")
public class ImageEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageEdit() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect(Constants.homePage);
		} else {
			LoginService login = new LoginService();
			String imageId = request.getParameter("imageId");
			ImageService  imageService = new ImageService();
			Image img = imageService.getImage(imageId);
			float currentImageSize = (float) img.getImageSize();
			double imageSize = 0;
			byte[] bytes = null;
			String imageName = null;
			
			response.setContentType("text/ html;charset=UTF-8");
			try {
				if(!ServletFileUpload.isMultipartContent(request)) {
					System.out.println("No File Uploaded");
				}
				
				DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
                
                List<FileItem> items = servletFileUpload.parseRequest(request);
                System.out.println(items.size());
                
                for(FileItem file : items) {
                	if(file.isFormField()) {
                		imageName = file.getString();
                		if(!imageName.isEmpty()) {
                			img.setImageName(imageName);
                		}
                	} else {
                		imageSize = file.getSize()/1024;
                		if(imageSize != 0) {
                			bytes = file.get();
                			img.setImageSize(imageSize);
                			img.setImage(bytes);
                		}
                	}
                }
			} catch(Exception e) {
				System.out.println("error occured during uploading");
			}
			
			if(img.getImageSize() > 1024) {
				System.out.println("Image Size Exceeded");
			}else if(GetImagesSize.getImagesSize(user.getUserName()) + imageSize - currentImageSize > 10240) {
				System.out.println("Total Size Exceeded");
			}else {
				imageService.editImage(img);
			}
			User userUpdated = login.getUserDetails(((User)request.getSession().getAttribute("user")).getUserName());
			request.getSession().setAttribute("user", userUpdated);
			response.sendRedirect(Constants.userPage);
		}
	}

}

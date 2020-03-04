package com.nagarro.controllers;

import java.util.Collection;

import com.nagarro.models.Image;
import com.nagarro.models.User;
import com.nagarro.services.LoginService;

public class GetImagesSize {
	
	private static double totalSize = 0;
    private static LoginService loginservice = new LoginService();
    private static User user;
    private static Collection<Image> images;

	public static double getImagesSize(String userName) {
		user = loginservice.getUserDetails(userName);
        images = user.getImages();
        for (Image image : images) {
            totalSize += image.getImageSize();
        }
        return totalSize;
	}

}

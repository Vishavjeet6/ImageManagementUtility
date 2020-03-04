package com.nagarro.interfaces;

import com.nagarro.models.Image;

public interface ImageServiceInterface {
	
	void addImage(Image image);
	
	Image getImage(String imageId);
	
	void editImage(Image newImage);
	
	void deleteImage(String imageId);
}

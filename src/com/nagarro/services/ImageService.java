package com.nagarro.services;


import org.hibernate.Query;
import org.hibernate.Session;

import com.nagarro.interfaces.ImageServiceInterface;
import com.nagarro.models.Image;
import com.nagarro.utils.HibernateUtilities;

public class ImageService implements ImageServiceInterface{
	
	@Override
	public void addImage(Image image) {
		try (Session session = HibernateUtilities.getSessionInstance();) {
            session.getTransaction().begin();
            session.save(image);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public Image getImage(String imageId) {
		Image image = null;
        try (Session session = HibernateUtilities.getSessionInstance();) {
            session.getTransaction().begin();
            String queryString = "from Image where imageId = :imageId";
            Query query = session.createQuery(queryString).setString("imageId", imageId);

            Object queryResult = query.uniqueResult();
            return (Image) queryResult;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to connect to database");
        }
        return image;
	}

	@Override
	public void editImage(Image newImage) {
		 try (Session session = HibernateUtilities.getSessionInstance();) {
	            session.beginTransaction();
	            session.update(newImage);
	            session.getTransaction().commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }	
	}

	@Override
	public void deleteImage(String imageId) {
		try (Session session = HibernateUtilities.getSessionInstance();) {
            session.beginTransaction();

            String query = "delete from Image where imageId= :imageId";
            session.createQuery(query).setString("imageId", imageId).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}

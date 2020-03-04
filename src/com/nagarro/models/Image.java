package com.nagarro.models;

import javax.persistence.*;

@Entity
@Table(name = "Image_Details")
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Image_Id", nullable=false)
	private Long imageId;
	
	@Column(name = "Image_Name")
	private String imageName;
	
	@Column(name = "Image_Size")
	private double imageSize;
	
	@Basic(fetch = FetchType.LAZY)
    @Column(name = "Image", columnDefinition = "mediumblob")
    private byte[] image;
	
	@ManyToOne
    @JoinColumn(name = "User_User", nullable = false)
    private User user;
	
	public Image() {
    }
	
	public Image(Long imageId, String imageName, double imageSize, byte[] image, User user) {
		super();
		this.imageId = imageId;
		this.imageName = imageName;
		this.imageSize = imageSize;
		this.image = image;
		this.user = user;
	}

	public Image(String imageName, double imageSize, byte[] image) {
		super();
		this.imageName = imageName;
		this.imageSize = imageSize;
		this.image = image;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public double getImageSize() {
		return imageSize;
	}

	public void setImageSize(double imageSize) {
		this.imageSize = imageSize;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

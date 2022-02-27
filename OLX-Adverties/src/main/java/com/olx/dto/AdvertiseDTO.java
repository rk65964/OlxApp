package com.olx.dto;

import java.time.LocalDate;

public class AdvertiseDTO {
	
	private int id;
	private String  title;
	private int price;
	private long categoryId;
	private String category;
	private String description;
	private String userName;
	private LocalDate createdDate;
	private LocalDate modifiedDate;
	private String active;
	private String postedBy;
	private String status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public AdvertiseDTO() {
	}
	
	@Override
	public String toString() {
		return "AdvertiseDTO [id=" + id + ", title=" + title + ", price=" + price + ", categoryId=" + categoryId
				+ ", category=" + category + ", description=" + description + ", userName=" + userName
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", active=" + active
				+ ", postedBy=" + postedBy + ", status=" + status + "]";
	}
	public AdvertiseDTO(int id, String title, int price, long categoryId, String category, String description,
			String userName, LocalDate createdDate, LocalDate modifiedDate, String active, String postedBy,
			String status) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.categoryId = categoryId;
		this.category = category;
		this.description = description;
		this.userName = userName;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.active = active;
		this.postedBy = postedBy;
		this.status = status;
	}
	@Override
	public boolean equals(Object obj) {
	AdvertiseDTO advertiseDTO = (AdvertiseDTO)obj;
	if(this.title==null) {
	return false;
	}
	if(this.title.equals(advertiseDTO.title)) {
	return true;
	}
	return false;
	}
}

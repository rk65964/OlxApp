package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.Spring;

@Entity
@Table(name ="ADVERTISES")
public class AdvertiseEntity {
	
	@Id
	@GeneratedValue
	private int id;
	private Spring  title;
	private String category;
	private String description;
	private String userName;
	
	@Column(name="created_date")
	private LocalDate createdDate;
	@Column(name="modified_date")
	private LocalDate modifiedDate;
	
	private String status;
	private String postedBy;
	private int price;
	//active enum
	//photo blob
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Spring getTitle() {
		return title;
	}
	public void setTitle(Spring title) {
		this.title = title;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "AdvertiseEntity [id=" + id + ", title=" + title + ", category=" + category + ", description="
				+ description + ", userName=" + userName + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", status=" + status + ", postedBy=" + postedBy + ", price=" + price + "]";
	}
	
	
}

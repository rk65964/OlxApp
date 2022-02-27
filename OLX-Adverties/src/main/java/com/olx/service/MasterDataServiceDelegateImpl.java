package com.olx.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.olx.dto.Category;

//for microService communication
@Service
public abstract class MasterDataServiceDelegateImpl implements MasterDataServiceDelegate {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public Category getCategoryById(String categoryId) {
		Category category =
		this.restTemplate.getForObject("http://localhost:8080/categories/category/" + categoryId, Category.class);
		return category;
		}

}

package com.olx.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.olx.dto.AdvertiseDTO;
import com.olx.service.AdvertiseService;

@WebMvcTest(AdvertiseControllerTest.class)
public class AdvertiseControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	AdvertiseService advertiseService;
	
	@Autowired
	ObjectMapper objectMapper;
	@Test
	public void testSearchAdvertisesByFilterCriteria() throws Exception {

	List<AdvertiseDTO> advertises = new ArrayList<AdvertiseDTO>();
	advertises.add(new AdvertiseDTO());
	advertises.add(new AdvertiseDTO());
	//when(this.advertiseService.searchAdvertisesByFilterCriteria(null, 0, null, null, null, null, null, null, 0, 0))
	//.thenReturn(advertises);

	MvcResult mvcResult = mockMvc.perform(get("/olx/advertise/search/filtercriteria")
	.accept("application/json")
	.param("category", "0")
	.param("startIndex", "0")
	.param("records", "0")
	)
	.andExpect(status().isOk())
	.andReturn();
	String response = mvcResult.getResponse().getContentAsString();
	System.out.println("response: " + response);
	assertEquals(response.contains("title"), true);
	}
	@Test
	public void testCreateNewAdvertise_success() throws  Exception {
		
		AdvertiseDTO advertiseDTO = new AdvertiseDTO();
		advertiseDTO.setTitle("Sofa for sale");
		HttpHeaders headers = new HttpHeaders();
		headers.set("Autherization", "D78U");
		
		AdvertiseDTO expectedAdvertiseDTO = new AdvertiseDTO();
		expectedAdvertiseDTO.setTitle("Sofa for sale");
		
		when(this.advertiseService.createaAvertise(advertiseDTO, "D78U"))
		.thenReturn(expectedAdvertiseDTO);
		
		mockMvc.perform(post("/olx/advertise/")
				.content("application/json")
				.content(objectMapper.writeValueAsString(advertiseDTO))
				.headers(headers)
				)
		.andExpect(status().isOk()) //expected result
		.andExpect(content().string(containsString("Sofa"))) //expected result
		.andReturn(); //actual call to Rest API
	}

}

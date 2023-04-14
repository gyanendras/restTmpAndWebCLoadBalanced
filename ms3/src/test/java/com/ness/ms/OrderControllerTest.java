package com.ness.ms;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void testGetDefaultOrder() throws Exception{
		mockMvc.perform(get("/deforder"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("FromSetter"));
		
		
	}

}

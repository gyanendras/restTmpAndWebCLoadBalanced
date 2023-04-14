package com.ness.ms.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ness.ms.domain.Order;

@Configuration
public class BeanConfiguration {

	@Bean
	Order getDefOrder(){
		return new Order(1l,1l,LocalDate.now(), 1.00, "InProcess");
		
	}
	
	@Bean
	@Qualifier("deforderbean")
	Order getDefOrder1(){
		return new Order(22l,2l,LocalDate.now(), 2.00, "InProcess");
		
	}
	
	@Bean
	Order getDefOrder2(){
		return new Order(3l,2l,LocalDate.now(), 2.00, "InProcess");
		
	}
	
	@Bean
	@Qualifier("constbean")
	Order getDefOrder3(){
		return new Order(4l,2l,LocalDate.now(), 2.00, "InProcess");
		
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	
	@LoadBalanced
	@Bean
	public WebClient webcl() {
	    return WebClient.create();
	}
}

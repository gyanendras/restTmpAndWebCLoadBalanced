package com.ness.ms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.ness.ms.domain.Order;
import com.ness.ms.domain.Product;
import com.ness.ms.integ.ProductServiceConsumer;
import com.ness.ms.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired 
	OrderService orderService ;
	
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private ProductServiceConsumer psr;
	
	@Value("${server.port}")
	String port;
	
	@GetMapping("/order/info")
	String getInfo(HttpServletRequest req) {
		return req.getRemoteHost()+ " " +req.getRequestURI() + " "+port;
	}
	
	
	@GetMapping("/deforder")
	Order getDefaultOrder(){
		logger.debug("Default order to be created-1");
		return orderService.getDefaultOrder();
	}
	
	@GetMapping("/get-details")
	Order getDefaultOrder2(){
		logger.debug("Default order to be created-2");
		return orderService.getDefaultOrder();
	}
	
	@GetMapping("/order/{id}")
	Order getOrder(@PathVariable Long id){
		logger.debug("Default order to be created-3");
		return orderService.getOrder(id);
	}
	
	@PostMapping("/order")
	Order createUpdOrder(@RequestBody Order order) {
		logger.debug("Creating order created-4");
		return orderService.createOrder(order);
	}
	
	@DeleteMapping("/order")
	void deleteOrder(@RequestBody Order order) throws Exception {
		logger.debug("Removing order");
		orderService.removeOrder(order);
	}
	
	@GetMapping("/order/2/{id}")
	Order getOrder2(@PathVariable Long id){
		logger.debug("Resttemplate order to be created");
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = "{\"userId\":1,\"totalPrice\":11.11,\"status\":\"InProcess\",\"items\":[{\"quantity\":10,\"price_per_unit\":33.33},{\"quantity\":12,\"price_per_unit\":332.33}],\"ordeDate\":\"2023-04-10\"}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        String url = "http://localhost:8090/order";
        ResponseEntity<Order> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Order.class);

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        Order responseBody = responseEntity.getBody();
        System.out.println("Status code: " + statusCode);
        System.out.println("Response body: " + responseBody.getOrderId());
        
		return responseBody;
	}
	
	@GetMapping("/order/3/{id}")
	Order getOrder3(@PathVariable Long id) {
		
		Product prd = psr.getPrd(id);
		logger.debug("Rest loadbalanced order creation-6 "+prd);
		String url = "http://ORDERAPP/order/"+id;
		
		return restTemplate.getForObject(url, Order.class); 
	}
	
	@GetMapping("/order/5")
	String getOrder5() {
		String url = "http://ORDERAPP/order/info";
		
		return restTemplate.getForObject(url, String.class);
	}
	
	@GetMapping("/order/4/{id}")
	Order getOrder4(@PathVariable Long id) {
		String url = "http://localhost:8765/order/"+id;
		Mono<Order> morder = webClient.get().uri(url).retrieve().bodyToMono(Order.class);
		 return morder.block();
	}
	
	@GetMapping("/order/4/info")
	String getOrder5(@RequestParam String param) {
		String url = "http://localhost:8765/order/info";
		Mono<String> mstr = webClient.get().uri(url).retrieve().bodyToMono(String.class);
		return mstr.block()+" "+param;
	}

}

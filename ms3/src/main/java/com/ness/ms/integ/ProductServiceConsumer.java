package com.ness.ms.integ;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ness.ms.domain.Product;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductServiceConsumer {
	
	@GetMapping("/product/{id}")
	Product getPrd(@PathVariable Long id);

}

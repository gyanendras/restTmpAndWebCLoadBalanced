package com.ness.ms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ness.ms.domain.Order;
import com.ness.ms.domain.Review;
import com.ness.ms.repo.OrderRepository;
import com.ness.ms.repo.ReviewRepository;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class OrderService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	@Value("${application.service.level}")
	String serviceLevel;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired 
	@Qualifier("deforderbean")
	Order order;
	
	@Autowired
	Order getDefOrder2;
	
	Order sorder;
	
	@Autowired
	OrderService(@Qualifier("constbean") Order order) {
		this.sorder = order;
	}
	
	@Autowired
	public void setOrder(@Qualifier("deforderbean") Order order) {
		this.sorder.setStatus("FromSetter");
	
	}
	
	public Order getDefaultOrder() {
		System.out.println("Using service level " + serviceLevel);
		logger.debug("Logging Using service level  %s",serviceLevel);
		return sorder;
	}
	

	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	public Order getOrder(Long  id) {
		
		return orderRepository.findById(id).get();
	}

	public List<Order> findByUserId(Long id) {
		return orderRepository.findByUserId(id);
	}

	public List<Order> findByUserIdAndStatus(long uid, String status) {
		return orderRepository.findByUserIdAndStatus(uid, status);
	}
	
	@Transactional (rollbackOn=Exception.class)
	public void removeOrder(Order order) throws Exception {
 		//Order orderDB = orderRepository.findById(order.getOrderId()).get();
		//orderDB.getItems().clear();
		//orderRepository.flush();
		
 		//orderRepository.deleteById(order.getOrderId());
 		Review review = new Review();
 		review.setComment("Its good");
 		
 		reviewRepository.save(review);
		orderRepository.delete(order);
		throw new Exception();
	}

}

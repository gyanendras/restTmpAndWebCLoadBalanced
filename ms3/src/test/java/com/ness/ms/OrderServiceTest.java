package com.ness.ms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ness.ms.domain.Item;
import com.ness.ms.domain.Order;
import com.ness.ms.repo.OrderRepository;
import com.ness.ms.service.OrderService;

import jakarta.transaction.Transactional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	@Mock
	OrderRepository orderRepository;

    @InjectMocks
	OrderService mockOrderService;
	
	@Autowired
	OrderService orderservice;
	
	@Test 
	void testGetDefOrder(){
		
		Order order = orderservice.getDefaultOrder();
		assertNotNull(order);
		
	}
	
	@Test 
	void testGetOrder(){
		
		//Mockito.when(orderRepository.getOrder(1L)).thenReturn(new Order(1l,1l,LocalDate.now(), 22.22, "InProcess"));
		//Order order = mockOrderService.getOrder(1L);
		//assertNotNull(order);
		
		Order order = orderservice.getOrder(3l);
		List<Item> ls = order.getItems();
		assertEquals(3, ls.size());
		
		
	}
	
	@Test
	void testRemoveOrder() throws Exception {
		Order order = new Order();
		order.setOrderId(19l);
		orderservice.removeOrder(order);
	}
	
	@Test
	// @Transactional
	void testCreateOrder() {
		Order order = new Order(null,1l,LocalDate.now(), 11.11, "InProcess");
		Item item1 = new Item(null,null,10l, 33.33);
		Item item2 = new Item(null,null,12l, 332.33);
	    List<Item> lst = List.of(item1,item2);
		
		order.setItems(lst);
		
		orderservice.createOrder(order);
		//get/read the order.
	}
	
	@Test
	void testFindByUserId( ) {
		List<Order> orderList = orderservice.findByUserId(5l);
		
		assertEquals(1, orderList.size());
	
	}
	
	@Test
	void testFindByUserIdAndStatus( ) {
		List<Order> orderList = orderservice.findByUserIdAndStatus(1l,"InProcess");
		
		assertEquals(2, orderList.size());
	
	}
	
}

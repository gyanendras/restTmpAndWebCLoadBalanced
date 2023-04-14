package com.ness.ms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ness.ms.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
       List<Order> findByUserId(Long userId);
       List<Order> findByUserIdAndStatus(Long userId,String status);
		

}

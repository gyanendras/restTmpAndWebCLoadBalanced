package com.ness.ms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="items")
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long itemId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="order_of_item_id")
	@JsonIgnore
	Order orderOfItem ;
	

	Long  quantity ;
	Double price_per_unit ;
	
	public Item() {
		
	}
	
	
	public Item(Long itemId, Order orderOfItem, Long quantity, Double price_per_unit) {
		super();
		this.itemId = itemId;
		this.orderOfItem = orderOfItem;
		this.quantity = quantity;
		this.price_per_unit = price_per_unit;
	}


	public Long getItemId() {
		return itemId;
	}


	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}


	public Order getOrderOfItem() {
		return orderOfItem;
	}


	public void setOrderOfItem(Order orderOfItem) {
		this.orderOfItem = orderOfItem;
	}


	public Long getQuantity() {
		return quantity;
	}


	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}


	public Double getPrice_per_unit() {
		return price_per_unit;
	}


	public void setPrice_per_unit(Double price_per_unit) {
		this.price_per_unit = price_per_unit;
	}
	
	
	

}

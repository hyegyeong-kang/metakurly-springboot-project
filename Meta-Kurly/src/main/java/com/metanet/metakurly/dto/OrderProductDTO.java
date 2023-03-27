package com.metanet.metakurly.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderProductDTO {
	/* 뷰에서 전달받을 값 */
	private Long p_id;
	private int quantity;
	
	/* DB에서 읽어올 값 */
	private String brand;
	private String name;
	private int price;
	private String img_url;
	
	/* 계산이 필요한 값 */
	private int totalPrice;
	private int point;
	
	private List<OrderProductDTO> orderProductList;
	
	public void init() {
		this.totalPrice = this.price * this.quantity;
		this.point = (int)(totalPrice * 0.1);
	}
}

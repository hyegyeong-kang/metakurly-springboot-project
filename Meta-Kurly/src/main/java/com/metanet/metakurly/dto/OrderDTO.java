package com.metanet.metakurly.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class OrderDTO {
	private Long o_id;
	private Date orders_date;
	private String status;
	private int total_amount;
	private int price;
	private Long m_id;
	
	private List<OrderDetailDTO> orderDetailList;

}

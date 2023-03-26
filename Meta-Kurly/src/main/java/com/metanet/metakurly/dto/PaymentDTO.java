package com.metanet.metakurly.dto;

import lombok.Data;

@Data
public class PaymentDTO {
	private Long payment_id;
	private Long o_id;
	private Long m_id;
	private String method;
	private int payment_amount;
}

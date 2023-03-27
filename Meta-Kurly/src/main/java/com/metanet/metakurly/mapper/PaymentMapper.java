package com.metanet.metakurly.mapper;

import com.metanet.metakurly.dto.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
	
	public void createPayment(PaymentDTO payment);
	
	public PaymentDTO getPayment(Long o_id);
}

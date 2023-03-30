package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.OrderDTO;
import com.metanet.metakurly.dto.OrderDetailDTO;
import com.metanet.metakurly.dto.OrderProductDTO;
import com.metanet.metakurly.dto.PaymentDTO;

import java.util.List;


public interface OrderService {

	public OrderDTO getOrder(Long o_id);

	public OrderDTO getOrderDetailList(Long o_id);
	
	public List<OrderDTO> getOrderList(Long m_id);
	
	public PaymentDTO getPayment(Long o_id);

	public List<OrderProductDTO> getProductsInfo(List<OrderProductDTO> orderProducts);

	public List<OrderDetailDTO> getOrderDetailsInfo(List<OrderProductDTO> orderProducts);

	public void addOrder(OrderDTO order, PaymentDTO payment);

	public int cancelOrder(Long o_id);
	
}

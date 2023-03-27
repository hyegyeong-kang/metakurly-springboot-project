package com.metanet.metakurly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.metanet.metakurly.dto.OrderDTO;
import com.metanet.metakurly.dto.OrderProductDTO;

@Mapper
public interface OrderMapper {
	
	public OrderDTO getOrder(Long o_id);
	
	public List<OrderDTO> getOrderList(Long m_id);

	public OrderDTO getOrderDetail(Long o_id);
	
	public OrderProductDTO getProductInfo(Long p_id);
	
	public void createOrderDetail(OrderDTO order);
	
	public void createOrder(OrderDTO order);

	public int cancelOrder(Long o_id);
}

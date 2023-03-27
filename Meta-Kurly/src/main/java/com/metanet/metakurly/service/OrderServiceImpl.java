package com.metanet.metakurly.service;

import java.util.ArrayList;
import java.util.List;

import com.metanet.metakurly.dto.OrderDetailDTO;
import com.metanet.metakurly.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metanet.metakurly.dto.OrderDTO;
import com.metanet.metakurly.dto.OrderProductDTO;
import com.metanet.metakurly.dto.PaymentDTO;
import com.metanet.metakurly.mapper.OrderMapper;
import com.metanet.metakurly.mapper.PaymentMapper;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public OrderDTO getOrderDetailList(Long o_id) {
		return orderMapper.getOrderDetail(o_id);
	}

	@Override
	public List<OrderDTO> getOrderList(Long m_id) {
		List<OrderDTO> list = orderMapper.getOrderList(m_id);
		list.forEach(order -> order.setOrderDetailList(orderMapper.getOrderDetail(order.getO_id()).getOrderDetailList()));

		return list;
	}
	
	@Transactional
	@Override
	public void addOrder(OrderDTO order, PaymentDTO payment) {
		orderMapper.createOrder(order);
		orderMapper.createOrderDetail(order);
		paymentMapper.createPayment(payment);
	}

	@Override
	public int cancelOrder(Long o_id) {
		return orderMapper.cancelOrder(o_id);
	}

	@Override
	public List<OrderProductDTO> getProductsInfo(List<OrderProductDTO> orderProducts) {
		List<OrderProductDTO> order = new ArrayList<>();

		for(OrderProductDTO product : orderProducts) {
			OrderProductDTO orderProduct = orderMapper.getProductInfo(product.getP_id());
			orderProduct.setQuantity(product.getQuantity());
			orderProduct.init();
			order.add(orderProduct);
		}

		return order;
	}

	@Override
	public List<OrderDetailDTO> getOrderDetailsInfo(List<OrderProductDTO> orderProducts) {
		List<OrderDetailDTO> list = new ArrayList<>();

		for(OrderProductDTO product : orderProducts) {
			OrderDetailDTO detailDto = new OrderDetailDTO();
			detailDto.setP_id(product.getP_id());
//			detailDto.setProductDTO(productMapper.read(product.getP_id()));
			detailDto.setQuantity(product.getQuantity());
			list.add(detailDto);
		}

		return list;
	}

	@Override
	public PaymentDTO getPayment(Long o_id) {
		return paymentMapper.getPayment(o_id);
	}


}

package com.metanet.metakurly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.metanet.metakurly.dto.*;
import com.metanet.metakurly.vo.RequestPayment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.metanet.metakurly.dto.MemberDTO;
import com.metanet.metakurly.service.CartService;
import com.metanet.metakurly.service.OrderService;
import com.metanet.metakurly.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class OrderController {

	private OrderService service;

	private CartService cService;

	/* 주문내역 보기 */
	@GetMapping("{m_id}/orders")
	public List<OrderDTO> getOrderList(@PathVariable("m_id") Long m_id, HttpSession session) {

//		MemberDTO member = (MemberDTO) session.getAttribute("member");
//		Long m_id = member.getM_id();

		return service.getOrderList(m_id);
	}

	/* 주문 상세 보기(+ 결제 정보) */
	@GetMapping("{m_id}/orders/{o_id}")
	public ResponseEntity<Map<String, Object>> getOrderDetail(@PathVariable("m_id") Long m_id, @PathVariable("o_id") Long o_id) {

		OrderDTO orderDTO = service.getOrderDetailList(o_id);
		PaymentDTO paymentDTO = service.getPayment(o_id);

		Map<String, Object> response = new HashMap<>();
		response.put("order", orderDTO);
		response.put("payment", paymentDTO);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/* 주문서 */
	@PostMapping("{m_id}/orders")
	public List<OrderProductDTO> orderProduct(@PathVariable("m_id") Long m_id, @RequestBody OrderProductDTO orderProduct) {

		List<OrderProductDTO> orderProducts = orderProduct.getOrderProductList();

		return service.getProductsInfo(orderProducts);
	}

	/* 결제하기 */
	@PostMapping("/{m_id}/orders/payment")
	public ResponseEntity<Map<String, Object>> payment(@PathVariable("m_id") Long m_id, @RequestBody RequestPayment requestPayment) {

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		OrderDTO order = mapper.map(requestPayment, OrderDTO.class);
		OrderProductDTO orderProduct = mapper.map(requestPayment, OrderProductDTO.class);
		PaymentDTO payment = mapper.map(requestPayment, PaymentDTO.class);

//		MemberDTO member = (MemberDTO) session.getAttribute("member");
//		Long m_id = member.getM_id();
		order.setM_id(m_id);
		List<OrderProductDTO> orderProducts = orderProduct.getOrderProductList();
//		order.setPrice(payment.getPayment_amount() + payment.getUsePoint());

//		List<OrderDetailDTO> list = new ArrayList<>();
////		int total_quantity = 0;
//		for(OrderProductDTO product : orderProducts) {
////			OrderDetailDTO detailDto = new OrderDetailDTO();
////			detailDto.setP_id(product.getP_id());
//			OrderDetailDTO detailDto = mapper.map(product, OrderDetailDTO.class);
//			detailDto.setProductDTO(pService.get(product.getP_id()));
//			detailDto.setQuantity(product.getQuantity());
//			list.add(detailDto);
////			total_quantity += product.getQuantity();
//		}
		List<OrderDetailDTO> list = service.getOrderDetailsInfo(orderProducts);
//		order.setTotal_amount(total_quantity); // 전달할 때 보내면 더 좋을듯
		order.setOrderDetailList(list);

		payment.setM_id(m_id);
		service.addOrder(order, payment);

		for(OrderDetailDTO orderDetail : order.getOrderDetailList()) {
			cService.deleteCart(orderDetail.getP_id(), order.getM_id());
		}

//		order = service.getOrderDetailList(order.getO_id());

		Map<String, Object> response = new HashMap<>();
		response.put("order", order);
		response.put("payment", payment);
		response.put("deliveryMsg", requestPayment.getDeliveryMsg());

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/* 주문 취소 */
	@PatchMapping("/{m_id}/orders/{o_id}")
	public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable("m_id") Long m_id, @PathVariable("o_id") Long o_id) {

		Map<String, Object> response = new HashMap<>();
		OrderDTO orderDTO = null;
		PaymentDTO paymentDTO = null;

		if(service.cancelOrder(o_id) == 1){
			orderDTO = service.getOrderDetailList(o_id);
			paymentDTO = service.getPayment(o_id);
		}

		response.put("order", orderDTO);
		response.put("payment", paymentDTO);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}

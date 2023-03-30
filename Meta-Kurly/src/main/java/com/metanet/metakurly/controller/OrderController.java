package com.metanet.metakurly.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.metanet.metakurly.dto.*;
import com.metanet.metakurly.exception.MemberNotFoundException;
import com.metanet.metakurly.service.MemberService;
import com.metanet.metakurly.vo.RequestPayment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.metanet.metakurly.dto.MemberDTO;
import com.metanet.metakurly.service.CartService;
import com.metanet.metakurly.service.OrderService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/members")
public class OrderController {

	private RequestPayment request;

	private OrderService oService;

	private CartService cService;

	private MemberService mService;

	public OrderController(OrderService oService, CartService cService, MemberService mService) {
		this.oService = oService;
		this.cService = cService;
		this.mService = mService;
	}

	/* 주문내역 보기 */
	@GetMapping("{m_id}/orders")
	public ResponseEntity<List<OrderDTO>> getOrderList(@PathVariable("m_id") Long m_id) throws Exception {

		MemberDTO memberDTO = mService.getMemberById(m_id);

		if(memberDTO == null){
			throw new MemberNotFoundException(String.format("Member ID[%s] not found", m_id));
		}

		return ResponseEntity.status(HttpStatus.OK).body(oService.getOrderList(m_id));
	}

	/* 주문 상세 보기(+ 결제 정보) */
	@GetMapping("{m_id}/orders/{o_id}")
	public ResponseEntity<Map<String, Object>> getOrderDetail(@PathVariable("m_id") Long m_id,
															  @PathVariable("o_id") Long o_id) throws Exception{

		OrderDTO orderDTO = oService.getOrderDetailList(o_id);
		PaymentDTO paymentDTO = oService.getPayment(o_id);

		MemberDTO memberDTO = mService.getMemberById(m_id);

		if(memberDTO == null){
			throw new MemberNotFoundException(String.format("Member ID[%s] not found", m_id));
		} else if(orderDTO == null){
			throw new MemberNotFoundException(String.format("Order ID[%s] not found", o_id));
		}

		Map<String, Object> response = new HashMap<>();
		response.put("order", orderDTO);
		response.put("payment", paymentDTO);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/* 주문서 */
	@PostMapping("{m_id}/orders")
	public ResponseEntity<List<OrderProductDTO>> orderProduct(@PathVariable("m_id") Long m_id,
															  @RequestBody OrderProductDTO orderProduct)
			throws Exception{

		MemberDTO memberDTO = mService.getMemberById(m_id);

		if(memberDTO == null){
			throw new MemberNotFoundException(String.format("Member ID[%s] not found", m_id));
		}

		List<OrderProductDTO> orderProducts = orderProduct.getOrderProductList();

		return ResponseEntity.status(HttpStatus.OK).body(oService.getProductsInfo(orderProducts));
	}

	/* 결제하기 */
	@PostMapping("/{m_id}/orders/payment")
	public ResponseEntity<Map<String, Object>> payment(@PathVariable("m_id") Long m_id,
													   @RequestBody RequestPayment requestPayment)
			throws Exception {

		MemberDTO memberDTO = mService.getMemberById(m_id);

		if(memberDTO == null){
			throw new MemberNotFoundException(String.format("Member ID[%s] not found", m_id));
		}

		request = requestPayment;

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		OrderDTO order = mapper.map(requestPayment, OrderDTO.class);
		OrderProductDTO orderProduct = mapper.map(requestPayment, OrderProductDTO.class);
		PaymentDTO payment = mapper.map(requestPayment, PaymentDTO.class);

		order.setM_id(m_id);
		List<OrderProductDTO> orderProducts = orderProduct.getOrderProductList();
		List<OrderDetailDTO> list = oService.getOrderDetailsInfo(orderProducts);

		order.setOrderDetailList(list);

		oService.addOrder(order, payment);
		request.setO_id(order.getO_id());

		for(OrderDetailDTO orderDetail : order.getOrderDetailList()) {
			cService.deleteCart(orderDetail.getP_id(), order.getM_id());
		}

		Map<String, Object> response = new HashMap<>();
		response.put("order", order);
		response.put("payment", payment);
		response.put("deliveryMsg", requestPayment.getDeliveryMsg());


		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/* 결제 성공 화면 */
	@GetMapping("/{m_id}/orders/payment")
	public EntityModel<Map<String, Object>> getPayment(@PathVariable("m_id") Long m_id) throws Exception{

		MemberDTO memberDTO = mService.getMemberById(m_id);

		if(memberDTO == null){
			throw new MemberNotFoundException(String.format("Member ID[%s] not found", m_id));
		}

		Long o_id = request.getO_id();
		OrderDTO order = oService.getOrder(o_id);
		List<OrderDetailDTO> list = oService.getOrderDetailsInfo(request.getOrderProductList());
		PaymentDTO payment = oService.getPayment(o_id);

		Map<String, Object> response = new HashMap<>();
		response.put("order", order);
		response.put("products", list);
		response.put("payment", payment);
		response.put("deliveryMsg", request.getDeliveryMsg());

		return EntityModel.of(response,
				linkTo(methodOn(OrderController.class).getOrderDetail(m_id, o_id)).withRel("order-detail"));
	}

	/* 주문 취소 */
	@PatchMapping("/{m_id}/orders/{o_id}")
	public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable("m_id") Long m_id,
														   @PathVariable("o_id") Long o_id) throws Exception{

		MemberDTO memberDTO = mService.getMemberById(m_id);
		OrderDTO orderDTO = oService.getOrder(o_id);

		if(memberDTO == null){
			throw new MemberNotFoundException(String.format("Member ID[%s] not found", m_id));
		} else if(orderDTO == null){
			throw new MemberNotFoundException(String.format("Order ID[%s] not found", o_id));
		}

		Map<String, Object> response = new HashMap<>();

		oService.cancelOrder(o_id);
		orderDTO = oService.getOrderDetailList(o_id);
		PaymentDTO paymentDTO = oService.getPayment(o_id);

		response.put("order", orderDTO);
		response.put("payment", paymentDTO);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}

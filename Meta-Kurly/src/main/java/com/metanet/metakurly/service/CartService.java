package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.CartDTO;

import java.util.List;

public interface CartService {
    // 해당 회원 장바구니 리스트 출력
    public List<CartDTO> getMyCartList(Long m_id);

    // 해당 회원 장바구니 추가
    public void addCart(CartDTO cart);

    // 장바구니 중복 상품 확인 (1보다 크면 해당 상품 장바구니에 있는것)
    public int checkCart(Long p_id, Long m_id);

    // 중복된 상품이 있다면 넣지말고 수량 더해주기
    public void updateCount(CartDTO cart);

    // 장바구니 물건 삭제
    public void deleteCart(Long p_id, Long m_id);

    // 장바구니 전체 비우기
    public void deleteAllCart(Long m_id);

    // 장바구니 물건 업데이트 (장바구니에서 수량변경하는 것)
    public void updateCart(Long p_id, Long m_id, int quantity);

    // 해당 회원의 장바구니 전체 금액 출력
    public Long getTotalPrice(Long m_id);

}

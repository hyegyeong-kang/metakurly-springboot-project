package com.metanet.metakurly.service;


import com.metanet.metakurly.dto.CartDTO;
import com.metanet.metakurly.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private final CartMapper mapper;
    public CartServiceImpl(CartMapper mapper) {
        this.mapper = mapper;
    }


    // 해당 회원 장바구니 리스트 출력
    @Override
    public List<CartDTO> getMyCartList(Long m_id) {
        return mapper.getMyCartList(m_id);
    }

    // 해당 회원 장바구니에 물건 추가
    @Override
    public void addCart(CartDTO cart) {
        mapper.addCart(cart);
    }

    // 장바구니 중복 상품 확인
    @Override
    public int checkCart(Long p_id, Long m_id) {
        return mapper.checkCart(p_id, m_id);
    }

    // 중복된 상품이 있다면 넣지말고 수량 더해주기
    @Override
    public void updateCount(CartDTO cart) {
        mapper.updateCount(cart);
    }

    // 장바구니 물건 삭제
    @Override
    public void deleteCart(Long p_id, Long m_id) {
        mapper.deleteCart(p_id, m_id);
    }

    // 장바구니 전체 비우기
    @Override
    public void deleteAllCart(Long m_id) {
        mapper.deleteAllCart(m_id);
    }

    // 장바구니 물건 업데이트 (장바구니에서 수량변경하는 것)
    @Override
    public void updateCart(Long p_id, Long m_id, int quantity) {
        mapper.updateCart(p_id, m_id, quantity);
    }

    // 해당 회원의 장바구니 전체 금액 출력
    @Override
    public Long getTotalPrice(Long m_id) {
        return mapper.getTotalPrice(m_id);
    }
}

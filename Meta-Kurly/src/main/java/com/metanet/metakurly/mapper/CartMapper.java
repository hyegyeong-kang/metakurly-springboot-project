package com.metanet.metakurly.mapper;

import com.metanet.metakurly.dto.CartDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    // 해당 회원 장바구니 리스트 출력
    public List<CartDTO> getMyCartList(Long m_id);

    // 해당 회원 장바구니 물건추가
    public void addCart(CartDTO cart);

    // 장바구니 중복 상품 확인
    public int checkCart(@Param("p_id")Long p_id, @Param("m_id")Long m_id);

    // 중복된 상품이 있다면 넣지말고 수량 더해주기
    public void updateCount(CartDTO cart);

    // 장바구니 물건 삭제
    public void deleteCart(@Param("p_id")Long p_id, @Param("m_id")Long m_id);

    // 장바구니 전체 비우기
    public void deleteAllCart(Long m_id);

    // 장바구니 물건 업데이트 (장바구니에서 수량변경하는 것)
    public void updateCart(@Param("p_id")Long p_id, @Param("m_id")Long m_id, @Param("quantity")int quantity);

    // 해당 회원의 장바구니 전체 금액 출력
    public Long getTotalPrice(Long m_id);
}

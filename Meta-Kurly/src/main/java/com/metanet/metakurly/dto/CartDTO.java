package com.metanet.metakurly.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {

    private Long cartNum;
    private Long m_id;
    private Long p_id;
    private int quantity;


    //private int totalPrice;

    private List<ProductDTO> productList;
}

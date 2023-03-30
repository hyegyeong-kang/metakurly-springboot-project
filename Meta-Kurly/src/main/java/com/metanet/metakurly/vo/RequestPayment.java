package com.metanet.metakurly.vo;


import com.metanet.metakurly.dto.OrderProductDTO;
import lombok.Data;

import java.util.List;

@Data
public class RequestPayment {

    private Long o_id;
    private int total_amount;
    private int price;
    private Long m_id;

    private List<OrderProductDTO> orderProductList;

    private String method;
    private int payment_amount;
    private int usePoint;

    private String deliveryMsg;
}

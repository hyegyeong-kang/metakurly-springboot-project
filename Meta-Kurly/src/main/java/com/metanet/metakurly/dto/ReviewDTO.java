package com.metanet.metakurly.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReviewDTO {
    private Long r_id;
    private Long m_id;
    private Date review_date;
    private String contents;
    private Long p_id;
}

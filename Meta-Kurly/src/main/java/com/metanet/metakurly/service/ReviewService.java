package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.ProductDTO;
import com.metanet.metakurly.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    public List<ReviewDTO> getMyReviewList(int m_id);

    public List<ReviewDTO> getProductReviewList(Long p_id);

    public void registerReview(ReviewDTO review);

    public int totalMyReviewList(int memberUid);

    public boolean updateReview(ReviewDTO review);

    public boolean deleteReview(ReviewDTO review);
}

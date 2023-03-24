package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.ProductDTO;
import com.metanet.metakurly.dto.ReviewDTO;
import com.metanet.metakurly.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper mapper;

    @Override
    public List<ReviewDTO> getMyReviewList(int u_id) {
        return mapper.getMyReviewList(u_id);
    }

    @Override
    public List<ProductDTO> getProductReviewList(Long p_id) {
        return mapper.getProductReviewList(p_id);
    }

    @Override
    public void registerReview(ReviewDTO review) {
        mapper.registerReview(review);
    }


    @Override
    public int totalMyReviewList(int memberUid) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public boolean updateReview(ReviewDTO review) {
        return mapper.updateReview(review) == 1;
    }


    @Override
    public boolean deleteReview(ReviewDTO review) {
        return mapper.deleteReview(review);
    }
}

package com.metanet.metakurly.mapper;

import com.metanet.metakurly.dto.ProductDTO;
import com.metanet.metakurly.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public List<ReviewDTO> getMyReviewList(int m_id);

    public List<ReviewDTO> getProductReviewList(Long p_id);

    public void registerReview(ReviewDTO review);

    public int updateReview(ReviewDTO review);

    public boolean deleteReview(ReviewDTO review);
}

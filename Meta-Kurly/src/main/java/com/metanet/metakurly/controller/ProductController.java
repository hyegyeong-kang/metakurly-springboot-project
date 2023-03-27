package com.metanet.metakurly.controller;

import com.metanet.metakurly.dto.ProductDTO;
import com.metanet.metakurly.dto.ReviewDTO;
import com.metanet.metakurly.service.ProductService;
import com.metanet.metakurly.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService service;

    private ReviewService rService;

    @GetMapping("")
    public List<ProductDTO> list() {
        List<ProductDTO> productDTO = service.getList();
        List<ReviewDTO> reviewDTO = (List<ReviewDTO>) new ReviewDTO();

        for (int i = 0; i < productDTO.size(); i++) {
            reviewDTO = rService.getProductReviewList(productDTO.get(i).getP_id());
            productDTO.get(i).setReviewList(reviewDTO);
        }

        return productDTO;
    }

    @GetMapping("/{p_id}")
    public ProductDTO get(@PathVariable("p_id") Long p_id) {
        ProductDTO productDTO = service.get(p_id);
        List<ReviewDTO> reviewDTO = rService.getProductReviewList(p_id);
        productDTO.setReviewList(reviewDTO);
        return productDTO;
    }

    @GetMapping("/bestList")
    public List<ProductDTO> getBestProductList() {
        return service.getBestProductList();
    }
}

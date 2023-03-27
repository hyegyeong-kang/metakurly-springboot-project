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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService service;

    private ReviewService rService;

    @GetMapping("")
    public List<ProductDTO> list() {
        List<ProductDTO> allProduct = service.getList();
        List<ReviewDTO> reviewDTO = new ArrayList<>();

        for (int i = 0; i < allProduct.size(); i++) {
            reviewDTO = rService.getProductReviewList(allProduct.get(i).getP_id());
            allProduct.get(i).setReviewList(reviewDTO);
        }

        return allProduct;
    }

    @GetMapping("/{p_id}")
    public ProductDTO get(@PathVariable("p_id") Long p_id) {
        ProductDTO product = service.get(p_id);
        List<ReviewDTO> reviewDTO = rService.getProductReviewList(p_id);
        product.setReviewList(reviewDTO);
        return product;
    }

    @GetMapping("/bestList")
    public List<ProductDTO> getBestProductList() {
        List<ProductDTO> bestProduct = service.getBestProductList();
        List<ReviewDTO> reviewDTO = new ArrayList<>();

        for (int i = 0; i < bestProduct.size(); i++) {
            reviewDTO = rService.getProductReviewList(bestProduct.get(i).getP_id());
            bestProduct.get(i).setReviewList(reviewDTO);
        }

        return bestProduct;
    }
}

package com.metanet.metakurly.controller;

import com.metanet.metakurly.dto.ProductDTO;
import com.metanet.metakurly.dto.ReviewDTO;
import com.metanet.metakurly.service.ProductService;
import com.metanet.metakurly.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
    public EntityModel<Map<String, Object>> get(@PathVariable("p_id") Long p_id) {
        ProductDTO product = service.get(p_id);
        List<ReviewDTO> reviews = rService.getProductReviewList(p_id);
        product.setReviewList(reviews);
        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        map.put("reviews", reviews);

        return EntityModel.of(map,
                linkTo(methodOn(ProductController.class).list()).withRel("all-products"),
                linkTo(methodOn(ReviewController.class).showProductReview(p_id)).withRel("all-product-reviews"));
    }

//    @GetMapping("/{p_id}")
//    public EntityModel<ProductDTO> get(@PathVariable("p_id") Long p_id) {
//        ProductDTO product = service.get(p_id);
//        List<ReviewDTO> reviewDTO = rService.getProductReviewList(p_id);
//        product.setReviewList(reviewDTO);
//        return EntityModel.of(product,
//                linkTo(methodOn(ProductController.class).list()).withRel("all-products"));
//    }

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

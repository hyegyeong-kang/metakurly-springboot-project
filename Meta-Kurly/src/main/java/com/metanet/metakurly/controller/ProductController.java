package com.metanet.metakurly.controller;

import com.metanet.metakurly.dto.ProductDTO;
import com.metanet.metakurly.service.ProductService;
import com.metanet.metakurly.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
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
        return service.getList();
    }

    @GetMapping("/{p_id}")
    public String get(@PathVariable("p_id") Long p_id, Model model) {

        model.addAttribute("product", service.get(p_id));
        return "products/productDetail";

    }

    @GetMapping("/bestList")
    public String getBestProductList(Model model) {
        model.addAttribute("bestProductList", service.getBestProductList());
        return "products/bestProductList";

    }
}

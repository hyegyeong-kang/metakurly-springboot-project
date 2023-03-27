package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public void register(ProductDTO product);

    public ProductDTO get(Long p_id);

    public boolean modify(ProductDTO product);

    public boolean remove(Long p_id);

    public List<ProductDTO> getList();

    public List<ProductDTO> getBestProductList();
}

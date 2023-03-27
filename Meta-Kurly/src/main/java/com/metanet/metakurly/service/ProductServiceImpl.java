package com.metanet.metakurly.service;

import com.metanet.metakurly.dto.ProductDTO;
import com.metanet.metakurly.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductMapper mapper;

    @Override
    public void register(ProductDTO product) {
        mapper.insert(product);
    }

    @Override
    public ProductDTO get(Long p_id) {
        return mapper.read(p_id);
    }

    @Override
    public boolean modify(ProductDTO product) {
        return mapper.update(product) == 1;
    }

    @Override
    public boolean remove(Long p_id) {
        return mapper.delete(p_id) == 1;
    }

    @Override
    public List<ProductDTO> getList() {
        return mapper.getList();
    }

    @Override
    public List<ProductDTO> getBestProductList() {
        return mapper.getBestProductList();
    }
}

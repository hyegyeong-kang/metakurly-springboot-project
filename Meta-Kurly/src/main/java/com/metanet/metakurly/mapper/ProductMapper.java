package com.metanet.metakurly.mapper;

import com.metanet.metakurly.dto.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    public List<ProductDTO> getList();

    public List<ProductDTO> getBestProductList();

    public void insert(ProductDTO product);

    public void insertSelectKey(ProductDTO product);

    public ProductDTO read(Long p_id);

    public int delete(Long p_id);

    public int update(ProductDTO product);
}

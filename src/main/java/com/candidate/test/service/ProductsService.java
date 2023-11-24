package com.candidate.test.service;

import com.candidate.test.dto.ProductsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface ProductsService {
    Page<Object> getAllProducts(int page, int size, String sortField, Sort.Direction sortOrder);

    Object addProduct(ProductsDTO productsDTO);
}

package com.candidate.test.controller;

import com.candidate.test.dto.ProductsDTO;
import com.candidate.test.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductsService productsService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<Object> addProducts(@RequestBody ProductsDTO productsDTO) {
        Object newProduct = productsService.addProduct(productsDTO);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Object>> getAllProducts(@RequestParam(defaultValue = "0") Integer page,
                                                                    @RequestParam(defaultValue = "10") Integer size,
                                                                    @RequestParam(defaultValue = "id") String sort,
                                                                    @RequestParam(defaultValue = "ASC") Sort.Direction ordering){
        return ResponseEntity.ok(productsService.getAllProducts(page, size, sort, ordering));
    }
}

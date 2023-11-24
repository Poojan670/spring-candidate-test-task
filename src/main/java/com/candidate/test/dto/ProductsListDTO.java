package com.candidate.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class ProductsListDTO {
    private Map<String, Object> values = new LinkedHashMap<>();

    public ProductsListDTO(Map<String, Object> row, Set<String> columnNames) {
        for (String columnName : columnNames) {
            values.put(columnName, row.get(columnName));
        }
    }
}


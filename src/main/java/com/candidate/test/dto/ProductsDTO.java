package com.candidate.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductsDTO {
    @NotNull(message = "table is required")
    private String table;
    @NotNull(message = "records is required")
    private List<Map<String, String>> records;
}

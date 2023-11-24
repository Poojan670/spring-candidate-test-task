package com.candidate.test.service;

import com.candidate.test.dto.ProductsDTO;
import com.candidate.test.dto.ProductsListDTO;
import com.candidate.test.exception.CustomException;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Object> getAllProducts(int page, int size, String sortField, Sort.Direction sortOrder) {
        try {
            Pageable pageable = PageRequest.of(page, size, sortOrder, sortField);

            String sql = "SELECT * FROM products";
            Query query = entityManager.createNativeQuery(sql);

            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());

            // Use AliasToEntityMapResultTransformer to transform result set into a map
            List<Map<String, Object>> results = query.unwrap(NativeQuery.class)
                    .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
                    .getResultList();

            // Retrieve column names
            Set<String> columnNames = results.isEmpty() ? Collections.emptySet() : results.get(0).keySet();

            // Convert results to a list of Maps
            List<Object> finalResultSet = results.stream()
                    .map(row -> new ProductsListDTO(row, columnNames).getValues())
                    .collect(Collectors.toList());

            // Count the total number of products
            String countSql = "SELECT COUNT(*) FROM products";
            Query countQuery = entityManager.createNativeQuery(countSql);
            long totalProducts = ((Number) countQuery.getSingleResult()).longValue();

            return new PageImpl<>(finalResultSet, pageable, totalProducts);

        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    @Override
    public Object addProduct(ProductsDTO productsDTO) {
        // Dynamically create the table
        this.createTableIfNotExists(productsDTO.getTable(), productsDTO.getRecords());

        // Save records to the database using native SQL query
        return this.insertRecords(productsDTO.getTable(), productsDTO.getRecords());
    }

    // Helper method to create table dynamically
    private void createTableIfNotExists(String tableName, List<Map<String, String>> records) {
        try {
            StringBuilder createTableSql = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                    .append(tableName)
                    .append(" (id SERIAL PRIMARY KEY");

            // Iterate over the fields in the first record to dynamically add columns
            for (Map.Entry<String, String> entry : records.get(0).entrySet()) {
                createTableSql.append(", ").append(entry.getKey()).append(" VARCHAR(255)");
            }

            createTableSql.append(")");

            entityManager.createNativeQuery(createTableSql.toString()).executeUpdate();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public Map<String, Object> insertRecords(String tableName, List<Map<String, String>> records) {
        try {
            for (Map<String, String> record : records) {
                // Construct the SQL query dynamically
                StringBuilder insertSql = new StringBuilder("INSERT INTO ")
                        .append(tableName)
                        .append(" (");

                // Append column names
                for (String columnName : record.keySet()) {
                    insertSql.append(columnName).append(", ");
                }
                // Remove the trailing comma
                insertSql.setLength(insertSql.length() - 2);

                // Append values
                insertSql.append(") VALUES (");

                for (String columnName : record.keySet()) {
                    insertSql.append(":").append(columnName).append(", ");
                }
                // Remove the trailing comma
                insertSql.setLength(insertSql.length() - 2);
                insertSql.append(")");

                // Create the query only once
                Query query = entityManager.createNativeQuery(insertSql.toString());

                // Set parameters
                for (Map.Entry<String, String> entry : record.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }

                // Execute the query
                query.executeUpdate();
                entityManager.flush();
            }
            return Map.of(
                    "msg", "Products Saved Successfully"
            );
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }
}

package com.jendra.Zen_Roti.service;


import com.jendra.Zen_Roti.entity.Product;
import com.jendra.Zen_Roti.entity.ProductDTO;
import com.jendra.Zen_Roti.exception.CategoryNotFoundException;
import com.jendra.Zen_Roti.exception.ProductNotFoundException;
import com.jendra.Zen_Roti.exception.RecipeNotFoundException;
import org.hibernate.PropertyValueException;

import java.util.List;

public interface ProductService {

    Product save(ProductDTO productDTO) throws RecipeNotFoundException, CategoryNotFoundException, PropertyValueException;

    List<Product> save(List<ProductDTO> productDTOS)

            throws RecipeNotFoundException, CategoryNotFoundException, PropertyValueException;
    Product findById(Long id) throws ProductNotFoundException;
    List<Product> findAll();
    void deleteById(Long id) throws ProductNotFoundException;
    Product update(ProductDTO productDTO) throws ProductNotFoundException, CategoryNotFoundException, RecipeNotFoundException;
    boolean existsById(Long id);
    double totalAmount(Long productId,int productQty) throws ProductNotFoundException;
}

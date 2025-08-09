package com.yuosef.ecommerce.Dao;

import com.yuosef.ecommerce.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer> {

    List<Product> findAllByIdInOrderById(List<Integer> productIds);
}

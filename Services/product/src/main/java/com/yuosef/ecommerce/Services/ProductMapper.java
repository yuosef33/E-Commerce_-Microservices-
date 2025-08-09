package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.category.Category;
import com.yuosef.ecommerce.product.Product;
import com.yuosef.ecommerce.product.ProductPurchaseResponse;
import com.yuosef.ecommerce.product.ProductRequest;
import com.yuosef.ecommerce.product.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProdduct(ProductRequest request) {
        return new Product(request.id(),
                request.name(),
                request.description(),
                request.price(),
                request.quantity(),
                new Category(request.categoryId()));
    }

    public ProductResponse toProductResponse(Product product){

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }


    public ProductPurchaseResponse toProductpurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}

package com.yuosef.ecommerce.Controller;

import com.yuosef.ecommerce.Services.ProductService;
import com.yuosef.ecommerce.product.ProductPurchaseRequest;
import com.yuosef.ecommerce.product.ProductPurchaseResponse;
import com.yuosef.ecommerce.product.ProductRequest;
import com.yuosef.ecommerce.product.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Integer> addproduct(
            @RequestBody @Valid ProductRequest request
    ){
        return ResponseEntity.ok(service.createProduct(request));
    }

    @PostMapping("/purchase/")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody @Valid List<ProductPurchaseRequest> requestList
    )
    {
        return ResponseEntity.ok(service.purchaseProducts(requestList));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ){

        return ResponseEntity.ok(service.findById(productId));
    }
    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

}

package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.product.*;
import com.yuosef.ecommerce.Dao.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDao repo;
    private final ProductMapper mapper;
    public ProductService(ProductDao productDao, ProductMapper mapper) {
        this.repo = productDao;
        this.mapper = mapper;
    }

    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProdduct(request);
        return repo.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList) {
    return null;
    }

    public ProductResponse findById(Integer productId) {
      Product product= repo.findById(productId).orElseThrow(()-> new
              ResponseStatusException(HttpStatus.NOT_FOUND,"no product"));

        return mapper.toProductResponse(product);

    }

    public List<ProductResponse> findAll() {
        return repo.findAll().stream()
                .map(mapper::toProductResponse)
                .collect(Collectors.toList());
    }
}

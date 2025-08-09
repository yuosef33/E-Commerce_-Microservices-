package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.product.*;
import com.yuosef.ecommerce.Dao.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
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
        var productIds=requestList
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = repo.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"One or more products does not exits");

        }
        var storedRequest =requestList.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0;i<storedProducts.size();i++){
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if(product.getQuantity()< productRequest.quantity()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"stock quantity for product with id :"+ productRequest.productId());
            }
            var newavailableQuantity= product.getQuantity()-productRequest.quantity();
            product.setQuantity(newavailableQuantity);
            repo.save(product);
            purchaseProducts.add(mapper.toProductpurchaseResponse(product,productRequest.quantity()));

        }
        return purchaseProducts;

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
    public void removeProduct(){

    }
}

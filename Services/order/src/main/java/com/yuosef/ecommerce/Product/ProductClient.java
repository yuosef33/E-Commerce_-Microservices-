package com.yuosef.ecommerce.Product;


import com.yuosef.ecommerce.Models.PurchaseRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ProductClient {
    @Value("${application.config.product-url}")
    private String producturl;
    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestList){
        HttpHeaders headers=new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity=new HttpEntity<>(requestList,headers);

        ParameterizedTypeReference<List<PurchaseResponse>> responseType=
                new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PurchaseResponse>> responseEntity=restTemplate.exchange(
                producturl+"/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        if(responseEntity.getStatusCode().isError()){
            throw new RuntimeException("error happened "+responseEntity.getStatusCode());}


        return responseEntity.getBody();
        }





}

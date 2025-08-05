package com.yuosef.ecommerce.Services;

import com.yuosef.ecommerce.Dao.CustomerDao;
import com.yuosef.ecommerce.Models.Customer;
import com.yuosef.ecommerce.Models.CustomerRequest;
import com.yuosef.ecommerce.Models.CustomerRespnse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerDao customerDao;
    private final CustomerMapper mapper;

    public CustomerService(CustomerDao customerDao, CustomerMapper mapper) {
        this.customerDao = customerDao;
        this.mapper = mapper;
    }

    public String createCustomer(CustomerRequest customerRequest) {
        Customer customer=new Customer();
        customer.setId(customerRequest.id());
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setEmail(customerRequest.email());
        customer.setAddress(customerRequest.address());

            customerDao.save(customer);

        return "Customer "+customer.getFirstName()+" added";
    }


    public void updateCustomer(CustomerRequest request) {
        var customer =customerDao.findById(request.id()).orElseThrow(()->
                new RuntimeException("no customer with this name"));
        mergerCustomer(customer,request);
        customerDao.save(customer);

    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.address() != null){
            customer.setAddress(request.address());
        }

    }


    public List<CustomerRespnse> findAll() {
      return customerDao.findAll().stream()
              .map(mapper::toResponse)
              .collect(Collectors.toList());
    }

    public CustomerRespnse findById(String customerId) {
      var customer=  customerDao.findById(customerId).orElseThrow(() ->
              new ResponseStatusException(HttpStatus.NOT_FOUND,"no customer with this id"));

      return mapper.toResponse(customer);
    }

    public void delteCustomer(String customerId) {
        customerDao.deleteById(customerId);
    }
}

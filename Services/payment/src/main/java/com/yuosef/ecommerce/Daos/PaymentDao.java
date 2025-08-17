package com.yuosef.ecommerce.Daos;

import com.yuosef.ecommerce.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDao extends JpaRepository<Payment,Integer> {
}

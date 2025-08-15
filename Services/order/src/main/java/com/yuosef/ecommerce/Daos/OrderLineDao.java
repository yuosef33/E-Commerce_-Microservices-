package com.yuosef.ecommerce.Daos;

import com.yuosef.ecommerce.Models.Order;
import com.yuosef.ecommerce.Models.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineDao extends JpaRepository<OrderLine,Integer> {
}

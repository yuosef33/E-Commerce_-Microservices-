package com.yuosef.ecommerce.Daos;

import com.yuosef.ecommerce.Models.Order;
import com.yuosef.ecommerce.Models.OrderLine;
import com.yuosef.ecommerce.orderline.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineDao extends JpaRepository<OrderLine,Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}

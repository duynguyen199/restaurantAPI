package com.cybersoft.osahaneat.reposistory;

import com.cybersoft.osahaneat.entity.OrderItem;
import com.cybersoft.osahaneat.entity.key.KeyOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {


}

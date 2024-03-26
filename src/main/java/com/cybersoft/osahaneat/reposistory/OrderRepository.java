package com.cybersoft.osahaneat.reposistory;

import com.cybersoft.osahaneat.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface OrderRepository extends JpaRepository <Orders, Integer> {
}

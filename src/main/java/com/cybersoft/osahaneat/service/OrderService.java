package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.entity.*;
import com.cybersoft.osahaneat.entity.key.KeyOrderItem;
import com.cybersoft.osahaneat.payload.request.OrderRequest;
import com.cybersoft.osahaneat.reposistory.OrderItemRepository;
import com.cybersoft.osahaneat.reposistory.OrderRepository;
import com.cybersoft.osahaneat.service.imp.OrderServiceImp;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public boolean insertOrder(OrderRequest orderRequest) {
        try {
            Users users=new Users();

            users.setId(orderRequest.getUserId());

            Restaurant restaurant =new Restaurant();
            restaurant.setId(orderRequest.getResId());



            Orders orders = new Orders();
            orders.setUsers(users);
            orders.setRestaurant(restaurant);

            orderRepository.save(orders);
            List<OrderItem> items = new ArrayList<>();

            for (int idFood:orderRequest.getFoodIds()) {
                Food food = new Food();
                food.setId(idFood);
                OrderItem orderItem=new OrderItem();
                KeyOrderItem keyOrderItem=new KeyOrderItem(orders.getId(),idFood);
                orderItem.setKeyOrderItem(keyOrderItem);
                items.add(orderItem);


            }
            orderItemRepository.saveAll(items);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage() +" Error insert order");
            return false;

        }


    }
}

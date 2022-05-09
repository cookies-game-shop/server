package net.javaForum.javaForum.service;
import net.javaForum.javaForum.model.Order;
import net.javaForum.javaForum.repository.OrderRepo;
import net.javaForum.javaForum.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;

    public OrderService(OrderRepo orderRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }

    public List<Order> getListOrders() {
        return orderRepo.findAll();
    }

    public void saveOrderToDB(Order order) {
        orderRepo.save(order);
    }
}

package ru.karamoff.mcdrive.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.karamoff.mcdrive.models.Order;
import ru.karamoff.mcdrive.repositories.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

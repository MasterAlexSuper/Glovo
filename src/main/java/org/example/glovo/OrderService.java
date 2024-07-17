package org.example.glovo;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    List<Order> orders = new ArrayList<>();

    public List<Order> getAll() {
        return orders;
    }

    public Order getById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public Order add(Order order) {
        orders.add(order);
        return order;
    }

    public Order update(Order prev, int id) {
        Order update = getById(id);
        String name = prev.getCustomerName();
        if (name != null) {
            update.setCustomerName(name);
        }
        List<String> product = prev.getProducts();
        if (product != null) {
            update.setProducts(product);
        }
        return update;
    }

    public Order addProduct(int id, String product) {
        Order order = getById(id);
        if (order != null) order.getProducts().add(product);
        return order;
    }

    public Order removeProduct(int id, String product) {
        Order order = getById(id);
        if (order != null) order.getProducts().remove(product);
        return order;
    }

    public Order removeById(int id) {
        Order order = getById(id);
        if (order != null) orders.remove(order);
        return order;
    }
}

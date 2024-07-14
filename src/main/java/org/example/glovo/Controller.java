package org.example.glovo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class Controller {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAll();
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.add(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable int id) {
        return orderService.update(order, id);
    }

    @PatchMapping("/{id}")
    public Order patchOrder(@PathVariable int id, @RequestBody String product) {
        return orderService.addProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public Order deleteProduct(@PathVariable int id, @RequestBody String product) {
        return orderService.removeProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public Order deleteOrder(@PathVariable int id) {
        return orderService.removeById(id);
    }

    @Autowired
    public Controller(OrderService orderService) {
        this.orderService = orderService;
    }

}

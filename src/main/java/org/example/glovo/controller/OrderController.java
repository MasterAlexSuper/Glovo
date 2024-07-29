package org.example.glovo.controller;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.ItemDTO;
import org.example.glovo.dto.OrderDTO;
import org.example.glovo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable int id) {
        return orderService.findById(id);
    }

    @PostMapping
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.save(orderDTO);
    }

    @PatchMapping("/{id}/add")
    public OrderDTO addItem(@RequestBody ItemDTO itemDTO, @PathVariable int id) {
        return orderService.addItem(itemDTO, id);
    }

    @DeleteMapping("/remove/{itemId}")
    public void removeItem(@PathVariable int itemId) {
        orderService.removeItem(itemId);
    }


    @DeleteMapping
    public void deleteOrder(@RequestBody OrderDTO orderDTO) {
        orderService.delete(orderDTO.getId());
    }

}

package org.example.glovo.service;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.ItemDTO;
import org.example.glovo.dto.OrderDTO;
import org.example.glovo.entity.ItemEntity;
import org.example.glovo.entity.OrderEntity;
import org.example.glovo.entity.ProductEntity;
import org.example.glovo.mapper.ItemMapper;
import org.example.glovo.mapper.OrderMapper;
import org.example.glovo.repository.ItemRepository;
import org.example.glovo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderMapper::toDTO).toList();
    }

    public OrderDTO findById(int id) {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow();
        return OrderMapper.toDTO(orderEntity);
    }

    public OrderDTO save(OrderDTO orderDTO) {
        OrderEntity orderEntity = OrderMapper.toEntity(orderDTO);
        orderEntity.setCreationDate(LocalDateTime.now());
        return OrderMapper.toDTO(orderRepository.save(orderEntity));
    }


    public OrderDTO addItem(ItemDTO itemDTO, int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        ItemEntity itemEntity = ItemMapper.toEntity(itemDTO);
        itemEntity.setOrder(orderEntity);
        itemEntity.setProduct(ProductEntity.builder().id(itemDTO.getProductId()).build());
        itemRepository.save(itemEntity);
        orderEntity.getItems().add(itemEntity);
        return OrderMapper.toDTO(orderRepository.save(orderEntity));
    }

    public void removeItem(int itemId) {
        itemRepository.deleteById(itemId);

    }

    public void delete(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow();
        orderEntity.getItems().forEach(item -> itemRepository.deleteById(item.getId()));
        orderRepository.deleteById(orderId);
    }
}

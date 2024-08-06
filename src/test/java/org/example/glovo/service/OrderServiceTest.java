package org.example.glovo.service;

import org.example.glovo.dto.ItemDTO;
import org.example.glovo.dto.OrderDTO;
import org.example.glovo.entity.ItemEntity;
import org.example.glovo.entity.OrderEntity;
import org.example.glovo.mapper.OrderMapper;
import org.example.glovo.repository.ItemRepository;
import org.example.glovo.repository.OrderRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class OrderServiceTest {

    private final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private final ItemRepository itemRepository = Mockito.mock(ItemRepository.class);

    @Test
    public void findAllTest() {
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        List<OrderEntity> ordersEntity = Arrays.asList(order1, order2);
        Mockito.when(orderRepository.findAll()).thenReturn(ordersEntity);

        OrderDTO dto1 = new OrderDTO();
        OrderDTO dto2 = new OrderDTO();
        List<OrderDTO> DTOs = Arrays.asList(dto1, dto2);

        OrderService orderService = new OrderService(orderRepository, itemRepository);
        Assertions.assertEquals(DTOs, orderService.findAll());

        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        OrderEntity order = OrderEntity.builder().id(1).build();
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.ofNullable(order));

        Assertions.assertEquals(order.getId(), orderRepository.findById(1).get().getId());
    }

    @Test
    public void saveTest() {
        OrderDTO dto = OrderDTO.builder().username("hello").description("none").build();
        OrderEntity entity = OrderMapper.toEntity(dto);

        Mockito.when(orderRepository.save(any(OrderEntity.class))).thenReturn(entity);

        OrderService orderService = new OrderService(orderRepository, itemRepository);

        Assertions.assertEquals(dto, orderService.save(dto));

        Mockito.verify(orderRepository, Mockito.times(1)).save(any(OrderEntity.class));
    }

    @Test
    public void addItemTest() {

        List<ItemEntity> entities = new ArrayList<>();

        ItemEntity itemEntity = ItemEntity.builder().id(1).build();
        ItemDTO itemDTO = ItemDTO.builder().id(1).build();
        entities.add(itemEntity);

        OrderEntity orderEntity = OrderEntity.builder().items(entities).id(1).build();
        OrderDTO orderDTO = OrderDTO.builder().id(1).itemsIds(List.of(1)).build();

        Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.ofNullable(orderEntity));
        Mockito.when(itemRepository.save(any(ItemEntity.class))).thenReturn(itemEntity);
        Mockito.when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);


        OrderService orderService = new OrderService(orderRepository, itemRepository);


        Assertions.assertEquals(orderDTO, orderService.addItem(itemDTO, 1));

    }

    @Test
    public void removeItemTest() {

        OrderService orderService = new OrderService(orderRepository, itemRepository);
        orderService.removeItem(1);
        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(anyInt());
    }

    @Test
    public void deleteTest() {

        OrderService orderService = new OrderService(orderRepository, itemRepository);

        OrderEntity orderEntity = OrderEntity.builder().id(1).items(List.of(ItemEntity.builder().id(1).build())).build();

        Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.ofNullable(orderEntity));

        orderService.delete(1);

        Mockito.verify(orderRepository, Mockito.times(1)).findById(anyInt());
        Mockito.verify(itemRepository, Mockito.times(1)).deleteById(anyInt());
        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(anyInt());

    }
}

package org.example.glovo.mapper;


import org.example.glovo.dto.OrderDTO;
import org.example.glovo.entity.ItemEntity;
import org.example.glovo.entity.OrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMapperTest {

    @Test
    public void toDTOTest() {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(2);
        OrderEntity orderEntity = OrderEntity.builder()
                .items(List.of(itemEntity))
                .id(2)
                .username("anton")
                .creationDate(LocalDateTime.parse("-999999999-01-01T00:00:00"))
                .build();
        OrderDTO result = OrderMapper.toDTO(orderEntity);
        OrderDTO expected = OrderDTO.builder()
                .itemsIds(List.of(2))
                .creationDate(LocalDateTime.parse("-999999999-01-01T00:00:00"))
                .username("anton")
                .id(2)
                .build();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void toEntityTest() {
        OrderDTO dto = OrderDTO.builder().username("hello").description("hello").build();
        OrderEntity result = OrderMapper.toEntity(dto);
        OrderEntity expected = OrderEntity.builder()
                .username("hello")
                .description("hello")
                .build();
        Assertions.assertEquals(expected, result);
    }
}

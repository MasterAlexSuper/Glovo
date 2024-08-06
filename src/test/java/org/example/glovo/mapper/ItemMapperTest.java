package org.example.glovo.mapper;

import org.example.glovo.dto.ItemDTO;
import org.example.glovo.entity.ItemEntity;
import org.example.glovo.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemMapperTest {

    @Test
    public void toEntityTest() {
        ItemDTO dto = ItemDTO.builder().id(1).quantity(1.2).build();
        ItemEntity result = ItemMapper.toEntity(dto);
        ItemEntity expected = ItemEntity.builder().id(1).quantity(1.2).build();
        assertEquals(result, expected);
    }

    @Test
    public void toDtoTest() {
        ItemEntity entity = ItemEntity.builder().id(2).product(ProductEntity.builder().id(2).build()).quantity(1.2).build();
        ItemDTO result = ItemMapper.toDto(entity);
        ItemDTO expected = ItemDTO.builder().id(2).productId(2).quantity(1.2).build();
        assertEquals(result, expected);
    }
}

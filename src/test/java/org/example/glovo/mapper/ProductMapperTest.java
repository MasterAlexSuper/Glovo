package org.example.glovo.mapper;

import org.example.glovo.dto.ProductDTO;
import org.example.glovo.entity.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductMapperTest {


    @Test
    public void toEntityTest() {
        ProductDTO productDTO = ProductDTO.builder().price(10).name("hello").build();
        ProductEntity result = ProductMapper.toEntity(productDTO);
        ProductEntity expected = ProductEntity.builder().price(10).name("hello").build();
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void toDTOTest() {
        ProductEntity productEntity = ProductEntity.builder().price(10).name("hello").build();
        ProductDTO result = ProductMapper.toDTO(productEntity);
        ProductDTO expected = ProductDTO.builder().price(10).name("hello").build();

        Assertions.assertEquals(expected, result);
    }

}

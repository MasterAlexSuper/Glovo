package org.example.glovo.service;

import org.example.glovo.dto.ProductDTO;
import org.example.glovo.entity.ProductEntity;
import org.example.glovo.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class ProductServiceTest {

    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    @Test
    public void findAllTest() {

        ProductService productService = new ProductService(productRepository);

        List<ProductEntity> productEntities = List.of(ProductEntity.builder().id(1).build(), ProductEntity.builder().id(2).build());
        List<ProductDTO> productDTOs = List.of(ProductDTO.builder().id(1).build(), ProductDTO.builder().id(2).build());

        Mockito.when(productRepository.findAll()).thenReturn(productEntities);

        Assertions.assertEquals(productDTOs, productService.findAll());

        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        ProductService productService = new ProductService(productRepository);
        int id = 1;

        ProductDTO dto = ProductDTO.builder().id(id).build();
        ProductEntity entity = ProductEntity.builder().id(id).build();

        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(entity));

        Assertions.assertEquals(dto, productService.findById(id));
    }

    @Test
    public void saveTest() {
        ProductService productService = new ProductService(productRepository);
        int id = 1;

        ProductDTO dto = ProductDTO.builder().id(id).build();
        ProductEntity entity = ProductEntity.builder().id(id).build();

        Mockito.when(productRepository.save(any(ProductEntity.class))).thenReturn(entity);

        Assertions.assertEquals(dto, productService.save(dto));

        Mockito.verify(productRepository, Mockito.times(1)).save(any(ProductEntity.class));
    }
}

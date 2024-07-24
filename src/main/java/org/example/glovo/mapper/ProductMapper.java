package org.example.glovo.mapper;

import org.example.glovo.dto.ProductDTO;
import org.example.glovo.entity.ProductEntity;

public class ProductMapper {

    public static ProductEntity toEntity(ProductDTO dto) {
        return ProductEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }

    public static ProductDTO toDTO(ProductEntity entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }
}

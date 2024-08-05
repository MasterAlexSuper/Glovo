package org.example.glovo.mapper;

import org.example.glovo.dto.ItemDTO;
import org.example.glovo.entity.ItemEntity;

public class ItemMapper {

    public static ItemEntity toEntity(ItemDTO dto) {
        return ItemEntity.builder()
                .id(dto.getId())
                .quantity(dto.getQuantity())
                .build();
    }

    public static ItemDTO toDto(ItemEntity entity) {
        return ItemDTO.builder()
                .quantity(entity.getQuantity())
                .id(entity.getId())
                .productId(entity.getProduct().getId())
                .build();
    }
}
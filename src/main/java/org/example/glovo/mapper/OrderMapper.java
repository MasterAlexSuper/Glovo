package org.example.glovo.mapper;

import org.example.glovo.dto.OrderDTO;
import org.example.glovo.entity.ItemEntity;
import org.example.glovo.entity.OrderEntity;


public class OrderMapper {

    public static OrderEntity toEntity(OrderDTO dto) {
        return OrderEntity.builder()
                .username(dto.getUsername())
                .description(dto.getDescription())
                .build();

    }

    public static OrderDTO toDTO(OrderEntity entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .description(entity.getDescription())
                .creationDate(entity.getCreationDate())
                .itemsIds(entity.getItems() != null ? entity.getItems().stream().map(ItemEntity::getId).toList() : null)
                .build();
    }
}

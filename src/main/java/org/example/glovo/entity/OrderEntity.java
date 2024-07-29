package org.example.glovo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String description;
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
    @OneToMany(mappedBy = "order")
    private List<ItemEntity> items;
}

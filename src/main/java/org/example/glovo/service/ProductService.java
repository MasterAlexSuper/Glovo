package org.example.glovo.service;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.ProductDTO;
import org.example.glovo.mapper.ProductMapper;
import org.example.glovo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(ProductMapper::toDTO).toList();
    }

    public ProductDTO findById(int id) {
        return productRepository.findById(id).map(ProductMapper::toDTO).orElseThrow();
    }

    public ProductDTO save(ProductDTO productDTO) {
        return ProductMapper.toDTO(productRepository.save(ProductMapper.toEntity(productDTO)));
    }
}
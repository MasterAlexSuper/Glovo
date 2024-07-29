package org.example.glovo.controller;

import lombok.AllArgsConstructor;
import org.example.glovo.dto.ProductDTO;
import org.example.glovo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductDTO add(@RequestBody ProductDTO product) {
        return productService.save(product);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }
}

package org.example.glovo;

import lombok.Data;

import java.util.List;


@Data
public class Order {

    private int id;
    private String customerName;
    private List<String> products;


    public Order(int id, String customerName, List<String> products) {
        this.id = id;
        this.customerName = customerName;
        this.products = products;
    }
}
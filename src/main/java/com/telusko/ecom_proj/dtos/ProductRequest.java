package com.telusko.ecom_proj.dtos;

public record ProductRequest(
        String name,
        String brand,
        String description,
        String price,
        String category,
        String quantity,
        String releaseDate,
        boolean available

) {

}

package com.telusko.ecom_proj.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String brand;
    private String description;
    private Long price;
    private String category;
    private int quantity;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING ,  pattern = "dd-MM-yyyy") // change data to string
    private Date releaseDate;
    private boolean available;

    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;

}

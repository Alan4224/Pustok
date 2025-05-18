package com.alan.Pustok.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {
    private int id;

    private int providerId;

    private int bookTypeId;

    @NotEmpty(message = "Tên sách không được để trống")
    private String name;

    @DecimalMin(value = "0.01", message = "Giá sách phải lớn hơn 0 đồng")
    private BigDecimal price;

    private String image;

    @Min(value = 1, message = "Số lượng phải lớn hơn hoặc bằng 1")
    private Integer quantity;

    private Integer sold = 0;

    private Integer viewed = 0;

    private String introduction;

    private String author;

    private String translator;

    private String publisher;

    private Integer releaseYear;

    private Float weight;

    private String packagingSize;

    private Integer totalPage;

    private String format;
}

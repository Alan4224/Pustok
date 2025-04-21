package com.alan.Pustok.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookTypeDto {
    private int id;
    @NotEmpty(message = "Tên thể loại không được trống")
    private String name;
}

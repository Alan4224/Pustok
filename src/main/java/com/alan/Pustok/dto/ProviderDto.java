package com.alan.Pustok.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProviderDto {
    private int id;
    @NotEmpty(message = "Tên nhà cung cấp không được trống")
    private String name;

    private String image;
}

package com.salesBusinessApi.domain.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    @NotNull
    private int code;

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal valueItem;

    private String description;

    private boolean active = true;

}

package com.business.sales.api.salesBusinessApi.domain.product;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DataUpdateProduct(

        @NotNull
        Long id,

        String name,

        int code,

        BigDecimal valueProduct,

        @Size(min=2, max=200)
        String description
) {
}

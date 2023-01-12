package com.business.sales.api.salesBusinessApi.domain.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

public record DataRegisterProduct(

        @NotBlank(message = "Name can't be null")
        @NotNull
        String name,

        @NotNull(message = "Code can't be null")
        int code,

        @NotNull(message = "Value can't be null")
        BigDecimal valueProduct,

        @Size(min=2, max=200)
        String description
) {
}

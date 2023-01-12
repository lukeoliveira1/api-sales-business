package com.business.sales.api.salesBusinessApi.domain.order;

import jakarta.validation.constraints.NotNull;

public record DataUpdateOrder(

        @NotNull
        Long id,
        String description
) {
}

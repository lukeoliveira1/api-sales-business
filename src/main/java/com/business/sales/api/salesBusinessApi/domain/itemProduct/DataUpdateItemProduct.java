package com.business.sales.api.salesBusinessApi.domain.itemProduct;

import com.business.sales.api.salesBusinessApi.domain.product.Product;
import jakarta.validation.constraints.NotNull;

public record DataUpdateItemProduct(

        @NotNull
        Long id,

        Product product,

        int quantity

) {
}

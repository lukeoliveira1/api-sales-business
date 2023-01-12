package com.business.sales.api.salesBusinessApi.domain.itemProduct;

import com.business.sales.api.salesBusinessApi.domain.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public record DataRegisterItemProduct(

        @ManyToOne(cascade = CascadeType.MERGE)
        @NotNull(message = "Product can't be null")
        Product product,

        @NotNull(message = "Quantity can't be null")
        int quantity

) {
}

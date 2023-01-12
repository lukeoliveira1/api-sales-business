package com.business.sales.api.salesBusinessApi.domain.product;

import java.math.BigDecimal;
import java.util.Date;

public record DataDetailsProduct(Long id, String name, int code, BigDecimal valueProduct, String description) {

    public DataDetailsProduct(Product product) {
        this(product.getId(), product.getName(), product.getCode(), product.getValueProduct(),product.getDescription());
    }

}

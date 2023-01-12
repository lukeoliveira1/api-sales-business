package com.business.sales.api.salesBusinessApi.domain.itemProduct;

import com.business.sales.api.salesBusinessApi.domain.product.Product;


public record DataListItemProduct(Long id, Product product, int quantity) {

    public DataListItemProduct(ItemProduct itemProduct) {
        this(itemProduct.getId(), itemProduct.getProduct(), itemProduct.getQuantity());
    }

}

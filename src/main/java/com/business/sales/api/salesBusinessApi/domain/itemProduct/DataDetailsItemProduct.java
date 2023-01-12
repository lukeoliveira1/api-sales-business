package com.business.sales.api.salesBusinessApi.domain.itemProduct;

import com.business.sales.api.salesBusinessApi.domain.product.Product;

public record DataDetailsItemProduct(Long id, Long product, int quantity) {

    //não tá pegando os dados do produto
    public DataDetailsItemProduct(ItemProduct itemProduct) {
        this(itemProduct.getId(), itemProduct.getProduct().getId(), itemProduct.getQuantity());
    }

}

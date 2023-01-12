package com.business.sales.api.salesBusinessApi.domain.order;

import com.business.sales.api.salesBusinessApi.domain.itemProduct.ItemProduct;

import java.util.List;

public record DataListOrder(Long id, List<ItemProduct> listProducts, float totalValue) {

    public DataListOrder(Order order) {
        this(order.getId(), order.getListProducts(), order.getTotalValue());
    }

}

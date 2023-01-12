package com.business.sales.api.salesBusinessApi.domain.order;

import com.business.sales.api.salesBusinessApi.domain.itemProduct.ItemProduct;

import java.util.List;

public record DataDetailsOrder(Long id, List<ItemProduct> listProducts, float totalValue, String description) {

    public DataDetailsOrder(Order order) {
        this(order.getId(), order.getListProducts(), order.getTotalValue(), order.getDescription());
    }
}

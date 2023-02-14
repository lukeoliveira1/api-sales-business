package com.salesBusinessApi.domain;

import com.salesBusinessApi.domain.cart.Cart;
import com.salesBusinessApi.domain.item.Item;
import com.salesBusinessApi.domain.ordereditem.OrderedItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest {

    @Test
    @DisplayName("Add orderedItem to Cart")
    public void addOrderedItemToCart() {
        var orderedItemTest = createOrderedItem(createItem());

        List list_cart = new ArrayList<OrderedItem>();

        Cart cartTest = new Cart(Long.valueOf(1), 3214, list_cart, 0, "cart test", true);
        cartTest.appendOrderedItemOnCart(orderedItemTest);

        Assertions.assertEquals(cartTest.getListOrderedItems().contains(orderedItemTest), true);
    }

    @Test
    @DisplayName("Remove orderedItem from Cart")
    public void removeOrderedItemFromCart() {
        var orderedItem = createOrderedItem(createItem());

        List list_cart = new ArrayList<OrderedItem>();

        Cart cartTest = new Cart(Long.valueOf(1), 3214, list_cart, 0, "cart test", true);
        cartTest.appendOrderedItemOnCart(orderedItem);
        cartTest.deleteOrderedItemOnCart(orderedItem);

        Assertions.assertEquals(cartTest.getListOrderedItems().isEmpty(),true);
    }

    private Item createItem() {
        Item soccerBall = new Item(Long.valueOf(4), 1564, "soccer ball", BigDecimal.valueOf(50), "profissional ball", true);

        return soccerBall;
    }

    private OrderedItem createOrderedItem(Item item) {
        OrderedItem orderedItem = new OrderedItem(8545, item, 3);

        return orderedItem;
    }
}

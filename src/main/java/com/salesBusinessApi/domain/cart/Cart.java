package com.salesBusinessApi.domain.cart;

import com.salesBusinessApi.domain.ordereditem.OrderedItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private List<OrderedItem> listOrderedItems;

    private float totalValue = 0;

    private String description;

    private boolean active;

    public void appendOrderedItemOnCart(OrderedItem orderedItem) {
        listOrderedItems.add(orderedItem);
        this.totalValue = totalValue + (orderedItem.getItem().getValueItem().floatValue() * orderedItem.getQuantity());
    }

    public void deleteOrderedItemOnCart(OrderedItem orderedItem) {
        listOrderedItems.remove(orderedItem);
        this.totalValue = totalValue - (orderedItem.getItem().getValueItem().floatValue() * orderedItem.getQuantity());
    }

    public void deleteLogically() {
        this.active = false;
    }
}

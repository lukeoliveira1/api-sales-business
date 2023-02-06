package com.salesBusinessApi.domain.ordereditem;

import com.salesBusinessApi.domain.item.Item;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private int quantity;

    @ManyToOne
    private Item item;

    private boolean active;

    public void deleteLogically() {
        this.active = false;
    }

    public OrderedItem(int code, Item item, int quantity) {
        this.code = code;
        this.item = item;
        this.quantity = quantity;
        this.active = true;
    }
}
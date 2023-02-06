package com.salesBusinessApi.domain.ordereditem;

import com.salesBusinessApi.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItemDTO {

    private int code;

    private Item item;

    private int quantity;

    private boolean active = true;

}

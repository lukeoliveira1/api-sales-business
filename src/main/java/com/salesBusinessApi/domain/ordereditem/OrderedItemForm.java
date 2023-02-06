package com.salesBusinessApi.domain.ordereditem;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderedItemForm {
    @NotNull
    private int code;

    @NotNull
    private int codeItem;

    @NotNull
    private int quantity;

    private boolean active = true;
}

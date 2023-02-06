package com.salesBusinessApi.domain.item;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private String name;

    private BigDecimal valueItem;

    private String description;

    private boolean active;

    public void deleteLogically() {
        this.active = false;
    }

}

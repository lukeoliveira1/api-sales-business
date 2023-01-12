package com.business.sales.api.salesBusinessApi.domain.order;

import com.business.sales.api.salesBusinessApi.domain.itemProduct.ItemProduct;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "order")
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //criar equals e hash code em cima do id
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<ItemProduct> listProducts;

    private float totalValue = 0;

    private String description;

    private boolean active;

    public Order(DataRegisterOrder dataRegisterOrder) {
        this.listProducts = new ArrayList<>();
        this.description = dataRegisterOrder.description();
        this.active = true;
    }

    public void addItemProductOnOrder(ItemProduct itemProduct) {
        listProducts.add(itemProduct);
        this.totalValue = totalValue + (itemProduct.getProduct().getValueProduct().floatValue() * itemProduct.getQuantity());
    }

    public void deleteItemProductOnOrder(ItemProduct itemProduct) {
        listProducts.remove(itemProduct);
        this.totalValue = totalValue - (itemProduct.getProduct().getValueProduct().floatValue() * itemProduct.getQuantity());
    }

    public void deleteLogically() {
        this.active = false;
    }

    public void updateInformations(DataUpdateOrder dataUpdateOrder) {
        if(dataUpdateOrder.description() != null) {
            this.description = dataUpdateOrder.description();
        }
    }
}

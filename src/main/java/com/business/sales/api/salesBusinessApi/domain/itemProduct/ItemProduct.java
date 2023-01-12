package com.business.sales.api.salesBusinessApi.domain.itemProduct;

import com.business.sales.api.salesBusinessApi.domain.order.Order;
import com.business.sales.api.salesBusinessApi.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "item_product")
@Table(name = "item_products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //criar equals e hash code em cima do id
public class ItemProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    private Product product;

    private boolean active;

    public ItemProduct(DataRegisterItemProduct dataRegisterItemProduct) {
        this.product = dataRegisterItemProduct.product();
        this.quantity = dataRegisterItemProduct.quantity();
        this.active = true;
    }

    public void deleteLogically() {
        this.active = false;
    }

    public void updateInformations(DataUpdateItemProduct dataUpdateItemProduct) {
        if(dataUpdateItemProduct.product() != null) {
            this.product = dataUpdateItemProduct.product();
        }
        if(dataUpdateItemProduct.quantity() != 0) {
            this.quantity = dataUpdateItemProduct.quantity();
        }
    }
}

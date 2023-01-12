package com.business.sales.api.salesBusinessApi.domain.product;

import com.business.sales.api.salesBusinessApi.domain.itemProduct.ItemProduct;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity(name = "product")
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //criar equals e hash code em cima do id
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int code;

    private BigDecimal valueProduct;

    private String description;

    private boolean active;

    public Product(DataRegisterProduct dataRegisterProduct) {
        this.name = dataRegisterProduct.name();
        this.code = dataRegisterProduct.code();
        this.valueProduct = dataRegisterProduct.valueProduct();
        this.description = dataRegisterProduct.description();
        this.active = true;
    }

    public void deleteLogically() {
        this.active = false;
    }

    public void updateInformations(DataUpdateProduct dataUpdateProduct) {
        if(dataUpdateProduct.name() != null) {
            this.name = dataUpdateProduct.name();
        }
        if(dataUpdateProduct.code() != 0) {
            this.code =dataUpdateProduct.code();
        }
        if(dataUpdateProduct.valueProduct() != null) {
            this.valueProduct = dataUpdateProduct.valueProduct();
        }
        if(dataUpdateProduct.description() != null) {
            this.description =dataUpdateProduct.description();
        }
    }
}

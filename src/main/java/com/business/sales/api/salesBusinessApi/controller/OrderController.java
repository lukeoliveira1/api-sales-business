package com.business.sales.api.salesBusinessApi.controller;

import com.business.sales.api.salesBusinessApi.domain.itemProduct.ItemProduct;
import com.business.sales.api.salesBusinessApi.domain.order.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        var order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<Page<DataListOrder>> list(Pageable pageable) {
        var pageResponse = orderService.list(pageable);

        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/params")
    public ResponseEntity<Page<DataListOrder>> listParams(@PageableDefault(size = 10, sort = {"totalValue"}) Pageable pageable) {
        var pageResponse = orderService.list(pageable);

        return ResponseEntity.ok(pageResponse);
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterOrder dataRegisterOrder, UriComponentsBuilder uriBuilder) {
        var order = orderService.create(dataRegisterOrder);

        //URIComponentsBuilder: classe do Spring que encapsula o endereço do servidor
        var uri = uriBuilder.path("/product/{id}").buildAndExpand(order.getId()).toUri();

        //devolvendo código 201 com ResponseEntity
        //devolvendo cabeçalho location da uri
        //devolvendo uma representação do objeto recém criado
        return ResponseEntity.created(uri).body(new DataDetailsOrder(order));
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateOrder dataUpdateOrder) {
        var order = orderService.update(dataUpdateOrder);

        return ResponseEntity.ok(new DataDetailsOrder(order));
    }

    @PutMapping("/addProduct/{id}/{idItemProduct}")
    @Transactional
    public ResponseEntity addItemOrder(@PathVariable("id") Long id, @PathVariable("idItemProduct") Long idItemProduct) {
        var order = orderService.addItemOrder(id, idItemProduct);

        return ResponseEntity.ok(order);
    }

    @PutMapping("/deleteProduct/{id}/{idItemProduct}")
    @Transactional
    public ResponseEntity deleteItemOrder(@PathVariable("id") Long id, @PathVariable("idItemProduct") Long idItemProduct) {
        var order = orderService.removeItemOrder(id, idItemProduct);

        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        orderService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

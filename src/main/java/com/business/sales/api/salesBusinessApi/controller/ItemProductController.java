package com.business.sales.api.salesBusinessApi.controller;

import com.business.sales.api.salesBusinessApi.domain.itemProduct.DataDetailsItemProduct;
import com.business.sales.api.salesBusinessApi.domain.itemProduct.DataRegisterItemProduct;
import com.business.sales.api.salesBusinessApi.domain.itemProduct.DataUpdateItemProduct;
import com.business.sales.api.salesBusinessApi.domain.itemProduct.ItemProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/itemProduct")
public class ItemProductController {

    @Autowired
    private ItemProductService itemProductService;


    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        var itemProduct = itemProductService.findById(id);
        return ResponseEntity.ok(itemProduct);
    }

    @GetMapping
    public ResponseEntity list(Pageable pageable) {
        var pageResponse = itemProductService.list(pageable);

        return ResponseEntity.ok(pageResponse);
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterItemProduct dataRegisterItemProduct, UriComponentsBuilder uriBuilder) {
        var itemProduct = itemProductService.create(dataRegisterItemProduct);

        //URIComponentsBuilder: classe do Spring que encapsula o endereço do servidor
        var uri = uriBuilder.path("/itemProduct/{id}").buildAndExpand(itemProduct.getId()).toUri();

        //devolvendo código 201 com ResponseEntity
        //devolvendo cabeçalho location da uri
        //devolvendo uma representação do objeto recém criado
        return ResponseEntity.created(uri).body(new DataDetailsItemProduct(itemProduct));
        //o product vem null
    }

    @GetMapping("/params")
    public ResponseEntity listParams(@PageableDefault(size = 10, sort = "product.name") Pageable pageable) {
        var pageResponse = itemProductService.list(pageable);

        return ResponseEntity.ok(pageResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody @Valid DataUpdateItemProduct dataUpdateItemProduct) {
        var itemProduct = itemProductService.update(dataUpdateItemProduct);

        return ResponseEntity.ok(new DataDetailsItemProduct(itemProduct));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        itemProductService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

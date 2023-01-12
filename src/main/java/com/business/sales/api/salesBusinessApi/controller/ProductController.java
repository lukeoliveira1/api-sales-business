package com.business.sales.api.salesBusinessApi.controller;

import com.business.sales.api.salesBusinessApi.domain.product.*;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterProduct dataRegisterProduct, UriComponentsBuilder uriBuilder) {
        var product = productService.create(dataRegisterProduct);

        //URIComponentsBuilder: classe do Spring que encapsula o endereço do servidor
        //endereço da aplicação escondido
        var uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();

        //devolvendo código 201 com ResponseEntity
        //devolvendo cabeçalho location da uri
        //devolvendo uma representação do objeto recém criado
        return ResponseEntity.created(uri).body(new DataDetailsProduct(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        var product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<Page<DataListProduct>> list(Pageable pageable) {
        var pageResponse = productService.list(pageable);

        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/params")
    public ResponseEntity<Page<DataListProduct>> listParams(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var pageResponse = productService.list(pageable);

        return ResponseEntity.ok(pageResponse);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateProduct dataUpdateProduct) {
        var product = productService.update(dataUpdateProduct);

        return ResponseEntity.ok(new DataDetailsProduct(product));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long id) {
        productService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

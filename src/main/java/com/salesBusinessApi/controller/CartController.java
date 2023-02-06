package com.salesBusinessApi.controller;

import com.salesBusinessApi.domain.cart.CartDTO;
import com.salesBusinessApi.domain.cart.CartMapper;
import com.salesBusinessApi.domain.cart.CartService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper mapper;


    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") int code) {
        var cart = cartService.findById(code);
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> list() {
        var list_carts = cartService.list();

        return ResponseEntity.ok(list_carts);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CartDTO> register(@RequestBody @Valid CartDTO cartDTO, UriComponentsBuilder uriBuilder) {
        var cart = cartService.create(cartDTO);

        return ResponseEntity.ok(cart);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<CartDTO> update(@PathVariable("id") int code, @RequestBody @Valid CartDTO cartDTO) {
        var cart = cartService.update(code, cartDTO);

        return ResponseEntity.ok(cart);
    }

    @PutMapping("/addProduct/{id}/{idItemProduct}")
    @Transactional
    public ResponseEntity<CartDTO> addItemOrder(@PathVariable("id") int code, @PathVariable("idItemProduct") int codeItem) {
        var cart = cartService.addItemProductonCart(code, codeItem);

        return ResponseEntity.ok(cart);
    }

    @PutMapping("/deleteProduct/{id}/{idItemProduct}")
    @Transactional
    public ResponseEntity<CartDTO> deleteItemOrder(@PathVariable("id") int code, @PathVariable("idItemProduct") int codeItem) {
        var cart = cartService.dropItemProductonCart(code, codeItem);

        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") int code) {
        cartService.delete(code);

        return ResponseEntity.noContent().build();
    }
}

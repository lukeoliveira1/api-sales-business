package com.salesBusinessApi.controller;

import com.salesBusinessApi.domain.cart.CartDTO;
import com.salesBusinessApi.domain.cart.CartMapper;
import com.salesBusinessApi.domain.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/cart")
@Tag(name = "Cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper mapper;


    @GetMapping("/{codeCart}")
    public ResponseEntity findById(@PathVariable("codeCart") int code) {
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

    @PutMapping("/update/{codeCart}")
    @Transactional
    public ResponseEntity<CartDTO> update(@PathVariable("codeCart") int code, @RequestBody @Valid CartDTO cartDTO) {
        var cart = cartService.update(code, cartDTO);

        return ResponseEntity.ok(cart);
    }

    @Operation(summary = "Add order on cart")
    @PutMapping("/addProduct/{codeCart}/{codeOrderedItem}")
    @Transactional
    public ResponseEntity<CartDTO> addItemOrder(@PathVariable("codeCart") int code, @PathVariable("codeOrderedItem") int codeItem) {
        var cart = cartService.addItemProductonCart(code, codeItem);

        return ResponseEntity.ok(cart);
    }

    @Operation(summary = "Remove order from cart")
    @PutMapping("/deleteProduct/{codeCart}/{codeOrderedItem}")
    @Transactional
    public ResponseEntity<CartDTO> deleteItemOrder(@PathVariable("codeCart") int code, @PathVariable("codeOrderedItem") int codeItem) {
        var cart = cartService.dropItemProductonCart(code, codeItem);

        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/delete/{codeCart}")
    @Transactional
    public ResponseEntity delete(@PathVariable("codeCart") int code) {
        cartService.delete(code);

        return ResponseEntity.noContent().build();
    }
}

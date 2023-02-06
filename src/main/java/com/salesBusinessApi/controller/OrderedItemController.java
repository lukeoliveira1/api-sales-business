package com.salesBusinessApi.controller;

import com.salesBusinessApi.domain.ordereditem.OrderedItemDTO;
import com.salesBusinessApi.domain.ordereditem.OrderedItemForm;
import com.salesBusinessApi.domain.ordereditem.OrderedItemMapper;
import com.salesBusinessApi.domain.ordereditem.OrderedItemService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/ordered-item")
public class OrderedItemController {

    @Autowired
    private OrderedItemService orderedItemService;

    @Autowired
    private OrderedItemMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<OrderedItemDTO> findById(@PathVariable("id") int code) {
        var orderedItem = orderedItemService.findById(code);
        return ResponseEntity.ok(orderedItem);
    }

    @GetMapping
    public ResponseEntity<List<OrderedItemDTO>> list() {
        var list_ordered_item = orderedItemService.list();

        return ResponseEntity.ok(list_ordered_item);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderedItemDTO> register(@RequestBody @Valid OrderedItemForm orderedItemForm, UriComponentsBuilder uriBuilder) {
        var orderedItem = orderedItemService.create(orderedItemForm);

        return ResponseEntity.ok(orderedItem);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrderedItemDTO> update(@PathVariable("id") int code, @RequestBody @Valid OrderedItemForm orderedItemForm) {
        var orderedItem = orderedItemService.update(code, orderedItemForm);

        return ResponseEntity.ok(orderedItem);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") int code) {
        orderedItemService.delete(code);

        return ResponseEntity.noContent().build();
    }
}

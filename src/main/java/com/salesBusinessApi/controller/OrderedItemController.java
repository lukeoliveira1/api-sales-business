package com.salesBusinessApi.controller;

import com.salesBusinessApi.domain.ordereditem.OrderedItemDTO;
import com.salesBusinessApi.domain.ordereditem.OrderedItemForm;
import com.salesBusinessApi.domain.ordereditem.OrderedItemMapper;
import com.salesBusinessApi.domain.ordereditem.OrderedItemService;
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
@RequestMapping("/ordered-item")
@Tag(name = "Order")
public class OrderedItemController {

    @Autowired
    private OrderedItemService orderedItemService;

    @Autowired
    private OrderedItemMapper mapper;

    @GetMapping("/{codeOrderedItem}")
    public ResponseEntity<OrderedItemDTO> findById(@PathVariable("codeOrderedItem") int code) {
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
    @Operation(description = "Add an item and quantity")
    public ResponseEntity<OrderedItemDTO> register(@RequestBody @Valid OrderedItemForm orderedItemForm, UriComponentsBuilder uriBuilder) {
        var orderedItem = orderedItemService.create(orderedItemForm);

        return ResponseEntity.ok(orderedItem);
    }

    @PutMapping("/update/{codeOrderedItem}")
    public ResponseEntity<OrderedItemDTO> update(@PathVariable("codeOrderedItem") int code, @RequestBody @Valid OrderedItemForm orderedItemForm) {
        var orderedItem = orderedItemService.update(code, orderedItemForm);

        return ResponseEntity.ok(orderedItem);
    }

    @DeleteMapping("/delete/{codeOrderedItem}")
    @Transactional
    public ResponseEntity delete(@PathVariable("codeOrderedItem") int code) {
        orderedItemService.delete(code);

        return ResponseEntity.noContent().build();
    }
}

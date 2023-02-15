package com.salesBusinessApi.controller;

import com.salesBusinessApi.domain.item.ItemMapper;
import com.salesBusinessApi.domain.item.ItemDTO;
import com.salesBusinessApi.domain.item.ItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/item")
@Tag(name = "Item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemMapper mapper;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid ItemDTO itemDTO, UriComponentsBuilder uriBuilder) {
        var item = itemService.create(itemDTO);

        return ResponseEntity.ok(item);
    }

    @GetMapping("/{codeItem}")
    public ResponseEntity findById(@PathVariable("codeItem") int code) {
        var item = itemService.findById(code);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> list() {
        var list_item = itemService.list();

        return ResponseEntity.ok(list_item);
    }

    @PutMapping("/update/{codeItem}")
    @Transactional
    public ResponseEntity update(@PathVariable("codeItem") int code, @RequestBody @Valid ItemDTO itemDTO) {
        var item = itemService.update(code, itemDTO);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/delete/{codeItem}")
    @Transactional
    public ResponseEntity delete(@PathVariable("codeItem") int code) {
        itemService.delete(code);

        return ResponseEntity.noContent().build();
    }
}

package com.salesBusinessApi.domain.ordereditem;

import com.salesBusinessApi.domain.item.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedItemService {

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    @Autowired
    private OrderedItemMapper mapper;

    @Autowired
    private ItemRepository itemRepository;

    public OrderedItemDTO create(OrderedItemForm orderedItemForm) {
        var item = itemRepository.findByCode(orderedItemForm.getCodeItem());
        var orderedItem = new OrderedItem(orderedItemForm.getCode(), item, orderedItemForm.getQuantity());
        orderedItemRepository.save(orderedItem);
        return mapper.toDtoFromEntity(orderedItem);
    }

    public OrderedItemDTO findById(int code) {
        var orderedItem = orderedItemRepository.findByCode(code);
        if(orderedItem != null) {
            return mapper.toDtoFromEntity(orderedItem);
        }
        throw new EntityNotFoundException("Entity not found!");
    }

    public List<OrderedItemDTO> list() {
        return orderedItemRepository.findByActiveTrue()
                .stream()
                .map(orderedItem -> mapper.toDtoFromEntity(orderedItem))
                .toList();
    }
    public OrderedItemDTO update(int code, OrderedItemForm orderedItemForm) {
        var orderedItem = orderedItemRepository.findByCode(code);
        if(orderedItem != null) {
            mapper.updateEntityFromForm(orderedItemForm, orderedItem);
            return mapper.toDtoFromEntity(orderedItem);
        }
        throw new EntityNotFoundException("Entity not found!!");

    }
    public void delete(int code) {
        var orderedItem = orderedItemRepository.findByCode(code);
        if(orderedItem != null) {
            orderedItem.deleteLogically();
        orderedItemRepository.saveAndFlush(orderedItem);
        }
    }

}

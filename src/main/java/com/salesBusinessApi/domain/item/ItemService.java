package com.salesBusinessApi.domain.item;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemMapper mapper;

    @Autowired
    private ItemRepository itemRepository;

    public ItemDTO create(ItemDTO itemDTO) {
        var item = mapper.toEntityFromDto(itemDTO);
        itemRepository.save(item);
        return mapper.toDtoFromEntity(item);
    }

    public List<ItemDTO> list() {
        return itemRepository.findAllByActiveTrue()
                .stream()
                .map(item -> mapper.toDtoFromEntity(item))
                .toList();
    }

    public ItemDTO findById(int code) {
        var item = itemRepository.findByCode(code);
        if(item != null) {
            return mapper.toDtoFromEntity(item);
        }
        throw new EntityNotFoundException("Entity not found!");
    }
    public ItemDTO update(int code, ItemDTO itemDTO) {
        var item = itemRepository.findByCode(code);
        if(item != null) {
            mapper.updateEntityFromDto(itemDTO, item);
            return mapper.toDtoFromEntity(item);
        }
        throw new EntityNotFoundException("Entity not found!");
    }

    public void delete(int code) {
        var item = itemRepository.findByCode(code);
        item.deleteLogically();
        itemRepository.saveAndFlush(item);
    }

}

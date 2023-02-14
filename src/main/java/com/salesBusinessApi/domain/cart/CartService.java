package com.salesBusinessApi.domain.cart;

import com.salesBusinessApi.domain.ordereditem.OrderedItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper mapper;

    @Autowired
    private OrderedItemRepository orderedItemRepository;

    public CartDTO create(CartDTO cartDTO) {
        var cart = mapper.toEntityFromDto(cartDTO);
        cartRepository.save(cart);
        return mapper.toDtoFromEntity(cart);
    }

    public CartDTO findById(int code) {
        var cart = cartRepository.findByCode(code);
        if(cart != null) {
            return mapper.toDtoFromEntity(cart);
        }
        throw new EntityNotFoundException("Entity not found!");
    }

    public List<CartDTO> list() {
        return cartRepository.findAllByActiveTrue().
                stream()
                .map(cart -> mapper.toDtoFromEntity(cart))
                .toList();
    }

    public CartDTO update(int code, CartDTO cartDTO) {
        var cart = cartRepository.findByCode(code);
        if(cart != null) {
            mapper.updateEntityFromDto(cartDTO, cart);
            return mapper.toDtoFromEntity(cart);
        }
        throw new EntityNotFoundException("Entity not found!");
    }

    public void delete(int code) {
        var cart = cartRepository.findByCode(code);

        cart.deleteLogically();
        cartRepository.saveAndFlush(cart);
    }

    public CartDTO addItemProductonCart(int code, int codeProduct) {
        var cart = cartRepository.findByCode(code);
        var item_quantity = orderedItemRepository.findByCode(codeProduct);

        if(cart != null && item_quantity != null){
            cart.appendOrderedItemOnCart(item_quantity);
            cartRepository.saveAndFlush(cart);
            return mapper.toDtoFromEntity(cart);
        }

        throw new EntityNotFoundException("Entity not found");
    }

    public CartDTO dropItemProductonCart(int code, int codeItem) {
        var cart = cartRepository.findByCode(code);
        var item_quantity = orderedItemRepository.findByCode(codeItem);

        if(cart != null && item_quantity != null){
            cart.deleteOrderedItemOnCart(item_quantity);
            cartRepository.saveAndFlush(cart);
            return mapper.toDtoFromEntity(cart);
        }

        throw new EntityNotFoundException("Entity not found");
    }
}

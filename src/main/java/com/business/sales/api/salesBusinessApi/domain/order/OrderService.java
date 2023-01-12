package com.business.sales.api.salesBusinessApi.domain.order;

import com.business.sales.api.salesBusinessApi.domain.itemProduct.ItemProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemProductRepository itemProductRepository;

    public Order create(@RequestBody @Valid DataRegisterOrder dataRegisterOrder) {
        var order = new Order(dataRegisterOrder);
        return orderRepository.save(order);
    }

    public DataDetailsOrder findById(@PathVariable("id") Long id) {
        var order = orderRepository.findById(id);
        if(order.isPresent()) {
            var entityOrder = order.get();
            return new DataDetailsOrder(entityOrder);
        }
        throw new EntityNotFoundException("Entity not found!");
    }

    public Page<DataListOrder> list(Pageable pageable) {
        return orderRepository.findAllByActiveTrue(pageable).map(DataListOrder::new);
    }

    public Order update(DataUpdateOrder dataUpdateOrder) {
        var order = orderRepository.getReferenceById(dataUpdateOrder.id());
        order.updateInformations(dataUpdateOrder);
        return order;
    }

    public void delete(Long id) {
        var order = orderRepository.getReferenceById(id);

        order.deleteLogically();
        orderRepository.saveAndFlush(order);
    }

    public DataDetailsOrder addItemOrder(Long id, Long idItemProduct) {
        var order = orderRepository.findById(id);
        var item_quantity = itemProductRepository.findById(idItemProduct);

        if(order.isPresent() && item_quantity.isPresent()){
            var entityOrder = order.get();
            entityOrder.addItemProductOnOrder(item_quantity.get());
            orderRepository.saveAndFlush(entityOrder);
            return new DataDetailsOrder(entityOrder);
        }

        throw new EntityNotFoundException("Não encontrado");
    }

    public DataDetailsOrder removeItemOrder(Long id, Long idItemProduct) {
        var order = orderRepository.findById(id);
        var item_quantity = itemProductRepository.findById(idItemProduct);

        if(order.isPresent() && item_quantity.isPresent()){
            var entityOrder = order.get();
            entityOrder.deleteItemProductOnOrder(item_quantity.get());
            orderRepository.saveAndFlush(entityOrder);
            return new DataDetailsOrder(entityOrder);
        }

        throw new EntityNotFoundException("Não encontrado");
    }
}

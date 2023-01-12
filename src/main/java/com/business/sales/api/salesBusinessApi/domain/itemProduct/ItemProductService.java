package com.business.sales.api.salesBusinessApi.domain.itemProduct;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ItemProductService {

    @Autowired
    private ItemProductRepository itemProductRepository;

    public ItemProduct create(@RequestBody @Valid DataRegisterItemProduct dataRegisterItemProduct) {
        var itemProduct = new ItemProduct(dataRegisterItemProduct);
        itemProductRepository.save(itemProduct);
        return itemProduct;
    }

    public DataListItemProduct findById(@PathVariable("id") Long id) {
        var itemProduct = itemProductRepository.findById(id);
        if(itemProduct.isPresent()) {
            var entityItemProduct = itemProduct.get();
            return new DataListItemProduct(entityItemProduct);
        }
        throw new EntityNotFoundException("Entity not found!");
    }

    public Page<DataListItemProduct> list(Pageable pageable) {
        return itemProductRepository.findByActiveTrue(pageable).map(DataListItemProduct::new);
    }
    public ItemProduct update(@RequestBody @Valid DataUpdateItemProduct dataUpdateItemProduct) {
        var itemProduct = itemProductRepository.getReferenceById(dataUpdateItemProduct.id());
        itemProduct.updateInformations(dataUpdateItemProduct);
        return itemProduct;
    }
    public void delete(@PathVariable("id") Long id) {
        var itemProduct = itemProductRepository.findById(id);
        if(itemProduct.isPresent()) {
        var entityProduct = itemProduct.get();
        entityProduct.deleteLogically();
        itemProductRepository.saveAndFlush(entityProduct);
        }
    }

}

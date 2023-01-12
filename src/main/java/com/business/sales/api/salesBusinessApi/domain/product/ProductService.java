package com.business.sales.api.salesBusinessApi.domain.product;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(@RequestBody @Valid DataRegisterProduct dataRegisterProduct) {
        var product = new Product(dataRegisterProduct);
        return productRepository.save(product);
    }

    public Page<DataListProduct> list(Pageable pageable) {
        return productRepository.findAllByActiveTrue(pageable).map(DataListProduct::new);
    }

    public DataDetailsProduct findById(@PathVariable("id") Long id) {
        var product = productRepository.findById(id);
        if(product.isPresent()) {
            var entityProduct = product.get();
            return new DataDetailsProduct(entityProduct);
        }
        throw new EntityNotFoundException("Entity not found!");
    }
    public Product update(@RequestBody @Valid DataUpdateProduct dataUpdateProduct) {
        var product = productRepository.getReferenceById(dataUpdateProduct.id());
        product.updateInformations(dataUpdateProduct);
        return product;
    }

    public void delete(Long id) {
        var product = productRepository.getReferenceById(id);
        product.deleteLogically();
        productRepository.saveAndFlush(product);
    }

}

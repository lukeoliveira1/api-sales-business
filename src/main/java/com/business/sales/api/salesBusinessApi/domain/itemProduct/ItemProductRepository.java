package com.business.sales.api.salesBusinessApi.domain.itemProduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemProductRepository extends JpaRepository<ItemProduct, Long> {

    Page<ItemProduct> findByActiveTrue(Pageable pageable);
}

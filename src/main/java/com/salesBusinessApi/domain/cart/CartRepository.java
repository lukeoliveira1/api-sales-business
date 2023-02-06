package com.salesBusinessApi.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findAllByActiveTrue();

    Cart findByCode(int code);
}

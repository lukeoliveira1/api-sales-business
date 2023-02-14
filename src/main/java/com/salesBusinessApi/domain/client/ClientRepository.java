package com.salesBusinessApi.domain.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ClientRepository extends JpaRepository<Client, Long> {

    UserDetails findByLogin(String login);

}

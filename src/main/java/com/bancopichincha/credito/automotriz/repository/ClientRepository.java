package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}

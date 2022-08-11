package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.CreditApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationRepository extends JpaRepository<CreditApplicationEntity, Long> {
}

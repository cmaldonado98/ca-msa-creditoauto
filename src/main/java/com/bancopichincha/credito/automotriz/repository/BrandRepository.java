package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
}

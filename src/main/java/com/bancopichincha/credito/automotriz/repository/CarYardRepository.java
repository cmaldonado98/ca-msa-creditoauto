package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.CarYardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarYardRepository extends JpaRepository<CarYardEntity, Long> {
}

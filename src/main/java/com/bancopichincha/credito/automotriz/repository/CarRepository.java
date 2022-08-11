package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Long> {
}

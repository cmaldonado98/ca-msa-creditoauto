package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.ExecutiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface ExecutiveRepository extends JpaRepository<ExecutiveEntity, Long> {
    @Query("select (count(e) > 0) from ExecutiveEntity e where e.carYardEntity.idCarYard = ?1")
    boolean existsByCarYardEntity_IdCarYard(@NonNull Long idCarYard);
}

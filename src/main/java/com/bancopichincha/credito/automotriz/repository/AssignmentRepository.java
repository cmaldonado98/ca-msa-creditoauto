package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, Long> {
    @Query("select a from AssignmentEntity a where a.client.idClient = ?1")
    Optional<AssignmentEntity> findByClient_IdClient(@NonNull Long idClient);

    @Query("select (count(a) > 0) from AssignmentEntity a where a.carYardEntity.idCarYard = ?1")
    boolean existsByCarYardEntity_IdCarYard(@NonNull Long idCarYard);


}

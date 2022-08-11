package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.CreditApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CreditApplicationRepository extends JpaRepository<CreditApplicationEntity, Long> {
    @Query("select c from CreditApplicationEntity c where c.clientEntity.idClient = ?1")
    Optional<CreditApplicationEntity> findByClientEntity_IdClient(@NonNull Long idClient);

    @Query("select c from CreditApplicationEntity c where c.carYardEntity.idCarYard = ?1")
    Optional<CreditApplicationEntity> findByCarYardEntity_IdCarYard(@NonNull Long idCarYard);


}

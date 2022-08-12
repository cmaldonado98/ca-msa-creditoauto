package com.bancopichincha.credito.automotriz.repository;

import com.bancopichincha.credito.automotriz.model.entities.CreditApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import java.util.List;

public interface CreditApplicationRepository extends JpaRepository<CreditApplicationEntity, Long> {
    @Query("select (count(c) > 0) from CreditApplicationEntity c where c.clientEntity.idClient = ?1")
    boolean existsByClientEntity_IdClient(@NonNull Long idClient);

    @Query("select (count(c) > 0) from CreditApplicationEntity c where c.carYardEntity.idCarYard = ?1")
    boolean existsByCarYardEntity_IdCarYard(@NonNull Long idCarYard);

    @Query("select (count(c) > 0) from CreditApplicationEntity c where c.carEntity.idCar = ?1")
    boolean existsByCarEntity_IdCar(@NonNull Long idCar);

    @Query("select c from CreditApplicationEntity c where c.clientEntity.idClient = ?1")
    List<CreditApplicationEntity> findByClientEntity_IdClient(@NonNull Long idClient);


}

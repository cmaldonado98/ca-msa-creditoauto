package com.bancopichincha.credito.automotriz.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credit_application")
@FieldDefaults(level = PRIVATE)
public class CreditApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id_credit_application", nullable = false)
    Long idCreditApplication;

    @Column(name = "date_create", nullable = false)
    LocalDateTime dateCreate;

    @Column(name = "months_term", nullable = false)
    Long monthsTerm;

    @Column(name = "fees", nullable = false)
    Double fees;

    @Column(name = "entrance_fee", nullable = false)
    Double entranceFee;

    @Column(name = "observation", nullable = false)
    String observation;

    @Column(name = "status", nullable = false)
    String status;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "id_client")
    ClientEntity clientEntity;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "id_car_yard")
    CarYardEntity carYardEntity;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "id_car", nullable = false)
    CarEntity carEntity;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "id_executive", nullable = false)
    ExecutiveEntity executiveEntity;

}

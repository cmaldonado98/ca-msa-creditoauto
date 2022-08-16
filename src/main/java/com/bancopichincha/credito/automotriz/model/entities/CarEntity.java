package com.bancopichincha.credito.automotriz.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
@FieldDefaults(level = PRIVATE)
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id_car", nullable = false)
    Long idCar;

    @Column(name = "plate", nullable = false, unique = true)
    String plate;

    @Column(name = "model", nullable = false)
    String model;

    @Column(name = "chassis_number", nullable = false)
    String chassisNumber;

    @Column(name = "type")
    String type;

    @Column(name = "displacement", nullable = false)
    Double displacement;

    @Column(name = "appraisal", nullable = false)
    Double appraisal;

    @Column(name = "status", nullable = false)
    String status;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "id_brand", nullable = false)
    BrandEntity brandEntity;

}

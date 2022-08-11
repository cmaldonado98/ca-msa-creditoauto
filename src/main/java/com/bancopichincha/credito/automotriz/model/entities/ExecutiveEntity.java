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
@Table(name = "executive")
@FieldDefaults(level = PRIVATE)
public class ExecutiveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id_executive", nullable = false)
    Long idExecutive;

    @Column(name = "identification", nullable = false, unique = true)
    String identification;

    @Column(name = "names", nullable = false)
    String names;

    @Column(name = "surnames", nullable = false)
    String surnames;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "phone", nullable = false)
    String phone;

    @Column(name = "cellphone", nullable = false)
    String cellphone;

    @Column(name = "age", nullable = false)
    Long age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car_yard")
    CarYardEntity carYardEntity;

}

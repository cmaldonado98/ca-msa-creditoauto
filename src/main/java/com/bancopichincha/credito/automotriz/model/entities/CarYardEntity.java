package com.bancopichincha.credito.automotriz.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_yard")
@FieldDefaults(level = PRIVATE)
public class CarYardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id_car_yard", nullable = false)
    Long idCarYard;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "phone", nullable = false)
    String phone;

    @Column(name = "number_sales_point", nullable = false)
    String numberSalesPoint;

}

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
@Table(name = "assignment")
@FieldDefaults(level = PRIVATE)
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id_assignment", nullable = false)
    Long idAssignment;

    LocalDateTime dateAssignment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", nullable = false, unique = true)
    ClientEntity client;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_car_yard", nullable = false)
    CarYardEntity carYardEntity;

}

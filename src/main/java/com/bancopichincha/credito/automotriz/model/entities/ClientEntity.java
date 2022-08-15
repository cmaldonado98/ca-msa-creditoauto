package com.bancopichincha.credito.automotriz.model.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@FieldDefaults(level = PRIVATE)
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
    @GenericGenerator(name = "seq", strategy="increment")
    @Column(name = "id_client", nullable = false)
    Long idClient;

    @Column(name = "identification", nullable = false, unique = true)
    String identification;

    @Column(name = "names", nullable = false)
    String names;

    @Column(name = "surnames", nullable = false)
    String surnames;

    @Column(name = "age", nullable = false)
    Long age;

    @Column(name = "date_of_birth", nullable = false)
    LocalDateTime dateOfBirth;

    @Column(name = "address", nullable = false)
    String address;

    @Column(name = "marital_status", nullable = false)
    String maritalStatus;

    @Column(name = "spouse_identification", nullable = false)
    String spouseIdentification;

    @Column(name = "spouse_name", nullable = false)
    String spouseName;

}

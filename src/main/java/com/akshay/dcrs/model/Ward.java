package com.akshay.dcrs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "wards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Double latitudeMin;
    private Double latitudeMax;
    private Double longitudeMin;
    private Double longitudeMax;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}

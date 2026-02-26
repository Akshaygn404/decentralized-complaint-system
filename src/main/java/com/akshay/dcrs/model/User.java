package com.akshay.dcrs.model;

import com.akshay.dcrs.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Optional relationship
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Optional relationship
    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}

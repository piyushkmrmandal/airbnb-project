package com.learning.project.airbnbapp.entity;

import com.learning.project.airbnbapp.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(nullable = false, length = 100)
    String name;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(nullable = false)
    Integer age;

}

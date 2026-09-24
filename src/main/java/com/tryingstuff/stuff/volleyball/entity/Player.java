package com.tryingstuff.stuff.volleyball.entity;

import com.tryingstuff.stuff.volleyball.enums.Attendance;
import com.tryingstuff.stuff.volleyball.enums.Gender;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GameAttendance> attendances = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<GameAttendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<GameAttendance> attendances) {
        this.attendances = attendances;
    }
}

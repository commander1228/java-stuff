package com.tryingstuff.stuff.volleyball.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Game {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 255)
    private String opponent;

    @Column(nullable = false, length = 255)
    private String gameTime;

    @Column(nullable = false, length = 255)
    private String gameDate;

    @Column(nullable = false)
    @Positive
    private Long court;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GameAttendance> attendances = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public Long getCourt() {
        return court;
    }

    public void setCourt(Long court) {
        this.court = court;
    }

    public Set<GameAttendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<GameAttendance> attendances) {
        this.attendances = attendances;
    }
}

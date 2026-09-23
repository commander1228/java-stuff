package com.tryingstuff.stuff.volleyball.entity;

import com.tryingstuff.stuff.volleyball.enums.Attendance;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class GameAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Attendance status = Attendance.UNDECIDED;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Attendance getStatus() {
        return status;
    }

    public void setStatus(Attendance status) {
        this.status = status;
    }
}

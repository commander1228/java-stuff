package com.tryingstuff.stuff.guild.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
public class GuildBankSync {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Instant syncTime;

    public Long getId() {
        return id;
    }

    public Instant getSyncTime() {
        return syncTime;
    }
}

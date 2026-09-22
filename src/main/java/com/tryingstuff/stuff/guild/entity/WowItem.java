package com.tryingstuff.stuff.guild.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class WowItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;

    @Positive
    @Column(nullable = false)
    private Long quantity;

    private String qualityType;

    private String qualityName;

    @Column(nullable = false,unique = true)
    private Long blizzardId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "last_sync_id", nullable = false)
    @JsonIgnore
    private GuildBankSync lastSync;

    public Long getId() {
        return id;
    }

    public Long getBlizzardId() {
        return blizzardId;
    }

    public void setBlizzardId(Long blizzardId) {
        this.blizzardId = blizzardId;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getQualityType() {
        return qualityType;
    }

    public void setQualityType(String qualityType) {
        this.qualityType = qualityType;
    }

    public String getQualityName() {
        return qualityName;
    }

    public void setQualityName(String qualityName) {
        this.qualityName = qualityName;
    }

    public GuildBankSync getLastSync() {
        return lastSync;
    }

    public void setLastSync(GuildBankSync lastSync) {
        this.lastSync = lastSync;
    }
}

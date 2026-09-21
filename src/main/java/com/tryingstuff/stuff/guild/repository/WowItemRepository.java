package com.tryingstuff.stuff.guild.repository;

import com.tryingstuff.stuff.guild.entity.GuildBankSync;
import com.tryingstuff.stuff.guild.entity.WowItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WowItemRepository extends JpaRepository<WowItem, Long> {
    void deleteByLastSyncNot(GuildBankSync currentSync);

    Optional<WowItem> findByBlizzardId(Long blizzardId);
}

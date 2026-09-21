package com.tryingstuff.stuff.guild.service;

import java.util.List;
import java.util.Optional;

import com.tryingstuff.stuff.guild.entity.GuildBankSync;
import com.tryingstuff.stuff.guild.entity.WowItem;

public interface WowItemService {
    WowItem saveWowItem(WowItem wowItem);

    List<WowItem> getAllWowItems();

    WowItem getWowItemById(Long id);

    void deleteWowItem(Long id);

    Optional<WowItem> findWowItemByBlizzardId(Long blizzardId);

    void deleteNotSyncedItems(GuildBankSync guildBankSync);

    WowItem upsertWowItem(WowItem wowItemToUpsert);
}

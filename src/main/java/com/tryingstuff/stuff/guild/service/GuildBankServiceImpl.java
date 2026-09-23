package com.tryingstuff.stuff.guild.service;


import com.tryingstuff.stuff.guild.blizzapi.BlizzardApiService;
import com.tryingstuff.stuff.guild.dto.AddOnItem;
import com.tryingstuff.stuff.guild.dto.BlizzardItemResponse;
import com.tryingstuff.stuff.guild.entity.GuildBankSync;
import com.tryingstuff.stuff.guild.entity.WowItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class GuildBankServiceImpl implements GuildBankService {
    private static final Logger logger = LoggerFactory.getLogger(GuildBankServiceImpl.class);

    private final WowItemService wowItemService;
    private final GuildBankSyncService guildBankSyncService;
    private final BlizzardApiService blizzardApiService;

    public GuildBankServiceImpl(
            WowItemService wowItemService,
            GuildBankSyncService guildBankSyncService,
            BlizzardApiService blizzardApiService){
        this.guildBankSyncService = guildBankSyncService;
        this.wowItemService = wowItemService;
        this.blizzardApiService = blizzardApiService;
    }

    @Override
    public WowItem addItemToBank(AddOnItem addOnItem,GuildBankSync guildBankSync) {
        WowItem wowItem = convertToWowItem(addOnItem);
        wowItem.setLastSync(guildBankSync);
        WowItem savedWowItem = wowItemService.upsertWowItem(wowItem);
        return savedWowItem;
    }

    @Override
    public Long addItemsToBank(List<AddOnItem> addOnItems) {
        Long itemsAdded = 0L;
        GuildBankSync guildBankSync = guildBankSyncService.createGuildBankSync();
        for(AddOnItem item : addOnItems){
            try {
                addItemToBank(item, guildBankSync);
                itemsAdded++;
            } catch (IllegalArgumentException | IllegalStateException
                | RestClientException | DataAccessException exception) {
            logger.warn("failed to add: {}", item.blizzardId(), exception);
            }
        }
        wowItemService.deleteNotSyncedItems(guildBankSync);
        return itemsAdded;
    }

    @Override
    public BlizzardItemResponse convertToBlizzItem(Long id) {
        return blizzardApiService.getItemById(id);
    }

    @Override
    public WowItem convertToWowItem(AddOnItem addOnItem) {
        BlizzardItemResponse blizzardItemResponse = convertToBlizzItem(addOnItem.blizzardId());
        WowItem wowItem = new WowItem();
        wowItem.setBlizzardId(blizzardItemResponse.id());
        wowItem.setName(blizzardItemResponse.name());
        wowItem.setQualityType(blizzardItemResponse.quality().type());
        wowItem.setQualityName(blizzardItemResponse.quality().name());
        wowItem.setQuantity(addOnItem.quantity());
        wowItem.setIconUrl(blizzardApiService.getItemIconUrl(wowItem.getBlizzardId()));

        logger.info(
                "Mapped Blizzard item to guild-bank item: blizzardId={}, name={}",
                wowItem.getBlizzardId(),
                wowItem.getName()
        );
        return wowItem;
    }

    @Override
    public List<WowItem> getItems() {
        return wowItemService.getAllWowItems();
    }
}

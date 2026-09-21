package com.tryingstuff.stuff.guild.service;


import com.tryingstuff.stuff.guild.blizzapi.BlizzardApiService;
import com.tryingstuff.stuff.guild.dto.AddOnItem;
import com.tryingstuff.stuff.guild.dto.BlizzardItemResponse;
import com.tryingstuff.stuff.guild.entity.GuildBankSync;
import com.tryingstuff.stuff.guild.entity.WowItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuildBankServiceImpl implements GuildBankService {
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
    public WowItem addItemToBank(AddOnItem addOnItem) {
        GuildBankSync guildBankSync = guildBankSyncService.createGuildBankSync();
        WowItem wowItem = convertToWowItem(addOnItem);
        wowItem.setLastSync(guildBankSync);
        return wowItemService.upsertWowItem(wowItem);
    }

    @Override
    public Long addItemsToBank(List<WowItem> wowItems) {
        return 0L;
    }

    @Override
    public BlizzardItemResponse convertToBlizzItem(Long id) {
        return blizzardApiService.getItemById(id);
    }

    @Override
    public WowItem convertToWowItem(AddOnItem addOnItem) {
        BlizzardItemResponse blizzardItemResponse = convertToBlizzItem(addOnItem.BlizzardId());
        WowItem wowItem = new WowItem();
        wowItem.setBlizzardId(blizzardItemResponse.id());
        wowItem.setName(blizzardItemResponse.name());
        wowItem.setQualityType(blizzardItemResponse.quality().type());
        wowItem.setQualityName(blizzardItemResponse.quality().name());
        wowItem.setQuantity(addOnItem.quantity());

        return wowItem;
    }

    @Override
    public void updateGuildBank(List<AddOnItem> addOnItems) {

    }
}

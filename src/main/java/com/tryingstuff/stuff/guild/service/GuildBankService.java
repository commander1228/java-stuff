package com.tryingstuff.stuff.guild.service;

import com.tryingstuff.stuff.guild.dto.AddOnItem;
import com.tryingstuff.stuff.guild.dto.BlizzardItemResponse;
import com.tryingstuff.stuff.guild.entity.GuildBankSync;
import com.tryingstuff.stuff.guild.entity.WowItem;

import java.util.List;

public interface GuildBankService {

    //kinda admin only, really only gonna use for my right now testing and maybe if we need to add something in there for whatever reason
    //fakes GuildBankSync
    WowItem addItemToBank(AddOnItem addOnItem, GuildBankSync guildBankSync);

    //this one actually does everything, both additem ones responsible for the own
    //bank guild sync though
    Long addItemsToBank(List<AddOnItem> addOnItems);

    BlizzardItemResponse convertToBlizzItem(Long id);

    WowItem convertToWowItem(AddOnItem addOnItem);

    //flow is get list of addonitems convert to blizz item convert to wow item, upsert each item then delete remaining
    List<WowItem> getItems();
}

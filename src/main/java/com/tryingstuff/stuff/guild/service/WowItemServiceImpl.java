package com.tryingstuff.stuff.guild.service;

import com.tryingstuff.stuff.guild.entity.GuildBankSync;
import com.tryingstuff.stuff.guild.entity.WowItem;
import com.tryingstuff.stuff.guild.repository.WowItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WowItemServiceImpl implements WowItemService{
    private final WowItemRepository wowItemRepository;

    public WowItemServiceImpl(
            WowItemRepository wowItemRepository
    ){
        this.wowItemRepository = wowItemRepository;
    }

    @Override
    public WowItem saveWowItem(WowItem wowItem) {
        return  wowItemRepository.save(wowItem);
    }

    @Override
    public List<WowItem> getAllWowItems() {
        return wowItemRepository.findAll();
    }

    @Override
    public WowItem getWowItemById(Long id) {
        return wowItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Wow Item not found with id: " + id));
    }

    @Override
    public void deleteWowItem(Long id) {
        wowItemRepository.deleteById(id);
    }

    @Override
    public Optional<WowItem> findWowItemByBlizzardId(Long blizzardId) {
        return wowItemRepository.findByBlizzardId(blizzardId);
    }

    @Override
    public void deleteNotSyncedItems(GuildBankSync guildBankSync) {
        wowItemRepository.deleteByLastSyncNot(guildBankSync);
    }

    @Override
    public  WowItem upsertWowItem(WowItem wowItemToUpsert) {
        Optional<WowItem> wowItem = findWowItemByBlizzardId(wowItemToUpsert.getBlizzardId());
        wowItem.ifPresent(item -> wowItemToUpsert.setId(item.getId()));
        return saveWowItem(wowItemToUpsert);
    }
}

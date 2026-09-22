package com.tryingstuff.stuff.guild.service;

import com.tryingstuff.stuff.guild.entity.GuildBankSync;
import com.tryingstuff.stuff.guild.entity.WowItem;
import com.tryingstuff.stuff.guild.repository.WowItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WowItemServiceImpl implements WowItemService{
    private static final Logger logger = LoggerFactory.getLogger(WowItemServiceImpl.class);

    private final WowItemRepository wowItemRepository;

    public WowItemServiceImpl(
            WowItemRepository wowItemRepository
    ){
        this.wowItemRepository = wowItemRepository;
    }

    @Override
    public WowItem saveWowItem(WowItem wowItem) {
        logger.info(
                "Persisting guild-bank item: id={}, blizzardId={}, lastSyncId={}",
                wowItem.getId(),
                wowItem.getBlizzardId(),
                wowItem.getLastSync().getId()
        );

        try {
            WowItem savedWowItem = wowItemRepository.save(wowItem);
            logger.info(
                    "Guild-bank item persisted: id={}, blizzardId={}",
                    savedWowItem.getId(),
                    savedWowItem.getBlizzardId()
            );
            return savedWowItem;
        } catch (DataAccessException exception) {
            logger.error(
                    "Failed to persist guild-bank item: id={}, blizzardId={}, lastSyncId={}",
                    wowItem.getId(),
                    wowItem.getBlizzardId(),
                    wowItem.getLastSync().getId(),
                    exception
            );
            throw exception;
        }
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
        if (wowItem.isPresent()) {
            wowItemToUpsert.setId(wowItem.get().getId());
            logger.info(
                    "Updating existing guild-bank item: id={}, blizzardId={}",
                    wowItemToUpsert.getId(),
                    wowItemToUpsert.getBlizzardId()
            );
        } else {
            logger.info(
                    "Creating guild-bank item: blizzardId={}",
                    wowItemToUpsert.getBlizzardId()
            );
        }
        return saveWowItem(wowItemToUpsert);
    }
}

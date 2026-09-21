package com.tryingstuff.stuff.guild.service;

import com.tryingstuff.stuff.guild.entity.GuildBankSync;
import com.tryingstuff.stuff.guild.repository.GuildBankSyncRepository;
import org.springframework.stereotype.Service;

@Service
public class GuildBankSyncServiceImpl implements GuildBankSyncService {
    private final GuildBankSyncRepository guildBankSyncRepository;

    public GuildBankSyncServiceImpl(GuildBankSyncRepository guildBankSyncRepository){
        this.guildBankSyncRepository = guildBankSyncRepository;
    }

    @Override
    public GuildBankSync createGuildBankSync() {
        return guildBankSyncRepository.save(new GuildBankSync());
    }
}

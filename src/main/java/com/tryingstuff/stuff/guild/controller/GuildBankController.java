package com.tryingstuff.stuff.guild.controller;


import com.tryingstuff.stuff.guild.dto.AddOnItem;
import com.tryingstuff.stuff.guild.entity.WowItem;
import com.tryingstuff.stuff.guild.service.GuildBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guild")
public class GuildBankController {
    private final GuildBankService guildBankService;

    @Autowired
    public GuildBankController(GuildBankService guildBankService){
        this.guildBankService = guildBankService;
    }
    
    @GetMapping("/{id}")
    public WowItem addWowItem(@PathVariable AddOnItem addOnItem){
        return guildBankService.addItemToBank(addOnItem);
    }
}

package com.tryingstuff.stuff.guild.controller;


import com.tryingstuff.stuff.guild.dto.AddOnItem;
import com.tryingstuff.stuff.guild.entity.WowItem;
import com.tryingstuff.stuff.guild.service.GuildBankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guild")
public class GuildBankController {
    private final GuildBankService guildBankService;

    @Autowired
    public GuildBankController(GuildBankService guildBankService){
        this.guildBankService = guildBankService;
    }

    @PostMapping("/add")
    public Long addWowItems(@Valid @RequestBody List<AddOnItem> addOnItems){
        return guildBankService.addItemsToBank(addOnItems);
    }

    @GetMapping
    public List<WowItem> getItems() {
        return guildBankService.getItems();
    }
}

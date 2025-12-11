package com.github.cadindev.listener;

import com.github.cadindev.database.repository.DatabaseRepository;
import com.github.cadindev.service.DiscordService;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@RequiredArgsConstructor
public class ChatListener implements Listener {
    private final FileConfiguration configuration;
    private final DatabaseRepository databaseRepository;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();

        databaseRepository.saveMessage(playerName, message);

        DiscordService.sendToChannel(this.configuration.getString("id-canal"),
                this.configuration.getString("message", "")
                        .replace("{name}", playerName)
                        .replace("{message}", message));
    }
}

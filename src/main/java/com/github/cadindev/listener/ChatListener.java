package com.github.cadindev.listener;

import com.github.cadindev.MessagePlugin;
import com.github.cadindev.discord.DiscordBot;
import com.github.cadindev.model.database.DatabaseRepository;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final MessagePlugin PLUGIN;
    private final DatabaseRepository databaseRepository;

    public ChatListener(MessagePlugin plugin, DatabaseRepository databaseRepository) {
        PLUGIN = plugin;
        this.databaseRepository = databaseRepository;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String playerName = event.getPlayer().getName();
        String message = event.getMessage();

        databaseRepository.saveMessage(playerName, message);

        DiscordBot.sendToChannel(PLUGIN.getConfig().getString("id-canal"), message);
    }
}

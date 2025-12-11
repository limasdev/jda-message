package com.github.cadindev;

import com.github.cadindev.discord.DiscordBot;
import org.bukkit.plugin.java.JavaPlugin;

public class MessagePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        try {
            DiscordBot.start(getConfig().getString("token"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

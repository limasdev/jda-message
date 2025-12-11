package com.github.cadindev.service;

import com.github.cadindev.MessagePlugin;
import com.github.cadindev.model.DiscordBot;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public class DiscordService {
    public static void sendToChannel(String channelId, String message) {
        DiscordBot discordBot = MessagePlugin.getInstance().getDiscordBot();
        if (discordBot != null) {
            JDA jda = discordBot.getJda();
            TextChannel channel = jda.getTextChannelById(channelId);
            if (channel != null)
                channel.sendMessage(message).queue();
        }
    }
}
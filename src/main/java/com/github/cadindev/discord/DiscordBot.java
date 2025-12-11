package com.github.cadindev.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class DiscordBot {

    private static JDA jda;

    public static void start(String token) throws Exception {
        jda = JDABuilder.createDefault(token)
                .setBulkDeleteSplittingEnabled(false)
                .build()
                .awaitReady();
    }

    public static void sendToChannel(String channelId, String message) {
        TextChannel channel = jda.getTextChannelById(channelId);
        if (channel != null) {
            channel.sendMessage(message).queue();
        }
    }
}

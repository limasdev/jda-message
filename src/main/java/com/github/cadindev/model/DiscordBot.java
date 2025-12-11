package com.github.cadindev.model;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {
    protected final String token;

    @Getter
    private JDA jda;

    public DiscordBot(String token) {
        this.token = token;
        try {
            jda = JDABuilder.createDefault(token)
                    .setBulkDeleteSplittingEnabled(false)
                    .build()
                    .awaitReady();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

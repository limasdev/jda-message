package com.github.cadindev;

import com.github.cadindev.model.DiscordBot;
import com.github.cadindev.listener.ChatListener;
import com.github.cadindev.database.repository.DatabaseRepository;
import com.github.cadindev.database.mysql.MySQL;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class MessagePlugin extends JavaPlugin {
    @Getter
    protected static MessagePlugin instance;

    private MySQL database;
    private DatabaseRepository repository;
    private DiscordBot discordBot;

    @Override
    public void onLoad() {
        saveDefaultConfig();
        instance = this;
    }

    @Override
    public void onEnable() {
        databaseFactory();
        handleDiscord();

        this.repository = new DatabaseRepository(this.database);
        getServer().getPluginManager().registerEvents(new ChatListener(getConfig(), this.repository), this);
    }

    private void handleDiscord() {
        String token = getConfig().getString("token", "");
        if (token.isEmpty()) {
            getServer().getLogger().severe("Discord token not found in config.yml!");
            getServer().shutdown();
            return;
        }

        this.discordBot = new DiscordBot(token);
    }

    @Override
    public void onDisable() {
        if(this.database != null)
            this.database.disconnect();
    }

    private void databaseFactory() {
        String address = getConfig().getString("database.address");
        int port = getConfig().getInt("database.port");
        String db = getConfig().getString("database.database");
        String user = getConfig().getString("database.username");
        String pass = getConfig().getString("database.password");

        this.database = new MySQL(address, port, db, user, pass);
        this.database.connect();
    }
}

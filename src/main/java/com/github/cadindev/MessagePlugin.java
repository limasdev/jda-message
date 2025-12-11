package com.github.cadindev;

import com.github.cadindev.discord.DiscordBot;
import com.github.cadindev.listener.ChatListener;
import com.github.cadindev.model.database.DatabaseRepository;
import com.github.cadindev.repository.mysql.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public class MessagePlugin extends JavaPlugin {
    private MySQL database;
    private DatabaseRepository repository;

    @Override
    public void onEnable() {
        databaseFactory();

        try {
            DiscordBot.start(getConfig().getString("token"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        repository = new DatabaseRepository(database);
        getServer().getPluginManager().registerEvents(new ChatListener(this, repository), this);

        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        if(database != null) {
            database.disconnect();
        }
    }

    private void databaseFactory() {
        String address = getConfig().getString("database.address");
        int port = getConfig().getInt("database.port");
        String db = getConfig().getString("database.database");
        String user = getConfig().getString("database.username");
        String pass = getConfig().getString("database.password");

        database = new MySQL(address, port, db, user, pass);

        database.connect();
    }

    public static MessagePlugin getInstance() {
        return getPlugin(MessagePlugin.class);
    }
}

package com.github.cadindev.database.repository;

import com.github.cadindev.database.mysql.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseRepository {

    private final MySQL database;

    public DatabaseRepository(MySQL database) {
        this.database = database;
        this.createTable();
    }

    private void createTable() {
        try (Connection conn = database.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     """
                CREATE TABLE IF NOT EXISTS chat_messages (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    player VARCHAR(16) NOT NULL,
                    message TEXT NOT NULL,
                    timestamp BIGINT NOT NULL
                );
                """
             )) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveMessage(String player, String message) {
        try {
            Connection conn = database.getConnection();

            if (conn == null || conn.isClosed()) {
                System.out.println("[MySQL] Conexão fechada. Reconectando...");
                database.connect();
                conn = database.getConnection();
            }

            String sql = "INSERT INTO chat_messages (player, message, timestamp) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, player);
                ps.setString(2, message);
                ps.setLong(3, System.currentTimeMillis());
                ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

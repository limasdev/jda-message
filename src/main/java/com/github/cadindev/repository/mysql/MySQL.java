package com.github.cadindev.repository.mysql;

import com.github.cadindev.repository.Database;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements Database {

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    @Getter
    private Connection connection;

    public MySQL(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }


    @Override
    public void connect() {
        try {
            if (connection != null && !connection.isClosed()) {
                System.out.println("[MySQL] Conexão já está ativa.");
                return;
            }

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database +
                    "?useSSL=false&autoReconnect=true&characterEncoding=utf8";

            connection = DriverManager.getConnection(url, username, password);

            System.out.println("[MySQL] Conexão criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("[MySQL] Erro ao conectar-se:");
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        if (connection == null) {
            System.out.println("[MySQL] Nenhuma conexão para encerrar.");
            return;
        }

        try {
            if (!connection.isClosed()) {
                connection.close();
            }

            connection = null;
            System.out.println("[MySQL] A conexão foi encerrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("[MySQL] Erro ao desconectar:");
            e.printStackTrace();
        }
    }
}

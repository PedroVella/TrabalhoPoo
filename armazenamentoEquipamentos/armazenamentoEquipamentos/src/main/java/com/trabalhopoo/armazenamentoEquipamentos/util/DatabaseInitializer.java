package com.trabalhopoo.armazenamentoEquipamentos.util;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        createTables();
    }

    private void createTables() {
        try {
            // Criar tabela Equipamento
            String createEquipamento = "CREATE TABLE IF NOT EXISTS Equipamento (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "descricao VARCHAR(255), " +
                    "localizacao VARCHAR(100), " +
                    "status VARCHAR(50))";
            
            jdbc.execute(createEquipamento);
            System.out.println("✅ Tabela Equipamento criada/verificada com sucesso!");

            // Criar tabela Produto
            String createProduto = "CREATE TABLE IF NOT EXISTS Produto (" +
                    "id SERIAL PRIMARY KEY, " +
                    "nome VARCHAR(100) NOT NULL, " +
                    "descricao VARCHAR(255), " +
                    "preco DECIMAL(10, 2), " +
                    "quantidade INT)";
            
            jdbc.execute(createProduto);
            System.out.println("✅ Tabela Produto criada/verificada com sucesso!");

        } catch (Exception e) {
            System.err.println("❌ Erro ao criar tabelas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

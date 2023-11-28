package com.bd1.m3.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/aula_banco_de_dados";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "vcdxswzaq1";

    private static final String UNIT_PROCEDURE = "CREATE PROCEDURE InsertUnit( " +
            "    IN unit_floor_param BIGINT, " +
            "    IN unit_number_param BIGINT, " +
            "    in block_param VARCHAR(100) " +
            ") " +
            "BEGIN " +
            "    INSERT INTO unit (unit_floor, unit_number, block) " +
            "    VALUES (unit_floor_param, unit_number_param, block_param); " +
            "END ";

    private static final String DEBTOR_PROCEDURE = "CREATE PROCEDURE InsertDebtor( " +
            "    IN name_param varchar(255), " +
            "    IN mail_param varchar(255), " +
            "    in phone_param BIGINT, " +
            "    in document_number_param BIGINT " +
            ") " +
            "BEGIN " +
            "    INSERT INTO debtor (name, mail, phone, document_number) " +
            "    VALUES (name_param, mail_param, phone_param, document_number_param); " +
            "END ";

    private static final String PAYMENT_PROCEDURE = "CREATE PROCEDURE InsertPayment( " +
            "    in debtor_id_param BIGINT, " +
            "    IN payment_date_param DATETIME, " +
            "    in note_param BLOB, " +
            "    in reference_year_param BIGINT, " +
            "    in reference_month_param BIGINT, " +
            "    in unit_id_param BIGINT " +
            ") " +
            "BEGIN " +
            "    INSERT INTO payment (debtor_id, payment_date, note, reference_year, reference_month, unit_id) " +
            "    VALUES (debtor_id_param, payment_date_param, note_param, reference_year_param, " +
            "   \treference_month_param, unit_id_param); " +
            "END ";

    private static final String DATE_TRIGGER = "CREATE TRIGGER date_insert " +
            "BEFORE INSERT ON payment " +
            "FOR EACH ROW " +
            "BEGIN " +
            " SET NEW.register_date = NOW(); " +
            "end ";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error when connecting to database.", e);
        }
    }

    public void configureDatabase() {
        Connection c = getConnection();
        try {
            dropProcedureIfExists("InsertUnit");
            dropProcedureIfExists("InsertDebtor");
            dropProcedureIfExists("InsertPayment");
            dropTriggerIfExists();

            PreparedStatement psUnit = c.prepareStatement(UNIT_PROCEDURE);
            PreparedStatement psDebtor = c.prepareStatement(DEBTOR_PROCEDURE);
            PreparedStatement psPayment = c.prepareStatement(PAYMENT_PROCEDURE);
            PreparedStatement psTrigger = c.prepareStatement(DATE_TRIGGER);
            psUnit.execute();
            psDebtor.execute();
            psPayment.execute();
            psTrigger.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Error configuring database.", e);
        }
    }

    private void dropTriggerIfExists() throws SQLException {
        Connection c = getConnection();
        String dropProcedure = "DROP TRIGGER IF EXISTS date_insert" ;
        PreparedStatement statement = c.prepareStatement(dropProcedure);
        statement.execute();
        System.out.println("Dropped trigger date_insert because it already exists in the database.");
    }

    private void dropProcedureIfExists(String procedureName) throws SQLException {
        Connection c = getConnection();
        String dropProcedure = "DROP PROCEDURE IF EXISTS " + procedureName;
        PreparedStatement statement = c.prepareStatement(dropProcedure);
        statement.execute();
        System.out.println("Dropped procedure " + procedureName + " because it already exists in the database.");
    }
}

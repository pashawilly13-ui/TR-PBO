package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    // PASTIKAN SERVICE XAMPP/WAMP/Laragon Anda sudah berjalan (khususnya MySQL)!
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver MySQL terbaru
    private static final String URL = "jdbc:mysql://localhost:3306/db_pbotr"; // Nama database: tr_pbo
    private static final String USER = "root"; // User default XAMPP/WAMP
    private static final String PASSWORD = ""; // Password default XAMPP/WAMP
    
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // 1. Muat Driver secara eksplisit 
                Class.forName(DRIVER); 
                // 2. Buat koneksi baru
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Koneksi Database Berhasil! (tr_pbo)");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC tidak ditemukan. Pastikan Anda sudah menambahkan Library MySQL Connector/J.");
                throw new SQLException("Driver Database Error: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Koneksi Database Gagal!");
                System.err.println("Penyebab: " + e.getMessage());
                throw e; // Lemparkan exception
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Koneksi Database Ditutup.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
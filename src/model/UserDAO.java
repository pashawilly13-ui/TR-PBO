// File: model/UserDAO.java
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Metode untuk mendapatkan koneksi dari KoneksiDB.java
    private Connection getConnection() throws SQLException {
        return KoneksiDB.getConnection();
    }

    /**
     * Memvalidasi kredensial pengguna dan mengambil data user terkait.
     * @param username Username yang diinput.
     * @param password Password yang diinput (plain text).
     * @return Objek User jika login berhasil, null jika gagal.
     */
    public User login(String username, String password) {
        User user = null;
        
        // QUERY UTAMA: Cek username & password di tabel user
        // Catatan: Di aplikasi nyata, gunakan hashing untuk password!
        String sql = "SELECT id, username, role FROM user WHERE username = ? AND password = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("id");
                    String role = rs.getString("role");
                    
                    // Panggil helper untuk mendapatkan identifier spesifik (NIM/NIDN/ID Admin)
                    String identifier = getSpecificIdentifier(conn, userId, role);
                    
                    if (identifier != null) {
                       user = new User(userId, username, role, identifier);
                    }
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Database Error saat login: " + e.getMessage());
        }
        return user;
    }

    /**
     * Metode Helper untuk mendapatkan NIM/NIDN/ID Admin berdasarkan role dan user_id.
     */
    private String getSpecificIdentifier(Connection conn, int userId, String role) throws SQLException {
        String sql = "";
        String column = "";
        
        switch (role.toLowerCase()) {
            case "mahasiswa":
                sql = "SELECT nim FROM mahasiswa WHERE user_id = ?";
                column = "nim";
                break;
            case "dosen":
                sql = "SELECT nidn FROM dosen WHERE user_id = ?";
                column = "nidn";
                break;
            case "admin":
                sql = "SELECT id FROM admin WHERE user_id = ?";
                column = "id"; // Menggunakan ID dari tabel admin sebagai identifier
                break;
            default:
                return null;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Mengambil data sebagai String
                    return rs.getString(column); 
                }
            }
        }
        return null; 
    }
}
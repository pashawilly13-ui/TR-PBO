// File: model/User.java
package model;

/**
 * Merepresentasikan data user dari tabel 'user' dan 
 * menyimpan ID spesifik (NIM/NIDN/ID Admin) untuk kemudahan navigasi.
 */
public class User {
    private int id;
    private String username;
    private String role; // Nilai: 'admin', 'dosen', 'mahasiswa'
    private String identifier; // NIM untuk Mahasiswa, NIDN untuk Dosen, atau ID Admin

    // Constructor untuk menyimpan hasil login
    public User(int id, String username, String role, String identifier) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.identifier = identifier;
    }

    // --- Getters and Setters ---
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    /**
     * Mengambil NIM, NIDN, atau ID Admin dari user yang berhasil login.
     */
    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }
}
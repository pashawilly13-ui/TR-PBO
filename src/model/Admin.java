// File: model/Admin.java
package model;

public class Admin {
    private int id; // Kolom 'id' di tabel admin
    private int userId; // FK ke tabel user
    private String nama;
    private String email;

    public Admin() {}

    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
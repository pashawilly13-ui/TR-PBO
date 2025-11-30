// File: model/Tagihan.java
package model;

import java.util.Date;

public class Tagihan {
    private int id; // Kolom 'id' di tabel tagihan
    private String keterangan; // Kolom 'keterangan'
    private int jumlah; // Kolom 'jumlah' (tipe INT di DB)
    private String status; // Kolom 'status' (berisi 'belum' atau 'lunas')
    private Date dueDate; // Kolom 'due_date' (gunakan java.util.Date atau java.sql.Date)

    // Constructor default
    public Tagihan() {}

    // --- Getters and Setters ---
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
}
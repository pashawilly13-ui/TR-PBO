// File: model/LogTagihanView.java
package model;

import java.util.Date;
import java.math.BigDecimal;

public class LogTagihanView {
    private String nim;
    private String namaMahasiswa;
    private String keterangan;
    private int jumlah;
    private String status; // 'belum' atau 'lunas'
    private Date dueDate;
    private Date tanggalBayar;

    public LogTagihanView() {}
    
    // --- Getters and Setters ---
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
    public String getNamaMahasiswa() { return namaMahasiswa; }
    public void setNamaMahasiswa(String namaMahasiswa) { this.namaMahasiswa = namaMahasiswa; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String keterangan) { this.keterangan = keterangan; }
    public int getJumlah() { return jumlah; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public Date getTanggalBayar() { return tanggalBayar; }
    public void setTanggalBayar(Date tanggalBayar) { this.tanggalBayar = tanggalBayar; }
}
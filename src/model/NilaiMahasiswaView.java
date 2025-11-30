// File: model/NilaiMahasiswaView.java
package model;

import java.math.BigDecimal;

public class NilaiMahasiswaView {
    private String nim;
    private String namaMahasiswa;
    private BigDecimal nilaiAngka;
    private String nilaiHuruf;
    private String statusSaatIni; 
    
    // Kunci untuk operasi DAO (ID)
    private int mahasiswaId; 
    private int matakuliahId;
    
    public NilaiMahasiswaView() {}
    
    // --- Getters and Setters ---
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
    public String getNamaMahasiswa() { return namaMahasiswa; }
    public void setNamaMahasiswa(String namaMahasiswa) { this.namaMahasiswa = namaMahasiswa; }
    public BigDecimal getNilaiAngka() { return nilaiAngka; }
    public void setNilaiAngka(BigDecimal nilaiAngka) { this.nilaiAngka = nilaiAngka; }
    public String getNilaiHuruf() { return nilaiHuruf; }
    public void setNilaiHuruf(String nilaiHuruf) { this.nilaiHuruf = nilaiHuruf; }
    public String getStatusSaatIni() { return statusSaatIni; }
    public void setStatusSaatIni(String statusSaatIni) { this.statusSaatIni = statusSaatIni; }
    public int getMahasiswaId() { return mahasiswaId; }
    public void setMahasiswaId(int mahasiswaId) { this.mahasiswaId = mahasiswaId; }
    public int getMatakuliahId() { return matakuliahId; }
    public void setMatakuliahId(int matakuliahId) { this.matakuliahId = matakuliahId; }
}
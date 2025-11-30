// File: model/Nilai.java
package model;

import java.math.BigDecimal; // Digunakan untuk menangani tipe data DECIMAL di SQL

public class Nilai {
    private int id; // Kolom 'id' di tabel nilai
    private String kodeMatkul; // Diperoleh dari join matakuliah.kode
    private String namaMatkul; // Diperoleh dari join matakuliah.nama
    private String nilaiHuruf; // Kolom 'nilai_huruf'
    private BigDecimal nilaiAngka; // Kolom 'nilai_angka' (Sangat disarankan menggunakan BigDecimal untuk presisi)
    private String semester; // Kolom 'semester'
    private String tahunAjaran; // Kolom 'tahun_ajaran'

    // Constructor default
    public Nilai() {}

    // --- Getters and Setters ---
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getKodeMatkul() { return kodeMatkul; }
    public void setKodeMatkul(String kodeMatkul) { this.kodeMatkul = kodeMatkul; }
    
    public String getNamaMatkul() { return namaMatkul; }
    public void setNamaMatkul(String namaMatkul) { this.namaMatkul = namaMatkul; }
    
    public String getNilaiHuruf() { return nilaiHuruf; }
    public void setNilaiHuruf(String nilaiHuruf) { this.nilaiHuruf = nilaiHuruf; }
    
    public BigDecimal getNilaiAngka() { return nilaiAngka; }
    public void setNilaiAngka(BigDecimal nilaiAngka) { this.nilaiAngka = nilaiAngka; }
    
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    
    public String getTahunAjaran() { return tahunAjaran; }
    public void setTahunAjaran(String tahunAjaran) { this.tahunAjaran = tahunAjaran; }
}
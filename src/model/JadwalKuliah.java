// File: model/JadwalKuliah.java
package model;

import java.sql.Time;
import java.util.Date;

public class JadwalKuliah {
    private int id; 
    private int matakuliahId; 
    private String kodeMatkul; // Field tambahan dari JOIN
    private String namaMatkul; // Field tambahan dari JOIN
    private String tahunAjaran;
    private String semester;
    private String kelasGroup;
    private String hari;
    private Time waktuMulai; 
    private Time waktuSelesai;
    private String ruangan;

    public JadwalKuliah() {}

    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMatakuliahId() { return matakuliahId; }
    public void setMatakuliahId(int matakuliahId) { this.matakuliahId = matakuliahId; }
    public String getKodeMatkul() { return kodeMatkul; }
    public void setKodeMatkul(String kodeMatkul) { this.kodeMatkul = kodeMatkul; }
    public String getNamaMatkul() { return namaMatkul; }
    public void setNamaMatkul(String namaMatkul) { this.namaMatkul = namaMatkul; }
    public String getTahunAjaran() { return tahunAjaran; }
    public void setTahunAjaran(String tahunAjaran) { this.tahunAjaran = tahunAjaran; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public String getKelasGroup() { return kelasGroup; }
    public void setKelasGroup(String kelasGroup) { this.kelasGroup = kelasGroup; }
    public String getHari() { return hari; }
    public void setHari(String hari) { this.hari = hari; }
    public Time getWaktuMulai() { return waktuMulai; }
    public void setWaktuMulai(Time waktuMulai) { this.waktuMulai = waktuMulai; }
    public Time getWaktuSelesai() { return waktuSelesai; }
    public void setWaktuSelesai(Time waktuSelesai) { this.waktuSelesai = waktuSelesai; }
    public String getRuangan() { return ruangan; }
    public void setRuangan(String ruangan) { this.ruangan = ruangan; }
}
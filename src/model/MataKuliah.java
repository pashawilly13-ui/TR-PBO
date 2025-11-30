// File: model/MataKuliah.java
package model;

public class MataKuliah {
    private int id; // Kolom 'id' di tabel matakuliah
    private String kode; // Kolom 'kode'
    private String nama; // Kolom 'nama'
    private int sks; // Kolom 'sks'
    private int semester; // Kolom 'semester'

    // Properti Tambahan untuk keperluan Tampilan Jadwal (meskipun kolom ini mungkin
    // berasal dari hasil JOIN atau tabel lain)
    private String hari;     
    private String waktu;    
    private String ruangan;  

    // Constructor default
    public MataKuliah() {}

    // --- Getters and Setters ---
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public int getSks() { return sks; }
    public void setSks(int sks) { this.sks = sks; }
    
    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }
    
    public String getHari() { return hari; }
    public void setHari(String hari) { this.hari = hari; }
    
    public String getWaktu() { return waktu; }
    public void setWaktu(String waktu) { this.waktu = waktu; }
    
    public String getRuangan() { return ruangan; }
    public void setRuangan(String ruangan) { this.ruangan = ruangan; }
}
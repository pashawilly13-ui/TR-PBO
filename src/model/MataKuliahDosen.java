package model;

public class MataKuliahDosen { // Nama kelas diubah
    private int id; 
    private String kode; 
    private String nama; 
    private int sks; 
    private int semester; 

    // --- Atribut Tambahan untuk Jadwal Kuliah ---
    private String hari;     
    private String waktu; 
    private String ruangan;  
    private String kelasGroup; 
    
    public MataKuliahDosen() {}
    
    // --- Getters and Setters (Semua disesuaikan menggunakan MataKuliahDosen) ---
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
    
    // Jadwal Getters/Setters
    public String getHari() { return hari; }
    public void setHari(String hari) { this.hari = hari; }
    public String getWaktu() { return waktu; }
    public void setWaktu(String waktu) { this.waktu = waktu; }
    public String getRuangan() { return ruangan; }
    public void setRuangan(String ruangan) { this.ruangan = ruangan; }
    public String getKelasGroup() { return kelasGroup; }
    public void setKelasGroup(String kelasGroup) { this.kelasGroup = kelasGroup; }
}
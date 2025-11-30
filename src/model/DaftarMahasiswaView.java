package model;

public class DaftarMahasiswaView {
    private String nim;
    private String namaMahasiswa;
    private String kodeMatkul;
    private String namaMatkul;
    private String semesterAmbil; 

    public DaftarMahasiswaView() {}

    // --- Getters and Setters ---
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
    public String getNamaMahasiswa() { return namaMahasiswa; }
    public void setNamaMahasiswa(String namaMahasiswa) { this.namaMahasiswa = namaMahasiswa; }
    public String getKodeMatkul() { return kodeMatkul; }
    public void setKodeMatkul(String kodeMatkul) { this.kodeMatkul = kodeMatkul; }
    public String getNamaMatkul() { return namaMatkul; }
    public void setNamaMatkul(String namaMatkul) { this.namaMatkul = namaMatkul; }
    public String getSemesterAmbil() { return semesterAmbil; }
    public void setSemesterAmbil(String semesterAmbil) { this.semesterAmbil = semesterAmbil; }
}
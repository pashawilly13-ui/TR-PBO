// File: model/Mahasiswa.java
package model;

import java.util.Date; 

public class Mahasiswa {
    private int id; // Kolom 'id' di tabel mahasiswa (Primary Key)
    private String nim; // Kolom 'nim'
    private String nama; // Kolom 'nama'
    private String email; // Kolom 'email'
    private String noHandphone; // Kolom 'no_handphone' (disimpan sebagai String)
    private String alamat; // Kolom 'alamat'
    private String fakultas; // Kolom 'fakultas'
    private String programStudi; // Kolom 'program_studi'
    
    private double ipk; // Properti tambahan yang dihitung oleh DAO (Model Service)

    // Constructor default
    public Mahasiswa() {}

    // Constructor dengan parameter
    public Mahasiswa(int id, String nim, String nama) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
    }

    // --- Getters and Setters ---
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNim() { return nim; }
    public void setNim(String nim) { this.nim = nim; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getNoHandphone() { return noHandphone; }
    public void setNoHandphone(String noHandphone) { this.noHandphone = noHandphone; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getFakultas() { return fakultas; }
    public void setFakultas(String fakultas) { this.fakultas = fakultas; }

    public String getProgramStudi() { return programStudi; }
    public void setProgramStudi(String programStudi) { this.programStudi = programStudi; }

    public double getIpk() { return ipk; }
    public void setIpk (double ipk) {this.ipk = ipk;}
}
package model;

public class Dosen {
    private int id; 
    private int userId; 
    private String nidn; 
    private String nama; 
    private String programStudi; 
    private String email; 
    private String noHandphone; 

    public Dosen() {}
    
    // --- Getters and Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getNidn() { return nidn; }
    public void setNidn(String nidn) { this.nidn = nidn; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getProgramStudi() { return programStudi; }
    public void setProgramStudi(String programStudi) { this.programStudi = programStudi; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNoHandphone() { return noHandphone; }
    public void setNoHandphone(String noHandphone) { this.noHandphone = noHandphone; }
}
package controller;

import model.*;
import java.util.List;

public class AdminController {
    
    private final AdminDAO dao;
    
    public AdminController() {
        this.dao = new AdminDAO();
    }
    
    public Admin getProfilAdmin(int userId) {
        return dao.getAdminByUserId(userId);
    }
    
    public List<Mahasiswa> getAllMahasiswa() { return dao.getAllMahasiswa(); }
    
    public boolean tambahMahasiswa(String nim, String nama, String prodi, String fakultas, String hp, String email, String alamat) {
        Mahasiswa m = new Mahasiswa();
        m.setNim(nim); m.setNama(nama); m.setProgramStudi(prodi); m.setFakultas(fakultas);
        m.setNoHandphone(hp); m.setEmail(email); m.setAlamat(alamat);
        return dao.insertMahasiswa(m);
    }
    
    public boolean updateMahasiswa(String nimLama, String nama, String prodi, String fakultas, String hp, String email, String alamat) {
        Mahasiswa m = new Mahasiswa();
        m.setNama(nama); m.setProgramStudi(prodi); m.setFakultas(fakultas);
        m.setNoHandphone(hp); m.setEmail(email); m.setAlamat(alamat);
        return dao.updateMahasiswa(nimLama, m);
    }
    
    public boolean hapusMahasiswa(String nim) { return dao.deleteMahasiswa(nim); }
    
    public List<Dosen> getAllDosen() { return dao.getAllDosen(); }
    
    public boolean tambahDosen(String nidn, String nama, String prodi, String email, String hp) {
        Dosen d = new Dosen();
        d.setNidn(nidn); d.setNama(nama); d.setProgramStudi(prodi); d.setEmail(email); d.setNoHandphone(hp);
        return dao.insertDosen(d);
    }
    
    public boolean updateDosen(String nidnLama, String nama, String prodi, String email, String hp) {
        Dosen d = new Dosen();
        d.setNama(nama); d.setProgramStudi(prodi); d.setEmail(email); d.setNoHandphone(hp);
        return dao.updateDosen(nidnLama, d);
    }
    
    public boolean hapusDosen(String nidn) { return dao.deleteDosen(nidn); }
    
    public List<MataKuliah> getAllMataKuliah() { return dao.getAllMataKuliah(); }
    
    public boolean tambahMataKuliah(String kode, String nama, int sks, int semester) {
        MataKuliah mk = new MataKuliah();
        mk.setKode(kode); mk.setNama(nama); mk.setSks(sks); mk.setSemester(semester);
        return dao.insertMataKuliah(mk);
    }
    
    public boolean updateMataKuliah(String kodeLama, String nama, int sks, int semester) {
        MataKuliah mk = new MataKuliah();
        mk.setNama(nama); mk.setSks(sks); mk.setSemester(semester);
        return dao.updateMataKuliah(kodeLama, mk);
    }
    
    public boolean hapusMataKuliah(String kode) { return dao.deleteMataKuliah(kode); }
    
    public List<User> getAllUsers() { return dao.getAllUsers(); }
    public boolean tambahUser(String u, String p, String r) { return dao.insertUserManual(u, p, r); }
    public boolean hapusUser(String username) { return dao.deleteUser(username); }
    public List<LogTagihanView> getLogTagihan() { return dao.getLogTagihan(); }
}

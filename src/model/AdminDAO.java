// File: model/AdminDAO.java
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.math.BigDecimal;

public class AdminDAO {

    private Connection getConnection() throws SQLException {
        // Menggunakan KoneksiDB.java yang sudah ada
        return KoneksiDB.getConnection(); 
    }
    
    // ------------------------------------------------------------------
    // 1. Manajemen Data: CRUD Mahasiswa (Tab Mahasiswa)
    // ------------------------------------------------------------------
    
    /**
     * Mengambil semua data profil mahasiswa.
     */
    public List<Mahasiswa> getAllMahasiswa() {
        // Implementasi SELECT * dari tabel 'mahasiswa'
        return new ArrayList<>(); 
    }
    
    /**
     * Memasukkan Mahasiswa baru dan membuat akun User terkait (Transaksi).
     */
    public boolean insertMahasiswa(Mahasiswa mhs, String passwordUser) {
        // Logika Transaksi: INSERT ke user, dapatkan user_id, INSERT ke mahasiswa.
        return true; 
    }
    
    /**
     * Menghapus Mahasiswa dan akun user terkait (Transaksi).
     */
    public boolean deleteMahasiswa(int mahasiswaId) {
        // Logika Transaksi: Hapus Foreign Key di krs, nilai, tagihan dulu, 
        // lalu DELETE dari mahasiswa, dan terakhir DELETE dari user.
        return true; 
    }
    
    // ------------------------------------------------------------------
    // 2. Manajemen Data: CRUD Dosen (Tab Dosen)
    // ------------------------------------------------------------------
    
    public List<Dosen> getAllDosen() {
        // Implementasi SELECT * dari tabel 'dosen'
        return new ArrayList<>(); 
    }

    public boolean updateDosen(Dosen dosen) {
        // Implementasi UPDATE tabel 'dosen'
        return true;
    }
    
    // ------------------------------------------------------------------
    // 3. Manajemen Data: CRUD Jadwal Kuliah (Tab Jadwal Kuliah)
    // ------------------------------------------------------------------
    
    /**
     * Mengambil semua jadwal kuliah dengan JOIN ke Mata Kuliah.
     */
    public List<JadwalKuliah> getAllJadwalKuliah() {
        List<JadwalKuliah> jadwalList = new ArrayList<>();
        // QUERY: SELECT jk.*, mk.kode, mk.nama FROM jadwal_kuliah jk JOIN matakuliah mk ON jk.matakuliah_id = mk.id
        return jadwalList;
    }
    
    public boolean insertJadwalKuliah(JadwalKuliah jk) {
        // Implementasi INSERT INTO jadwal_kuliah
        return true;
    }
    
    // ------------------------------------------------------------------
    // 4. Manajemen Akses (Tabel user)
    // ------------------------------------------------------------------
    
    /**
     * Mengambil semua akun user.
     */
    public List<User> getAllUsers() {
        // Implementasi SELECT id, username, role FROM user 
        // Note: Controller yang akan JOIN untuk Nama Lengkap
        return new ArrayList<>();
    }
    
    /**
     * Mengupdate password akun user.
     */
    public boolean updatePassword(int userId, String newPassword) {
        // Implementasi UPDATE user SET password = ? WHERE id = ?
        return true;
    }
    
    // ------------------------------------------------------------------
    // 5. Log Aktivitas (Log Tagihan)
    // ------------------------------------------------------------------

    /**
     * Mengambil data log tagihan berdasarkan filter Status dan Tahun Ajaran.
     */
    public List<LogTagihanView> getLogTagihan(String statusFilter, String tahunAjaranFilter) {
        List<LogTagihanView> logList = new ArrayList<>();
        
        // QUERY: JOIN tagihan dan mahasiswa
        String sql = "SELECT t.keterangan, t.jumlah, t.status, t.due_date, t.tanggal_bayar, "
                   + "m.nim, m.nama AS nama_mhs "
                   + "FROM tagihan t JOIN mahasiswa m ON t.mahasiswa_id = m.id "
                   + "WHERE t.tahun_ajaran = ? AND (? IS NULL OR t.status = ?) " // Logika filter
                   + "ORDER BY t.due_date DESC";
        
        // ... (Implementasi eksekusi query dan mapping ke LogTagihanView)
        return logList;
    }
}
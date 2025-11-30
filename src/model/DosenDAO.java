// File: model/DosenDAO.java
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class DosenDAO {

    private Connection getConnection() throws SQLException {
        // Asumsi memanggil KoneksiDB.java
        return KoneksiDB.getConnection();
    }
    
    // ------------------------------------------------------------------
    // 1. Profil Dosen (HomeDosen.java) - IMPLEMENTASI LENGKAP
    // ------------------------------------------------------------------

    public Dosen getProfilDosen(String nidn) {
        Dosen dosen = null;
        // QUERY: Ambil semua data profil dosen berdasarkan NIDN
        String sql = "SELECT * FROM dosen WHERE nidn = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nidn);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    dosen = new Dosen();
                    // Mapping data dari ResultSet ke objek Dosen
                    dosen.setId(rs.getInt("id"));
                    dosen.setUserId(rs.getInt("user_id"));
                    dosen.setNidn(rs.getString("nidn"));
                    dosen.setNama(rs.getString("nama")); 
                    dosen.setProgramStudi(rs.getString("program_studi")); 
                    dosen.setEmail(rs.getString("email"));
                    dosen.setNoHandphone(rs.getString("no_handphone"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil profil Dosen: " + e.getMessage());
        }
        return dosen; 
    }
    
    // ------------------------------------------------------------------
    // 2. Jadwal Dosen (JadwalDosen.java) - CODE LAMA (KARENA SUDAH BENAR)
    // ------------------------------------------------------------------
    
    /**
     * Mengambil Jadwal Kuliah Dosen untuk semester/tahun tertentu.
     */
    public List<MataKuliahDosen> getJadwalDosen(int dosenId, String tahunAjaran, String semester) {
        List<MataKuliahDosen> jadwalList = new ArrayList<>(); 
        
        String sql = "SELECT mk.kode, mk.nama, mk.sks, jk.hari, "
                   + "TIME_FORMAT(jk.waktu_mulai, '%H:%i') AS waktu_mulai, "
                   + "TIME_FORMAT(jk.waktu_selesai, '%H:%i') AS waktu_selesai, "
                   + "jk.ruangan, jk.kelas_group "
                   + "FROM matakuliah mk JOIN jadwal_kuliah jk ON mk.id = jk.matakuliah_id "
                   + "WHERE mk.dosen_id = ? AND jk.tahun_ajaran = ? AND jk.semester = ? "
                   + "ORDER BY FIELD(jk.hari, 'Senin', 'Selasa', 'Rabu', 'Kamis', 'Jumat', 'Sabtu'), jk.waktu_mulai"; 

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, dosenId);
            stmt.setString(2, tahunAjaran);
            stmt.setString(3, semester);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MataKuliahDosen mk = new MataKuliahDosen();
                    mk.setKode(rs.getString("kode"));
                    mk.setNama(rs.getString("nama"));
                    mk.setSks(rs.getInt("sks"));
                    mk.setHari(rs.getString("hari"));
                    mk.setWaktu(rs.getString("waktu_mulai") + " - " + rs.getString("waktu_selesai"));
                    mk.setRuangan(rs.getString("ruangan"));
                    mk.setKelasGroup(rs.getString("kelas_group"));
                    jadwalList.add(mk);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil jadwal dosen: " + e.getMessage());
        }
        return jadwalList;
    }
    
    // ------------------------------------------------------------------
    // 3. Daftar Mahasiswa (DaftarMahasiswaDosen.java) - CODE LAMA
    // ------------------------------------------------------------------
    
    /**
     * Mengambil daftar Mahasiswa untuk Matkul, Semester, dan Kelas tertentu.
     */
    public List<DaftarMahasiswaView> getDaftarMahasiswaFiltered(int dosenId, String tahunAjaran, String kodeMatkul, String kelasGroup) {
        List<DaftarMahasiswaView> daftarMhs = new ArrayList<>();
        
        String sql = "SELECT m.nim, m.nama, mk.kode, mk.nama AS nama_matkul, kr.semester "
                   + "FROM mahasiswa m JOIN krs kr ON m.id = kr.mahasiswa_id "
                   + "JOIN matakuliah mk ON kr.matakuliah_id = mk.id "
                   + "JOIN jadwal_kuliah jk ON kr.jadwal_id = jk.id " 
                   + "WHERE mk.dosen_id = ? AND kr.tahun_ajaran = ? "
                   + "AND mk.kode = ? AND jk.kelas_group = ? "; 
        
        // ... (Implementasi eksekusi query dan mapping ke list DaftarMahasiswaView)
        return daftarMhs;
    }

    // ------------------------------------------------------------------
    // 4. Input Nilai (NilaiDosen.java) - CODE LAMA
    // ------------------------------------------------------------------
    
    /**
     * Mengambil daftar Mahasiswa dan Nilai mereka.
     */
    public List<NilaiMahasiswaView> getDaftarNilaiMahasiswa(int matakuliahId, String kelasGroup, String tahunAjaran, String semester) {
        List<NilaiMahasiswaView> listNilai = new ArrayList<>();
        
        String sql = "SELECT m.id AS mhs_id, m.nim, m.nama, n.nilai_huruf, n.nilai_angka "
                   + "FROM mahasiswa m JOIN krs k ON m.id = k.mahasiswa_id "
                   + "LEFT JOIN nilai n ON k.mahasiswa_id = n.mahasiswa_id AND k.matakuliah_id = n.matakuliah_id "
                   + "JOIN jadwal_kuliah jk ON k.jadwal_id = jk.id " 
                   + "WHERE k.matakuliah_id = ? AND jk.kelas_group = ? AND k.tahun_ajaran = ? AND k.semester = ?";
        
        // ... (Implementasi eksekusi query dan mapping ke list NilaiMahasiswaView)
        return listNilai;
    }
    
    /**
     * Menyimpan atau Mengupdate Nilai.
     */
    public boolean simpanUpdateNilai(int mahasiswaId, int matakuliahId, String nilaiHuruf, BigDecimal nilaiAngka, String semester, String tahunAjaran) {
        // Implementasi logika INSERT/UPDATE pada tabel 'nilai'
        return true; 
    }
    
    // ------------------------------------------------------------------
    // 5. Helper Methods (untuk ComboBox Filters) - CODE LAMA
    // ------------------------------------------------------------------
    
    /**
     * Mengambil daftar Mata Kuliah yang diampu dosen (untuk dropdown Matkul).
     */
    public List<MataKuliahDosen> getMataKuliahDiampu(int dosenId, String tahunAjaran, String semester) {
        // Implementasi query SELECT dan GROUP BY kode matkul dan kelas group
        return new ArrayList<>(); 
    }
    
    /**
     * Mengambil daftar Kelas/Group unik yang diampu Dosen.
     */
    public List<String> getDaftarKelasDiampu(int dosenId, String kodeMatkul, String tahunAjaran, String semester) {
        // Implementasi query SELECT DISTINCT kelas_group dari jadwal_kuliah
        return new ArrayList<>();
    }
    
    /**
     * Helper Method: Mendapatkan ID Dosen (dosen.id) berdasarkan NIDN.
     */
    public int getDosenIdByNidn(String nidn) {
        String sql = "SELECT id FROM dosen WHERE nidn = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nidn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil ID Dosen: " + e.getMessage());
        }
        return -1;
    }
}
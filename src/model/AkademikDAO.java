// File: model/AkademikDAO.java
package model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AkademikDAO {

    // Metode internal untuk mendapatkan koneksi dari KoneksiDB.java
    private Connection getConnection() throws SQLException {
        // Karena KoneksiDB ada di package model, kita bisa memanggilnya langsung
        return KoneksiDB.getConnection();
    }
    
    /**
     * Helper Method: Mendapatkan ID Mahasiswa (mahasiswa.id) berdasarkan NIM.
     * Ini penting karena tabel krs, nilai, dan tagihan menggunakan ID, bukan NIM.
     */
    private int getMahasiswaId(Connection conn, String nim) throws SQLException {
        String sql = "SELECT id FROM mahasiswa WHERE nim = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nim);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1; // Kembalikan -1 jika NIM tidak ditemukan
    }
    
    // ------------------------------------------------------------------
    // 0. Model untuk Menu "Home" (Profil Mahasiswa)
    // ------------------------------------------------------------------

    /**
     * Mengambil semua data profil mahasiswa berdasarkan NIM.
     */
    public Mahasiswa getProfilMahasiswa(String nim) {
        Mahasiswa mahasiswa = null;
        String sql = "SELECT id, nim, nama, email, no_handphone, alamat, fakultas, program_studi "
                   + "FROM mahasiswa WHERE nim = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nim);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mahasiswa = new Mahasiswa();
                    // Mapping data dari ResultSet ke objek Mahasiswa (sesuai Home.jpg)
                    mahasiswa.setId(rs.getInt("id"));
                    mahasiswa.setNim(rs.getString("nim"));
                    mahasiswa.setNama(rs.getString("nama"));
                    mahasiswa.setEmail(rs.getString("email"));
                    
                    // Kolom no_handphone bertipe INT di SQL, kita ambil sebagai String
                    mahasiswa.setNoHandphone(rs.getString("no_handphone")); 
                    
                    mahasiswa.setAlamat(rs.getString("alamat"));
                    mahasiswa.setFakultas(rs.getString("fakultas"));
                    mahasiswa.setProgramStudi(rs.getString("program_studi"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil profil mahasiswa: " + e.getMessage());
        }
        return mahasiswa; 
    }

    // ------------------------------------------------------------------
    // 1. Model untuk Menu "Regis Matkul"
    // ------------------------------------------------------------------
    
    /**
     * Mengambil daftar mata kuliah yang tersedia untuk registrasi di semester/tahun tertentu.
     */
    public List<MataKuliah> getMatkulTersedia(String nim, String semesterAktif, String tahunAjaran) {
        List<MataKuliah> matkulList = new ArrayList<>();
        
        // Query disesuaikan untuk join dengan KRS melalui ID Mahasiswa
        // MENCARI matakuliah yang TIDAK ADA di KRS mahasiswa saat ini
        String sql = "SELECT m.id, m.kode, m.nama, m.sks, m.semester "
                   + "FROM matakuliah m "
                   + "WHERE m.semester = ? AND m.id NOT IN ("
                   + "  SELECT matakuliah_id FROM krs WHERE mahasiswa_id = ("
                   + "    SELECT id FROM mahasiswa WHERE nim = ?" // Subquery untuk mendapatkan mahasiswa_id
                   + "  ) AND tahun_ajaran = ?"
                   + ")";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, semesterAktif);
            stmt.setString(2, nim);
            stmt.setString(3, tahunAjaran);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MataKuliah mk = new MataKuliah();
                    mk.setId(rs.getInt("id"));
                    mk.setKode(rs.getString("kode"));
                    mk.setNama(rs.getString("nama"));
                    mk.setSks(rs.getInt("sks"));
                    mk.setSemester(rs.getInt("semester"));
                    matkulList.add(mk);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil matkul tersedia: " + e.getMessage());
        }
        return matkulList;
    }
    
    /**
     * Menyimpan mata kuliah yang dipilih ke tabel KRS.
     */
    public boolean simpanKRS(String nim, int matakuliahId, String semester, String tahunAjaran) {
        String sql = "INSERT INTO krs (mahasiswa_id, matakuliah_id, semester, tahun_ajaran) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            int mhsId = getMahasiswaId(conn, nim);
            if (mhsId == -1) return false;

            stmt.setInt(1, mhsId);
            stmt.setInt(2, matakuliahId);
            stmt.setString(3, semester);
            stmt.setString(4, tahunAjaran);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error saat menyimpan KRS: " + e.getMessage());
            return false;
        }
    }

    // ------------------------------------------------------------------
    // 2. Model untuk Menu "Jadwal"
    // ------------------------------------------------------------------
    
    /**
     * Mengambil jadwal kuliah mahasiswa yang sudah diregistrasi.
     * Catatan: Karena tabel Anda tidak memiliki kolom hari/waktu/ruangan (Jadwal.jpg),
     * query ini hanya mengambil mata kuliah yang diambil.
     */
    public List<MataKuliah> getJadwalKuliah(String nim, String tahunAjaran) {
        List<MataKuliah> jadwalList = new ArrayList<>();
        String sql = "SELECT mk.kode, mk.nama, mk.sks, mk.semester "
                   + "FROM krs kr JOIN matakuliah mk ON kr.matakuliah_id = mk.id "
                   + "WHERE kr.mahasiswa_id = ("
                   + "  SELECT id FROM mahasiswa WHERE nim = ?"
                   + ") AND kr.tahun_ajaran = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nim);
            stmt.setString(2, tahunAjaran);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MataKuliah mk = new MataKuliah();
                    mk.setKode(rs.getString("kode"));
                    mk.setNama(rs.getString("nama"));
                    mk.setSks(rs.getInt("sks"));
                    mk.setSemester(rs.getInt("semester"));
                    // Kolom Hari, Waktu, Ruangan harus diisi dari data dummy atau tabel lain jika ada.
                    jadwalList.add(mk);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil jadwal: " + e.getMessage());
        }
        return jadwalList;
    }

    // ------------------------------------------------------------------
    // 3. Model untuk Menu "Nilai"
    // ------------------------------------------------------------------
    
    /**
     * Mengambil transkrip/daftar nilai mahasiswa (Nilai.jpg).
     */
    public List<Nilai> getTranskripNilai(String nim) {
        List<Nilai> nilaiList = new ArrayList<>();
        String sql = "SELECT mk.kode, mk.nama, n.nilai_huruf, n.nilai_angka, n.semester, n.tahun_ajaran "
                   + "FROM nilai n JOIN matakuliah mk ON n.matakuliah_id = mk.id "
                   + "WHERE n.mahasiswa_id = ("
                   + "  SELECT id FROM mahasiswa WHERE nim = ?"
                   + ") ORDER BY n.tahun_ajaran, n.semester";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nim);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Nilai n = new Nilai();
                    n.setKodeMatkul(rs.getString("kode"));
                    n.setNamaMatkul(rs.getString("nama"));
                    n.setNilaiHuruf(rs.getString("nilai_huruf"));
                    // Menggunakan getBigDecimal karena nilai_angka bertipe DECIMAL(5,2)
                    n.setNilaiAngka(rs.getBigDecimal("nilai_angka")); 
                    n.setSemester(rs.getString("semester"));
                    n.setTahunAjaran(rs.getString("tahun_ajaran"));
                    nilaiList.add(n);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil transkrip nilai: " + e.getMessage());
        }
        return nilaiList;
    }

    // ------------------------------------------------------------------
    // 4. Model untuk Menu "Tagihan"
    // ------------------------------------------------------------------
    
    /**
     * Mengambil daftar tagihan pembayaran yang statusnya Belum Lunas (Tagihan.jpg).
     */
    public List<Tagihan> getTagihanBelumLunas(String nim) {
        List<Tagihan> tagihanList = new ArrayList<>();
        String sql = "SELECT id, keterangan, jumlah, status, due_date "
                   + "FROM tagihan WHERE mahasiswa_id = ("
                   + "  SELECT id FROM mahasiswa WHERE nim = ?"
                   + ") AND status = 'belum'"; // Status di DB: 'belum' atau 'lunas'
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nim);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Tagihan t = new Tagihan();
                    t.setId(rs.getInt("id"));
                    t.setKeterangan(rs.getString("keterangan"));
                    t.setJumlah(rs.getInt("jumlah"));
                    t.setStatus(rs.getString("status"));
                    t.setDueDate(rs.getDate("due_date"));
                    tagihanList.add(t);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error saat mengambil tagihan: " + e.getMessage());
        }
        return tagihanList;
    }
    
    // ------------------------------------------------------------------
    // 5. Model untuk Menu "Rekom Galir" (Rekomendasi Gelar/Jalur Riset)
    // ------------------------------------------------------------------

    /**
     * Menghitung Indeks Prestasi Kumulatif (IPK) mahasiswa.
     * Catatan: Query ini memerlukan kolom 'bobot' di tabel nilai atau perhitungan bobot
     * nilai (A=4, B=3, dst.) secara manual di Java, dan SKS dari matakuliah.
     */
    public double hitungIPK(String nim) {
        // Karena skema Anda tidak menyediakan kolom bobot langsung, IPK perlu dihitung
        // dengan mengambil semua nilai dan SKS, lalu menghitungnya di Java.
        
        // **Ini adalah contoh sederhana dengan nilai placeholder, Anda harus
        //   mengimplementasikan logika perhitungan IPK yang sebenarnya.**
        
        try (Connection conn = getConnection()) {
            // Placeholder: Ambil data nilai dan SKS
            // ...
            
            // Logika perhitungan:
            double totalBobotSKS = 0;
            int totalSKS = 0;
            
            // Anda bisa memanggil getTranskripNilai(nim) di sini
            // dan melakukan perhitungan iteratif di Java
            
            return 3.55; // Nilai dummy
        } catch (SQLException e) {
            System.err.println("Error saat menghitung IPK: " + e.getMessage());
            return 0.0;
        }
    }
    
    /**
     * Memberikan rekomendasi jalur riset berdasarkan IPK.
     */
    public String getRekomendasiGelar(String nim) {
        double ipk = hitungIPK(nim);
        
        if (ipk >= 3.75) {
            return "Sangat Direkomendasikan Jalur Riset (Cum Laude). IPK: " + String.format("%.2f", ipk);
        } else if (ipk >= 3.00) {
            return "Memenuhi Syarat Jalur Tesis/Riset. IPK: " + String.format("%.2f", ipk);
        } else {
            return "Dibutuhkan Peningkatan Nilai. IPK: " + String.format("%.2f", ipk);
        }
    }
}
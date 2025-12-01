package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    
    private Connection getConnection() throws SQLException {
        return KoneksiDB.getConnection();
    }

    public Admin getAdminByUserId(int userId) {
        Admin admin = null;
        String sql = "SELECT * FROM admin WHERE user_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUserId(rs.getInt("user_id"));
                admin.setNama(rs.getString("nama"));
                admin.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return admin;
    }
    
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> list = new ArrayList<>();
        String sql = "SELECT * FROM mahasiswa";
        try (Connection conn = getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Mahasiswa m = new Mahasiswa();
                m.setId(rs.getInt("id"));
                m.setUserId(rs.getInt("user_id"));
                m.setNim(rs.getString("nim"));
                m.setNama(rs.getString("nama"));
                m.setProgramStudi(rs.getString("program_studi"));
                m.setFakultas(rs.getString("fakultas"));
                m.setNoHandphone(rs.getString("no_handphone"));
                m.setEmail(rs.getString("email"));
                m.setAlamat(rs.getString("alamat"));
                list.add(m);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean insertMahasiswa(Mahasiswa m) {
    
        String sqlUser = "INSERT INTO user (username, password, role) VALUES (?, '12345', 'mahasiswa')";
        String sqlMhs = "INSERT INTO mahasiswa (user_id, nim, nama, program_studi, fakultas, no_handphone, email, alamat) VALUES (?,?,?,?,?,?,?,?)";
        
        Connection conn = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); 

            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, m.getNim());
            stmtUser.executeUpdate();
            
            int userId = 0;
            ResultSet rs = stmtUser.getGeneratedKeys();
            if (rs.next()) userId = rs.getInt(1);

            PreparedStatement stmtMhs = conn.prepareStatement(sqlMhs);
            stmtMhs.setInt(1, userId);
            stmtMhs.setString(2, m.getNim());
            stmtMhs.setString(3, m.getNama());
            stmtMhs.setString(4, m.getProgramStudi());
            stmtMhs.setString(5, m.getFakultas());
            stmtMhs.setString(6, m.getNoHandphone());
            stmtMhs.setString(7, m.getEmail());
            stmtMhs.setString(8, m.getAlamat());
            stmtMhs.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) {}
            return false;
        } finally {
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ex) {}
        }
    }

    public boolean updateMahasiswa(String nimLama, Mahasiswa m) {
        String sql = "UPDATE mahasiswa SET nama=?, program_studi=?, fakultas=?, no_handphone=?, email=?, alamat=? WHERE nim=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNama());
            stmt.setString(2, m.getProgramStudi());
            stmt.setString(3, m.getFakultas());
            stmt.setString(4, m.getNoHandphone());
            stmt.setString(5, m.getEmail());
            stmt.setString(6, m.getAlamat());
            stmt.setString(7, nimLama);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean deleteMahasiswa(String nim) {
        String sqlGetId = "SELECT user_id FROM mahasiswa WHERE nim = ?";
        String sqlDelUser = "DELETE FROM user WHERE id = ?";
        try (Connection conn = getConnection()) {
            PreparedStatement stmtGet = conn.prepareStatement(sqlGetId);
            stmtGet.setString(1, nim);
            ResultSet rs = stmtGet.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                PreparedStatement stmtDel = conn.prepareStatement(sqlDelUser);
                stmtDel.setInt(1, userId);
                return stmtDel.executeUpdate() > 0;
            }
            return false;
        } catch (SQLException e) { return false; }
    }

    public List<Dosen> getAllDosen() {
        List<Dosen> list = new ArrayList<>();
        String sql = "SELECT * FROM dosen";
        try (Connection conn = getConnection(); ResultSet rs = conn.createStatement().executeQuery(sql)) {
            while (rs.next()) {
                Dosen d = new Dosen();
                d.setId(rs.getInt("id"));
                d.setNidn(rs.getString("nidn"));
                d.setNama(rs.getString("nama"));
                d.setProgramStudi(rs.getString("program_studi"));
                d.setEmail(rs.getString("email"));
                d.setNoHandphone(rs.getString("no_handphone"));
                list.add(d);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean insertDosen(Dosen d) {
        String sqlUser = "INSERT INTO user (username, password, role) VALUES (?, '12345', 'dosen')";
        String sqlDosen = "INSERT INTO dosen (user_id, nidn, nama, program_studi, email, no_handphone) VALUES (?,?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = getConnection(); conn.setAutoCommit(false);
            PreparedStatement stmtUser = conn.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
            stmtUser.setString(1, d.getNidn());
            stmtUser.executeUpdate();
            int userId = 0;
            ResultSet rs = stmtUser.getGeneratedKeys();
            if (rs.next()) userId = rs.getInt(1);

            PreparedStatement stmtDosen = conn.prepareStatement(sqlDosen);
            stmtDosen.setInt(1, userId);
            stmtDosen.setString(2, d.getNidn());
            stmtDosen.setString(3, d.getNama());
            stmtDosen.setString(4, d.getProgramStudi());
            stmtDosen.setString(5, d.getEmail());
            stmtDosen.setString(6, d.getNoHandphone());
            stmtDosen.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            if(conn!=null)try{conn.rollback();}catch(SQLException ex){}
            return false;
        } finally {
            if(conn!=null)try{conn.setAutoCommit(true);conn.close();}catch(SQLException ex){}
        }
    }
    
    public boolean updateDosen(String nidnLama, Dosen d) {
        String sql = "UPDATE dosen SET nama=?, program_studi=?, email=?, no_handphone=? WHERE nidn=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, d.getNama());
            stmt.setString(2, d.getProgramStudi());
            stmt.setString(3, d.getEmail());
            stmt.setString(4, d.getNoHandphone());
            stmt.setString(5, nidnLama);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
    
    public boolean deleteDosen(String nidn) {
        String sqlGetId = "SELECT user_id FROM dosen WHERE nidn = ?";
        String sqlDelUser = "DELETE FROM user WHERE id = ?";
        try (Connection conn = getConnection()) {
            PreparedStatement stmtGet = conn.prepareStatement(sqlGetId);
            stmtGet.setString(1, nidn);
            ResultSet rs = stmtGet.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                PreparedStatement stmtDel = conn.prepareStatement(sqlDelUser);
                stmtDel.setInt(1, userId);
                return stmtDel.executeUpdate() > 0;
            }
            return false;
        } catch (SQLException e) { return false; }
    }

    public List<MataKuliah> getAllMataKuliah() {
        List<MataKuliah> list = new ArrayList<>();
        try (Connection conn = getConnection(); ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM matakuliah")) {
            while (rs.next()) {
                MataKuliah mk = new MataKuliah();
                mk.setId(rs.getInt("id"));
                mk.setKode(rs.getString("kode"));
                mk.setNama(rs.getString("nama"));
                mk.setSks(rs.getInt("sks"));
                mk.setSemester(rs.getInt("semester"));
                list.add(mk);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean insertMataKuliah(MataKuliah mk) {
        String sql = "INSERT INTO matakuliah (kode, nama, sks, semester) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mk.getKode());
            stmt.setString(2, mk.getNama());
            stmt.setInt(3, mk.getSks());
            stmt.setInt(4, mk.getSemester());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
    
    public boolean updateMataKuliah(String kodeLama, MataKuliah mk) {
        String sql = "UPDATE matakuliah SET nama=?, sks=?, semester=? WHERE kode=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mk.getNama());
            stmt.setInt(2, mk.getSks());
            stmt.setInt(3, mk.getSemester());
            stmt.setString(4, kodeLama);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
    
    public boolean deleteMataKuliah(String kode) {
        String sql = "DELETE FROM matakuliah WHERE kode=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kode);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
    
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = getConnection(); ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM user")) {
            while(rs.next()){ 
                list.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("role"), "")); 
            }
        } catch (SQLException e) {}
        return list;
    }
    
    public boolean insertUserManual(String u, String p, String r) {
        try (Connection conn = getConnection(); PreparedStatement s = conn.prepareStatement("INSERT INTO user (username,password,role) VALUES(?,?,?)")) {
            s.setString(1, u); s.setString(2, p); s.setString(3, r);
            return s.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
    
    public boolean deleteUser(String username) {
        try (Connection conn = getConnection(); PreparedStatement s = conn.prepareStatement("DELETE FROM user WHERE username=?")) {
            s.setString(1, username);
            return s.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
    
    public List<LogTagihanView> getLogTagihan() {
        List<LogTagihanView> list = new ArrayList<>();
        String sql = "SELECT t.*, m.nim, m.nama FROM tagihan t JOIN mahasiswa m ON t.mahasiswa_id = m.id";
        try (Connection conn = getConnection(); ResultSet rs = conn.createStatement().executeQuery(sql)) {
            while(rs.next()){
                LogTagihanView log = new LogTagihanView();
                log.setNim(rs.getString("nim"));
                log.setNamaMahasiswa(rs.getString("nama"));
                log.setKeterangan(rs.getString("keterangan"));
                log.setJumlah(rs.getInt("jumlah"));
                log.setStatus(rs.getString("status"));
                log.setTanggalJatuhTempo(rs.getDate("tanggal_jatuh_tempo"));
                log.setTanggalBayar(rs.getDate("tanggal_bayar"));
                list.add(log);
            }
        } catch(SQLException e){}
        return list;
    }
}

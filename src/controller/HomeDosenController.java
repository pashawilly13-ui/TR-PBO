// File: controller/HomeDosenController.java
package controller;

import model.Dosen;
import model.DosenDAO;
import view.HomeDosen; // Import View yang sesuai
import view.JadwalDosen; // Asumsi ada View Jadwal
import view.DaftarMahasiswaDosen; // Asumsi ada View Daftar Mahasiswa
import view.NilaiDosen; // Asumsi ada View Input Nilai
import view.Login; // Asumsi View Login

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeDosenController {
    
    private final HomeDosen view;
    private final DosenDAO dosenDAO;
    private final String nidn; 

    public HomeDosenController(HomeDosen view, String nidn) {
        this.view = view;
        this.nidn = nidn;
        this.dosenDAO = new DosenDAO();
        
        // 1. Muat data profil segera setelah Controller dibuat
        loadDosenProfile();
        
        // 2. Pasang Listener untuk navigasi
        attachNavigationListeners();
    }

    /**
     * Memuat data profil Dosen dari DAO dan menampilkannya di View.
     */
    public void loadDosenProfile() {
        Dosen dsn = dosenDAO.getProfilDosen(nidn);
        if (dsn != null) {
            view.tampilkanDataProfil(dsn);
        } else {
            JOptionPane.showMessageDialog(view, 
                "Gagal memuat data profil dosen. NIDN tidak ditemukan.", 
                "Error Data", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Memasang Action Listener pada semua tombol navigasi di HomeDosen.
     */
    private void attachNavigationListeners() {
        // Tombol Jadwal
        view.getBtnJadwal().addActionListener(e -> navigateToJadwal());
        
        // Tombol Daftar Mahasiswa
        view.getBtnDaftarMahasiswa().addActionListener(e -> navigateToDaftarMahasiswa());
        
        // Tombol Nilai
        view.getBtnNilai().addActionListener(e -> navigateToNilai());

        // Tombol Logout
        view.getBtnLogout().addActionListener(e -> handleLogout());
        
        // Tombol Home (Muat ulang profil)
        view.getBtnHome().addActionListener(e -> loadDosenProfile());
    }

    // --- Metode Navigasi (Membuka Frame Baru) ---
    
    public void navigateToJadwal() {
        // new JadwalDosen(nidn).setVisible(true); // Asumsi constructor JadwalDosen menerima NIDN
    }
    
    public void navigateToDaftarMahasiswa() {
        // new DaftarMahasiswaDosen(nidn).setVisible(true); // Asumsi constructor DaftarMahasiswaDosen menerima NIDN
    }
    
    public void navigateToNilai() {
        // new NilaiDosen(nidn).setVisible(true); // Asumsi constructor NilaiDosen menerima NIDN
    }
    
    public void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(view, "Yakin ingin Logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            // new Login().setVisible(true); // Kembali ke halaman Login
        }
    }
}
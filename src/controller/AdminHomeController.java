// File: controller/AdminHomeController.java
package controller;

import model.Admin;
import model.AdminDAO;
import view.HomeAdmin;
import view.ManajemenAkses; // Asumsi View Manajemen Akses
import view.ManajemenData; // Asumsi View Manajemen Data (JTabbedPane)
import view.LogAktivasi;   // Asumsi View Log Aktivitas

import javax.swing.JOptionPane;

public class AdminHomeController {
    
    private final HomeAdmin view;
    private final AdminDAO adminDAO;
    private final int adminId; 

    public AdminHomeController(HomeAdmin view, int adminId) {
        this.view = view;
        this.adminId = adminId;
        this.adminDAO = new AdminDAO();
        
        loadAdminProfile(); // Muat data profil saat Controller dibuat
        attachNavigationListeners();
    }

    /**
     * Memuat data profil Admin (Nama, Email) dan menampilkannya di View.
     */
    public void loadAdminProfile() {
        
        // Placeholder/Dummy Admin
        Admin admin = new Admin();
        admin.setNama("Admin Utama Sistem");
        admin.setEmail("admin@universitas.ac.id"); 

        if (admin != null) {
            view.tampilkanDataProfil(admin);
        } else {
            JOptionPane.showMessageDialog(view, 
                "Gagal memuat data profil Admin.", 
                "Error Data", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Memasang Action Listener pada semua tombol navigasi di HomeAdmin.
     */
    private void attachNavigationListeners() {
        // Tombol Manajemen Akses
        view.getBtnManajemenAkses().addActionListener(e -> navigateToManajemenAkses());
        
        // Tombol Manajemen Data
        view.getBtnManajemenData().addActionListener(e -> navigateToManajemenData());
        
        // Tombol Log Aktivasi
        view.getBtnLogAktivasi().addActionListener(e -> navigateToLogAktivasi());
        
        // Tombol Home (Muat ulang profil)
        view.getBtnHome().addActionListener(e -> loadAdminProfile());
        
        // Tombol Logout
        view.getBtnLogout().addActionListener(e -> handleLogout());
    }

    // --- Metode Navigasi (Membuka Frame Baru) ---
    
    public void navigateToManajemenAkses() {
        // new ManajemenAkses(adminId).setVisible(true); // Asumsi View ManajemenAkses menerima ID
    }
    
    public void navigateToManajemenData() {
        // new ManajemenData(adminId).setVisible(true); // Asumsi View ManajemenData (JTabbedPane) menerima ID
    }
    
    public void navigateToLogAktivasi() {
        // new LogAktivasi(adminId).setVisible(true); // Asumsi View LogAktivasi menerima ID
    }
    
    public void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(view, "Yakin ingin Logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            // new Login().setVisible(true); // Kembali ke halaman Login
        }
    }
}
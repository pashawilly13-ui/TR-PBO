// File: controller/MahasiswaHomeController.java
package controller;

import model.Mahasiswa;
import model.AkademikDAO;
import view.HomeMahasiswa; // Sesuaikan nama file Home Mahasiswa Anda

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;

public class MahasiswaHomeController {
    private final HomeMahasiswa view;
    private final AkademikDAO akademikDAO;
    private final String nim; // Identifier

    public MahasiswaHomeController(HomeMahasiswa view, String nim) {
        this.view = view;
        this.nim = nim;
        this.akademikDAO = new AkademikDAO();
        
        // Memasang listener navigasi segera setelah Controller dibuat
        attachNavigationListeners();
    }

    /**
     * Memuat data profil mahasiswa dan menampilkannya di View.
     */
    public void loadMahasiswaProfile() {
        Mahasiswa mhs = akademikDAO.getProfilMahasiswa(nim);
        if (mhs != null) {
            view.tampilkanDataProfil(mhs);
        } else {
            JOptionPane.showMessageDialog(view, "Gagal memuat data profil mahasiswa.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Memasang Action Listener untuk semua tombol navigasi di HomeMahasiswa.
     */
    private void attachNavigationListeners() {
        // Asumsi nama tombol: btnRegisMatkul, btnJadwal, btnNilai, btnLogout
        
        view.getBtnRegisMatkul().addActionListener(e -> navigateToRegisMatkul());
        view.getBtnJadwal().addActionListener(e -> navigateToJadwal());
        view.getBtnNilai().addActionListener(e -> navigateToNilai());
        view.getBtnTagihan().addActionListener(e -> navigateToTagihan());
        view.getBtnRekomGalir().addActionListener(e -> navigateToRekomGalir());
        view.getBtnLogout().addActionListener(e -> handleLogout());
    }

    // --- Metode Navigasi ---
    
    public void navigateToRegisMatkul() {
        // new RegisMatkul(nim).setVisible(true); // Asumsi class RegisMatkul ada
    }
    public void navigateToJadwal() {
        // new JadwalMahasiswa(nim).setVisible(true); // Asumsi class JadwalMahasiswa ada
    }
    public void navigateToNilai() {
        // new NilaiMahasiswa(nim).setVisible(true); // Asumsi class NilaiMahasiswa ada
    }
    public void navigateToTagihan() {
        // new TagihanMahasiswa(nim).setVisible(true); // Asumsi class TagihanMahasiswa ada
    }
    public void navigateToRekomGalir() {
        // new RekomGalirMahasiswa(nim).setVisible(true); // Asumsi class RekomGalirMahasiswa ada
    }
    public void handleLogout() {
        // new Login().setVisible(true); // Asumsi class Login ada
        view.dispose();
    }
}
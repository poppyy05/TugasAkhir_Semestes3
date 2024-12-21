import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.util.*;
import java.util.List;

public class PenjualanHPSecond {

    // Data dummy untuk HP yang tersedia
    static List<HP> hpList = new ArrayList<>();

    public static void main(String[] args) {
        // Menambahkan beberapa data HP
        hpList.add(new HP("Samsung Galaxy S10", 5));
        hpList.add(new HP("iPhone 11", 3));
        hpList.add(new HP("Xiaomi Redmi Note 10", 10));

        // Membuat frame utama
        JFrame mainFrame = new JFrame("Penjualan HP Second");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);
        mainFrame.setLocationRelativeTo(null);

        // Menambahkan tombol Pembeli dan Admin
        JPanel panel = new JPanel();
        JButton btnPembeli = new JButton("Pembeli");
        JButton btnAdmin = new JButton("Admin");

        btnPembeli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPembeliWindow();
            }
        });

        btnAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAdminWindow();
            }
        });

        panel.add(btnPembeli);
        panel.add(btnAdmin);
        mainFrame.add(panel);
        mainFrame.setVisible(true);
    }

    // Fungsi untuk menampilkan jendela Pembeli
    private static void showPembeliWindow() {
        JFrame pembeliFrame = new JFrame("Pembeli");
        pembeliFrame.setSize(500, 400);
        pembeliFrame.setLocationRelativeTo(null);

        // Tabel HP yang tersedia
        String[] columnNames = {"Nama HP", "Jumlah"};
        Object[][] data = new Object[hpList.size()][2];

        for (int i = 0; i < hpList.size(); i++) {
            data[i][0] = hpList.get(i).getNamaHP();
            data[i][1] = hpList.get(i).getJumlah();
        }

        JTable hpTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(hpTable);

        // Form untuk input pembelian
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField txtNamaPembeli = new JTextField("Nama Pembeli");
        JComboBox<String> comboBoxHP = new JComboBox<>();
        for (HP hp : hpList) {
            comboBoxHP.addItem(hp.getNamaHP());
        }

        JTextField txtMetodePembayaran = new JTextField("Metode Pembayaran");
        JButton btnBeli = new JButton("Beli");

        btnBeli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String namaPembeli = txtNamaPembeli.getText();
                String tipeHP = comboBoxHP.getSelectedItem().toString();
                String metodePembayaran = txtMetodePembayaran.getText();
                int selectedIndex = comboBoxHP.getSelectedIndex();
                HP selectedHP = hpList.get(selectedIndex);

                if (namaPembeli.isEmpty() || metodePembayaran.isEmpty()) {
                    JOptionPane.showMessageDialog(pembeliFrame, "Semua field harus diisi!");
                } else if (selectedHP.getJumlah() <= 0) {
                    JOptionPane.showMessageDialog(pembeliFrame, "Stok HP tidak tersedia!");
                } else {
                    selectedHP.setJumlah(selectedHP.getJumlah() - 1);  // Kurangi stok
                    JOptionPane.showMessageDialog(pembeliFrame, "Pembelian berhasil!\nPembeli: " + namaPembeli +
                            "\nTipe HP: " + tipeHP + "\nMetode Pembayaran: " + metodePembayaran);
                    pembeliFrame.dispose();  // Menutup window pembeli
                }
            }
        });

        panel.add(scrollPane);
        panel.add(txtNamaPembeli);
        panel.add(comboBoxHP);
        panel.add(txtMetodePembayaran);
        panel.add(btnBeli);

        pembeliFrame.add(panel);
        pembeliFrame.setVisible(true);
    }

    // Fungsi untuk menampilkan jendela Admin
    private static void showAdminWindow() {
        JFrame adminFrame = new JFrame("Admin");
        adminFrame.setSize(500, 400);
        adminFrame.setLocationRelativeTo(null);

        // Tabel HP yang tersedia
        String[] columnNames = {"Nama HP", "Jumlah"};
        Object[][] data = new Object[hpList.size()][2];

        for (int i = 0; i < hpList.size(); i++) {
            data[i][0] = hpList.get(i).getNamaHP();
            data[i][1] = hpList.get(i).getJumlah();
        }

        JTable hpTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(hpTable);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton btnTambahHP = new JButton("Tambah HP");
        JButton btnHapusHP = new JButton("Hapus HP");
        JButton btnKurangiHP = new JButton("Kurangi Stok");

        btnTambahHP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String namaHP = JOptionPane.showInputDialog("Nama HP baru:");
                if (namaHP != null && !namaHP.isEmpty()) {
                    hpList.add(new HP(namaHP, 1));  // Tambah HP dengan stok 1
                    JOptionPane.showMessageDialog(adminFrame, "HP berhasil ditambahkan!");
                    adminFrame.dispose();
                    showAdminWindow();  // Menampilkan ulang daftar HP
                }
            }
        });

        btnHapusHP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String namaHP = JOptionPane.showInputDialog("Nama HP yang ingin dihapus:");
                for (Iterator<HP> iterator = hpList.iterator(); iterator.hasNext();) {
                    HP hp = iterator.next();
                    if (hp.getNamaHP().equals(namaHP)) {
                        iterator.remove();
                        JOptionPane.showMessageDialog(adminFrame, "HP berhasil dihapus!");
                        adminFrame.dispose();
                        showAdminWindow();  // Menampilkan ulang daftar HP
                        return;
                    }
                }
                JOptionPane.showMessageDialog(adminFrame, "HP tidak ditemukan!");
            }
        });

        btnKurangiHP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String namaHP = JOptionPane.showInputDialog("Nama HP untuk mengurangi stok:");
                for (HP hp : hpList) {
                    if (hp.getNamaHP().equals(namaHP)) {
                        String jumlahStr = JOptionPane.showInputDialog("Berapa stok yang ingin dikurangi?");
                        try {
                            int jumlah = Integer.parseInt(jumlahStr);
                            if (jumlah > 0 && hp.getJumlah() >= jumlah) {
                                hp.setJumlah(hp.getJumlah() - jumlah);
                                JOptionPane.showMessageDialog(adminFrame, "Stok berhasil dikurangi!");
                                adminFrame.dispose();
                                showAdminWindow();  // Menampilkan ulang daftar HP
                            } else {
                                JOptionPane.showMessageDialog(adminFrame, "Jumlah stok tidak cukup!");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(adminFrame, "Input jumlah tidak valid!");
                        }
                        return;
                    }
                }
                JOptionPane.showMessageDialog(adminFrame, "HP tidak ditemukan!");
            }
        });

        panel.add(scrollPane);
        panel.add(btnTambahHP);
        panel.add(btnHapusHP);
        panel.add(btnKurangiHP);

        adminFrame.add(panel);
        adminFrame.setVisible(true);
    }

    // Kelas untuk menyimpan data HP
    static class HP {
        private String namaHP;
        private int jumlah;

        public HP(String namaHP, int jumlah) {
            this.namaHP = namaHP;
            this.jumlah = jumlah;
        }

        public String getNamaHP() {
            return namaHP;
        }

        public int getJumlah() {
            return jumlah;
        }

        public void setJumlah(int jumlah) {
            this.jumlah = jumlah;
        }
    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PenjualanHPSecond {

    static List<HP> hpList = new ArrayList<>();
    static List<Pembelian> riwayatPembelian = new ArrayList<>();

    public static void main(String[] args) {

        hpList.add(new HP("Samsung Galaxy S10", 5, "C:/path/to/samsung_galaxy_s10.jpg"));
        hpList.add(new HP("iPhone 11", 3, "C:/Users/damar/Downloads/download (1).jpeg"));
        hpList.add(new HP("Xiaomi Redmi Note 10", 10, "C:/Users/damar/Downloads/id-11134207-7rasm-m25b1sgel08k77.jpeg"));

        JFrame mainFrame = new JFrame("Penjualan HP Second");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 123, 255));
        JLabel titleLabel = new JLabel("Selamat Datang di Penjualan HP Second");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        JButton btnPembeli = new JButton("Pembeli");
        btnPembeli.setFont(new Font("Arial", Font.BOLD, 18));
        btnPembeli.setBackground(new Color(40, 167, 69));
        btnPembeli.setForeground(Color.WHITE);
        btnPembeli.setFocusPainted(false);

        JButton btnAdmin = new JButton("Admin");
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 18));
        btnAdmin.setBackground(new Color(220, 53, 69));
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFocusPainted(false);

        btnPembeli.addActionListener(e -> showPembeliWindow(mainFrame));
        btnAdmin.addActionListener(e -> showAdminLoginWindow(mainFrame));

        buttonPanel.add(btnPembeli);
        buttonPanel.add(btnAdmin);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 240));
        centerPanel.add(buttonPanel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private static void showPembeliWindow(JFrame mainFrame) {
        JFrame pembeliFrame = new JFrame("Pembeli");
        pembeliFrame.setSize(600, 650);
        pembeliFrame.setLocationRelativeTo(null);
        pembeliFrame.setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        String[] columnNames = {"Gambar", "Nama HP", "Jumlah"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        updateHPTable(model);
        JTable hpTable = new JTable(model);
        hpTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        hpTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        hpTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        hpTable.setDefaultRenderer(Object.class, new ImageRenderer());
        JScrollPane scrollPane = new JScrollPane(hpTable);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);

        JLabel lblNamaPembeli = new JLabel("Nama Pembeli:");
        JTextField txtNamaPembeli = new JTextField();

        JLabel lblTipeHP = new JLabel("Tipe HP:");
        JTextField txtTipeHP = new JTextField();

        JLabel lblJumlah = new JLabel("Jumlah:");
        JTextField txtJumlah = new JTextField();

        JLabel lblMetodePembayaran = new JLabel("Metode Pembayaran:");
        JComboBox<String> cbMetodePembayaran = new JComboBox<>(new String[]{"Tunai", "Kartu Kredit", "Transfer Bank"});

        inputPanel.add(lblNamaPembeli);
        inputPanel.add(txtNamaPembeli);
        inputPanel.add(lblTipeHP);
        inputPanel.add(txtTipeHP);
        inputPanel.add(lblJumlah);
        inputPanel.add(txtJumlah);
        inputPanel.add(lblMetodePembayaran);
        inputPanel.add(cbMetodePembayaran);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton btnBeli = new JButton("Beli");
        btnBeli.addActionListener(e -> {
            String namaPembeli = txtNamaPembeli.getText();
            String tipeHP = txtTipeHP.getText();
            String jumlahStr = txtJumlah.getText();
            String metodePembayaran = (String) cbMetodePembayaran.getSelectedItem();

            if (namaPembeli.isEmpty() || tipeHP.isEmpty() || jumlahStr.isEmpty()) {
                JOptionPane.showMessageDialog(pembeliFrame, "Semua field harus diisi!");
                return;
            }

            try {
                int jumlah = Integer.parseInt(jumlahStr);
                boolean isAvailable = false;
                for (HP hp : hpList) {
                    if (hp.getNamaHP().equalsIgnoreCase(tipeHP)) {
                        if (hp.getJumlah() >= jumlah) {
                            hp.setJumlah(hp.getJumlah() - jumlah);
                            riwayatPembelian.add(new Pembelian(namaPembeli, tipeHP, metodePembayaran));
                            JOptionPane.showMessageDialog(pembeliFrame, "Pembelian berhasil!");
                            updateHPTable(model);
                            isAvailable = true;
                        } else {
                            JOptionPane.showMessageDialog(pembeliFrame, "Stok HP tidak mencukupi!");
                        }
                        break;
                    }
                }

                if (!isAvailable) {
                    JOptionPane.showMessageDialog(pembeliFrame, "Tipe HP tidak ditemukan!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(pembeliFrame, "Jumlah harus berupa angka!");
            }
        });

        JButton btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(e -> {
            pembeliFrame.dispose();
            mainFrame.setVisible(true);
        });

        buttonPanel.add(btnBeli);
        buttonPanel.add(btnKembali);

        panel.add(scrollPane, BorderLayout.NORTH);
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        pembeliFrame.add(panel);
        pembeliFrame.setVisible(true);
    }



    private static void updateHPTable(DefaultTableModel model) {
        model.setRowCount(0);
        for (HP hp : hpList) {
            ImageIcon imageIcon = new ImageIcon(hp.getImagePath());
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            model.addRow(new Object[]{new ImageIcon(image), hp.getNamaHP(), hp.getJumlah()});
        }
    }

    private static void showAdminLoginWindow(JFrame mainFrame) {
        JFrame loginFrame = new JFrame("Admin Login");
        loginFrame.setSize(300, 200);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.setBackground(Color.WHITE);

        JLabel lblPassword = new JLabel("Masukkan Password Admin:", JLabel.CENTER);
        JPasswordField txtPassword = new JPasswordField();
        JButton btnLogin = new JButton("Login");

        btnLogin.addActionListener(e -> {
            String password = new String(txtPassword.getPassword());
            if (password.equals("admin123")) {
                loginFrame.dispose();
                showAdminWindow(mainFrame);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Password salah!");
            }
        });

        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);

        loginFrame.add(panel);
        loginFrame.setVisible(true);
    }

    private static void showAdminWindow(JFrame mainFrame) {
        JFrame adminFrame = new JFrame("Admin");
        adminFrame.setSize(600, 500);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        String[] columnNames = {"Gambar", "Nama HP", "Jumlah"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        updateHPTable(model);
        JTable hpTable = new JTable(model);
        hpTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        hpTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        hpTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        hpTable.setDefaultRenderer(Object.class, new ImageRenderer());
        JScrollPane scrollPane = new JScrollPane(hpTable);

        String[] riwayatColumnNames = {"Nama Pembeli", "Tipe HP", "Metode Pembayaran"};
        DefaultTableModel riwayatModel = new DefaultTableModel(riwayatColumnNames, 0);
        updateRiwayatTable(riwayatModel);
        JTable riwayatTable = new JTable(riwayatModel);
        JScrollPane riwayatScrollPane = new JScrollPane(riwayatTable);

        JButton btnTambahHP = new JButton("Tambah HP");
        btnTambahHP.addActionListener(e -> {
            String namaHP = JOptionPane.showInputDialog("Masukkan Nama HP:");
            String jumlahStr = JOptionPane.showInputDialog("Masukkan Jumlah HP:");
            String imagePath = JOptionPane.showInputDialog("Masukkan Path Gambar HP:");
            try {
                int jumlah = Integer.parseInt(jumlahStr);
                if (jumlah > 0) {
                    hpList.add(new HP(namaHP, jumlah, imagePath));
                    JOptionPane.showMessageDialog(adminFrame, "HP berhasil ditambahkan!");
                    updateHPTable(model);
                } else {
                    JOptionPane.showMessageDialog(adminFrame, "Jumlah harus lebih dari 0!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(adminFrame, "Masukkan jumlah yang valid!");
            }
        });

        JButton btnHapusHP = new JButton("Hapus HP");
        btnHapusHP.addActionListener(e -> {
            int selectedRow = hpTable.getSelectedRow();
            if (selectedRow >= 0) {
                hpList.remove(selectedRow);
                JOptionPane.showMessageDialog(adminFrame, "HP berhasil dihapus!");
                updateHPTable(model);
            } else {
                JOptionPane.showMessageDialog(adminFrame, "Pilih HP yang ingin dihapus!");
            }
        });

        JButton btnKembali = new JButton("Kembali");
        btnKembali.addActionListener(e -> {
            adminFrame.dispose();
            mainFrame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 10));
        buttonPanel.add(btnTambahHP);
        buttonPanel.add(btnHapusHP);
        buttonPanel.add(btnKembali);

        panel.add(scrollPane);
        panel.add(new JLabel("Riwayat Pembelian:", JLabel.CENTER));
        panel.add(riwayatScrollPane);
        panel.add(buttonPanel);
        adminFrame.add(panel);
        adminFrame.setVisible(true);
    }

    private static void updateRiwayatTable(DefaultTableModel model) {
        model.setRowCount(0);
        for (Pembelian pembelian : riwayatPembelian) {
            model.addRow(new Object[]{pembelian.getNamaPembeli(), pembelian.getTipeHP(), pembelian.getMetodePembayaran()});
        }
    }
}

class HP {
    private String namaHP;
    private int jumlah;
    private String imagePath;

    public HP(String namaHP, int jumlah, String imagePath) {
        this.namaHP = namaHP;
        this.jumlah = jumlah;
        this.imagePath = imagePath;
    }

    public String getNamaHP() {
        return namaHP;
    }

    public void setNamaHP(String namaHP) {
        this.namaHP = namaHP;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getImagePath() {
        return imagePath;
    }
}

class Pembelian {
    private String namaPembeli;
    private String tipeHP;
    private String metodePembayaran;

    public Pembelian(String namaPembeli, String tipeHP, String metodePembayaran) {
        this.namaPembeli = namaPembeli;
        this.tipeHP = tipeHP;
        this.metodePembayaran = metodePembayaran;
    }

    public String getNamaPembeli() {
        return namaPembeli;
    }

    public String getTipeHP() {
        return tipeHP;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }
}

class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof ImageIcon) {
            return new JLabel((ImageIcon) value);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

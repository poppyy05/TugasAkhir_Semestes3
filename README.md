##  1. PenjualanHPSecond.java
Kelas utama yang mengatur antarmuka pengguna dan logika aplikasi. Kelas ini berisi metode untuk menampilkan jendela pembeli dan admin, serta mengelola interaksi pengguna.

Atribut:

static List<HP> hpList: Menyimpan daftar ponsel yang tersedia.
static List<Pembelian> riwayatPembelian: Menyimpan riwayat pembelian yang dilakukan oleh pembeli.
Metode:

public static void main(String[] args): Titik masuk aplikasi yang menginisialisasi daftar ponsel dan menampilkan jendela utama.

Menambahkan beberapa objek HP ke dalam hpList dengan nama, jumlah, dan path gambar.
Membuat dan menampilkan jendela utama aplikasi.
private static void showPembeliWindow(JFrame mainFrame): Menampilkan jendela untuk pembeli.

Membuat tabel untuk menampilkan daftar ponsel.
Menambahkan komponen untuk memasukkan nama pembeli dan metode pembayaran.
Menangani logika pembelian ponsel.
private static void updateHPTable(DefaultTableModel model): Memperbarui tabel ponsel yang ditampilkan.

Menghapus semua baris yang ada di model tabel.
Menambahkan baris baru untuk setiap objek HP dalam hpList, termasuk gambar yang telah diubah ukurannya.
private static void showAdminLoginWindow(JFrame mainFrame): Menampilkan jendela login untuk admin.

Menangani logika autentikasi admin.
private static void showAdminWindow(JFrame mainFrame): Menampilkan jendela untuk admin setelah login.

Menampilkan tabel ponsel dan riwayat pembelian.
Menangani logika untuk menambah ponsel baru.
private static void updateRiwayatTable(DefaultTableModel model): Memperbarui tabel riwayat pembelian.

Menghapus semua baris yang ada di model tabel.
Menambahkan baris baru untuk setiap objek Pembelian dalam riwayatPembelian.
##  2. HP.java
Kelas yang merepresentasikan objek ponsel dengan atribut nama, jumlah, dan path gambar.

Atribut:

private String namaHP: Nama ponsel.
private int jumlah: Jumlah ponsel yang tersedia.
private String imagePath: Path gambar ponsel.
Metode:

public HP(String namaHP, int jumlah, String imagePath): Konstruktor untuk menginisialisasi objek HP.
public String getNamaHP(): Mengembalikan nama ponsel.
public int getJumlah(): Mengembalikan jumlah ponsel.
public String getImagePath(): Mengembalikan path gambar ponsel.
public void setJumlah(int jumlah): Mengatur jumlah ponsel yang tersedia.
## 3. Pembelian.java
Kelas yang merepresentasikan objek pembelian dengan atribut nama pembeli, tipe ponsel, dan metode pembayaran.

Atribut:

private String namaPembeli: Nama pembeli.
private String tipeHP: Tipe ponsel yang dibeli.
private String metodePembayaran: Metode pembayaran yang digunakan oleh pembeli.
Metode:

public Pembelian(String namaPembeli, String tipeHP, String metodePembayaran): Konstruktor untuk menginisialisasi objek Pembelian.
public String getNamaPembeli(): Mengembalikan nama pembeli.
public String getTipeHP(): Mengembalikan tipe ponsel yang dibeli.
public String getMetodePembayaran(): Mengembalikan metode pembayaran yang digunakan.
##  4. Database.java
Kelas yang bertanggung jawab untuk mengelola penyimpanan data ponsel dan riwayat pembelian. Kelas ini menggunakan file untuk menyimpan data secara permanen.

Atribut:
private static final String FILE_PATH: Path file untuk menyimpan data.
Metode:
public static void saveData(List<HP> hpList, List<Pembelian> riwayatPembelian): Menyimpan daftar ponsel dan riwayat pembelian ke dalam file.
public static void loadData(List<HP> hpList, List<Pembelian> riwayatPembelian): Memuat data dari file ke dalam daftar ponsel dan riwayat pembelian.
Kontribusi
Jika Anda ingin berkontribusi pada proyek ini, silakan fork repositori ini dan buat pull request dengan perubahan yang Anda buat. Pastikan untuk mengikuti pedoman pengkodean yang telah ditetapkan.

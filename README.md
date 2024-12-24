Penjualan HP Second - Aplikasi Penjualan Handphone Second
Aplikasi Penjualan HP Second adalah aplikasi berbasis Java menggunakan Swing yang menyediakan antarmuka grafis untuk membeli dan mengelola stok HP second. Aplikasi ini memiliki dua mode, yaitu Pembeli untuk membeli HP dan Admin untuk mengelola data HP yang dijual.

Fitur
Pembeli:

Pembeli dapat melihat daftar HP yang tersedia beserta gambar dan jumlahnya.
Pembeli dapat mengisi form untuk membeli HP dengan menentukan nama pembeli, tipe HP, dan jumlah yang diinginkan.
Pembeli akan menerima pesan jika pembelian berhasil atau jika stok tidak mencukupi.
Admin:

Admin dapat login dengan password untuk mengakses fitur manajemen.
Admin dapat melihat riwayat pembelian, menambah HP baru, atau menghapus HP yang ada.
Struktur Proyek
1. Kelas Utama: PenjualanHPSecond
Kelas ini adalah kelas utama yang mengatur aplikasi. Berikut adalah bagian-bagian utama di dalamnya:

a. Deklarasi dan Inisialisasi Data
hpList: Daftar HP yang dijual, yang berisi objek HP.
riwayatPembelian: Daftar riwayat pembelian yang berisi objek Pembelian.
b. Metode main
Metode ini adalah titik masuk utama aplikasi yang memulai jendela utama dengan dua tombol:

Pembeli: Mengarahkan pengguna ke tampilan untuk melakukan pembelian HP.
Admin: Mengarahkan pengguna ke tampilan login admin.
c. Metode showPembeliWindow
Menampilkan tampilan untuk pembeli, yang mencakup:

Daftar HP: Menggunakan JTable untuk menampilkan gambar, nama HP, dan jumlah HP yang tersedia.
Form Input: Terdiri dari JTextField untuk nama pembeli, tipe HP, dan jumlah yang diinginkan.
Tombol Beli: Tombol ini akan memproses pembelian HP dengan memvalidasi input dan mengurangi stok HP yang dibeli.
d. Metode showAdminLoginWindow
Menampilkan tampilan login untuk admin. Admin harus memasukkan password yang benar untuk masuk.

e. Metode showAdminWindow
Tampilan untuk admin setelah login berhasil. Admin dapat:

Melihat daftar HP yang dijual.
Melihat riwayat pembelian.
Menambah HP baru dengan memasukkan nama, jumlah, dan path gambar HP.
Menghapus HP yang ada.
f. Metode updateHPTable
Metode ini digunakan untuk memperbarui tabel yang menampilkan daftar HP yang tersedia. Gambar HP akan disesuaikan ukurannya menggunakan ImageIcon dan Image.

g. Metode updateRiwayatTable
Memperbarui tabel yang menampilkan riwayat pembelian, termasuk nama pembeli, tipe HP, dan metode pembayaran.

2. Kelas HP
Kelas ini merepresentasikan objek HP yang dijual.

Atribut:
namaHP: Nama dari HP yang dijual.
jumlah: Jumlah stok HP yang tersedia.
imagePath: Path gambar untuk HP tersebut.
Metode:
getNamaHP(), setNamaHP(): Mengambil dan mengubah nama HP.
getJumlah(), setJumlah(): Mengambil dan mengubah jumlah stok HP.
getImagePath(): Mengambil path gambar HP.
3. Kelas Pembelian
Kelas ini merepresentasikan pembelian yang dilakukan oleh pembeli.

Atribut:

namaPembeli: Nama pembeli.
tipeHP: Tipe HP yang dibeli.
metodePembayaran: Metode pembayaran yang digunakan (misalnya: "Tunai").
Metode:

getNamaPembeli(): Mengambil nama pembeli.
getTipeHP(): Mengambil tipe HP yang dibeli.
getMetodePembayaran(): Mengambil metode pembayaran.
4. Kelas ImageRenderer
Kelas ini adalah custom TableCellRenderer untuk menampilkan gambar dalam JTable. Gambar akan ditampilkan dengan ukuran yang sudah disesuaikan.

Cara Menjalankan Aplikasi
Pastikan Anda memiliki JDK 8 atau yang lebih tinggi terinstal di komputer Anda.
Kompilasi dan jalankan file PenjualanHPSecond.java menggunakan IDE seperti IntelliJ IDEA atau Eclipse, atau dari terminal dengan perintah:
bash
Salin kode
javac PenjualanHPSecond.java
java PenjualanHPSecond
Aplikasi akan menampilkan jendela utama dengan dua tombol: Pembeli dan Admin.
Instalasi Gambar
Pastikan Anda telah menyediakan gambar HP yang sesuai dengan path yang diberikan dalam kode (imagePath). Anda dapat mengganti path gambar sesuai dengan lokasi gambar di komputer Anda.

Catatan
Aplikasi ini belum dilengkapi dengan database atau penyimpanan data jangka panjang. Semua data HP dan riwayat pembelian hanya disimpan selama aplikasi berjalan.
Password untuk login admin adalah admin123.

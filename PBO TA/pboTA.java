//package PBO;

import java.io.BufferedReader; //membaca input user 
import java.io.InputStreamReader; //membaca melalui konsol bufferedReader

import java.sql.DriverManager; //memanggil driver JDBC
import java.sql.Connection; //mempresentasikan koneksi dengan data sourch
import java.sql.Statement; //mempresentasikan perintah sql
import java.sql.ResultSet; //mempresentasikan hasil database dari statement sql select


//konekting database
public class pboTA {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/pbo";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);


    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            menuAwal();

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }


    //membersihkan layar
    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }


    //menampilkan pilihan dimenu awal
    static void menuAwal() {
        System.out.println("========= CamRental =========");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            //eksekusi inputan
            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                default:
                    System.out.println("\nPilihan Tidak Tersedia!");
                    menuAwal();
            }
        } 

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    //form login
    static void login() {
        try {

            cls();
            System.out.println("========= LOGIN =========\n");
            System.out.print("Username : ");
            String Username = input.readLine();
            System.out.print("Password : ");
            String Password = input.readLine();

            //memanggil data dari database
            String sql = "SELECT * FROM login WHERE username='"+Username+"' AND password='"+Password+"'";
            ResultSet rsLogin = stmt.executeQuery(sql);
            rsLogin.next();
            if (rsLogin.getRow()==1){
                showMenu();
            }
            
            else {
                cls();
                System.out.println("Username atau Password Yang Anda Masukkan Salah!\n\n");
                menuAwal();
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    //form registrasi
    static void register() {
        try {
            cls();
            System.out.println("========= REGISTRSI =========\n");
            System.out.print("Nama       : ");
            String Nama = input.readLine().trim();
            System.out.print("Username   : ");
            String Username = input.readLine().trim();
            System.out.print("Password   : ");
            String Password = input.readLine().trim();
            System.out.print("Email      : ");
            String Email = input.readLine().trim();
            System.out.print("Telepon    : ");
            String Telepon = input.readLine().trim();

            String sql = "INSERT INTO login (username, password, nama, Email, Telp) VALUE('%s', '%s', '%s', '%s', '%s')";
            sql = String.format(sql, Username, Password, Nama, Email, Telepon);

            stmt.execute(sql);

            menuAwal();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void showMenu() {
        //menampilkan menu pilihan menu utama
        System.out.println("========= MENU UTAMA =========");
        System.out.println("1. Lihat Data Kamera");
        System.out.println("2. Ajukan Rental");
        System.out.println("3. Lihat Struk");
        System.out.println("0. Keluar");
        System.out.println("");
        System.out.print("PILIHAN> ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            switch (pilihan) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    cls();
                    dataKamera();
                    break;
                case 2:
                    cls();
                    tambahRental();
                    break;
                case 3:
                    cls();
                    lihatTransaksi();
                    break;
                default:
                    System.out.println("\nPilihan Tidak Tersedia!");
                    showMenu();
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void dataKamera() {
        //menampilkan data kamera dari database tabel kamera_table
        String sql = "SELECT * FROM kamera_table"; 

        try {
            rs = stmt.executeQuery(sql);

            cls();
            System.out.println("+-----------------------------------------+");
            System.out.println("|          DATA KAMERA CAMRENTAL          |");
            System.out.println("+-----------------------------------------+");
            System.out.println("id\tnama_kamera \t\tharga_harian ");

            while (rs.next()) {
                int id_kamera = rs.getInt("id_kamera");
                String nama_kamera = rs.getString("nama_kamera");
                int harga_harian = rs.getInt("harga_harian");
                
                System.out.println(String.format("%s \t%s \t\t%s", id_kamera, nama_kamera, harga_harian));
            }

            System.out.println("\n");
            showMenu();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void tambahRental() {
        try {

            cls();
            //form isian rental
            System.out.println("========== FORM PENGAJUAN RENTAL ==========\n");
            System.out.print("tanggal_rental            : ");
            String Tanggal_Rental = input.readLine().trim();
            System.out.print("nama_penyewa              : ");
            String nama_penyewa = input.readLine().trim();
            System.out.print("telp_penyewa              : ");
            String alamat_penyewa = input.readLine().trim();
            System.out.print("alamat_penyewa            : ");
            String telp_penyewa = input.readLine().trim();
            System.out.print("id_kamera                 : ");
            String id_kamera = input.readLine().trim();
            System.out.print("waktu_sewa                : ");
            String lama_hari = input.readLine().trim();
            System.out.print("total harga               : ");
            String total_harga = input.readLine().trim();

            //menambah data peminjaman ke database rental
            String sql = "INSERT INTO rental (tanggal_rental, id_kamera, lama_hari, total_harga, nama_penyewa, alamat_penyewa, telp_penyewa) VALUE('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
            sql = String.format(sql, Tanggal_Rental, id_kamera, lama_hari, total_harga, nama_penyewa, alamat_penyewa, telp_penyewa);
            stmt.execute(sql);

            cls();
            System.out.println("Data Berhasil Ditambahkan\n\n");
            showMenu();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void lihatTransaksi() {
        //menampilkan data penyewaan, dari databse table rental dan kamera_table sesuai format dibawah
        String sql = "SELECT rental.tanggal_rental, rental.nama_penyewa, rental.lama_hari, rental.total_harga, kamera_table.nama_kamera, kamera_table.harga_harian FROM kamera_table JOIN rental ON kamera_table.id_kamera = rental.id_kamera";

        try {
            rs = stmt.executeQuery(sql);

            cls();
            //output riwayat transaksi
            System.out.println("+------------------------------------------------------------------------------------+");
            System.out.println("|                                 DATA TRANSAKSI ANDA                                |");
            System.out.println("+------------------------------------------------------------------------------------+");
            System.out.println("tanggal_rental\t\tnama_penyewa\tnama_kamera\tharga_harian\t\tlama_hari\t\ttotal_harga");

            while (rs.next()) {
                String tanggal_rental = rs.getString("tanggal_rental");
                String nama_penyewa = rs.getString("nama_penyewa");
                String nama_kamera = rs.getString("nama_kamera");
                int harga_harian = rs.getInt("harga_harian");
                int lama_hari = rs.getInt("lama_hari");
                int total_harga = rs.getInt("total_harga");

                System.out.println(String.format("%s \t%s \t\t%s \t%s \t\t\t%s \t\t\t%s", tanggal_rental, nama_penyewa, nama_kamera ,harga_harian, lama_hari, total_harga));
            }
            
            System.out.println("\n");
            showMenu();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}


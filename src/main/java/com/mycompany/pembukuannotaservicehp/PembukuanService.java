/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;
import java.util.Scanner;
/**
 *
 * @author Bintang
 */
public class PembukuanService {
    
    public static void cariNota(String nama, LayananService[] daftarNota, int jumlah) {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian by Nama: " + nama + " ---");
        for (int i = 0; i < jumlah; i++) {
            if (daftarNota[i].getNamaPelanggan().equalsIgnoreCase(nama)) {
                daftarNota[i].cetakNota();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Data pelanggan tidak ditemukan.");
    }

    public static void cariNota(String tipeHp, LayananService[] daftarNota, int jumlah, boolean isTipe) {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian by Tipe HP: " + tipeHp + " ---");
        for (int i = 0; i < jumlah; i++) {
            if (daftarNota[i].getTipeHp().equalsIgnoreCase(tipeHp)) {
                daftarNota[i].cetakNota();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Data tipe HP tidak ditemukan.");
    }

    public static void simulasiProses(LayananService layanan) {
        layanan.jalankanDiagnostik();
        layanan.prosesService();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LayananService[] daftarNota = new LayananService[15]; 
        int jumlahNota = 0;
        boolean isRunning = true;

        System.out.println("=================================================");
        System.out.println("           SISTEM PEMBUKUAN SERVICE HP           ");
        System.out.println("=================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Catatan Service Baru");
            System.out.println("2. Tampilkan Seluruh Nota & Simulasi Diagnostik");
            System.out.println("3. Cari Nota (Fitur Overloading)");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu (1-4): ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1 -> {
                    if (jumlahNota < daftarNota.length) {
                        System.out.println("\n-- Pilih Jenis Perbaikan --");
                        System.out.println("1. Hardware (Ganti Sparepart)");
                        System.out.println("2. Software (Flashing/Bypass)");
                        System.out.println("3. Pembersihan (Debu/Karat)");
                        System.out.print("Pilihan (1/2/3): ");
                        int jenis = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Masukkan Nama Pelanggan: ");
                        String nama = scanner.nextLine();
                        System.out.print("Masukkan Tipe HP: ");
                        String tipe = scanner.nextLine();
                        System.out.print("Masukkan Biaya Jasa Dasar (Rp): ");
                        double biayaJasa = scanner.nextDouble();
                        scanner.nextLine();

                        if (jenis == 1) {
                            System.out.print("Nama Sparepart yang Diganti: ");
                            String sparepart = scanner.nextLine();
                            System.out.print("Biaya Sparepart (Rp): ");
                            double biayaSparepart = scanner.nextDouble();
                            scanner.nextLine();
                            daftarNota[jumlahNota] = new ServiceHardware(nama, tipe, biayaJasa, sparepart, biayaSparepart);
                        } else if (jenis == 2) {
                            System.out.print("Jenis Tindakan (ROM/Bypass): ");
                            String tindakan = scanner.nextLine();
                            daftarNota[jumlahNota] = new ServiceSoftware(nama, tipe, biayaJasa, tindakan);
                        } else if (jenis == 3) {
                            System.out.print("Area yang dibersihkan (misal: Speaker/Mesin): ");
                            String area = scanner.nextLine();
                            daftarNota[jumlahNota] = new ServicePembersihan(nama, tipe, biayaJasa, area);
                        }
                        jumlahNota++;
                        System.out.println("Sukses! Nota berhasil ditambahkan.");
                    } else {
                        System.out.println("Maaf, kapasitas pembukuan sudah penuh!");
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Buku Catatan Service HP & Simulasi Diagnostik ---");
                    if (jumlahNota == 0) {
                        System.out.println("Belum ada data service yang tercatat.");
                    } else {
                        System.out.println("----------------------------------------------------------------------");
                        for (int i = 0; i < jumlahNota; i++) {
                            System.out.print((i + 1) + ". ");
                            daftarNota[i].cetakNota();
                            simulasiProses(daftarNota[i]);
                            System.out.println("");
                        }
                        System.out.println("----------------------------------------------------------------------");
                    }
                    System.out.println("* Total Nota Fisik yang Dibuat: " + LayananService.totalNotaDibuat);
                    System.out.print("Tekan Enter untuk melanjutkan...");
                    scanner.nextLine();
                }
                case 3 -> {
                    System.out.println("\n-- Fitur Cari Nota --");
                    System.out.println("1. Cari berdasarkan Nama Pelanggan");
                    System.out.println("2. Cari berdasarkan Tipe HP");
                    System.out.print("Pilih (1/2): ");
                    int modeCari = scanner.nextInt();
                    scanner.nextLine();

                    if (modeCari == 1) {
                        System.out.print("Masukkan Nama Pelanggan: ");
                        String keyword = scanner.nextLine();
                        cariNota(keyword, daftarNota, jumlahNota);
                    } else if (modeCari == 2) {
                        System.out.print("Masukkan Tipe HP: ");
                        String keyword = scanner.nextLine();
                        cariNota(keyword, daftarNota, jumlahNota, true);
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                }
                case 4 -> {
                    System.out.println("Terima kasih telah menggunakan sistem Pembukuan Service HP!");
                    isRunning = false;
                }
                default -> {
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-4.");
                }
            }
        }
        scanner.close();
    }
}
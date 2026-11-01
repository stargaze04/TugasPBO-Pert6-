/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author Bintang
 */
public class PembukuanService {

    public static void cariNota(String keyword, ArrayList<LayananService> daftarNota) {
        boolean ditemukan = false;
        System.out.println("\n--- Hasil Pencarian by Nama: " + keyword + " ---");
        for (LayananService nota : daftarNota) {
            if (nota.getNamaPelanggan().equalsIgnoreCase(keyword)) {
                nota.cetakNota();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Data pelanggan tidak ditemukan.");
    }

    public static void simulasiProses(LayananService layanan) {
        layanan.jalankanDiagnostik();
        layanan.prosesService();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<LayananService> daftarNota = new ArrayList<>();
        HashMap<String, String> statusService = new HashMap<>();
        
        boolean isRunning = true;

        System.out.println("=================================================");
        System.out.println("            SISTEM PEMBUKUAN SERVICE HP          ");
        System.out.println("=================================================");

        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Tambah Catatan Service Baru");
            System.out.println("2. Tampilkan Seluruh Nota & Simulasi");
            System.out.println("3. Cari Nota");
            System.out.println("4. Keluar");
            System.out.print("Pilih Menu (1-4): ");
            
            int pilihan = 0;
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Input menu harus berupa angka!");
                scanner.nextLine(); 
                continue;
            }

            switch (pilihan) {
                case 1 -> {
                    try {
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
                            daftarNota.add(new ServiceHardware(nama, tipe, biayaJasa, sparepart, biayaSparepart));
                            statusService.put(nama, "Menunggu Sparepart");
                        } else if (jenis == 2) {
                            System.out.print("Jenis Tindakan (misal: Flashing ROM): ");
                            String tindakan = scanner.nextLine();
                            daftarNota.add(new ServiceSoftware(nama, tipe, biayaJasa, tindakan));
                            statusService.put(nama, "Sedang Flashing");
                        } else if (jenis == 3) {
                            System.out.print("Area yang dibersihkan (misal: Speaker): ");
                            String area = scanner.nextLine();
                            daftarNota.add(new ServicePembersihan(nama, tipe, biayaJasa, area));
                            statusService.put(nama, "Proses Pembersihan");
                        }
                        System.out.println("Sukses! Nota berhasil ditambahkan.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error dari validasi: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Input biaya atau nominal harus berupa angka yang benar!");
                        scanner.nextLine();
                    } finally {
                        System.out.println("[Sistem]: Selesai memproses menu penambahan data.");
                    }
                }
                case 2 -> {
                    System.out.println("\n--- Buku Catatan Service HP & Simulasi Diagnostik ---");
                    if (daftarNota.isEmpty()) {
                        System.out.println("Belum ada data service yang tercatat.");
                    } else {
                        System.out.println("----------------------------------------------------------------------");
                        int i = 1;
                        for (LayananService nota : daftarNota) {
                            System.out.print(i + ". ");
                            nota.cetakNota();
                            simulasiProses(nota);
                            System.out.println("Status HP: " + statusService.get(nota.getNamaPelanggan()));
                            System.out.println("");
                            i++;
                        }
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
                case 3 -> {
                    System.out.print("\nMasukkan Nama Pelanggan: ");
                    String keyword = scanner.nextLine();
                    cariNota(keyword, daftarNota);
                }
                case 4 -> {
                    System.out.println("Terima kasih telah menggunakan sistem Pembukuan Service HP!");
                    isRunning = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}
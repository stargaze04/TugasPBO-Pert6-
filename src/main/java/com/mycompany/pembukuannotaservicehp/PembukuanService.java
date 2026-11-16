/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Bintang
 */
public class PembukuanService {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        KonterService konter = new KonterService("Konter Service HP GnB", "POS-04", "v10.0");
        TransaksiService transaksi = new TransaksiService("TRX-2026");

        boolean isRunning = true;

        while (isRunning) {
            konter.infoBengkel();
            System.out.println("Menu Utama:");
            System.out.println("1. Tambah Catatan Service Baru");
            System.out.println("2. Tambah Data Teknisi Baru");
            System.out.println("3. Tampilkan Seluruh Nota & Teknisi");
            System.out.println("4. Proses Penugasan Teknisi ke Nota");
            System.out.println("5. Keluar");
            System.out.print("Pilih Menu (1-5): ");

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
                        System.out.println("1. Hardware (Sparepart)");
                        System.out.println("2. Software (Flashing/Bypass)");
                        System.out.println("3. Pembersihan (Luar/Dalam)");
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

                        LayananService notaBaru = null;
                        if (jenis == 1) {
                            System.out.print("Nama Sparepart yang Diganti: ");
                            String sparepart = scanner.nextLine();
                            System.out.print("Biaya Sparepart (Rp): ");
                            double biayaSparepart = scanner.nextDouble();
                            scanner.nextLine();
                            notaBaru = new ServiceHardware(nama, tipe, biayaJasa, sparepart, biayaSparepart);
                        } else if (jenis == 2) {
                            System.out.print("Jenis Tindakan: ");
                            String tindakan = scanner.nextLine();
                            notaBaru = new ServiceSoftware(nama, tipe, biayaJasa, tindakan);
                        } else if (jenis == 3) {
                            System.out.print("Area yang dibersihkan: ");
                            String area = scanner.nextLine();
                            notaBaru = new ServicePembersihan(nama, tipe, biayaJasa, area);
                        } else {
                            System.out.println("Pilihan jenis tidak valid.");
                            break;
                        }

                        konter.tambahNota(notaBaru);
                        System.out.println("Sukses! Nota berhasil ditambahkan ke KonterService.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error Validasi: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Nominal biaya harus berupa angka!");
                        scanner.nextLine();
                    } finally {
                        System.out.println("[Sistem]: Selesai memproses input nota.");
                    }
                }
                case 2 -> {
                    System.out.println("\n-- Tambah Teknisi --");
                    System.out.print("Masukkan ID Teknisi: ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan Nama Teknisi: ");
                    String namaTek = scanner.nextLine();
                    System.out.print("Masukkan Spesialisasi: ");
                    String spesialis = scanner.nextLine();

                    Teknisi teknisiBaru = new Teknisi(id, namaTek, spesialis);
                    konter.tambahTeknisi(teknisiBaru);
                    System.out.println("Sukses! Teknisi baru ditambahkan ke sistem.");
                }
                case 3 -> {
                    konter.tampilkanSemuaNota();
                    konter.tampilkanSemuaTeknisi();
                }
                case 4 -> {
                    System.out.println("\n-- Simulasi Interaksi --");
                    System.out.print("Masukkan ID Teknisi yang bertugas: ");
                    String idTek = scanner.nextLine();
                    System.out.print("Masukkan Nama Pelanggan pada Nota: ");
                    String namaPel = scanner.nextLine();

                    Teknisi tekDitemukan = konter.cariTeknisi(idTek);
                    LayananService notaDitemukan = konter.cariNotaByPelanggan(namaPel);

                    if (tekDitemukan != null && notaDitemukan != null) {
                        transaksi.prosesPenugasan(tekDitemukan, notaDitemukan);
                    } else {
                        System.out.println("Gagal: ID Teknisi atau Nama Pelanggan tidak ditemukan!");
                    }
                }
                case 5 -> {
                    System.out.println("Terima kasih telah menggunakan sistem Pembukuan Service HP!");
                    isRunning = false;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
    }
}
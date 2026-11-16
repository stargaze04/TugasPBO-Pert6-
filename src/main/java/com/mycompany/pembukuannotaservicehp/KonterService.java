/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/**
 *
 * @author Bintang
 */
public class KonterService {
    private String namaKonter;
    private SistemKasir sistemKasir;
    private ArrayList<LayananService> daftarNota;
    private HashMap<String, Teknisi> daftarTeknisi;

    private final String FILE_LOG = "log_aktivitas.txt";
    private final String FILE_DATA = "data_konter.dat";

    public KonterService(String namaKonter, String nomorMesin, String versiSistem) {
        this.namaKonter = namaKonter;
        this.sistemKasir = new SistemKasir(nomorMesin, versiSistem);
        this.daftarNota = new ArrayList<>();
        this.daftarTeknisi = new HashMap<>();
    }

    public void infoKonter() {
        System.out.println("======================================================================");
        System.out.println("  " + this.namaKonter.toUpperCase());
        this.sistemKasir.tampilkanInfoSistem();
        System.out.println("======================================================================");
    }

    public void tambahNota(LayananService nota) {
        this.daftarNota.add(nota);
        catatLog("Tambah Nota: Pelanggan " + nota.getNamaPelanggan() + " (" + nota.getTipeHp() + ")");
    }

    public void tambahTeknisi(Teknisi teknisi) {
        this.daftarTeknisi.put(teknisi.getIdTeknisi(), teknisi);
        catatLog("Tambah Teknisi: " + teknisi.getIdTeknisi() + " - " + teknisi.getNamaTeknisi());
    }

    public Teknisi cariTeknisi(String idTeknisi) {
        return this.daftarTeknisi.get(idTeknisi);
    }

    public LayananService cariNotaByPelanggan(String namaPelanggan) {
        for (LayananService nota : this.daftarNota) {
            if (nota.getNamaPelanggan().equalsIgnoreCase(namaPelanggan)) {
                return nota;
            }
        }
        return null;
    }

    public void tampilkanSemuaNota() {
        System.out.println("\n--- Daftar Nota Service ---");
        if (this.daftarNota.isEmpty()) {
            System.out.println("Belum ada data nota yang tercatat.");
        } else {
            int i = 1;
            for (LayananService nota : this.daftarNota) {
                System.out.print(i + ". ");
                nota.cetakNota();
                nota.jalankanDiagnostik();
                nota.prosesService();
                System.out.println("");
                i++;
            }
        }
    }

    public void tampilkanSemuaTeknisi() {
        System.out.println("\n--- Daftar Teknisi Terdaftar ---");
        if (this.daftarTeknisi.isEmpty()) {
            System.out.println("Belum ada teknisi yang terdaftar.");
        } else {
            for (Teknisi t : this.daftarTeknisi.values()) {
                t.tampilkanInfoTeknisi();
            }
        }
    }

    public void catatLog(String aktivitas) {
        try {
            FileWriter writer = new FileWriter(FILE_LOG, true);
            writer.write(aktivitas + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Gagal menulis log ke file: " + e.getMessage());
        }
    }

    public void bacaLog() {
        System.out.println("\n--- Riwayat Log Aktivitas (" + FILE_LOG + ") ---");
        try {
            File file = new File(FILE_LOG);
            Scanner reader = new Scanner(file);
            if (!reader.hasNextLine()) {
                System.out.println("File log masih kosong.");
            }
            while (reader.hasNextLine()) {
                System.out.println("- " + reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Belum ada file log aktivitas (" + FILE_LOG + ").");
        }
    }

    public void simpanDataObjek() {
        try {
            FileOutputStream fos = new FileOutputStream(FILE_DATA);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.daftarNota);
            oos.writeObject(this.daftarTeknisi);
            oos.close();
            fos.close();
            System.out.println("Sukses! Seluruh data objek berhasil disimpan ke " + FILE_DATA);
            catatLog("Serialization: Menyimpan data objek ke " + FILE_DATA);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data objek: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void muatDataObjek() {
        try {
            FileInputStream fis = new FileInputStream(FILE_DATA);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.daftarNota = (ArrayList<LayananService>) ois.readObject();
            this.daftarTeknisi = (HashMap<String, Teknisi>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Sukses! Data objek berhasil dimuat kembali dari " + FILE_DATA);
        } catch (FileNotFoundException e) {
            System.out.println("File data (" + FILE_DATA + ") belum ditemukan.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Gagal memuat data objek: " + e.getMessage());
        }
    }
}
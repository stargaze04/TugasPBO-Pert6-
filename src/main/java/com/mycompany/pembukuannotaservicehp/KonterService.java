/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Bintang
 */
public class KonterService {
    private String namaKonter;
    private SistemKasir sistemKasir;
    private ArrayList<LayananService> daftarNota;
    private HashMap<String, Teknisi> daftarTeknisi;

    public KonterService(String namaKonter, String tahunRilis, String versiSistem) {
        this.namaKonter = namaKonter;
        this.sistemKasir = new SistemKasir(tahunRilis, versiSistem);
        this.daftarNota = new ArrayList<>();
        this.daftarTeknisi = new HashMap<>();
    }

    public void infoBengkel() {
        System.out.println("======================================================================");
        System.out.println("  " + this.namaKonter.toUpperCase());
        this.sistemKasir.tampilkanInfoSistem();
        System.out.println("======================================================================");
    }

    public void tambahNota(LayananService nota) {
        this.daftarNota.add(nota);
    }

    public void tambahTeknisi(Teknisi teknisi) {
        this.daftarTeknisi.put(teknisi.getIdTeknisi(), teknisi);
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
}
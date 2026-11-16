/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;

/**
 *
 * @author Bintang
 */
public class TransaksiService {
    private String kodeTransaksi;

    public TransaksiService(String kodeTransaksi) {
        this.kodeTransaksi = kodeTransaksi;
    }

    public void prosesPenugasan(Teknisi teknisi, LayananService nota) {
        System.out.println("\n======================================================================");
        System.out.println("[TRANSAKSI - " + this.kodeTransaksi + "]");
        System.out.println("Teknisi Bertugas : " + teknisi.getNamaTeknisi() + " (" + teknisi.getSpesialisasi() + ")");
        System.out.println("Pelanggan        : " + nota.getNamaPelanggan() + " - Unit: " + nota.getTipeHp());
        System.out.println("Status           : Teknisi sedang mengerjakan unit sesuai nota.");
        nota.prosesService();
        System.out.println("======================================================================");
    }
}
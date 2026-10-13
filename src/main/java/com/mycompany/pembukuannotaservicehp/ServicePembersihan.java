/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;

/**
 *
 * @author Bintang
 */
public class ServicePembersihan extends LayananService {
 private String areaPembersihan;
    public ServicePembersihan(String namaPelanggan, String tipeHp, double biayaDasar, String areaPembersihan) {
        super(namaPelanggan, tipeHp, biayaDasar);
        this.areaPembersihan = areaPembersihan;
    }

    public String getAreaPembersihan() {
        return this.areaPembersihan;
    }

    public void setAreaPembersihan(String areaPembersihan) {
        this.areaPembersihan = areaPembersihan;
    }

    @Override
    public void cetakNota() {
        System.out.printf("[PEMBERSIHAN] Pelanggan: %s | Tipe HP: %s | Area: %s | Total: Rp%,.2f\n", 
                          getNamaPelanggan(), getTipeHp(), this.areaPembersihan, getBiayaDasar());
    }

    @Override
    public void prosesService() {
        System.out.println("-> Info Proses: Membersihkan " + this.areaPembersihan + " menggunakan cairan khusus dan sikat anti-statis.");
    }
}

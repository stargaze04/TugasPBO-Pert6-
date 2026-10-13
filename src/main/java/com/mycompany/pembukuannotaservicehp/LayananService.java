/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;
/**
 *
 * @author Bintang
 */
public abstract class LayananService implements BisaDites {
    private String namaPelanggan;
    private String tipeHp;
    private double biayaDasar;
    
    public static int totalNotaDibuat = 0;

    public LayananService(String namaPelanggan, String tipeHp, double biayaDasar) {
        this.namaPelanggan = namaPelanggan;
        this.tipeHp = tipeHp;
        this.biayaDasar = biayaDasar;
        totalNotaDibuat++;
    }

    public String getNamaPelanggan() {
        return this.namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public String getTipeHp() {
        return this.tipeHp;
    }

    public void setTipeHp(String tipeHp) {
        this.tipeHp = tipeHp;
    }

    public double getBiayaDasar() {
        return this.biayaDasar;
    }

    public void setBiayaDasar(double biayaDasar) {
        if (biayaDasar >= 0) {
            this.biayaDasar = biayaDasar;
        } else {
            System.out.println("Biaya dasar tidak valid!");
        }
    }

    public abstract void cetakNota();
    public abstract void prosesService();

    @Override
    public void jalankanDiagnostik() {
        System.out.println("-> [DIAGNOSTIK SISTEM] Mengecek kelayakan fungsi HP " + this.tipeHp);
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;

/**
 *
 * @author Bintang
 */
public class ServiceHardware extends LayananService{
    private String jenisSparepart;
    private double biayaSparepart;
    
    public ServiceHardware(String namaPelanggan, String tipeHP, double biayaDasar,String jenisSparepart, double biayaSparepart){
        super(namaPelanggan, tipeHP, biayaDasar);
        this.jenisSparepart = jenisSparepart;
        this.biayaSparepart = biayaSparepart;
}
    public String getJenisSparepart(){
        return this.jenisSparepart;
    }
    public void setJenisSparepart(String jenisSparepart){
        this.jenisSparepart = jenisSparepart;
    }
    public double getBiayaSparepart(){
        return this.biayaSparepart;
    }
    public void setBiayaSparepart(double biayaSparepart){
        if(biayaSparepart >= 0){
        this.biayaSparepart = biayaSparepart;
    } else {
        System.out.println("Peringatan: Biaya sparepart tidak boleh negatif!");
        }
    }
    @Override
    public void cetakNota(){
        double totalBiaya = getBiayaDasar()+ this.biayaSparepart;
        System.out.printf("[HARDWARE] Pelanggan: %-12s | Tipe HP: %-10s | Part: %-12s | Total: Rp%,.2f%n", 
                          getNamaPelanggan(), getTipeHp(), this.jenisSparepart, totalBiaya);
    }
    @Override
    public void prosesService() {
        System.out.println("-> Info Proses: Membongkar baut dan mengganti sparepart" + this.jenisSparepart);
    }
}

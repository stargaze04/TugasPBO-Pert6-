/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;

/**
 *
 * @author Bintang
 */
public class ServiceSoftware extends LayananService{
    private String jenisTindakan;
    
    public ServiceSoftware(String namaPelanggan, String tipeHp, double biayaDasar, String jenisTindakan){
        super(namaPelanggan, tipeHp, biayaDasar);
        this.jenisTindakan = jenisTindakan;
    }
    public String getJenisTindakan(){
        return this.jenisTindakan;
    }
    public void setJenisTindakan(String jenisTindakan){
        this.jenisTindakan = jenisTindakan;
    }
    @Override
    public void cetakNota(){
        System.out.printf("[SOFTWARE] Pelangga: %-12s | Tipe HP: %-10s | Aksis: %-11s | Total: Rp%,.2f%n",
                getNamaPelanggan(), getTipeHp(), this.jenisTindakan, getBiayaDasar());
    }
    @Override
    public void prosesService() {
        System.out.println("-> Info Proses: Menginstal ulang firmware dan melakukan Flashing" + this.jenisTindakan);
    }
}

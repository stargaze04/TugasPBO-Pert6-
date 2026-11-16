/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;

/**
 *
 * @author Bintang
 */
public class Teknisi {
    private String idTeknisi;
    private String namaTeknisi;
    private String spesialisasi;

    public Teknisi(String idTeknisi, String namaTeknisi, String spesialisasi) {
        this.idTeknisi = idTeknisi;
        this.namaTeknisi = namaTeknisi;
        this.spesialisasi = spesialisasi;
    }

    public String getIdTeknisi() {
        return this.idTeknisi;
    }

    public String getNamaTeknisi() {
        return this.namaTeknisi;
    }

    public String getSpesialisasi() {
        return this.spesialisasi;
    }

    public void tampilkanInfoTeknisi() {
        System.out.printf("ID: %-6s | Nama Teknisi: %-15s | Spesialisasi: %s\n", 
                          this.idTeknisi, this.namaTeknisi, this.spesialisasi);
    }
}

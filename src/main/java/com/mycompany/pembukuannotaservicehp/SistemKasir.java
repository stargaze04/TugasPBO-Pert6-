/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pembukuannotaservicehp;

/**
 *
 * @author Bintang
 */
public class SistemKasir {
    private String tahunRilis;
    private String versiSistem;

    public SistemKasir(String nomorMesin, String versiSistem) {
        this.tahunRilis = nomorMesin;
        this.versiSistem = versiSistem;
    }

    public void tampilkanInfoSistem() {
        System.out.println("[SISTEM INTERNAL - COMPOSITION] Terminal: "
                + this.tahunRilis + " | Versi: " + this.versiSistem + " (Status: Aktif)");
    }
}

package org.example.bucketsortfx;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        //Application.launch(HelloApplication.class, args);
        BucketSort bk = new BucketSort();
        bk.geraVetor();
        bk.exibeVetor();
        System.out.print('\n');
        bk.ordenaVetor();
        bk.exibeVetor();
        System.out.print('\n');
        bk.exibePassos();
    }
}

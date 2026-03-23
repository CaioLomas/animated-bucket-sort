package org.example.bucketsortfx;

import java.util.Random;

public class BucketSort {

    private Passo vet_passo[] = new Passo[1000];
    private int qtd_passos=0;
    private int vetor[] = new int[10];
    private int baldes[][] = new int[10][10];
    private int qtd_balde[] = new int[10];
    private int maior=0;
    private Random gerador = new Random();

    public void geraVetor(){
        for (int i=0;i<10;i++){
            vetor[i]=gerador.nextInt(100)+1;
            if (vetor[i]>maior)
                maior=vetor[i];
        }
    }

    public void exibeVetor(){
        for(int i=0;i<10;i++)
            System.out.printf("[%d] ",vetor[i]);
    }

    public int[] getVetor() {
        return vetor;
    }

    public Passo[] getVet_passo() {
        return vet_passo;
    }

    public int getQtd_passos() {
        return qtd_passos;
    }

    private void insertion(int balde[],int tamanho) {
        if (tamanho>1) {
            int aux,ppos,pos=1;

            while(pos<tamanho){
                aux=balde[pos];
                ppos=pos;
                while(ppos>0 && aux<balde[ppos-1]){
                    balde[ppos]=balde[ppos-1];
                    ppos--;
                }

                balde[ppos]=aux;
                pos++;
            }
        }
    }

    public void ordenaVetor() {
        int indice;
        for (int i=0; i<10;i++) {
            indice=(10*vetor[i]/(maior+1));
            vet_passo[qtd_passos++] = new Passo("PEGA_ELEMENTO",vetor[i],i,-1);
            vet_passo[qtd_passos++] = new Passo("INSERE_BALDE",vetor[i],i,indice);
            baldes[indice][qtd_balde[indice]]=vetor[i];
            qtd_balde[indice]++;
        }

        for(int i=0;i<10;i++)
            if(qtd_balde[i]>0){
                vet_passo[qtd_passos++] = new Passo("ORDENA_BALDE",-1,i,-1);
                insertion(baldes[i],qtd_balde[i]);
            }

        for(int i=0,j=0,k;i<10;i++) {
            k=0;
            while(k<qtd_balde[i]){
                vet_passo[qtd_passos++] = new Passo("PEGA_ELEMENTO",baldes[i][k],i,k);
                vet_passo[qtd_passos++] = new Passo("INSERE_VETOR",baldes[i][k],i,j);
                vetor[j++]=baldes[i][k];
                k++;
            }
        }
    }

    public void exibePassos() {
        for(int i=0;i<qtd_passos;i++)
            vet_passo[i].mostraPasso();
    }
}
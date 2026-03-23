package org.example.bucketsortfx;

public class Passo {

    private String acao;
    private int valor;
    private int i_origem;
    private int i_destino;

    Passo(String acao, int valor, int i_origem, int i_destino) {
        this.acao = acao;
        this.valor = valor;
        this.i_origem = i_origem;
        this.i_destino = i_destino;
    }

    public void mostraPasso() {
        System.out.printf("Ação: %s Valor: %d   Origem: %d  Destino: %d\n",
                acao, valor, i_origem, i_destino);
    }

    public String getAcao() {
        return acao;
    }

    public int getValor() {
        return valor;
    }

    public int getI_origem() {
        return i_origem;
    }

    public int getI_destino() {
        return i_destino;
    }
}
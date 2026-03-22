package org.example.bucketsortfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    private String[] codigo_fonte = {
            "int indice;",
            "for(int i=0;i<10;i++){",
            "    indice=(10*vetor[i]/(maior+1));",
            "    vet_passo[qtd_passos++] = new Passo(\"PEGA_ELEMENTO\",vetor[i],i,-1);",
            "    vet_passo[qtd_passos++] = new Passo(\"INSERE_BALDE\",vetor[i],i,indice);",
            "    baldes[indice][qtd_balde[indice]]=vetor[i];",
            "    qtd_balde[indice]++;",
            "}",
            "for(int i=0;i<10;i++)",
            "    if(qtd_balde[i]>0)",
            "    {",
            "        vet_passo[qtd_passos++] = new Passo(\"ORDENA_BALDE\",-1,i,-1);",
            "        insertion(baldes[i],qtd_balde[i]);",
            "    }",
            "for(int i=0,j=0,k;i<10;i++){",
            "    k=0;",
            "    while(k<qtd_balde[i]){",
            "        vet_passo[qtd_passos++] = new Passo(\"PEGA_ELEMENTO\",baldes[i][k],i,k);",
            "        vet_passo[qtd_passos++] = new Passo(\"INSERE_VETOR\",baldes[i][k],i,j);",
            "        vetor[j++]=baldes[i][k];",
            "        k++;",
            "    }",
            "}"
    };

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    //    A animação do algoritmo deverá conter:
    //            1. geração aleatória dos valores a serem ordenados;
    //2. objetos com os valores sendo movidos na tela;
    //3. código-fonte do metodo de ordenação demarcando cada linha de execução;
    //4. variáveis de índices mostradas na tela, setas indicando o processo, cores, etc.
}

package org.example.bucketsortfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

public class Controller {

    @FXML
    private HBox container;
    @FXML
    private VBox codigoContainer;
    @FXML
    private Label lblIndice;
    @FXML
    private Label lblI;
    @FXML
    private Label lblJ;
    @FXML
    private Label lblK;
    @FXML
    private Polygon funil;

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
    public void initialize() {
        BucketSort bk=new BucketSort();
        bk.geraVetor();

        for (int v : bk.getVetor()) {
            Label l = new Label(String.valueOf(v));
            l.setPrefSize(80, 80);
            l.setAlignment(javafx.geometry.Pos.CENTER);
            l.setStyle("-fx-background-color: #007acc; -fx-text-fill: white; -fx-font-size: 30px; -fx-font-weight: bold; -fx-border-color: #005999; -fx-border-width: 2;");
            container.getChildren().add(l);
        }

        for (String linha : codigo_fonte) {
            Label labelLinha = new Label(linha);
            labelLinha.setStyle("-fx-text-fill: #d4d4d4; -fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-padding: 2 5 2 5;");
            codigoContainer.getChildren().add(labelLinha);
        }
    }
}
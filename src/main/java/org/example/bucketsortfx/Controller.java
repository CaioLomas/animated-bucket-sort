package org.example.bucketsortfx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;

public class Controller {

    @FXML private Label lblIndice;
    @FXML private Label lblI;
    @FXML private Label lblJ;
    @FXML private Label lblK;
    @FXML private VBox codigoContainer;
    @FXML private Polygon funil;
    @FXML private HBox container;

    @FXML private VBox balde0, balde1, balde2, balde3, balde4, balde5, balde6, balde7, balde8, balde9;

    private Label[] blocosVetor = new Label[10];
    private Label[] linhasVisuais;
    private VBox[] baldesVisuais = new VBox[10];
    private int[] contagemBaldes = new int[10];

    private String[] codigo_fonte = {
            "for(int i = 0; i < 10; i++) {",
            "    indice = (10 * vetor[i] / (maior + 1));",
            "    baldes[indice][qtd_balde[indice]] = vetor[i];",
            "    qtd_balde[indice]++;",
            "}",
            "for(int i = 0; i < 10; i++) {",
            "    if(qtd_balde[i] > 0) {",
            "        insertion(baldes[i], qtd_balde[i]);",
            "    }",
            "}",
            "for(int i = 0, j = 0; i < 10; i++) {",
            "    int k = 0;",
            "    while(k < qtd_balde[i]) {",
            "        vetor[j++] = baldes[i][k];",
            "        k++;",
            "    }",
            "}"
    };

    @FXML
    public void initialize() {
        BucketSort bk = new BucketSort();
        bk.geraVetor();

        baldesVisuais[0] = balde0;
        baldesVisuais[1] = balde1;
        baldesVisuais[2] = balde2;
        baldesVisuais[3] = balde3;
        baldesVisuais[4] = balde4;
        baldesVisuais[5] = balde5;
        baldesVisuais[6] = balde6;
        baldesVisuais[7] = balde7;
        baldesVisuais[8] = balde8;
        baldesVisuais[9] = balde9;

        int[] vetorOriginal = bk.getVetor();
        for (int i = 0; i < 10; i++) {
            Label l = new Label(String.valueOf(vetorOriginal[i]));
            l.setPrefSize(80, 80);
            l.setAlignment(javafx.geometry.Pos.CENTER);
            l.setStyle("-fx-background-color: #007acc; -fx-text-fill: white; -fx-font-size: 30px; -fx-font-weight: bold; -fx-border-color: #005999; -fx-border-width: 2;");

            blocosVetor[i] = l;
            container.getChildren().add(l);
        }

        linhasVisuais = new Label[codigo_fonte.length];
        for (int i = 0; i < codigo_fonte.length; i++) {
            Label labelLinha = new Label(codigo_fonte[i]);
            labelLinha.setStyle("-fx-text-fill: #d4d4d4; -fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-padding: 2 5 2 5;");
            linhasVisuais[i] = labelLinha;
            codigoContainer.getChildren().add(labelLinha);
        }

        bk.ordenaVetor();

        iniciarMotorDeAnimacao(bk);
    }

    private void iniciarMotorDeAnimacao(BucketSort bk) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1500);

                Passo[] passos = bk.getVet_passo();
                int qtd = bk.getQtd_passos();

                for (int p = 0; p < qtd; p++) {
                    Passo passo = passos[p];

                    if (passo.getAcao().equals("PEGA_ELEMENTO") && (passo.getI_destino() == -1 || (passo.getI_destino() >= 0 && p + 1 < qtd && !passos[p+1].getAcao().equals("INSERE_VETOR")))) {
                        Platform.runLater(() -> {
                            lblI.setText("i = " + (passo.getI_origem() != -1 ? passo.getI_origem() : ""));
                        });

                        Label bloco = blocosVetor[passo.getI_origem()];
                        double fX = funil.localToScene(funil.getBoundsInLocal()).getMinX();
                        double bX = bloco.localToScene(bloco.getBoundsInLocal()).getMinX();
                        double dX = fX - bX + 20;

                        deslizarElemento(bloco, dX, -100);
                        Thread.sleep(100);
                    }

                    if (passo.getAcao().equals("INSERE_BALDE")) {
                        Platform.runLater(() -> lblIndice.setText("indice = " + passo.getI_destino()));

                        Label bloco = blocosVetor[passo.getI_origem()];
                        VBox baldeDestino = baldesVisuais[passo.getI_destino()];
                        int idBalde = passo.getI_destino();

                        Platform.runLater(() -> {
                            bloco.setOpacity(0);
                            bloco.setScaleX(0.7);
                            bloco.setScaleY(0.7);
                        });
                        Thread.sleep(300);

                        Bounds fBounds = funil.localToScene(funil.getBoundsInLocal());
                        Bounds bBounds = baldeDestino.localToScene(baldeDestino.getBoundsInLocal());
                        double baldeCentroX = bBounds.getMinX() + (bBounds.getWidth() / 2);

                        deslizarElemento(funil, 0, 135);
                        Thread.sleep(150);

                        double funilDestinoX = baldeCentroX - (fBounds.getWidth() / 2);
                        double distFunilX = funilDestinoX - fBounds.getMinX();

                        deslizarMovimentoLento(funil, distFunilX, 0, 60, 25);
                        Thread.sleep(300);

                        Platform.runLater(() -> {
                            Pane root = (Pane) funil.getScene().getRoot();
                            if (bloco.getParent() != root) {
                                Pane pai = (Pane) bloco.getParent();
                                int index = pai.getChildren().indexOf(bloco);

                                Label fantasma = new Label();
                                fantasma.setPrefSize(80, 80);
                                pai.getChildren().set(index, fantasma);

                                root.getChildren().add(bloco);
                            }
                            bloco.setLayoutX(0);
                            bloco.setLayoutY(0);

                            double blocoDestinoX = baldeCentroX - 40;
                            Bounds currentFunil = funil.localToScene(funil.getBoundsInLocal());
                            double blocoDestinoY = currentFunil.getMaxY() - 20;

                            bloco.setTranslateX(blocoDestinoX);
                            bloco.setTranslateY(blocoDestinoY);

                            bloco.toFront();
                            bloco.setOpacity(1);
                        });
                        Thread.sleep(150);

                        Bounds currentFunil = funil.localToScene(funil.getBoundsInLocal());
                        double topoBlocoY = currentFunil.getMaxY() - 20;

                        double chaoInternoY = bBounds.getMaxY() - 4;
                        double alturaOcupada = contagemBaldes[idBalde] * 85;
                        double destinoCenaY = chaoInternoY - alturaOcupada - 80;
                        double quedaReal = destinoCenaY - topoBlocoY;

                        deslizarElemento(bloco, 0, quedaReal);
                        Thread.sleep(600);

                        contagemBaldes[idBalde]++;

                        double vX = -funil.getTranslateX();
                        double vY = -funil.getTranslateY();
                        deslizarElemento(funil, vX, 0);
                        Thread.sleep(100);
                        deslizarElemento(funil, 0, vY);
                        Thread.sleep(1200);

                        Platform.runLater(() -> {
                            Pane root = (Pane) funil.getScene().getRoot();
                            root.getChildren().remove(bloco);
                            baldeDestino.getChildren().add(0, bloco);
                            bloco.setTranslateX(0);
                            bloco.setTranslateY(0);
                        });
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void deslizarElemento(Node elemento, double moverX, double moverY) throws InterruptedException {
        int totalFrames = 30;
        int tempoPorFrame = 15;

        double inicioX = elemento.getTranslateX();
        double inicioY = elemento.getTranslateY();

        double passoX = moverX / totalFrames;
        double passoY = moverY / totalFrames;

        for (int frame = 1; frame <= totalFrames; frame++) {
            final int f = frame;
            Platform.runLater(() -> {
                elemento.setTranslateX(inicioX + (passoX * f));
                elemento.setTranslateY(inicioY + (passoY * f));
            });
            Thread.sleep(tempoPorFrame);
        }
    }

    private void deslizarMovimentoLento(Node elemento, double moverX, double moverY, int frames, int delay) throws InterruptedException {
        double inicioX = elemento.getTranslateX();
        double inicioY = elemento.getTranslateY();
        double pX = moverX / frames;
        double pY = moverY / frames;

        for (int i = 1; i <= frames; i++) {
            final int f = i;
            Platform.runLater(() -> {
                elemento.setTranslateX(inicioX + (pX * f));
                elemento.setTranslateY(inicioY + (pY * f));
            });
            Thread.sleep(delay);
        }
    }

    private void destacarLinha(int indiceLinha) {
        Platform.runLater(() -> {
            for (int i = 0; i < linhasVisuais.length; i++) {
                if (i == indiceLinha) {
                    linhasVisuais[i].setStyle("-fx-text-fill: white; -fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-padding: 2 5 2 5; -fx-background-color: #005999;");
                } else {
                    linhasVisuais[i].setStyle("-fx-text-fill: #d4d4d4; -fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-padding: 2 5 2 5; -fx-background-color: transparent;");
                }
            }
        });
    }
}
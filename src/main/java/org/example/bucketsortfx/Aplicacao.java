package org.example.bucketsortfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacao extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        BucketSort bk = new BucketSort();
        bk.geraVetor();
        bk.ordenaVetor();

        FXMLLoader fxmlLoader = new FXMLLoader(Aplicacao.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }
}

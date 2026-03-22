module org.example.bucketsortfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.bucketsortfx to javafx.fxml;
    exports org.example.bucketsortfx;
}
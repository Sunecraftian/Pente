package com.sc;

import java.io.IOException;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    static String previousfxml, currentfxml;
    static String fxmlFile;
    static FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Pente v1.0.0 - Java");
        scene = new Scene(loadFXML("main-menu"), 640, 480);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        previousfxml = (fxmlLoader.getLocation().getFile() != null)
                ? fxmlLoader.getLocation().getFile().substring(fxmlLoader.getLocation().getFile().lastIndexOf("/") + 1,
                        fxmlLoader.getLocation().getFile().length() - 5)
                : "";
        currentfxml = fxml;

        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }

}
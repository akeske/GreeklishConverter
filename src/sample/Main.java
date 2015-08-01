package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    private Desktop desktop = Desktop.getDesktop();

    private Stage primaryStage;
    private Controller myController;
    private AnchorPane rooLayout;

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Greek to Greeklish Converter");

        initRootLayout();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("sample.fxml"));
            rooLayout = (AnchorPane) loader.load();
            myController = loader.getController();
            myController.setMainApp(this);

            Scene scence = new Scene(rooLayout);
            primaryStage.setResizable(false);
            primaryStage.setScene(scence);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public Desktop getDesktop() {
        return desktop;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

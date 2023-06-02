package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane anchorPane = new AnchorPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(50, 100, 100, 100));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        Label headerLabel = new Label("");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20));

        Text stText = new javafx.scene.text.Text("Stadt: ");
        gridPane.add(stText, 0, 1);
        stText.setUnderline(true);

        TextField stTextField = new TextField();
        gridPane.add(stTextField, 1, 1);

        Text timeText = new javafx.scene.text.Text("Time: ");
        gridPane.add(timeText, 0, 2);
        timeText.setUnderline(true);

        TextField timeTextField = new TextField();
        gridPane.add(timeTextField, 1, 2);

        javafx.scene.text.Text tempText = new javafx.scene.text.Text("Temparatur: ");
        gridPane.add(tempText, 0, 3);
        tempText.setUnderline(true);

        TextField tempTextField = new TextField();
        gridPane.add(tempTextField, 1, 3);

        Button loginButton = new Button("Login");
        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);
        gridPane.add(loginButton, 0, 4, 2, 1);
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setMargin(loginButton, new Insets(20));

        loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
                    headerLabel.setText(stTextField.getText() + "TEMPARATUR");
                WetterLoad wetterLoad = WetterLoad.getInstance();
                WetterInfo[] wetterInfos = wetterLoad.loads(stTextField.getText());
                for (int x = 0; x < wetterInfos.length; x++) {
                    WetterInfo wetterInfo = wetterInfos[x];
                    long temp = Math.round(Double.parseDouble(wetterInfo.getTemparature()) - 273.15);
                    timeTextField.setText(wetterInfos[0].getTimestamp());
                    tempTextField.setText(Double.toString(temp) + "° C");
                    //System.out.println(wetterInfo.getTimestamp() + ": \n" + "Temparatur: " + temp + "°C" + "\n"
                          //  + "Speed: " + wetterInfo.getSpeed() + " m/s \n" + "Humidity: " + wetterInfo.getHumidity() + " %");
                }
            }catch (Exception e){

                }
            }
        });

        FileInputStream input = new FileInputStream("image/image.jpg");
        Image image = new Image(input);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        anchorPane.setBackground(background);

        primaryStage.setTitle("JavaFX Wetter App");
        anchorPane.getChildren().add(gridPane);
        Scene scene = new Scene(anchorPane, 595, 500);
        primaryStage.setScene(scene);
        //primaryStage.getIcons().add(new javafx.scene.image.Image("/image/javaicon.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package org.example.menuapplication;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MenuApplication extends Application {

    private TextArea displayMessage = new TextArea();

    /**
     *
     * @param primary
     */

    @Override
    public void start(Stage primary) {

        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();

        Menu optionsMenuList = new Menu("Select Options");
        Menu displayColorCode = new Menu("...");

        MenuItem displayDateAndTime = new MenuItem("Show Date and Time");
        MenuItem saveFile = new MenuItem("Save to File");
        MenuItem backgroundColorChange = new MenuItem("Change Background Color");
        MenuItem primaryStageExit = new MenuItem("Exit");

        optionsMenuList.getItems().addAll(displayDateAndTime);
        optionsMenuList.getItems().addAll(saveFile);
        optionsMenuList.getItems().addAll(backgroundColorChange);
        optionsMenuList.getItems().addAll(primaryStageExit);

        menuBar.getMenus().add(optionsMenuList);
        menuBar.getMenus().add(displayColorCode);
        root.setTop(menuBar);

        root.setCenter(displayMessage);

        displayDateAndTime.setOnAction(e -> {
            String currentDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            displayMessage.setText("Current Date and Time: " + currentDateAndTime);
        });

        saveFile.setOnAction(e -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt", true))) {
                writer.write(displayMessage.getText());
                writer.newLine();
                displayMessage.setText(displayMessage.getText() + " - Saved to log.txt");
                System.out.println("Text saved to log.txt");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        backgroundColorChange.setOnAction(e -> {
            Random random = new Random();

            float hueOfColorGreen = 120;
            float saturation = random.nextFloat() * 1;
            float lightness = random.nextFloat() * 1;

            Color randomHueGreenColor = Color.hsb(hueOfColorGreen, saturation, lightness);
            displayMessage.setStyle("-fx-background-color: #" + randomHueGreenColor.toString().substring(2, 8) + ";");
            menuBar.setStyle("-fx-background-color: #" + randomHueGreenColor.toString().substring(2, 8) + ";");
            displayColorCode.setText("Color: #" + randomHueGreenColor.toString().substring(2, 8).toUpperCase());

            System.out.println("Background color changed to hue: " + randomHueGreenColor.toString().substring(2, 8));
        });

        primaryStageExit.setOnAction(e -> {
            primary.close();
        });

        // Set up the stage
        Scene scene = new Scene(root, 600, 400);
        primary.setTitle("CSC372 Critical Thinking #3");
        primary.setScene(scene);
        primary.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


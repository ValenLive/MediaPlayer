package com.example.mediaplayer;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.Objects;

public class MainApplicationEntryPoint extends Application {
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) {
        MediaPlayerApp.setStage(stage);

        String styleSheet = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane, 1280, 720);

        scene.getStylesheets().add(styleSheet);
        stackPane.getStyleClass().add("bg-4");

        stage.setScene(scene);
        //No windows cap

        stage.initStyle(StageStyle.UNDECORATED);
        ResizeHelper.addResizeListener(stage);
        stage.setIconified(false);
        stage.show();
        stage.setMaximized(false);

        //adding Media Player to Scene
        MediaView mediaView = MediaPlayerApp.createMediaView();
        stackPane.getChildren().add(mediaView);
        //Full screen
//        stage.setFullScreen(true);
        //Mute player
//        mediaPlayer.setMute(false);


        //hBox with buttons
        MediaPlayerApp.pauseAndPlayMedia();


        //Appending hBox to Pane

        StackPane horizontalStackPane1 = new StackPane(MediaPlayerApp.createTopHBox());
        StackPane horizontalStackPane2 = new StackPane(MediaPlayerApp.createBottomBox());

        horizontalStackPane1.setMinHeight(20);
        horizontalStackPane1.setMaxHeight(40);
        horizontalStackPane1.setVisible(false);
        horizontalStackPane1.getStyleClass().add("bg-5");

        horizontalStackPane2.setMinHeight(20);
        horizontalStackPane2.setMaxHeight(40);
        horizontalStackPane2.setVisible(false);
        horizontalStackPane2.getStyleClass().add("bg-2");


        Button top = new Button("__");
        Button bottom = new Button("-^-");
        VBox buttons = new VBox(top, bottom);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.CENTER);
//        StackPane.setMargin(buttons, new Insets(15));

        StackPane content = new StackPane(buttons);

        content.setOnMouseClicked(e -> {
            FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(500), horizontalStackPane1);
            fadeTransition1.setOnFinished(e1 -> horizontalStackPane1.setVisible(false));
            fadeTransition1.setFromValue(1.0);
            fadeTransition1.setToValue(0.0);
            fadeTransition1.play();

            FadeTransition fadeTransition2 = new FadeTransition(Duration.millis(500), horizontalStackPane2);
            fadeTransition2.setOnFinished(e1 -> horizontalStackPane2.setVisible(false));
            fadeTransition2.setFromValue(1.0);
            fadeTransition2.setToValue(0.0);
            fadeTransition2.play();
        });

        stackPane.getChildren().addAll(content, horizontalStackPane1, horizontalStackPane2);

        //Rendering top Pane with buttons method
        top.setOnAction(event -> {
//            horizontalStackPane1.setVisible(false);
            StackPane.setAlignment(horizontalStackPane1, Pos.TOP_CENTER);
            horizontalStackPane1.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), horizontalStackPane1);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();
        });

        //Rendering bottom Pane with buttons method
        bottom.setOnAction(event -> {
//            horizontalStackPane2.setVisible(false);
            StackPane.setAlignment(horizontalStackPane2, Pos.BOTTOM_CENTER);
            horizontalStackPane2.setVisible(true);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), horizontalStackPane2);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(1.0);
            fadeTransition.play();
        });

        //Close button method

        //Dragging window method
//        stackPane.setOnMousePressed(event -> {
//            xOffset = event.getSceneX();
//            yOffset = event.getSceneY();
//        });
//        stackPane.setOnMouseDragged(event -> {
//            stage.setX(event.getScreenX() - xOffset);
//            stage.setY(event.getScreenY() - yOffset);
//        });


    }

    public static void main(String[] args) {
        launch();
    }
}
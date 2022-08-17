package com.example.mediaplayer;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MediaPlayerApp {
    private static Button closeButton;
    private static Button fileButton;

    private static boolean isPaused = true;
    private static boolean isFullscreen = false;
    private static MediaPlayer mediaPlayer;

    private static Stage stage;

    public static void setStage(Stage stageMain) {
        stage = stageMain;
    }


    public static HBox createTopHBox() {
        closeButton = new Button("x");
        closeButton.setPrefSize(30, 30);

        fileButton = new Button("...");
        fileButton.setPrefSize(30, 30);

        HBox hBox = new HBox(fileButton, closeButton);
        HBox.setHgrow(closeButton, Priority.ALWAYS);

        hBox.setPadding(new Insets(10, 5, 10, 5));
        hBox.setAlignment(Pos.CENTER_RIGHT);

        closeButton.setOnAction(event -> closeApp());
        fileButton.setOnAction(event -> fullscreenWindow());

        hBox.setSpacing(10);


        return hBox;
    }

    public static HBox createBottomBox() {
        Button playAndPauseButton = new Button("||");
        playAndPauseButton.setPrefSize(50, 15);

        Slider timeSlider = new Slider();
//        timeSlider.setPrefWidth(900);

        Button muteButton = new Button("/");
        muteButton.setPrefSize(20, 20);

        Slider volumeSlider = new Slider();
        volumeSlider.setPrefWidth(100);
        volumeSlider.setMinWidth(30);
        volumeSlider.setValue(100);

        HBox hBox = new HBox(playAndPauseButton, timeSlider, muteButton, volumeSlider);
        HBox.setHgrow(timeSlider, Priority.ALWAYS);

        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 15, 10, 15));
        hBox.setSpacing(15);

        playAndPauseButton.setOnAction(event -> pauseAndPlayMedia());

        return hBox;
    }

    public static MediaView createMediaView() {
        Media media = new Media("file:///C:/Users/phans/Videos/jid.mp4");
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.fitWidthProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mediaView.fitHeightProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        mediaView.setPreserveRatio(true);
        return mediaView;
    }

    private static void closeApp() {
        javafx.application.Platform.exit();
    }

    //media player pause and play
    public static void pauseAndPlayMedia() {
        if (isPaused) {
            mediaPlayer.play();
            isPaused = false;
        } else {
            mediaPlayer.pause();
            isPaused = true;
        }
    }

    public static void fullscreenWindow() {
        if (isFullscreen) {
            stage.setMaximized(false);
            isFullscreen = false;
        } else {
            stage.setMaximized(true);
            isFullscreen = true;
        }
    }


}

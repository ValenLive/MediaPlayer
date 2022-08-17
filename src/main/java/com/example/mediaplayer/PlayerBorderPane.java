package com.example.mediaplayer;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class PlayerBorderPane {

    public static BorderPane createExample() {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("bg-1");
//        borderPane.setPadding(new Insets(5));

        Label top = createLabel("Top", "bg-2");
//        top.setPrefHeight(100);
        borderPane.setTop(top);

//        Label left = createLabel("Left", "bg-3");
//        left.setPrefWidth(150);
//        left.setMaxHeight(200);
//        BorderPane.setAlignment(left, Pos.BOTTOM_LEFT);
//        borderPane.setLeft(left);


//        MediaView mediaView = createMediaView();
//
//        BorderPane.setAlignment(mediaView, Pos.TOP_CENTER);
//        borderPane.setCenter(mediaView);

        BorderPane center = createPlayerPane();
//        center.setMinWidth(250);
//        center.setMaxWidth(450);
//        center.setMaxHeight(200);
        BorderPane.setAlignment(center, Pos.CENTER);
        borderPane.setCenter(center);

//        Label right = createLabel("Right", "bg-5");
//        right.setPrefWidth(75);
//        borderPane.setRight(right);

        Label bottom = createLabel("Bottom", "bg-6");
        borderPane.setBottom(bottom);

        return borderPane;
    }

    private static Label createLabel(String text, String styleClass) {
        Label label = new Label(text);
        label.getStyleClass().add(styleClass);
        BorderPane.setMargin(label, new Insets(10,10,10,10));
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }

    private static BorderPane createPlayerPane() {
        Media media = new Media("file:///C:/Users/phans/Videos/newhope.mp4");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        DoubleProperty mvWidth = mediaView.fitWidthProperty();
        DoubleProperty mvHeight = mediaView.fitHeightProperty();

        mvWidth.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvHeight.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

        mediaView.setPreserveRatio(true);


        mediaPlayer.play();


        BorderPane pane = new BorderPane();
        pane.getStyleClass().add("bg-4");
//        BorderPane.setMargin(pane, new Insets(5));
        pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        pane.setCenter(mediaView);

        return pane;
    }

    private static MediaView createMediaView(){
        Media media = new Media("file:///C:/Users/phans/Videos/newhope.mp4");
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        DoubleProperty mvWidth = mediaView.fitWidthProperty();
        DoubleProperty mvHeight = mediaView.fitHeightProperty();

        mvWidth.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvHeight.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

        mediaView.setPreserveRatio(true);

        BorderPane.setMargin(mediaView, new Insets(5));

        mediaPlayer.play();

        return mediaView;
    }

}

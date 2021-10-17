package com.liu.view.component;

import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Alert {
	public static void showAlert(String message) {
		Stage alert = new Stage();
		alert.setTitle("信息");
		alert.getIcons().add(new Image("black.png"));
		VBox messageBox = new VBox();
		Label mesLbl = new Label(message);
		JFXButton closeBtn = new JFXButton("关闭");
		closeBtn.setOnAction(e -> {
			alert.close();
		});
		messageBox.getChildren().add(mesLbl);
		messageBox.getChildren().add(closeBtn);
		messageBox.setSpacing(20);
		messageBox.setPadding(new Insets(20));
		messageBox.setAlignment(Pos.CENTER);
		messageBox.getStyleClass().add("body");
		Scene scene = new Scene(messageBox);
		scene.getStylesheets().add("modal.css");
		alert.setScene(scene);
		alert.showAndWait();
		
	}

}

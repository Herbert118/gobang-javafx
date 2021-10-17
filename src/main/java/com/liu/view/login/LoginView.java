package com.liu.view.login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.Stack;

public class LoginView extends StackPane{
	protected TextField idFld;
	protected PasswordField passwordFld;
	protected JFXButton loginBtn;
	protected GridPane loginPane;
 	public LoginView() {
 		super();
 		initialize();
 		
 	}

	private void initialize() {
		
		idFld = new TextField();
		passwordFld = new PasswordField();
		loginBtn = new JFXButton("登录");
		loginPane = new GridPane();
		Label title = new Label("五子棋棋局管理系统");
		title.setStyle("-fx-text-fill:#ffffff;"
				+"-fx-font-size:26;" +
				"-fx-font-weight: bold");
		
		//this.getStyleClass().add("loginView");
		Label passLb = new Label("密码");
		Label idLb = new Label("用户名");
			
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setHgap(10);
		loginPane.setVgap(35);
		ImageView personImg = new ImageView(new Image("person.png"));
		ImageView lockImg = new ImageView(new Image("lock.png"));
		lockImg.setFitHeight(41);
		loginPane.setPadding(new Insets(25));
		loginPane.add(personImg, 0, 0);
		loginPane.add(lockImg, 0, 1);
		loginPane.add(idFld, 1, 0);
		loginPane.add(passwordFld, 1, 1);
		
		Pane rec = new Pane();
		rec.setPrefSize(100, 100);
		rec.setMaxSize(450, 500);
		rec.setStyle("-fx-background-color:#686868;"
				+"-fx-background-radius:5");
		idFld.setStyle("-fx-background-color:#ffffff;" +
				"-fx-font-size: 20;" +
				"-fx-pref-width: 270;" +
				"-fx-pref-height: 43");
		passwordFld.setStyle("-fx-background-color:#ffffff;" +
				"-fx-font-size: 20;" +
				"-fx-pref-width: 270;" +
				"-fx-pref-height: 43");
		passLb.setStyle("-fx-text-fill:#ffffff;"+
		"-fx-font-size:20");
		idLb.setStyle("-fx-text-fill:#ffffff;"
				+"-fx-font-size:20");

		loginBtn.setStyle(
		"-fx-text-fill:#ffffff;"+
		"-fx-font-size:20;"+
		"-fx-pref-width: 300;" +
				"-fx-background-color: #2B2B2B");
		loginBtn.setPadding(new Insets(7,30,7,30));

		ImageView titlePng = new ImageView(new Image("title.png"));
		ImageView black  =new ImageView(new Image(("black.png")));
		ImageView white = new ImageView(new Image("white.png"));


		this.getChildren().addAll(titlePng,black,white,rec,loginPane,loginBtn,title);
		this.setAlignment(Pos.CENTER);
		StackPane.setAlignment(rec, Pos.CENTER);
		StackPane.setAlignment(loginPane,Pos.CENTER);
		StackPane.setAlignment(loginBtn,Pos.CENTER);
		StackPane.setAlignment(title, Pos.CENTER);
		StackPane.setMargin(titlePng,new Insets(-650,0,0,0));
		StackPane.setMargin(black,new Insets(0,600,0,0));
		StackPane.setMargin(white,new Insets(0,0,0,600));
		StackPane.setMargin(loginBtn, new Insets(280,0,0,0));
		StackPane.setMargin(title, new Insets(-280,0,0,0));
	}
	
}

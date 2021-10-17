package com.liu.mainapp;

import com.liu.model.dataModel.User;
import com.liu.model.dataService.Service;
import com.liu.view.component.Alert;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Main extends Application {

    private Stage primaryStage;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Router router = Router.getInstance(this);
        router.setUser(new User("name","id", "123pass","254@qq.com"));
        //router.navToLoginView();
        //router.navToUserMenuView();
        //router.navToAdminMenuView();
        router.navToLoginView();
        //Service service = Service.getInstance();

        primaryStage.setResizable(false);
        primaryStage.setTitle("五子棋");
        primaryStage.getIcons().add(new Image("black.png"));
        primaryStage.setOnCloseRequest(e ->{
            Service.getInstance().saveFile();
        });
    }

    public static void main(String [] args) {
        Application.launch(args);
    }

    public void setStageScene(Scene newScene) {
        this.primaryStage.setScene(newScene);
        primaryStage.show();
    }
}

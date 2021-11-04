package com.liu.view.user.playFrame;

import com.liu.model.dataModel.Save;
import com.liu.model.dataModel.User;
import com.liu.model.dataService.Service;
import com.liu.view.component.Alert;
import com.liu.view.user.board.BoardViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.liu.mainapp.Router;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;

public class PlayFrameController {



    @FXML
    private VBox stateArea;

    @FXML
    private ImageView stateImg;

    Service service;
    Router router;

    public PlayFrameController(BoardViewController boardViewController, User user, String blackPlayer, String whitePlayer) {
        this.boardViewController = boardViewController;
        this.user = user;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;

        initInsideController();
    }

    private BoardViewController boardViewController;
    private User user;
    private String blackPlayer;
    private String whitePlayer;



    @FXML
    void initialize(){
        router = Router.getInstance();
        service = Service.getInstance();
    }

    @FXML
    void config(ActionEvent event) {
        Alert.showAlert("功能尚未开放,请耐心等待");
    }

    @FXML
    void exit(ActionEvent event) {
        router.navToUserMenuView();
    }

    @FXML
    void regret(ActionEvent event) {
        boardViewController.regret();
    }

    @FXML
    void restart(ActionEvent event) {
        router.navToGameView();
    }

    @FXML
    void save(ActionEvent event) {
        Save save = new Save(boardViewController.getStepList(),false,
                LocalDateTime.now(), user.getId(),blackPlayer,whitePlayer,null);
        if(service.addSave(user.getId(), service.saveClone(save))){//clone to make sure it is different object
            Alert.showAlert("保存成功!");
        }
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void initInsideController() {

        boardViewController.sideProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                stateImg.setImage(new Image(newValue+"Char.png"));
                stateImg.setPreserveRatio(true);
                stateImg.setFitWidth(120);
            }
        });

        boardViewController.endProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                Save review = new Save(boardViewController.getStepList(),true, LocalDateTime.now(), user.getId(),blackPlayer,whitePlayer,null);
                review.setWinner(
                        boardViewController.sideProperty().get().equals("black")? blackPlayer: whitePlayer);
                service.addSave(user.getId(), review);
                Alert.showAlert(review.getWinner()+"胜!\n回放已保存");
                router.navToUserMenuView();
            }
            //默认玩家执黑,可能改动//TODO:in case for change
        });
    }
}

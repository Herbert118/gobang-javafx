package com.liu.view.user.userMenu;

import com.liu.mainapp.Router;
import com.liu.model.dataModel.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserMenuController {
    Router router;
    private User user;
    @FXML
    private ImageView titleImg;
    @FXML
    void initialize() {
        router = Router.getInstance();
        titleImg.setImage(new Image("title.png"));
        titleImg.setPreserveRatio(true);
        titleImg.setFitHeight(135);
    }

    @FXML
    void navToGameView(ActionEvent event) {
        router.navToGameView();
    }

    @FXML
    void navToLoadView(ActionEvent event) {
        router.navToLoadView();
    }

    @FXML
    void navToReviewView(ActionEvent event) {
        router.navToReviewView();
    }
    @FXML
    void logOut(ActionEvent event){router.navToLoginView();}
    public void setUser(User user) {
        this.user = user;
    }
}

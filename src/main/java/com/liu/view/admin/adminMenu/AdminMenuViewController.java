package com.liu.view.admin.adminMenu;

import com.liu.mainapp.Router;
import com.liu.model.dataModel.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AdminMenuViewController {
    Router router;
    private User user;
    private User admin;
    @FXML
    private ImageView titleImg;

    @FXML
    void initialize() {
        titleImg.setImage(new Image("title.png"));
        titleImg.setPreserveRatio(true);
        titleImg.setFitHeight(135);
        router = Router.getInstance();
    }

    @FXML
    void navToUserManageView(ActionEvent event) {
        router.navToUserManageView();
    }

    @FXML
    void navToReviewManageView(ActionEvent event) {
        router.navToReviewManageView();
    }

    @FXML
    void navToSystemManageView(ActionEvent event) {
        router.navToSystemManageView();
    }

    @FXML
    void logOut(ActionEvent event){router.navToLoginView();}

    public void setUser(User user) {
        this.user = user;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
}

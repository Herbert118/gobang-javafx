package com.liu.view.user.review;

import com.liu.mainapp.Router;
import com.liu.model.dataModel.Save;
import com.liu.model.dataModel.User;
import com.liu.view.component.Alert;
import com.liu.view.user.board.BoardViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ReviewFrameViewController {
    @FXML
    private VBox stateArea;

    Router router;
    private BoardViewController controller;
    private Save review;
    private User user;
    @FXML
    void  initialize(){
        router = Router.getInstance();
        controller.
                review(this.review);
    }
    @FXML
    private ImageView stateImg;

    @FXML
    void config(ActionEvent event) {
        Alert.showAlert("功能尚未开放,请耐心等待");
    }

    @FXML
    void exit(ActionEvent event) {
        //TODO:in case for change\
        System.out.println(user.getAuthority());
        if(user.getAuthority().equals("user")){
            router.navToReviewView();
        }
        else {
            router.navToReviewManageView();
        }
    }

    @FXML
    void restart(ActionEvent event) {
        router.navToReviewFrameView(this.review);
    }


    public void setUser(User user){
        this.user = user;
    }
    public void setInsideController(BoardViewController controller){
        this.controller = controller;
    }
    public void setReview(Save review){
        this.review = review;
    }
}

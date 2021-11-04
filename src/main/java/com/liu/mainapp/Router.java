package com.liu.mainapp;

import com.liu.model.dataModel.Save;
import com.liu.model.dataModel.User;
import com.liu.view.admin.adminMenu.AdminMenuViewController;
import com.liu.view.admin.reviewManage.ReviewManageViewController;
import com.liu.view.admin.userManage.UserManageViewController;
import com.liu.view.component.Alert;
import com.liu.view.login.LoginView;
import com.liu.view.login.LoginViewController;
import com.liu.view.user.load.LoadViewController;
import com.liu.view.user.playFrame.MatchInfoModal;
import com.liu.view.user.review.ReviewFrameViewController;
import com.liu.view.user.review.ReviewViewController;
import com.liu.view.user.userMenu.UserMenuController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import com.liu.view.user.board.BoardView;
import com.liu.view.user.board.BoardViewController;
import com.liu.view.user.playFrame.PlayFrameController;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;

public class Router {
    private static Router router;
    private static Main app;
    private User user;
    private String from;
    private Router(Main app) {
        this.app = app;
    }
    protected static Router getInstance(Main app) {
        if(router == null) {
            router = new Router(app);
        }
        return router;
    }

    public static Router getInstance() {
        if(router == null||app == null) {
            throw new IllegalArgumentException("router is not initialized!");
        }
        return router;
    }
    public void setUser(User user){
        this.user = user;
    }

    public void setSceneRoot(Parent root){
        //double width = (int) Screen.getPrimary().getBounds().getWidth();
        //double height = (int) Screen.getPrimary().getBounds().getHeight();
        double width = 1200;
        double height = 950;
        Scene scene = new Scene(root,width,height);
        scene.getStylesheets().add("styleSheet.css");
        app.setStageScene(scene);
    }

    public void initToMenu() {//User user

    }



    public void navToGameView() {
        String [] infoArr = new String[2];
        System.out.println(infoArr);
        infoArr[0] = null;
        infoArr[1] = null;
        MatchInfoModal modal = new MatchInfoModal(infoArr);
        if(infoArr[0]==null||infoArr[1] == null){
            Alert.showAlert("请填写比赛信息!");
            return;
        }


        BoardView board = new BoardView(45);
        BoardViewController controllerB = new BoardViewController(board);

        //加载fxml文件和相应的控制器
        FXMLLoader loader = new FXMLLoader();
        URL url = this.getClass().getResource("/com/liu/view/user/playFrame/playFrame.fxml");
        System.out.println(url);
        loader.setLocation(url);
        PlayFrameController controllerP = new PlayFrameController(controllerB,user,infoArr[0],infoArr[1]);
        //TODO:add choice
        loader.setController(controllerP);
        BorderPane playFrame = null;
        try {
            playFrame = loader.load();
            playFrame.setRight(null);
            playFrame.setBottom(null);
            playFrame.setCenter(board);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(playFrame!=null){
            setSceneRoot(playFrame);
        }
        //new User("name","id", "123pass","254@qq.com")

    }
    public void navToGameView(Save save) {
        BoardView board = new BoardView(45);
        BoardViewController controllerB = new BoardViewController(board);
        //加载fxml文件和相应的控制器
        FXMLLoader loader = new FXMLLoader();
        URL url = this.getClass().getResource("/com/liu/view/user/playFrame/playFrame.fxml");
        System.out.println(url);
        loader.setLocation(url);
        PlayFrameController controllerP = new PlayFrameController(controllerB,user,save.getBlackPlayer(),save.getWhitePlayer());
        //TODO:add choice

        //loadSavehere
        BorderPane playFrame = null;
        try {
            loader.setController(controllerP);
            playFrame = loader.load();
            playFrame.setRight(null);
            playFrame.setBottom(null);
            playFrame.setCenter(board);
            controllerB.loadSave(save);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(playFrame!=null){
            setSceneRoot(playFrame);
        }
        //new User("name","id", "123pass","254@qq.com")

    }


    public void navToUserMenuView() {//without user
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/com/liu/view/user/userMenu/userMenuView.fxml"));
        UserMenuController controller = new UserMenuController();
        controller.setUser(user);
        loader.setController(controller);
        Parent userMenuView = null;
        try {
            userMenuView = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(userMenuView!= null){
            setSceneRoot(userMenuView);
        }
    }

    public void navToLoadView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/com/liu/view/user/load/loadView.fxml"));
        LoadViewController controller = new LoadViewController();
        controller.setUser(user);
        loader.setController(controller);
        Parent loadView = null;
        try {
            loadView =  loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(loadView!=null){
            setSceneRoot(loadView);
        }
    }

    public void navToReviewView() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/com/liu/view/user/review/reviewView.fxml"));
        ReviewViewController controller = new ReviewViewController();
        controller.setUser(user);
        loader.setController(controller);
        Parent reviewView = null;
        try {
            reviewView =  loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(reviewView!=null){
            setSceneRoot(reviewView);
        }
    }




    public void navToReviewFrameView(Save review){
        BoardView board = new BoardView(45);
        BoardViewController controllerB = new BoardViewController(board);

        FXMLLoader loader = new FXMLLoader();
        URL url = this.getClass().getResource("/com/liu/view/user/review/reviewFrameView.fxml");
        System.out.println(url);
        loader.setLocation(url);
        ReviewFrameViewController controllerR = new ReviewFrameViewController();
        controllerR.setInsideController(controllerB);
        controllerR.setReview(review);
        controllerR.setUser(user);
        loader.setController(controllerR);
        try {
            BorderPane reviewFrameView = loader.load();
            reviewFrameView.setCenter(board);
            reviewFrameView.setBottom(null);
            reviewFrameView.setRight(null);
            setSceneRoot(reviewFrameView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navToAdminMenuView(){
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(this.getClass().getResource("/com/liu/view/admin/adminMenu/adminMenuView.fxml"));
        AdminMenuViewController controller = new AdminMenuViewController();
        controller.setAdmin(user);//admin
        loader.setController(controller);
        try {
            Parent adminMenuView = loader.load();
            setSceneRoot(adminMenuView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navToUserManageView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/com/liu/view/admin/userManage/userManageView.fxml"));
        UserManageViewController controller = new UserManageViewController();
        controller.setAdmin(user);
        loader.setController(controller);
        try {
            Parent adminMenuView = loader.load();
            setSceneRoot(adminMenuView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void navToReviewManageView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/com/liu/view/admin/reviewManage/reviewManageView.fxml"));
        ReviewManageViewController controller = new ReviewManageViewController();
        controller.setAdmin(user);
        loader.setController(controller);
        try {
            Parent reviewManageView = loader.load();
            setSceneRoot(reviewManageView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void navToSystemManageView() {

    }

    public void navToLoginView(){
        LoginView view = new LoginView();
        LoginViewController loginController = new LoginViewController(view);
        view.setPrefHeight(200);
        view.setPrefWidth(200);
        StackPane pane = new StackPane();
        pane.getChildren().add(view);
        view.setAlignment(Pos.CENTER);
        setSceneRoot(pane);
    }
}

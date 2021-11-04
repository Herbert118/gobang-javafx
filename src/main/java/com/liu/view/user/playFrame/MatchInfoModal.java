package com.liu.view.user.playFrame;

import com.jfoenix.controls.JFXButton;
import com.liu.util.CheckUtil;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class MatchInfoModal {
    String [] infoArr;
    Stage modal;
    Scene modalScene;
    VBox modalRoot;
    HBox blackBox;
    HBox whiteBox;
    HBox vsBox;
    TextField blackPlayerFld;
    TextField whitePlayerFld;
    JFXButton confirmBtn;
    private Text warnText;

    public MatchInfoModal(String [] infoArr){
        init();
        this.infoArr = infoArr;
        modal = new Stage();
        modalScene = new Scene(modalRoot);
        modalScene.getStylesheets().add("modal.css");
        modal.setScene(modalScene);
        modal.setTitle("比赛信息");
        modal.showAndWait();

    }
    private void init(){
        modalRoot = new VBox();
        modalRoot.setAlignment(Pos.TOP_CENTER);
        blackBox = new HBox();
        whiteBox = new HBox();
        vsBox = new HBox();
        blackPlayerFld = new TextField();
        whitePlayerFld = new TextField();
        confirmBtn = new JFXButton("确认");
        warnText = new Text();

        modalRoot.setPadding(new Insets(30));
        modalRoot.setSpacing(30);
        blackBox.setAlignment(Pos.CENTER);
        whiteBox.setAlignment(Pos.CENTER);
        vsBox.setAlignment(Pos.CENTER);

        blackBox.getChildren().addAll(new Label("黑方"),blackPlayerFld);
        vsBox.getChildren().add(new Label("vs"));
        whiteBox.getChildren().addAll(new Label("白方"),whitePlayerFld);


        confirmBtn.setOnAction(this::confirm);

        warnText.setFill(Color.RED);

        modalRoot.getChildren().addAll(blackBox,vsBox,whiteBox,confirmBtn);
    }

    private void confirm(ActionEvent event){
        String blackPlayer = blackPlayerFld.getText();
        String whitePlayer = whitePlayerFld.getText();
        if(check(blackPlayer,whitePlayer)){
            infoArr[0] = blackPlayer;
            infoArr[1] = whitePlayer;

        }
        modal.close();

    }

    private boolean check(String blackPlayer, String whitePlayer){
        boolean result = true;
        String text ="";
        if(!CheckUtil.checkName(blackPlayer)){
            result =false;
            text+="黑方棋手信息";
        }
        if(!CheckUtil.checkName(whitePlayer)){
            result = false;
            text += "白方棋手信息";
        }
        if(!result){
            text += "有误";
            warnText.setText(text);
        }
        else{
            warnText.setText("");
        }
        return  result;
    }


}

package com.liu.view.admin.userManage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.liu.model.dataModel.User;
import com.liu.model.dataService.Service;
import com.liu.util.CheckUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;

public class UserModal {
    private Service service;
    private Stage staffModal;
    private VBox modalRoot;
    private GridPane fieldGridPane;
    private TextField nameFld;
    private TextField idFld;
    private PasswordField passwordFld;
    private PasswordField checkPasswordFld;
    private JFXComboBox<String> authorityBox;
    private TextField emailFld;
    private JFXButton confirmButton;
    private Text warnText;
    public UserModal(Service service){
        this.service = service;
        init();
        lay();
    }
    private void init(){
        staffModal = new Stage();
        modalRoot = new VBox();
        fieldGridPane = new GridPane();
        nameFld = new TextField();
        passwordFld = new PasswordField();
        checkPasswordFld = new PasswordField();

        idFld = new TextField();
        emailFld = new TextField();
        confirmButton = new JFXButton("确认");
        warnText = new Text();
        authorityBox = new JFXComboBox<String>();
        authorityBox.getItems().addAll("普通用户","管理员");
    }
    private void lay(){
        fieldGridPane.add(new Label("用户名"),0,0);
        fieldGridPane.add(new Label(("密码")),0,1);
        fieldGridPane.add(new Label("确认密码"),0,2);
        fieldGridPane.add(new Label("姓名"),0,3);
        fieldGridPane.add(new Label("email"),0,4);
        fieldGridPane.add(new Label("权限"),0,5);

        fieldGridPane.add(idFld,1,0);
        fieldGridPane.add(passwordFld,1,1);
        fieldGridPane.add(checkPasswordFld,1,2);
        fieldGridPane.add(nameFld,1,3);
        fieldGridPane.add(emailFld,1,4);
        fieldGridPane.add(authorityBox,1,5);

        fieldGridPane.setHgap(20);
        fieldGridPane.setVgap(20);
        fieldGridPane.setAlignment(Pos.CENTER);
        fieldGridPane.setPadding(new Insets(50,30,30,30));

        modalRoot.getChildren().add(fieldGridPane);
        modalRoot.getChildren().add(confirmButton);
        modalRoot.setAlignment(Pos.CENTER);

        VBox.setMargin(confirmButton,new Insets(30));
        modalRoot.getChildren().add(warnText);
        VBox.setMargin(warnText, new Insets(30,0,0,0));
        warnText.setFill(Color.RED);

    }

    public void addUser(){
        confirmButton.setOnAction(e ->{
            String name = nameFld.getText();
            String id = idFld.getText();
            String password = passwordFld.getText();
            String email = emailFld.getText();
            String authority = authorityBox.getValue();
            String checkPassword = checkPasswordFld.getText();
            authority = "管理员".equals(authority)?"admin":"user";
            if(!check(name, id,password,email,authority)){
                return;
            }
            if(!password.equals(checkPassword)){
                warnText.setText("密码不一致！");
                return;
            }

            else{
                if(service.addUser(name, id,password,email,authority)){
                    warnText.setText("添加成功!");
                }
                else{
                    warnText.setText("添加失败!");
                }
                //TODO: better add a check
            }
        });

        modalRoot.getStyleClass().add("body");
        Scene scene = new Scene(modalRoot);
        staffModal.setScene(scene);
        scene.getStylesheets().add("modal.css");
        staffModal.setTitle("新增用户");
        staffModal.showAndWait();
    }

    public void updateUser(User oldUser) {
        nameFld.setText(oldUser.getName());
        emailFld.setText(oldUser.getEmail());
        idFld.setText(oldUser.getId());
        passwordFld.setText(oldUser.getPassword());
        authorityBox.setValue(oldUser.getAuthority());


        confirmButton.setOnAction(e ->{
            String name = nameFld.getText();
            String id = idFld.getText();
            String password = passwordFld.getText();
            String authority = authorityBox.getValue();
            authority = "管理员".equals(authority)?"admin":"user";
            String checkPassword = checkPasswordFld.getText();
            String email = emailFld.getText();
            if(!check(name, id,password,email,authority)){
                return;
            }
            if(!password.equals(checkPassword)){
                warnText.setText("密码不一致！");
                return;
            }

            else{
                if(service.updateUser(oldUser,name, id,password,email,authority)){
                    warnText.setText("修改成功!");
                }
                else{
                    warnText.setText("修改失败!");
                }
                //TODO: better add a check
            }
        });

        Scene scene = new Scene(modalRoot);
        staffModal.setScene(scene);
        scene.getStylesheets().add("modal.css");
        staffModal.setTitle("修改职工");
        staffModal.showAndWait();
    }

    private boolean check(String name, String id, String password, String email,String authority) {
        boolean result = true;
        String text = "";
        if (!CheckUtil.checkName(name)){
            text += "姓名 ";
            result = false;
        }
        if (!CheckUtil.checkNotBlank(id)){
            text += "用户名";
            result = false;
        }
        if(!CheckUtil.checkEmail(email)){
            text += "联系电话 " ;
            result = false;
        }
        if (!CheckUtil.checkPassword(password)){
            text += "密码 ";
            result = false;
        }
        if (!CheckUtil.checkAuthority(authority)){
            text += "权限";
            result = false;
        }
        if(result == false){
            warnText.setText(text+"格式有误");
        }
        return result;

    }
}

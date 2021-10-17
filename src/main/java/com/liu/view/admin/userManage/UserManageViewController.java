package com.liu.view.admin.userManage;

import com.liu.mainapp.Router;
import com.liu.model.dataModel.User;
import com.liu.model.dataService.Service;
import com.liu.view.component.ConfirmAlert;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import com.liu.view.component.Alert;

public class UserManageViewController {
    @FXML
    private TableColumn<User, String> authorityCol;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private TableColumn<User, String> idCol;

    @FXML
    private TableColumn<User, String> nameCol;

    @FXML
    private TextField searchFld;

    @FXML
    private TableView<User> userTable;

    @FXML
    private Label title;

    Router router;
    Service service;
    TableSelectionModel<User> tsm;
    private User admin;

    @FXML
    void initialize(){
        router = Router.getInstance();
        service = Service.getInstance();
        initTable();
    }
    void initTable(){
        userTable.setItems(FXCollections.observableList(service.getUserList()));
        authorityCol.setCellValueFactory(cellData->{
            return new SimpleStringProperty(cellData.getValue().getAuthority().equals("admin")?"管理员":"普通用户");
        });
        emailCol.setCellValueFactory(cellData->{
            return new SimpleStringProperty(cellData.getValue().getEmail());
        });
        idCol.setCellValueFactory(cellData->{
            return new SimpleStringProperty(cellData.getValue().getId());
        });
        nameCol.setCellValueFactory(cellData->{
            return new SimpleStringProperty(cellData.getValue().getName());
        });
        tsm = userTable.getSelectionModel();
    }
    @FXML
    void addUser(ActionEvent event) {
        UserModal modal = new UserModal(service);
        modal.addUser();
        userTable.setItems(FXCollections.observableList(service.getUserList()));
    }

    @FXML
    void deleteUser(ActionEvent event) {
        User user = tsm.getSelectedItem();
        if(user == null){
            Alert.showAlert("请先选择！");
            return;
        }
        if(ConfirmAlert.getConfirm("您确定要删除这名用户吗?")){
            service.deleteUser(user);
            userTable.setItems(FXCollections.observableList(service.getUserList()));
        }

    }

    @FXML
    void searchUser(ActionEvent event) {
        String text = searchFld.getText();
        if(text==null){
            return;
        }
        if(text.isEmpty()){
            return;
        }
        userTable.setItems(FXCollections.observableList(service.searchUser(text)));
    }
    @FXML
    void updateUser(){
        User user = tsm.getSelectedItem();
        if(user == null){
            Alert.showAlert("请先选择一个用户！");
            return;
        }
        UserModal modal = new UserModal(service);
        modal.updateUser(user);
        userTable.setItems(FXCollections.observableList(service.getUserList()));
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }
    @FXML
    void navBack(){
        router.navToAdminMenuView();
    }
}

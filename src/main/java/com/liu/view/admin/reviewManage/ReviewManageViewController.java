package com.liu.view.admin.reviewManage;

import com.liu.mainapp.Router;
import com.liu.model.dataModel.Save;
import com.liu.model.dataModel.User;
import com.liu.model.dataService.Service;
import com.liu.view.component.Alert;
import com.liu.view.component.ConfirmAlert;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;

public class ReviewManageViewController {
    @FXML
    private TableColumn<Save, String> playersCol;

    @FXML
    private TableView<Save> reviewTable;

    @FXML
    private TableColumn<Save, String> timeCol;
    @FXML
    private TableColumn<Save,String> userCol;

    @FXML
    private HBox vbox;

    @FXML
    private TableColumn<Save, String> winnerCol;
    @FXML
    private TextField searchFld;
    Router router;
    Service service;
    private User admin;

    @FXML
    void initialize(){
        router = Router.getInstance();
        service = Service.getInstance();
        initTable();
    }


    private void initTable() {
        reviewTable.setItems(FXCollections.observableList(service.getallReviewList()));
        playersCol.setCellValueFactory(cellData ->{
            return new SimpleStringProperty(
                    cellData.getValue().getBlackPlayer()+" vs "+cellData.getValue().getWhitePlayer());
        });
        winnerCol.setCellValueFactory(cellData->{
            return new SimpleStringProperty(cellData.getValue().getWinner());
        });
        timeCol.setCellValueFactory(cellData->{
            return new SimpleStringProperty(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getTime()));
        });
        userCol.setCellValueFactory(cellData->{
            return new SimpleStringProperty(cellData.getValue().getUserId());
        });


    }

    @FXML
    void deleteReview(ActionEvent event) {
        TableSelectionModel<Save> tsm = reviewTable.getSelectionModel();
        Save review = tsm.getSelectedItem();
        if(review == null){
            Alert.showAlert("请先选择一局回放!");
            return;
        }
        if(ConfirmAlert.getConfirm("您确定要删除这场回放吗?")){
            service.deleteSave(review);
        }
        reviewTable.setItems(FXCollections.observableList(service.getallReviewList()));
    }

    @FXML
    void viewReview(ActionEvent event) {
        TableSelectionModel<Save> tsm = reviewTable.getSelectionModel();
        Save review = tsm.getSelectedItem();
        if(review == null){
            Alert.showAlert("请先选择一局回放!");
            return;
        }
        router.navToReviewFrameView(review);
    }

    public void setAdmin(User user) {
        this.admin = user;
    }
    @FXML
    void searchReview(ActionEvent event){
        String text = searchFld.getText();
        if(text==null){
            return;
        }
        if(text.isEmpty()){
            return;
        }
        reviewTable.setItems(FXCollections.observableList(service.searchReview(text)));

    }
    @FXML
    void navBack(){
        router.navToAdminMenuView();
    }
}

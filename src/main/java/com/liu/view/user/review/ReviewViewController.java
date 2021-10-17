package com.liu.view.user.review;

import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;

public class ReviewViewController {
    @FXML
    private TableColumn<Save, String> players;

    @FXML
    private TableView<Save> reviewTable;

    @FXML
    private TableColumn<Save, String> time;

    @FXML
    private HBox vbox;

    @FXML
    private TableColumn<Save, String> winner;
    @FXML
    private TextField searchFld;

    Router router;
    Service service;
    User user;

    @FXML
    void initialize(){
        router = Router.getInstance();
        service = Service.getInstance();
        initTable();
    }

    public void setUser(User user){
        this.user = user;
    }

    private void initTable() {
        reviewTable.setItems(FXCollections.observableList(service.getReviewList(user.getId())));
        players.setCellValueFactory(cellData ->{
            return new SimpleStringProperty(
                    cellData.getValue().getBlackPlayer()+" vs "+cellData.getValue().getWhitePlayer());
        });
        winner.setCellValueFactory(cellData->{
            return new SimpleStringProperty(cellData.getValue().getWinner());
        });
        time.setCellValueFactory(cellData->{
            return new SimpleStringProperty(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getTime()));
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
        reviewTable.setItems(FXCollections.observableList(service.getReviewList(user.getId())));
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

    @FXML
    void searchReview(ActionEvent event){
        String text = searchFld.getText();
        if(text==null){
            return;
        }
        if(text.isEmpty()){
            reviewTable.setItems(FXCollections.observableList(service.getReviewList(user.getId())));
        }
        reviewTable.setItems(FXCollections.observableList(service.searchReview(user.getId(),text)));
    }
    @FXML
    void navBack(){
        router.navToUserMenuView();
    }

}

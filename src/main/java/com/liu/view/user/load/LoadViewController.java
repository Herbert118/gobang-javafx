package com.liu.view.user.load;

import com.liu.mainapp.Router;
import com.liu.model.dataModel.Save;
import com.liu.model.dataModel.User;
import com.liu.model.dataService.Service;
import com.liu.view.component.Alert;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class LoadViewController {
    @FXML
    private TableView<Save> loadTable;

    @FXML
    private TableColumn<Save, String> players;

    @FXML
    private TableColumn<Save, String> time;

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
        loadTable.setItems(FXCollections.observableList(service.getSaveList(user.getId())));
        players.setCellValueFactory(cellData ->{
            return new SimpleStringProperty(
                    cellData.getValue().getBlackPlayer()+" vs "+cellData.getValue().getWhitePlayer());
        });
        time.setCellValueFactory(cellData->{
            return new SimpleStringProperty(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(cellData.getValue().getTime()));
        });

    }


    @FXML
    void deleteSave(ActionEvent event) {

    }

    @FXML
    void loadSave(ActionEvent event) {
        TableSelectionModel<Save> tsm = loadTable.getSelectionModel();
        Save save = tsm.getSelectedItem();
        if(save==null){
            Alert.showAlert("请先选择一个存档!");
            return;
        }
        router.navToGameView(save);
    }

    @FXML
    void navBack(){
        router.navToUserMenuView();
    }
}

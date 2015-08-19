package Conrtoller;

import Model.Contracts.Accumulator;
import Model.Contracts.BasicContract;
import Model.DAOs.DataBaseManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Maksim on 18.08.2015.
 */
public class AdviserController {
    @FXML
    private TableView<BasicContract> contractTable;

    @FXML
    private TableColumn<BasicContract, String> idColumn;
    @FXML
    private TableColumn<BasicContract, String> bankColumn;
    @FXML
    private TableColumn<BasicContract, String> typeColumn;
    @FXML
    private TableColumn<BasicContract, Date> startColumn;
    @FXML
    private TableColumn<BasicContract, Date> finishColumn;
    @FXML
    private TableColumn<BasicContract, String> buyColumn;
    @FXML
    private TableColumn<BasicContract, String> sellColumn;
    @FXML
    private TableColumn<BasicContract, Double> valueColumn;
    @FXML
    private TableColumn<BasicContract, Integer> leverageColumn;
    @FXML
    private TableColumn<BasicContract, Double> spotRefColumn;
    @FXML
    private TableColumn<BasicContract, Boolean> isCloseColumn;
    @FXML
    private TableColumn<BasicContract, Integer> quantityColumn;
    @FXML
    private TableColumn<BasicContract, Double> highStrikeColumn;
    @FXML
    private TableColumn<BasicContract, Double> lowStrikeColumn;
    @FXML
    private TableColumn<BasicContract, Double> knockoutColumn;
    @FXML
    private TableColumn<BasicContract, Double> pivotColumn;

    public AdviserController() {

    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("contractId"));
        bankColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("bank"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("contractType"));
        startColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Date>("dateStart"));
        finishColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Date>("dateFinish"));
        buyColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("buyAsset"));
        sellColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("sellAsset"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("assetValue"));
        leverageColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Integer>("leverage"));
        spotRefColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("spotRef"));
        isCloseColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Boolean>("isClose"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Integer>("quantity"));
        highStrikeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("highStrike"));
        lowStrikeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("lowStrike"));
        knockoutColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("knockout"));
        pivotColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("pivot"));
    }

    @FXML
    private void handleGetAllContracts() {
        try {
            contractTable.getItems().clear();
            List<Accumulator> tempList = DataBaseManager.getInstance("jdbc:sqlite:spivverydb").getAccDAO().getAllItems();
            contractTable.getItems().addAll(tempList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

package Conrtoller;

import Model.Contracts.Accumulator;
import Model.Contracts.BasicContract;
import Model.Contracts.Pivot;
import Model.Contracts.Strike;
import Model.DAOs.BasicContractDAO;
import Model.DAOs.ServiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public class RealContractsController {
    private ObservableList<BasicContract> basicContractsData = FXCollections.observableArrayList();

    @FXML
    private TableView<BasicContract> tableBasicContracts;
    @FXML
    private TableColumn<BasicContract, String> idColumn;
    @FXML
    private TableColumn<BasicContract, String> bankColumn;
    @FXML
    private TableColumn<BasicContract, String> typeColumn;
    @FXML
    private TableColumn<BasicContract, String> startColumn;
    @FXML
    private TableColumn<BasicContract, String> finishColumn;
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
    private TableColumn<BasicContract, Double> highStrikeColumn;
    @FXML
    private TableColumn<BasicContract, Double> lowStrikeColumn;
    @FXML
    private TableColumn<BasicContract, Double> knockoutColumn;
    @FXML
    private TableColumn<BasicContract, Double> pivotColumn;

    @FXML
    private void initialize() {
        getData();

        idColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("id"));
        bankColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("bank"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("type"));
        startColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("start"));
        finishColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("finish"));
        buyColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("buy"));
        sellColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("sell"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("value"));
        leverageColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Integer>("leverage"));
        spotRefColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("spotRef"));
        isCloseColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Boolean>("isClose"));
        highStrikeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("highStrike"));
        lowStrikeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("lowStrike"));
        knockoutColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("knockout"));
        pivotColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("pivot"));

        tableBasicContracts.setItems(basicContractsData);
    }

    public void getData() {
        ServiceManager sm = ServiceManager.getInstance("spivverydb");
        try{
            List<BasicContract> basicContracts = sm.getBasicContractDAO().getAllBasicContracts();
            basicContractsData.addAll(basicContracts);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



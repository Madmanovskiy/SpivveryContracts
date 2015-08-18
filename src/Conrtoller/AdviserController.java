package Conrtoller;

import Model.Contracts.BasicContract;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.Date;
import java.util.List;

/**
 * Created by Maksim on 18.08.2015.
 */
public class AdviserController {
    @FXML
    private TableView<BasicContract>contractTable;

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
    private void initialize(){

    }

    @FXML
    private void handleGetAllContracts(){
        contractTable.getItems().clear();

//        contractTable.getItems().addAll()
    }

}

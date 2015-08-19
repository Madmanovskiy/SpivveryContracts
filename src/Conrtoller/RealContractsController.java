//package Conrtoller;
//
//import Model.Contracts.BasicContract;
//import Model.Contracts.Pivot;
//import Model.DAOs.DataBaseManager;
//import Model.DAOs.PivotDAO;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.fxml.FXML;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.sql.Date;
//import java.sql.SQLException;
//
//
//public class RealContractsController {
//
//    private ObservableList<BasicContract> contractsData = FXCollections.observableArrayList();
//
//    @FXML
//    private TableView<BasicContract> contractsTable;
//    @FXML
//    private TableColumn<BasicContract, String> idColumn;
//    @FXML
//    private TableColumn<BasicContract, String> bankColumn;
//    @FXML
//    private TableColumn<BasicContract, String> typeColumn;
//    @FXML
//    private TableColumn<BasicContract, Date> startColumn;
//    @FXML
//    private TableColumn<BasicContract, Date> finishColumn;
//    @FXML
//    private TableColumn<BasicContract, String> buyColumn;
//    @FXML
//    private TableColumn<BasicContract, String> sellColumn;
//    @FXML
//    private TableColumn<BasicContract, Double> valueColumn;
//    @FXML
//    private TableColumn<BasicContract, Integer> leverageColumn;
//    @FXML
//    private TableColumn<BasicContract, Double> spotRefColumn;
//    @FXML
//    private TableColumn<BasicContract, Boolean> isCloseColumn;
//    @FXML
//    private TableColumn<BasicContract, Integer> quantityColumn;
//    @FXML
//    private TableColumn<BasicContract, Double> highStrikeColumn;
//    @FXML
//    private TableColumn<BasicContract, Double> lowStrikeColumn;
//    @FXML
//    private TableColumn<BasicContract, Double> knockoutColumn;
//    @FXML
//    private TableColumn<BasicContract, Double> pivotColumn;
//
//    @FXML
//    private void initialize() {
//        getData();
//
//        idColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("contractId"));
//        bankColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("bank"));
//        typeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("contractType"));
//        startColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Date>("dateStart"));
//        finishColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Date>("dateFinish"));
//        buyColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("buyAsset"));
//        sellColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, String>("sellAsset"));
//        valueColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("assetValue"));
//        leverageColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Integer>("leverage"));
//        spotRefColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("spotRef"));
//        isCloseColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Boolean>("isClose"));
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Integer>("quantity"));
//        highStrikeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("highStrike"));
//        lowStrikeColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("lowStrike"));
//        knockoutColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("knockout"));
//        pivotColumn.setCellValueFactory(new PropertyValueFactory<BasicContract, Double>("pivot"));
//
//        contractsTable.setItems(contractsData);
//    }
//
//    public void getData() {
//        DataBaseManager dbm = DataBaseManager.getInstance("spivverydb");
//        Pivot pivot = new Pivot("CYP_", Date.valueOf("2015-06-20"), Date.valueOf("2016-06-20"), "EUR", "USD", 20000d, 2, 1.35d, 1.33d, 1.3d, 1.4d);
//        String key = pivot.getContractId();
//
//        try{
//            PivotDAO pivotDAO = dbm.getPivDAO();
////            pivotDAO.addItem(pivot);
//            Pivot pivotCopyDB = pivotDAO.getItemById(key);
//            System.out.println(pivotCopyDB);
//            contractsData.addAll(pivot);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
//
//

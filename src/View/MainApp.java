package View;

import Model.Contracts.BasicContract;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayot;
    private ObservableList<BasicContract> mainData = FXCollections.observableArrayList();

    public MainApp() {
    }

    public ObservableList<BasicContract> getMainData() {
        return mainData;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AdviserApp");

        initRootLayout();

        showMainOverview();
    }

    public void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("rootLayout.fxml"));
            rootLayot = loader.load();

            Scene scene = new Scene(rootLayot);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("adviser.fxml"));
            AnchorPane mainPane = loader.load();

            rootLayot.setCenter(mainPane);



        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showContractOverview(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("contractOverview.fxml"));
            AnchorPane contractPane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Contract");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(contractPane);
            dialogStage.setScene(scene);


            //..

            dialogStage.showAndWait();


        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}

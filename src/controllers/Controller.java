package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class Controller {

    @FXML private BorderPane mainPane;
    @FXML private Button serverStatusBtn;
    @FXML private Button createAccountBtn;
    @FXML private Button deleteAccountBtn;
    @FXML private HBox paneControllers;

    private static HashMap<String, Parent> uiMap;


    private static Stage stage;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() throws IOException {
        if(uiMap == null){
            uiMap = new HashMap<>();
        }
        makeStageDraggable();
        createAccount();
        deleteAccount();
        serverStatus();
    }

    public void setStage(Stage stage){
        Controller.stage = stage;
    }



    private  void makeStageDraggable(){
        paneControllers.setOnMousePressed((event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        }));
        paneControllers.setOnMouseDragged((event -> {
            stage.setX(event.getScreenX()- xOffset);
            stage.setY(event.getScreenY()- yOffset);
            stage.setOpacity(0.8f);
        }));

        paneControllers.setOnDragDone((event -> stage.setOpacity(1.0f)));
        paneControllers.setOnMouseReleased((event -> stage.setOpacity(1.0f)));
    }

    public void setCurrentTabButton(String name){

        serverStatusBtn.setStyle("-fx-background-color:  #1B1D29");
        createAccountBtn.setStyle("-fx-background-color:  #1B1D29");
        deleteAccountBtn.setStyle("-fx-background-color:  #1B1D29");

        switch (name) {
            case "status":
                serverStatusBtn.setStyle("-fx-background-color:  #2D3142");
                break;
            case "createAccount":
                createAccountBtn.setStyle("-fx-background-color:  #2D3142");
                break;
            case "deleteAccount":
                deleteAccountBtn.setStyle("-fx-background-color:  #2D3142");
                break;
        }
    }

    public void serverStatus() throws IOException{
        setCurrentTabButton("status");
        loadUI("serverStatus", "/views/statusScreen.fxml");
    }
    public void createAccount() throws IOException {
        setCurrentTabButton("createAccount");
        loadUI("createAccount", "/views/createAccount.fxml");
    }

    public void deleteAccount() throws IOException {
        setCurrentTabButton("deleteAccount");
        loadUI("deleteAccount", "/views/deleteAccount.fxml");
    }

    public void loadUI(String key, String ui) throws IOException {

        if(uiMap.containsKey(key)){
            Parent screen = uiMap.get(key);
            mainPane.setCenter(screen);
        }
        else{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(ui));
            Parent root1 = loader.load();

            uiMap.put(key,root1);
            mainPane.setCenter(root1);
        }

    }

    public void minimize(ActionEvent actionEvent) {
        ((Stage)((Button)actionEvent.getSource()).getScene().getWindow()).setIconified(true);
    }

    public void close(ActionEvent actionEvent) {
        (((Button)actionEvent.getSource()).getScene().getWindow()).hide();
        System.exit(0);
    }
}

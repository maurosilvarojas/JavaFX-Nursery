package nurseryApp.Controller;

/**
 * Created by regga on 15/01/2017.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nurseryApp.Models.Charges;
import nurseryApp.Models.DBConnection;
import nurseryApp.Models.WaitingParents;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;



public class WaitingListController implements Initializable {
    ObservableList<WaitingParents> waitingparents = FXCollections.observableArrayList();
    @FXML
    private JFXTextField childrenAgeField;

    @FXML
    private JFXButton exitWaitingButton;

    @FXML
    private TableColumn<WaitingParents, String> numChildColumn = new TableColumn<>("numOdChildren");

    @FXML
    private TableColumn<WaitingParents, String> surnameColumn = new TableColumn<>("surname");

    @FXML
    private TableColumn<WaitingParents,String> numberColumn = new TableColumn<>("mobile");

    @FXML
    private JFXButton refreshWaitingListButton;

    @FXML
    private JFXTextField parentEmailField;

    @FXML
    private JFXTextField numChildrenField;

    @FXML
    private JFXTextField parentNumberField;

    @FXML
    private JFXTextField parentSurnameField;

    @FXML
    private JFXButton addWaitiningButton;

    @FXML
    private TableColumn<WaitingParents,String> nameColumn = new TableColumn<>("name");

    @FXML
    private JFXTextField parentNameField;

    @FXML
    private TableColumn<WaitingParents, String> emailColumn = new TableColumn<>("email");

    @FXML
    private TableView<WaitingParents> waitingTable;

    @FXML
    void refreshWaitingListButton(ActionEvent event) {
        WaitingParents waitingParents = new WaitingParents();
        waitingparents= waitingParents.createWaitingList();
        waitingTable.setItems(waitingparents);
    }



    @FXML
    void addWaitiningButtonEvent(ActionEvent event) {
        insertData();
    }

    @FXML
    void exitWaitingButton(ActionEvent event) {
        Stage stage =(Stage) exitWaitingButton.getScene().getWindow();
        stage.close();
    }

    void insertData (){
        Connection connection=null;
        Statement statement = null;
        String empty = "";
        try{
            connection = DBConnection.getConnection();
            if (connection != null){
                System.out.println("Conection succesfull");
                PreparedStatement ps ;
                ps = connection.prepareStatement("INSERT INTO waitingList (name,surname,mobile,email,numOfChildren,childrenAge)" +
                        "VALUES (?,?,?,?,?,?)   ");
                ps.setString(1, parentNameField.getText());
                ps.setString(2, parentSurnameField.getText());
                ps.setString(3, parentNumberField.getText());
                ps.setString(4, parentEmailField.getText());
                ps.setString(5, numChildrenField.getText());
                ps.setString(6, childrenAgeField.getText());
                ps.executeUpdate();
                ps.close();
                connection.close();
                System.out.println("Query executed: insert new waiting list candidate, connection terminated ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        numChildColumn.setCellValueFactory(new PropertyValueFactory<>("numOfChildren"));








    }
}

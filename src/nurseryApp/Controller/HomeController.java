package nurseryApp.Controller;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nurseryApp.Models.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by regga on 18/12/2016.
 */
public class HomeController implements Initializable {

    Connection connection = null;
    ResultSet rs = null;
    ObservableList<Adults> parents = FXCollections.observableArrayList();
    ObservableList<Children> children = FXCollections.observableArrayList();
    ObservableList<Adults> staff = FXCollections.observableArrayList();



// Parents table declaration
    @FXML
    private TableColumn<Parents, Integer> parentID = new TableColumn<>("parentID");//fromDB to table column object ,then object to column

    @FXML
    private TableColumn<Parents, String> name= new TableColumn<>("name");

    @FXML
    private TableColumn<Parents, String> surname= new TableColumn<>("surname");

    @FXML
    private TableColumn<Parents, String> DoB= new TableColumn<>("DoB");

    @FXML
    private TableColumn<Parents, String> email = new TableColumn<>("email");

    @FXML
    private TableColumn<Parents, String> mobile = new TableColumn<>("mobile");

    @FXML
    private TableColumn<Parents, String> address = new TableColumn<>("address");

    @FXML
    private TableColumn<Parents, String> postcode = new TableColumn<>("postcode");

    @FXML
    private TableView<Adults> parentsTable;

    @FXML
    private JFXButton refreshParents;

    @FXML
    void refreshParentEvent(ActionEvent event) {
        parentsTable.getItems().clear();
        loadParents();
    }
    // end parents table declaration

    // Staff table declaration
    @FXML
    private TableColumn<Staff, Integer> staffID = new TableColumn<>("staffID");

    @FXML
    private TableColumn<Staff, String> staffNameTable= new TableColumn<>("name");

    @FXML
    private TableColumn<Staff, String> staffSurname= new TableColumn<>("surname");

    @FXML
    private TableColumn<Staff, String> staffAvailability= new TableColumn<>("abailability");

    @FXML
    private TableColumn<Staff, String> staffMobile = new TableColumn<>("mobile");

    @FXML
    private TableColumn<Staff, String> staffEmail = new TableColumn<>("email");

    @FXML
    private TableView<Adults> staffTable;

    @FXML
    private JFXButton refreshStaffTableButton;

    @FXML
    void refreshStaffTableButtonEvent(ActionEvent event) {
        staffTable.getItems().clear();
        loadStaff();
    }
    // end Staff table declaration

    // children table declaration

    @FXML
    private TableColumn<Children, Integer> childID = new TableColumn<>("child_Id");

    @FXML
    private TableColumn<Children, String> childName= new TableColumn<>("name");

    @FXML
    private TableColumn<Children, String> childSurname= new TableColumn<>("surname");

    @FXML
    private TableColumn<Children, String> childDoB = new TableColumn<>("DoB");

    @FXML
    private TableColumn<Children, String> childAllergy = new TableColumn<>("allergy");

    @FXML
    private TableColumn<Children, Integer> parentIDChildTable = new TableColumn<>("parentID");

    @FXML
    private TableColumn<Children, String> childCategory = new TableColumn<>("category");

    @FXML
    private TableView<Children> childrenTable;

    @FXML
    private JFXButton refreshChildTable;

    @FXML
    void refreshChildTableEvent(ActionEvent event) {
        childrenTable.getItems().clear();
        loadChildren();
    }

    // end children declaration

    @FXML
    private JFXButton registerChild;

    @FXML
    private Pane homePane;

    @FXML
    private JFXButton exitButton;
    @FXML
    private JFXButton invoiceButton;

    @FXML
    void invoiceButtonEvent(ActionEvent event) throws IOException {
        windowSelector("../Views/invoice.fxml");
    }

    @FXML
    void sessionEvent(ActionEvent event) throws IOException {
        windowSelector("../Views/session.fxml");

    }

    @FXML
    void searchEvent(ActionEvent event) throws IOException {
        windowSelector("../Views/search.fxml");
    }
    @FXML
    void sheduleEvent(ActionEvent event) throws IOException {
        windowSelector("../Views/waitingList.fxml");
    }

    @FXML
    void exitButtonEvent(ActionEvent event) {
        Stage stage =(Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void registerChildEvent(ActionEvent event) throws IOException {
        windowSelector("../Views/addChildren.fxml");
    }

    @FXML
    void addUser(ActionEvent event) throws IOException {

        windowSelector("../Views/addUser.fxml");
    }

    void windowSelector (String location) throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(location));
            Stage stage= new Stage((StageStyle.TRANSPARENT));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(parent));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public ObservableList<Person> getParent(){
//        setupColumnNames();
//
//
//
//        for (Person p : person
//             ) {
//            System.out.println(person);
//
//        }
//
//        return person;
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupColumnNames();
        loadParents();
        loadChildren();
        NurseryNotifications notifyer = new NurseryNotifications();
        notifyer.showNotification("new Session Started");
        Children newC= new Children(5);

    }

    private void loadParents() {
        Parents newParents = new Parents();
        parents=newParents.createAdultsList();
        parentsTable.setItems(parents);
    }

    private void loadStaff() {
        Staff newStaff = new Staff();
        staff=newStaff.createStaffList();
        staffTable.setItems(staff);
    }

    private void loadChildren() {
        Children newChildren= new Children();
        children= newChildren.createChildrenList();
        childrenTable.setItems(children);
    }

    public void setupColumnNames(){

        //parents
        parentID.setCellValueFactory(new PropertyValueFactory<>("parentID"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        DoB.setCellValueFactory(new PropertyValueFactory<>("datOfBirth"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        postcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));

        //children
        childID.setCellValueFactory(new PropertyValueFactory<>("child_Id"));
        childName.setCellValueFactory(new PropertyValueFactory<>("name"));
        childSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        childDoB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        parentIDChildTable.setCellValueFactory(new PropertyValueFactory<>("parentID"));
        childCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        childAllergy.setCellValueFactory(new PropertyValueFactory<>("allergy"));

        //Staff
        staffID.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        staffNameTable.setCellValueFactory(new PropertyValueFactory<>("staffName"));
        staffSurname.setCellValueFactory(new PropertyValueFactory<>("staffSurname"));
        staffAvailability.setCellValueFactory(new PropertyValueFactory<>("staffAvailability"));
        staffMobile.setCellValueFactory(new PropertyValueFactory<>("staffMobile"));
        staffEmail.setCellValueFactory(new PropertyValueFactory<>("staffEmail"));

    }
}



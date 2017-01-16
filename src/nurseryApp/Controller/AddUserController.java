package nurseryApp.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import nurseryApp.Models.Charges;
import nurseryApp.Models.DBConnection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {
    Connection connection=null;
    Statement statement = null;
    String empty = "";

    @FXML
    private JFXCheckBox registrationFee;

    @FXML
    private JFXButton cancelRegister;

    @FXML
    private JFXTextField surnameField;

    @FXML
    private JFXTextField mobileField;

    @FXML
    private JFXButton addUser;

    @FXML
    private JFXTextField postcodeField;

    @FXML
    private JFXTextField nameField;

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXTextField addressField;

    @FXML
    private DatePicker parentsDoB;

    @FXML
    void registrationFeeEvent(ActionEvent event) {

    }

    @FXML
    void addUserEvent(ActionEvent event) {

        if ((nameField.getText().equals(empty))&(surnameField.getText().equals(empty))&(mobileField.getText().equals(empty))) {
            System.out.println("Query not executed,lack of data");
        } else {
            insertData();
        }
    }

    @FXML
    void cancelRegisterEvent(ActionEvent event) {
        Stage stage =(Stage) cancelRegister.getScene().getWindow();
        stage.close();

    }

    void insertData (){
        try{
            connection = DBConnection.getConnection();
            if (connection != null){
                System.out.println("Conection succesfull");
                PreparedStatement ps ;
                ps = connection.prepareStatement("INSERT INTO parentsTable (name,surname,DoB,email,mobile,address,postcode)" +
                        "VALUES (?,?,?,?,?,?,?)   ");
                ps.setString(1, nameField.getText());
                ps.setString(2, surnameField.getText());
                ps.setString(3, parentsDoB.getValue().toString());
                ps.setString(4, emailField.getText());
                ps.setString(5, mobileField.getText());
                ps.setString(6, addressField.getText());
                ps.setString(7, postcodeField.getText());
                ps.executeUpdate();
                ps.close();
                connection.close();
                System.out.println("Query executed, connection terminated ");
                Stage stage =(Stage) addUser.getScene().getWindow();
                stage.close();


            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registrationFee.setOnAction(event -> {
            boolean selected = registrationFee.isSelected();
            addUser.setDisable(!selected);

        });
    }
}

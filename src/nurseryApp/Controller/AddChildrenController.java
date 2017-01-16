package nurseryApp.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import nurseryApp.Models.Children;
import nurseryApp.Models.DBConnection;

import java.sql.*;

/**
 * Created by regga on 26/12/2016.
 */
public class AddChildrenController {
    Connection connection=null;
    Statement statement = null;
    String userDB=null;
    String foundParentID = null;

    @FXML
    private JFXButton cancelRegister;

    @FXML
    private JFXButton addChildButton;

    @FXML
    private JFXTextField childCategory;

    @FXML
    private JFXTextField childRH;

    @FXML
    private JFXTextField foundParentName;

    @FXML
    private JFXTextField foundParentSurname;

    @FXML
    private DatePicker parentDoB;

    @FXML
    private JFXTextField childSurnameField;

    @FXML
    private DatePicker childDoB;

    @FXML
    private JFXTextField childNameField;

    @FXML
    private JFXTextField childDisabilities;

    @FXML
    private JFXButton searchParent;

    @FXML
    private JFXTextField parentSurname;

    @FXML
    private JFXTextField childAllergies;

    @FXML
    void addChildButtonEvent(ActionEvent event) {
        if ((foundParentID != null) && (childNameField.getText() != null) && (childSurnameField.getText() != null) && (childDoB.getValue().toString() != null)  ) {
            try {
                connection = DBConnection.getConnection();
                if (connection != null) {

                    int codeParent = Integer.parseInt(foundParentID);
                    System.out.println("Conection succesfull");
                    PreparedStatement ps;
                    ps = connection.prepareStatement("INSERT INTO childrenTable (name,surname,DoB,allergy,parentID,category)" +
                            "VALUES (?,?,?,?,?,?)   ");
                    ps.setString(1, childNameField.getText());
                    ps.setString(2, childSurnameField.getText());
                    ps.setString(3, childDoB.getValue().toString());
                    ps.setString(4, childAllergies.getText());
                    ps.setInt(5, codeParent);
                    ps.setString(6, childCategory.getText());

                    ps.executeUpdate();
                    ps.close();
                    connection.close();
                    System.out.println("Query executed, connection terminated ");
                    Stage stage = (Stage) addChildButton.getScene().getWindow();
                    stage.close();


                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println("Please fill the form");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("NOTICE");
            alert.setHeaderText("Lack of Details");
            alert.setContentText("Please, Fill the form");
        }
    }

    @FXML
    void cancelRegisterEvent(ActionEvent event) {
        Stage stage =(Stage) cancelRegister.getScene().getWindow();
        stage.close();
    }



    @FXML
    void searchParentEvent(ActionEvent event) throws SQLException {
        System.out.println("test");
        String searchDoB = parentDoB.getValue().toString();
        String parentsName = parentSurname.getText();


        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conection succesfull");
                Statement st = null;
                String query = "SELECT * FROM parentsTable WHERE surname="+"'"+parentsName+"'" +"AND DoB ="+"'"+searchDoB+"'" ;
                st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                if (resultSet.next()) {
                    foundParentSurname.setText(resultSet.getString("surname"));
                    System.out.println(resultSet.getString("surname"));
                    foundParentID=resultSet.getString("parentID");
                    foundParentName.setText(resultSet.getString("name"));
                }else{
                    System.out.println("Parent Not Found");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("NOTICE");
                    alert.setHeaderText("User not Found");
                    alert.setContentText("Please, verify parent Details");

                    alert.showAndWait();
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
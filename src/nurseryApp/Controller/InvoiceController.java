package nurseryApp.Controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nurseryApp.Models.Charges;
import nurseryApp.Models.Children;
import nurseryApp.Models.DBConnection;
import nurseryApp.Models.PrintToFile;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable{
    String finalBalance;
    NurseryNotifications notifyer = new NurseryNotifications();
    Children children= new Children();
    Connection connection=null;
    Statement statement = null;
    ObservableList<Charges> newCharges = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Charges, Float> balanceField;

    @FXML
    private TableColumn<Charges, Float> extraChargesField;

    @FXML
    private TableColumn<Charges,Double> price=  new TableColumn<>("price");

    @FXML
    private TableColumn<Charges,String> generatedDate=  new TableColumn<>("generatedDate");

    @FXML
    private TableColumn<Charges,Integer> childIDTableField=  new TableColumn<>("childIDTableField");

    @FXML
    private TableColumn<Charges,String> description =  new TableColumn<>("description");

    @FXML
    private TableView<Charges> invoiceTableView;


    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label foundChildID;

    @FXML
    private JFXTextField childIDField;

    @FXML
    private JFXButton finderButton;

    @FXML
    private JFXButton generateInvoice;

    @FXML
    private Label foundName;

    @FXML
    private Label totalBalancelabel;




    @FXML
    private JFXButton savetoFile;



    @FXML
    void generateInvoiceEvent(ActionEvent event) {
        final double[] balance = {0};
        Charges charges = new Charges();
        newCharges=charges.chargesList(children.getChild_Id());
        invoiceTableView.setItems(newCharges);
        newCharges.forEach((price)->{      // lampda expression to generate a sum of all prices found
            balance[0] = price.getPrice()+ balance[0];
            System.out.println(balance[0]);
        });
        finalBalance=Double.toString(balance[0]);
        totalBalancelabel.setText(Double.toString(balance[0]));

    }



    @FXML
    void savetoFileEvent(ActionEvent event) throws IOException {

        String location = children.getSurname()+children.getName()+children.getChild_Id()+".txt";
        String message =("Children Surname: "+ children.getSurname()+". \n Children Name"+ children.getName()+".\n New Balance:"+ finalBalance);
        PrintToFile p2f=new PrintToFile(message,location,"11 jan");
        p2f.print();
        System.out.println(message);
    }

    @FXML
    void finderButtonEvent(ActionEvent event) {
        Children children = getChildreDetails();
        foundChildID.setText(children.getName()+" "+children.getSurname());
    }

    @FXML
    void cancelButtonEvent(ActionEvent event) {
        Stage stage =(Stage) cancelButton.getScene().getWindow();
        stage.close();
        notifyer.showNotification("Charges Cancelled");
    }

    public Children getChildreDetails() {
        System.out.println("childIDFinderEvent_ start");
        Integer searchID = Integer.parseInt(childIDField.getText());
        String foundChildName;
        Boolean checkingID = false;
        try {
            connection = DBConnection.getConnection();
            if (connection != null) {
                System.out.println("Conection succesfull");
                Statement st = null;
                String query = "SELECT * FROM childrenTable WHERE child_Id=" + "'" + searchID + "'";
                st = connection.createStatement();
                ResultSet resultSet = st.executeQuery(query);
                if (resultSet.next()) {
                    children = new Children(resultSet.getInt("child_Id"));
                    return children;

                } else {
                    notifyer.showNotification("Child Not Found");
                    System.out.println("Child Not Found");
                }
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return children;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
          childIDTableField.setCellValueFactory(new PropertyValueFactory<>("childID"));
          generatedDate.setCellValueFactory(new PropertyValueFactory<Charges, String>("generatedDate"));
          price.setCellValueFactory(new PropertyValueFactory<Charges, Double>("price"));
          description.setCellValueFactory(new PropertyValueFactory<Charges, String>("description"));

    }


}

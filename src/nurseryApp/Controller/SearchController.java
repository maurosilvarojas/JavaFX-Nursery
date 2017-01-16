package nurseryApp.Controller;

/**
 * Created by regga on 15/01/2017.
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import nurseryApp.Models.Bill;
import nurseryApp.Models.Charges;
import nurseryApp.Models.Children;
import nurseryApp.Models.Parents;

import java.sql.SQLException;

public class SearchController {

    @FXML
    private JFXTextField childrenIDSearch;

    @FXML
    private JFXTextField parentSurnameSearch;

    @FXML
    private Label searchingStatusField;

    @FXML
    private JFXButton searchParentInfo;

    @FXML
    private JFXButton exitSearchButton;


    @FXML
    private JFXTextField childrenRhField;

    @FXML
    private JFXTextField ChildrenSurnameField;

    @FXML
    private JFXTextField parentDuedateField;

    @FXML
    private DatePicker parentDoBSearch;

    @FXML
    private JFXTextField addChargesField;

    @FXML
    private JFXTextField childrenNameField;

    @FXML
    private JFXTextField parentMobileField;

    @FXML
    private JFXTextField parentBalanceField;

    @FXML
    private JFXButton searchChildrenInfo;

    @FXML
    private JFXTextField childrenIDField;

    @FXML
    private JFXTextField parentEmailField;

    @FXML
    private JFXTextField childrenCategoryField;

    @FXML
    private JFXButton addChargesButton;

    @FXML
    private JFXTextField parentSurnameField;

    @FXML
    private JFXTextField parentDobField;

    @FXML
    private JFXTextField childrenAllergyField;

    @FXML
    private JFXTextField childrenDoBField;

    @FXML
    private JFXTextField parentDiscountField;

    @FXML
    private JFXTextField parentAddressField;

    @FXML
    private JFXTextField parentPoscodeField;

    @FXML
    private JFXTextField childrenNaneSearch;

    @FXML
    private JFXTextField parentNameField;

    @FXML
    void addChargesButton(ActionEvent event) {
        Charges charges= new Charges();
        //charges.addCharges(child);
    }

    @FXML
    void searchChildrenInfoEvent(ActionEvent event) {
        Children children=new Children(Integer.parseInt(childrenIDSearch.getText()));
        fillChildrenFields(children);
    }

    @FXML
    void searchParentInfoEvent(ActionEvent event) throws SQLException {
       Parents parents= new Parents();
       fillparentFields(parents.searchParentInfo(parentSurnameSearch.getText(),parentDoBSearch.getValue().toString()));

    }

    private void fillparentFields(Parents parents) {
        parentNameField.setText(parents.getName());
        parentSurnameField.setText(parents.getSurname());
        parentDobField.setText(parents.getdatOfBirth());
        parentEmailField.setText(parents.getEmail());
        parentMobileField.setText(parents.getMobile());
        parentAddressField.setText(parents.getAddress());
        parentPoscodeField.setText(parents.getPostcode());
        Children children = new Children(parents.findOwnChildren());
        Bill bill = new Bill(parents.getParentID());
        parentBalanceField.setText(String.valueOf(bill.getBalance()));

    }

    @FXML
    void exitSearchButtonEvent(ActionEvent event) {
        Stage stage =(Stage) exitSearchButton.getScene().getWindow();
        stage.close();
    }

    public void fillChildrenFields(Children children){
        childrenNaneSearch.setText(children.getName()+" ,"+ children.getSurname());
        childrenIDField.setText(String.valueOf(children.getChild_Id()));
        childrenNameField.setText(children.getName());
        ChildrenSurnameField.setText(children.getSurname());
        childrenDoBField.setText(children.getDateOfBirth());
        childrenCategoryField.setText(children.getCategory());
        childrenRhField.setText(children.getAllergy());
        childrenAllergyField.setText(children.getAllergy());
        Parents parents= new Parents(children.getParentID());
        fillparentFields(parents);
    }
}

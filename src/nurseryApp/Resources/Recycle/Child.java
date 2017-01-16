//package nurseryApp.Models;
//
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.scene.control.Alert;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.Period;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by regga on 12/12/2016.
// */
//public class Child {
//    Connection connection=null;
//    Statement statement = null;
//    String userDB=null;
//    //Session session;
//
//
//    private final SimpleIntegerProperty child_Id;
//    private final SimpleStringProperty name;
//    private final SimpleStringProperty surname;
//    private final SimpleStringProperty dateOfBirth;
//    private final SimpleStringProperty allergy;
//    private final SimpleIntegerProperty parentID;
//    private final SimpleStringProperty category;
//
//
//    public Child(int child_Id, String name, String surname, String dateOfBirth, String allergy, int parentID, String category) {
//        this.child_Id = new SimpleIntegerProperty(child_Id);
//        this.name = new SimpleStringProperty(name);
//        this.surname = new SimpleStringProperty(surname);
//        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
//        this.allergy = new SimpleStringProperty(allergy);
//        this.parentID = new SimpleIntegerProperty(parentID);
//        this.category = new SimpleStringProperty(category);
//    }
//
//
//    public int getChild_Id() {
//        return child_Id.get();
//    }
//
//    public SimpleIntegerProperty child_IdProperty() {
//        return child_Id;
//    }
//
//    public void setChild_Id(int child_Id) {
//        this.child_Id.set(child_Id);
//    }
//
//    public String getName() {
//        return name.get();
//    }
//
//    public SimpleStringProperty nameProperty() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name.set(name);
//    }
//
//    public String getSurname() {
//        return surname.get();
//    }
//
//    public SimpleStringProperty surnameProperty() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname.set(surname);
//    }
//
//    public String getDateOfBirth() {
//        return dateOfBirth.get();
//    }
//
//    public SimpleStringProperty dateOfBirthProperty() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(String dateOfBirth) {
//        this.dateOfBirth.set(dateOfBirth);
//    }
//
//    public String getAllergy() {
//        return allergy.get();
//    }
//
//    public SimpleStringProperty allergyProperty() {
//        return allergy;
//    }
//
//    public void setAllergy(String allergy) {
//        this.allergy.set(allergy);
//    }
//
//    public int getParentID() {
//        return parentID.get();
//    }
//
//    public SimpleIntegerProperty parentIDProperty() {
//        return parentID;
//    }
//
//    public void setParentID(int parentID) {
//        this.parentID.set(parentID);
//    }
//
//    public String getCategory() {
//        return category.get();
//    }
//
//    public SimpleStringProperty categoryProperty() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category.set(category);
//    }
//
////    public int getChildAge(){
////        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
////        int counterDays = 0;
////        String todaysDay = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss").format(Calendar.getInstance().getTime());
////        //String dateFrom = "2016-11-09";
////        //String dateTo = "2016-11-10";
////        int counterYears = Period.between(LocalDate.now(), LocalDate.parse(getDateOfBirth())).getYears();
////        System.out.println("Days: " + TimeUnit.DAYS.convert(counterYears, TimeUnit.MILLISECONDS));
////
////        return counterDays;
////    }
////
////    public Integer getChildreDetails(){
////
////        try {
////            connection = DBConnection.getConnection();
////            if (connection != null) {
////                System.out.println("Conection succesfull");
////                Statement st = null;
////                String query = "SELECT * FROM childrenTable WHERE child_Id="+"'"+searchID+"'"  ;
////                st = connection.createStatement();
////                ResultSet resultSet = st.executeQuery(query);
////                if (resultSet.next()) {
////
////                    foundChildName = resultSet.getString("name") +"  "+ resultSet.getString("surname");
////                    fouundChildDoB = resultSet.getInt("DoB");
////                    foundChildID = resultSet.getInt("child_Id");
////                    System.out.println(foundChildID + "name Found: "+ foundChildName);
////                    foundTextField.setText(foundChildName);
////                    return foundChildID;
////
////                }else{
////                    System.out.println("Child Not Found");
////                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
////                    alert.setTitle("NOTICE");
////                    alert.setHeaderText("Child not Found");
////                    alert.setContentText("Please, verify ID");
////
////                    alert.showAndWait();
////                }
////                connection.close();
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
//}
//

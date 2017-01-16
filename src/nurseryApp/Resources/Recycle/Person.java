//package nurseryApp.Models;
//
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//
///**
// * Created by regga on 12/12/2016.
// */
//public class Person {
//    private final SimpleIntegerProperty parentID;
//    private final SimpleStringProperty name;
//    private final SimpleStringProperty surname;
//    private final SimpleStringProperty DatOfBirth;
//    private final SimpleStringProperty email;
//    private final SimpleStringProperty mobile;
//    private final SimpleStringProperty address;
//    private final SimpleStringProperty postcode;
//
//
//
//
//    public Person(int parentID, String name, String surname, String DatOfBirth, String email, String mobile, String address, String postcode) {
//        this.parentID = new SimpleIntegerProperty(parentID);
//        this.name = new SimpleStringProperty(name);
//        this.surname = new SimpleStringProperty(surname);
//        this.DatOfBirth = new SimpleStringProperty(DatOfBirth);
//        this.email = new SimpleStringProperty(email);
//        this.mobile = new SimpleStringProperty(mobile);
//        this.address = new SimpleStringProperty(address);
//        this.postcode = new SimpleStringProperty(postcode);
//    }
//
//
//    public SimpleIntegerProperty parentIDProperty() {
//        return parentID;
//    }
//
//    public void setParentID(int parentID) {
//        this.parentID.set(parentID);
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
//    public String getDatOfBirth() {
//        return DatOfBirth.get();
//    }
//
//    public SimpleStringProperty datOfBirthProperty() {
//        return DatOfBirth;
//    }
//
//    public void setDatOfBirth(String datOfBirth) {
//        this.DatOfBirth.set(datOfBirth);
//    }
//
//    public String getEmail() {
//        return email.get();
//    }
//
//    public SimpleStringProperty emailProperty() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email.set(email);
//    }
//
//    public String getMobile() {
//        return mobile.get();
//    }
//
//    public SimpleStringProperty mobileProperty() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile.set(mobile);
//    }
//
//    public String getAddress() {
//        return address.get();
//    }
//
//    public SimpleStringProperty addressProperty() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address.set(address);
//    }
//
//    public String getPostcode() {
//        return postcode.get();
//    }
//
//    public SimpleStringProperty postcodeProperty() {
//        return postcode;
//    }
//
//    public void setPostcode(String postcode) {
//        this.postcode.set(postcode);
//    }
//
//
//    public SimpleIntegerProperty getParentID(){
//        return parentID;
//    }
//
//}

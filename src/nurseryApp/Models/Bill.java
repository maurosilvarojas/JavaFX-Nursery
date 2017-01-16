package nurseryApp.Models;

/**
 * Created by regga on 15/01/2017.
 */
public class Bill {
    Double balance;
    String dueDate;
    double discounts;

    public Bill(double balance, String dueDate, double discounts) {
        this.balance = balance;
        this.dueDate = dueDate;
        this.discounts = discounts;
    }

    public Bill(int parentID){
        Parents parents = new Parents(parentID);
        int id = parents.findOwnChildren();
        Charges charges= new Charges();
        this.balance=charges.getFinalBalance(id);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }
}




package nurseryApp.Models;

/**
 * Created by regga on 12/01/2017.
 */
public class Rates {
    //All Day","Morning","Lunch","Afternoon","Pre School

    int age;
    String sessionRange;
    double rate;


    public Rates(int age,String sessionRange) {
        this.age = age;
        this.sessionRange = sessionRange;
    }

    public double getSessionRate() {
        switch (sessionRange) {
            case "Morning":
                System.out.println("Morning choosed");
                if (age < 2) {
                     rate = 16.5;
                } else if (2 <= age | age < 3) {
                     rate = 15.5;
                } else if (age >= 3) {
                     rate = 14.5;
                }
                break;

            case "All Day":
                System.out.println("All day choosed");
                if (age < 2) {
                     rate = 35;
                } else if (2 <= age | age < 3) {
                     rate = 34;
                } else if (age >= 3) {
                     rate = 32;
                }
                break;

            case "Lunch":
                System.out.println("Lunch choosed");
                 rate = 5;
                 break;

            case "Afternoon":
                System.out.println("Afternoon choosed");
                if (age < 2) {
                     rate = 18;
                } else if (2 <= age | age < 3) {
                     rate = 17;
                } else if (age >= 3) {
                     rate = 16;
                }
                break;

            case "Pre School":
                if (age < 2) {
                     rate = 25;
                } else if (2 <= age | age < 3) {
                     rate = 24.5;
                } else if (age >= 3) {
                     rate = 23.5;
                }
                break;
        }
        return rate;
    }
}


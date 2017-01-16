package nurseryApp.Controller;

import javafx.geometry.Pos;
import javafx.util.Duration;

/**
 * Created by regga on 12/01/2017.
 */
public class NurseryNotifications {







    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String tittle;
    String message;

    public void showNotification(String message){
        org.controlsfx.control.Notifications sessionNotification= org.controlsfx.control.Notifications.create()
                .title(tittle)
                .text(message)
                .darkStyle()
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(e-> {
                    System.out.println("notification has been clicked");
                });

        sessionNotification.showConfirm();
    }

}

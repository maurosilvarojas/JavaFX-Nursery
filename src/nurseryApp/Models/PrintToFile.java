package nurseryApp.Models;

import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by regga on 12/01/2017.
 */
public class PrintToFile {
            String message;
            String fileName;


    public PrintToFile(String message, String fileName, String todaysDate) throws IOException {
        this.message = message;
        this.fileName = fileName;
    }
//
//    public PrintToFile(ObservableList<listMessages>, String fileName, String todaysDate) throws IOException {
//        this.message = message;
//        this.fileName = fileName;
//    }


    public void print(){

        try {
            FileWriter newFileWriter = new FileWriter(fileName,true);
            PrintWriter newPrintWriter = new PrintWriter(newFileWriter);
            newPrintWriter.println(message);
            newPrintWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

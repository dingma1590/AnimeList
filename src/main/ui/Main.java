package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

// main interface class
// This class references code from CPSC 210 JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    GraphicalApp.createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//        try {
//            new AnimeListApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
    }
}

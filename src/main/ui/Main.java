package ui;

import java.io.FileNotFoundException;

// main interface class
// This class references code from CPSC 210 JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Main {
    public static void main(String[] args) {
        try {
            new AnimeListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

package ui;

import java.io.FileNotFoundException;

// main interface class
public class Main {
    public static void main(String[] args) {
        try {
            new AnimeListApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

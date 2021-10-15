package ui;

import model.AnimeEntry;
import model.lists.AnimeList;
import model.lists.FinishedList;
import model.lists.PlannedList;
import model.lists.WatchingList;

import java.util.Scanner;

// anime list application
// partially adapted from CPSC 210 TellerApp
public class AnimeListApp {
    private AnimeList fl;
    private AnimeList pl;
    private AnimeList wl;
    private Scanner scan;

    // EFFECTS: run the anime list application
    public AnimeListApp() {
        runAnimeList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runAnimeList() {
        boolean running = true;
        String input;

        init();

        while (running) {
            printMenu();
            input = scan.next();

            if (input.equalsIgnoreCase("e")) {
                running = false;
            } else {
                processInput(input);
            }
        }

        System.out.println("Session Complete");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processInput(String input) {
        if (input.equalsIgnoreCase("p")) {
            printList();
        } else if (input.equalsIgnoreCase("a")) {
            newEntry();
        } else if (input.equalsIgnoreCase("r")) {
            deleteEntry();
        } else if (input.equalsIgnoreCase("l")) {
            lookEntry();
        } else {
            System.out.println("Invalid Input");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes lists
    private void init() {
        fl = new FinishedList();
        pl = new PlannedList();
        wl = new WatchingList();
        scan = new Scanner(System.in);
        scan.useDelimiter("\n");
    }

    // EFFECTS: print out a menu of options
    private void printMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("\tp -> print out one of the lists");
        System.out.println("\ta -> add a new anime entry");
        System.out.println("\tr -> remove an anime entry");
        System.out.println("\tl -> look up an anime entry");
        System.out.println("\te -> exit the application");
    }

    // EFFECTS: print out the chosen list
    private void printList() {
        AnimeList chosen = chooseList();
        if (chosen.listSize() == 0) {
            System.out.println("List is empty!");
        }
        for (String next: chosen.getTitles()) {
            System.out.println(next);
        }

    }

    // EFFECTS: add a new entry to a list
    private void newEntry() {
        AnimeList chosen = chooseList();
        String status;

        System.out.println("\n What's the title?");
        String title = scan.next();

        System.out.println("\n Any notes to add?");
        String notes = scan.next();

        if (chosen == fl) {
            status = "Finished";
        } else if (chosen == pl) {
            status = "Planned";
        } else {
            status = "Watching";
        }

        if (chosen.addEntry(new AnimeEntry(title, AnimeEntry.Status.valueOf(status), notes))) {
            System.out.println("Added successfully!");
        } else {
            System.out.println("Entry already exists, please try again.");
        }
    }

    // EFFECTS: delete an existing entry
    private void deleteEntry() {
        System.out.println("\n What's the title?");
        String title = scan.next();

        if (fl.removeEntry(title)) {
            System.out.println("Entry removed from Finished list!");
        } else if (pl.removeEntry(title)) {
            System.out.println("Entry removed from Planned list!");
        } else if (wl.removeEntry(title)) {
            System.out.println("Entry removed from Watching list!");
        } else {
            System.out.println("Entry does not exist, please try again.");
        }
    }

    // EFFECTS: returns the information of a given entry
    private void lookEntry() {
        System.out.println("\n What's the title?");
        String title = scan.next();
        AnimeEntry ae;

        if (fl.hasEntry(title)) {
            ae = fl.getEntry(title);
            System.out.println("Title: " + ae.getTitle() + " " + "List: " + ae.getStatus().toString() + " "
                    + "Notes: " + ae.getNotes());
        } else if (pl.hasEntry(title)) {
            ae = pl.getEntry(title);
            System.out.println("Title: " + ae.getTitle() + " " + "List: " + ae.getStatus().toString() + " "
                    + "Notes: " + ae.getNotes());
        } else if (wl.hasEntry(title)) {
            ae = wl.getEntry(title);
            System.out.println("Title: " + ae.getTitle() + " " + "List: " + ae.getStatus().toString() + " "
                    + "Notes: " + ae.getNotes());
        } else {
            System.out.println("Entry does not exist, please try again.");
        }
    }

    // EFFECTS: prompts user to choose a list and returns it
    private AnimeList chooseList() {
        String chosen = "";

        while (!(chosen.equalsIgnoreCase("fl") || chosen.equalsIgnoreCase("pl")
                || chosen.equalsIgnoreCase("wl"))) {
            System.out.println("fl for Finished List");
            System.out.println("pl for Planned List");
            System.out.println("wl for Watching List");
            chosen = scan.next();
            System.out.println("Invalid Input");
        }

        if (chosen.equalsIgnoreCase("fl")) {
            return fl;
        } else if (chosen.equalsIgnoreCase("pl")) {
            return pl;
        } else {
            return wl;
        }
    }

}

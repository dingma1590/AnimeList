package ui;

import model.AnimeEntry;
import model.lists.AnimeList;
import model.lists.FinishedList;
import model.lists.PlannedList;
import model.lists.WatchingList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// anime list application
// This class references code from CPSC 210 TellerApp, and JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp,
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class AnimeListApp {
    private static final String JSON_STORE1 = "./data/finished.json";
    private static final String JSON_STORE2 = "./data/planned.json";
    private static final String JSON_STORE3 = "./data/watching.json";
    private AnimeList fl;
    private AnimeList pl;
    private AnimeList wl;
    private Scanner scan;
    private JsonWriter jsonWriter1;
    private JsonReader jsonReader1;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader2;
    private JsonWriter jsonWriter3;
    private JsonReader jsonReader3;

    // EFFECTS: run the anime list application
    public AnimeListApp() throws FileNotFoundException {
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

        System.out.println("-----Session Complete-----");
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
        } else if (input.equalsIgnoreCase("save")) {
            saveLists();
        } else if (input.equalsIgnoreCase("load")) {
            loadLists();
        } else {
            System.out.println("-----Invalid Input-----");
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
        jsonWriter1 = new JsonWriter(JSON_STORE1);
        jsonReader1 = new JsonReader(JSON_STORE1);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
        jsonWriter3 = new JsonWriter(JSON_STORE3);
        jsonReader3 = new JsonReader(JSON_STORE3);
    }

    // EFFECTS: print out a menu of options
    private void printMenu() {
        System.out.println("\033[1;95m" + "\n-----What would you like to do?-----" + "\033[0m");
        System.out.println("\tp -> print out one of the lists");
        System.out.println("\ta -> add a new anime entry");
        System.out.println("\tr -> remove an anime entry");
        System.out.println("\tl -> look up an anime entry");
        System.out.println("\tsave -> save lists to file");
        System.out.println("\tload -> load lists from file");
        System.out.println("\te -> exit the application");
    }

    // EFFECTS: print out the chosen list
    private void printList() {
        AnimeList chosen = chooseList();
        if (chosen.listSize() == 0) {
            System.out.println("-----List is empty!-----");
        }

        Integer num = 0;
        for (AnimeEntry next: chosen.getEntries()) {
            num++;
            System.out.println(num + ". " + "Title: " + next.getTitle() + " | " + "Status: "
                    + next.getStatus().toString() + " | " + "Notes: " + next.getNotes());
        }
    }

    // MODIFIES: this
    // EFFECTS: add a new entry to a list
    private void newEntry() {
        AnimeList chosen = chooseList();
        String status;

        System.out.println("\n-----What's the title?-----");
        String title = scan.next();

        System.out.println("\n-----Any notes to add?-----");
        String notes = scan.next();

        if (chosen == fl) {
            status = "Finished";
        } else if (chosen == pl) {
            status = "Planned";
        } else {
            status = "Watching";
        }

        if (fl.hasEntry(title) || pl.hasEntry(title) || wl.hasEntry(title)) {
            System.out.println("-----Entry already exists, please try again.-----");
        } else if (chosen.addEntry(new AnimeEntry(title, AnimeEntry.Status.valueOf(status), notes))) {
            System.out.println("-----Added successfully!-----");
        }
    }

    // MODIFIES: this
    // EFFECTS: delete an existing entry
    private void deleteEntry() {
        System.out.println("\n-----What's the title?-----");
        String title = scan.next();

        if (fl.removeEntry(title)) {
            System.out.println("-----Entry removed from Finished list!-----");
        } else if (pl.removeEntry(title)) {
            System.out.println("-----Entry removed from Planned list!-----");
        } else if (wl.removeEntry(title)) {
            System.out.println("-----Entry removed from Watching list!-----");
        } else {
            System.out.println("-----Entry does not exist, please try again.-----");
        }
    }

    // EFFECTS: returns the information of a given entry
    private void lookEntry() {
        System.out.println("\n-----What's the title?-----");
        String title = scan.next();
        AnimeEntry ae;

        if (fl.hasEntry(title)) {
            ae = fl.getEntry(title);
            System.out.println("Title: " + ae.getTitle() + " | " + "Status: " + ae.getStatus().toString() + " | "
                    + "Notes: " + ae.getNotes());
        } else if (pl.hasEntry(title)) {
            ae = pl.getEntry(title);
            System.out.println("Title: " + ae.getTitle() + " | " + "Status: " + ae.getStatus().toString() + " | "
                    + "Notes: " + ae.getNotes());
        } else if (wl.hasEntry(title)) {
            ae = wl.getEntry(title);
            System.out.println("Title: " + ae.getTitle() + " | " + "Status: " + ae.getStatus().toString() + " | "
                    + "Notes: " + ae.getNotes());
        } else {
            System.out.println("-----Entry does not exist, please try again.-----");
        }
    }

    // EFFECTS: prompts user to choose a list and returns it
    private AnimeList chooseList() {
        String chosen = null;
        boolean asking = true;

        while (asking) {
            System.out.println("\n-----Choose a list-----");
            System.out.println("\tfl -> Finished List");
            System.out.println("\tpl -> Planned List");
            System.out.println("\twl -> Watching List");
            chosen = scan.next();

            if (!(chosen.equalsIgnoreCase("fl") || chosen.equalsIgnoreCase("pl")
                    || chosen.equalsIgnoreCase("wl"))) {
                System.out.println("-----Invalid Input-----");
            } else {
                asking = false;
            }
        }

        if (chosen.equalsIgnoreCase("fl")) {
            return fl;
        } else if (chosen.equalsIgnoreCase("pl")) {
            return pl;
        } else {
            return wl;
        }
    }

    // EFFECTS: saves the lists to file
    private void saveLists() {
        try {
            jsonWriter1.open();
            jsonWriter1.write(fl);
            jsonWriter1.close();
            jsonWriter2.open();
            jsonWriter2.write(pl);
            jsonWriter2.close();
            jsonWriter3.open();
            jsonWriter3.write(wl);
            jsonWriter3.close();
            System.out.println("Saved " + fl.getType() + " list to " + JSON_STORE1);
            System.out.println("Saved " + pl.getType() + " list to " + JSON_STORE2);
            System.out.println("Saved " + wl.getType() + " list to " + JSON_STORE3);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE1 + " or " + JSON_STORE3 + " or "
                    + JSON_STORE3);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads lists from file
    private void loadLists() {
        try {
            fl = jsonReader1.read();
            pl = jsonReader2.read();
            wl = jsonReader3.read();
            System.out.println("Loaded " + fl.getType() + " list from " + JSON_STORE1);
            System.out.println("Loaded " + pl.getType() + " list from " + JSON_STORE2);
            System.out.println("Loaded " + wl.getType() + " list from " + JSON_STORE3);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE1 + " or " + JSON_STORE3 + " or "
                    + JSON_STORE3);
        }
    }
}

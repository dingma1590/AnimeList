package ui;

import model.lists.AnimeList;
import model.lists.FinishedList;
import model.lists.PlannedList;
import model.lists.WatchingList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Anime list application with GUI
public class GraphicalApp {
    static JFrame frame;
    private static final String JSON_STORE1 = "./data/finished.json";
    private static final String JSON_STORE2 = "./data/planned.json";
    private static final String JSON_STORE3 = "./data/watching.json";
    private static JsonWriter jsonWriter1;
    private static JsonReader jsonReader1;
    private static JsonWriter jsonWriter2;
    private static JsonReader jsonReader2;
    private static JsonWriter jsonWriter3;
    private static JsonReader jsonReader3;
    private static AnimeList fl;
    private static AnimeList wl;
    private static AnimeList pl;

    // EFFECTS: constructs the JPanel
    public static JPanel createUI() throws IOException {

        init();

        //Create the labels.
        JLabel title = new JLabel("Anime List");
        BufferedImage myPicture = ImageIO.read(new File("./data/ayaya.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));

        title.setFont(new Font("Helvetica Neue", Font.BOLD, 14));

        final JButton button1 = makeFinishedButton();

        final JButton button2 = makeWatchingButton();

        final JButton button3 = makePlannedButton();

        final JButton button4 = makeSaveButton();

        final JButton button5 = makeLoadButton();

        JPanel panel = createPanel(title, picLabel, button1, button2, button3, button4, button5);

        return panel;
    }

    // MODIFIES: this
    // EFFECTS: makes the finished list button and open its window
    private static JButton makeFinishedButton() {
        //Create the "Finished" button.
        final JButton button1 = new JButton("Finished");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Finished.createAndShowGUI(fl);
            }
        });
        return button1;
    }

    // MODIFIES: this
    // EFFECTS: makes the watching list button and open its window
    private static JButton makeWatchingButton() {
        final JButton button2 = new JButton("Watching");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Watching.createAndShowGUI(wl);
            }
        });
        return button2;
    }

    // MODIFIES: this
    // EFFECTS: makes the planned list button and open its window
    private static JButton makePlannedButton() {
        final JButton button3 = new JButton("Planned");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Planned.createAndShowGUI(pl);
            }
        });
        return button3;
    }

    // MODIFIES: this
    // EFFECTS: makes the save button and saves lists to file
    private static JButton makeSaveButton() {
        //Create the "Save" button.
        final JButton button4 = new JButton("Save");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        return button4;
    }

    // MODIFIES: this
    // EFFECTS: makes the load button and loads lists from file
    private static JButton makeLoadButton() {
        //Create the "Load" button.
        final JButton button5 = new JButton("Load");
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    fl = jsonReader1.read();
                    pl = jsonReader2.read();
                    wl = jsonReader3.read();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        return button5;
    }

    // EFFECTS: creates the jPanel with all labels
    private static JPanel createPanel(JLabel title, JLabel picLabel, JButton button1,
                                      JButton button2, JButton button3, JButton button4, JButton button5) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,
                BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button3.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button4.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button5.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        picLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(5));
        panel.add(picLabel);

        panel.add(Box.createRigidArea(new Dimension(150,10)));

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: initializes lists and JSON
    private static void init() {
        fl = new FinishedList();
        wl = new WatchingList();
        pl = new PlannedList();
        jsonWriter1 = new JsonWriter(JSON_STORE1);
        jsonReader1 = new JsonReader(JSON_STORE1);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
        jsonWriter3 = new JsonWriter(JSON_STORE3);
        jsonReader3 = new JsonReader(JSON_STORE3);
    }

    // MODIFIES: this
    // EFFECTS: Set up and display the window.
    public static void createAndShowGUI() throws IOException {
        frame = new JFrame("My Anime List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = createUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }
}

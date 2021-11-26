package ui;

import model.AnimeEntry;
import model.lists.AnimeList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Button for the finished list
public class Finished extends JPanel {
    AnimeList fl;
    private static final String addString = "Add Entry";
    private static final String removeString = "Remove Entry";
    private JTextField entryName1;
    private JTextField entryName2;
    private JTextField entryName3;
    private final JTable table;

    private final String[] col = {"title", "notes"};
    DefaultTableModel tableModel;

    // constructor for finished list
    public Finished(AnimeList list) {
        super(new BorderLayout());

        fl = list;
        tableModel = new DefaultTableModel(col, 0);
        for (AnimeEntry next: fl.getEntries()) {
            String title = next.getTitle();
            String notes = next.getNotes();
            Object[] data = {title, notes};
            tableModel.addRow(data);
        }

        table = new JTable(tableModel);
        JScrollPane listScrollPane = new JScrollPane(table);

        JButton addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        JButton removeButton = new JButton(removeString);
        RemoveListener removeListener = new RemoveListener(removeButton);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
        removeButton.setEnabled(false);

        textEntries(addListener, removeListener);

        createPanel(listScrollPane, addButton, removeButton);
    }

    // MODIFIES: this
    // EFFECTS: initializes text entries
    private void textEntries(AddListener addListener, RemoveListener removeListener) {
        entryName1 = new JTextField(10);
        entryName1.addActionListener(addListener);
        entryName1.getDocument().addDocumentListener(addListener);
        entryName2 = new JTextField(10);
        entryName2.addActionListener(removeListener);
        entryName2.getDocument().addDocumentListener(removeListener);
        entryName3 = new JTextField(10);
        entryName3.addActionListener(addListener);
        entryName3.getDocument().addDocumentListener(addListener);
    }

    // EFFECTS: creates JPanel
    private void createPanel(JScrollPane listScrollPane, JButton addButton, JButton removeButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(entryName2);
        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(entryName1);
        buttonPane.add(entryName3);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }

    // event listener for remove button and its text field
    class RemoveListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public RemoveListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = entryName2.getText();

            tableModel.removeRow(fl.getEntries().indexOf(fl.getEntry(name)));
            fl.removeEntry(name);

            //Reset the text field.
            entryName2.requestFocusInWindow();
            entryName2.setText("");
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // event listener for add button and its text fields
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private final JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = entryName1.getText();
            String notes = entryName3.getText();

            fl.addEntry(new AnimeEntry(name, AnimeEntry.Status.Finished, notes));
            Object[] entry = {name, notes};
            tableModel.addRow(entry);

            //Reset the text field.
            entryName1.requestFocusInWindow();
            entryName1.setText("");
            entryName3.requestFocusInWindow();
            entryName3.setText("");
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and displays the list
    public static void createAndShowGUI(AnimeList list) {
        //Create and set up the window.
        JFrame frame = new JFrame("Finished List");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new Finished(list);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}

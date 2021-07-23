package src.main.java;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window implements ActionListener {
    JFrame frame = new JFrame();
    JButton buttonChooseFolder = new JButton("Pick folder to scan!");
    JButton buttonScan = new JButton("Scan for xmls and convert!");
    JButton butonClose = new JButton("Exit App!");
    JLabel label = new JLabel("Pick folder to scan for xml files!");
    JFileChooser chooser = new JFileChooser(".");

    int choice;
    File chosenFile;
    boolean folderChoosed = false;

    public Window() {
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        label.setBounds(200, 10, 400, 50);
        label.setBackground(Color.LIGHT_GRAY);
        label.setOpaque(true);
        label.setFont(new Font(null, Font.PLAIN, 25));

        buttonScan.setBounds(300, 170, 200, 40);
        buttonScan.setFocusable(false);
        buttonScan.addActionListener(this);

        buttonChooseFolder.setBounds(300, 100, 200, 40);
        buttonChooseFolder.setFocusable(false);
        buttonChooseFolder.addActionListener(this);

        butonClose.setBounds(300, 240, 200, 40);
        butonClose.setFocusable(false);
        butonClose.addActionListener(this);

        frame.add(buttonChooseFolder);
        frame.add(label);
        frame.add(buttonScan);
        frame.add(butonClose);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setBounds(1920 / 2 - 400, 1080 / 2 - 300, 800, 600);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == butonClose) {
            frame.dispose();
        }
        if (e.getSource() == buttonChooseFolder) {
            choice = chooser.showOpenDialog(frame);

            if (choice != JFileChooser.APPROVE_OPTION)
                return;

            chosenFile = chooser.getSelectedFile();
            folderChoosed = true;
        }
        if (e.getSource() == buttonScan) {
            if (!folderChoosed)
                chosenFile = new File(".");
            new Converter(chosenFile);
        }

    }

}

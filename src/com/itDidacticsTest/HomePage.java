package com.itDidacticsTest;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class HomePage extends JFrame{
    private JPanel MainPanel;
    private JButton profileButton;
    private JTabbedPane tabbedPane;
    private JButton ch1l1button;
    private JButton ch1l2Button;
    private JButton ch1Test;
    private JLabel welcomeLabel;
    private JButton ch2l1Button;
    private JButton ch2l2Button;
    private JButton ch2Test;
    private JButton ch3l1Button;
    private JButton ch3l2Button;
    private JButton ch3Test;

    public HomePage(String logUser, Integer logUserID) throws HeadlessException, SQLException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(500, 600);

        welcomeLabel.setText("Welcome " + logUser);

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new ProfilePage(logUser, logUserID).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch1l1button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Lessons(logUser,logUserID,1,1,"Piast Dynasty(966 - 1385)", "https://www.youtube.com/watch?v=YbFhmfvXZqg").setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch1l2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Lessons(logUser,logUserID,1,2,"Jagiellonian dynasty(1385-1569)", "https://www.youtube.com/watch?v=WWmmXVZcSqY").setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch2l1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Lessons(logUser,logUserID,2,1,"Polish–Lithuanian Commonwealth (1569 - 1795)", "https://www.youtube.com/watch?v=NeUMMjLK4i8").setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch2l2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Lessons(logUser,logUserID,2,2,"Partitions (1795 - 1918)", "https://www.youtube.com/watch?v=ftmoWPu81j4").setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch3l1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Lessons(logUser,logUserID,3,1,"Second Polish Republic (1918-1939)", "https://www.youtube.com/watch?v=kfm0AQNzpkc").setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch3l2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Lessons(logUser,logUserID,3,2,"World War II (1939-1945)", "https://www.youtube.com/watch?v=17NArJ6wQqo").setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch1Test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Tests("1", logUser,logUserID).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch2Test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Tests("2", logUser,logUserID).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
        ch3Test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Tests("3", logUser,logUserID).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
    }
}

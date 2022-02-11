package com.itDidacticsTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class ProfilePage extends JFrame{
    private JButton logoutButton;
    private JTabbedPane tabbedPane;
    private JPanel MainPanel;
    private JPanel StatisticsPanel;
    private JButton backButton;
    private JPanel chapter1;
    private JPanel chapter2;
    private JPanel chapter3;
    private JLabel ls1ch1;
    private JLabel ls2ch1;
    private JLabel tch1;
    private JLabel trch1;
    private JLabel l1ch2;
    private JLabel l2ch2;
    private JLabel tch2;
    private JLabel trch2;
    private JLabel l1ch3;
    private JLabel l2ch3;
    private JLabel tch3;
    private JLabel trch3;

    String jdbcURL = "jdbc:mysql://localhost:3306/test_itdidactics?serverTimezone=UTC&useSSL=false";
    String usernameDB = "root";
    String passwordDB = "K4rOl78qw@";

    public ProfilePage(String logUser, Integer logUserID) throws HeadlessException, SQLException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(MainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(500, 600);

        Connection connection = (Connection) DriverManager.getConnection(jdbcURL,usernameDB, passwordDB);
        PreparedStatement st = (PreparedStatement) connection
                .prepareStatement(
                        "SELECT numOfAttempts, bestResult FROM statistics WHERE user_id =?"
                );
        st.setInt(1, logUserID);
        ArrayList<Integer> stat = new ArrayList<>();
        ResultSet rs = st.executeQuery();
        while(rs.next()){
            stat.add(rs.getInt(1));
            stat.add(rs.getInt(2));
        }
        //Statistics of Chapter 1
        ls1ch1.setText(String.valueOf(stat.get(0)));
        ls2ch1.setText(String.valueOf(stat.get(2)));
        tch1.setText(String.valueOf(stat.get(4)));
        trch1.setText(String.valueOf(stat.get(5)));

        //Statistics of Chapter 2
        l1ch2.setText(String.valueOf(stat.get(6)));
        l2ch2.setText(String.valueOf(stat.get(8)));
        tch2.setText(String.valueOf(stat.get(10)));
        trch2.setText(String.valueOf(stat.get(11)));

        //Statistics of Chapter 3
        l1ch3.setText(String.valueOf(stat.get(12)));
        l2ch3.setText(String.valueOf(stat.get(14)));
        tch3.setText(String.valueOf(stat.get(16)));
        trch3.setText(String.valueOf(stat.get(17)));


        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage().setVisible(true);
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new HomePage(logUser, logUserID).setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
    }
}

package com.itDidacticsTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WelcomePage extends JFrame{
    private JButton loginButton;
    private JButton registerButton;
    private JLabel welcomeText;
    private JPanel panel1_1;
    private JPanel panel1;

    public WelcomePage(String title) throws HeadlessException{
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1_1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(500,600);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Login button CLick");
                new LoginPage().setVisible(true);
                dispose();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Register button CLick");
                try {
                    new RegisterPage().setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });
    }

}

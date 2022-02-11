package com.itDidacticsTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginPage extends JFrame {
    private JPanel loginPanel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JButton backToStartButton;

    String jdbcURL = "jdbc:mysql://localhost:3306/test_itdidactics?serverTimezone=UTC&useSSL=false";
    String usernameDB = "root";
    String passwordDB = "K4rOl78qw@";

    public LoginPage() throws HeadlessException{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(500,600);
        backToStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage("Hello World").setVisible(true);
                dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = usernameField.getText();
                String password = passwordField.getText();

                try{
                    Connection connection = (Connection) DriverManager.getConnection(jdbcURL,usernameDB, passwordDB);
                    PreparedStatement st = (PreparedStatement) connection
                            .prepareStatement(
                                    "SELECT user_id, username, password FROM users WHERE username =? AND password =? "
                            );

                    st.setString(1, userName);
                    st.setString(2,password);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()){
                        int idUser = rs.getInt(1); //We got a id of login user
                        dispose();
                        new HomePage(userName, idUser).setVisible(true);
                        JOptionPane.showMessageDialog(loginButton,"You have successfully logged in");

                    }else{
                        JOptionPane.showMessageDialog(loginButton,"Wrong UserName or Password");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
    }
}

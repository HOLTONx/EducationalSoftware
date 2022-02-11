package com.itDidacticsTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class RegisterPage extends JFrame {
    private JPanel registrationPanel;
    private JTextField nameField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField repPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JLabel nameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel repPasswordLabel;
    private JLabel emailLabel;
    private JButton backToStartButton;

    String jdbcURL = "jdbc:mysql://localhost:3306/test_itdidactics?serverTimezone=UTC&useSSL=false";
    String usernameDB = "root";
    String passwordDB = "K4rOl78qw@";



    public RegisterPage() throws HeadlessException, SQLException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registrationPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(500,600);

        backToStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage("Back from register").setVisible(true);
                dispose();
            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String reppassword = repPasswordField.getText();
                String email = emailField.getText();
                Integer user_id = 0;

                if(password.equals(reppassword)){
                    //System.out.println(password + " " + reppassword);

                    try{
                        Connection connection = DriverManager.getConnection(jdbcURL,usernameDB,passwordDB);
                        PreparedStatement st = (PreparedStatement) connection
                                .prepareStatement(
                                        "SELECT username, password FROM users WHERE username =? "
                                );
                        st.setString(1, username);

                        String query = "INSERT INTO users(user_id, name, username, password, email) values ("+0+",'"+name+"','"+username+"','"+password+"','"+email+"')";
                        PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);


                        ResultSet rs = st.executeQuery();
                        if(rs.next()){
                            JOptionPane.showMessageDialog(registerButton,"This is already exist");
                        }else{
                            Statement sta = connection.createStatement();
//                            int x = sta.executeUpdate(query);
                            ps.execute();
                            ResultSet rs2 = ps.getGeneratedKeys();
                            int generatedKey = 0;
                            if(rs2.next()){
                                generatedKey = rs2.getInt(1);
                            }
                            System.out.println("ID = " + generatedKey);
                            JOptionPane.showMessageDialog(registerButton, "Registration Done");

                            ArrayList<String> statisticsQuery = new ArrayList<>();
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+1+", 'Lesson 1', "+0+", "+0+")");
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+1+", 'Lesson 2', "+0+", "+0+")");
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+1+", 'Test', "+0+", "+0+")");
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+2+", 'Lesson 1', "+0+", "+0+")");
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+2+", 'Lesson 2', "+0+", "+0+")");
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+2+", 'Test', "+0+", "+0+")");
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+3+", 'Lesson 1', "+0+", "+0+")");
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+3+", 'Lesson 2', "+0+", "+0+")");
                            statisticsQuery.add("INSERT INTO statistics(statistic_id, user_id, chapter, type, numOfAttempts, bestResult) VALUES("+0+", "+ generatedKey +", "+3+", 'Test', "+0+", "+0+")");
                            for(String n : statisticsQuery){
                                int x = sta.executeUpdate(n);
                            }


                            connection.close();
                            new LoginPage().setVisible(true);
                            dispose();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }else{
                    JOptionPane.showMessageDialog(registerButton, "Passwords aren't equal");
                }


            }
        });
    }
}

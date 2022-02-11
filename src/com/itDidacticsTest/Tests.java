package com.itDidacticsTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Tests extends JFrame{
    private JLabel mainLabel;
    private JPanel question1;
    private JLabel q1;
    private JRadioButton q1a1;
    private JRadioButton q1a2;
    private JRadioButton q1a3;
    private JRadioButton q1a4;
    private JPanel question2;
    private JLabel q2;
    private JRadioButton q2a1;
    private JRadioButton q2a2;
    private JRadioButton q2a3;
    private JRadioButton q2a4;
    private JPanel question3;
    private JLabel q3;
    private JRadioButton q3a1;
    private JRadioButton q3a2;
    private JRadioButton q3a3;
    private JRadioButton q3a4;
    private JPanel question4;
    private JLabel q4;
    private JRadioButton q4a1;
    private JRadioButton q4a2;
    private JRadioButton q4a3;
    private JRadioButton q4a4;
    private JPanel question5;
    private JPanel question6;
    private JPanel question7;
    private JLabel q5;
    private JRadioButton q5a1;
    private JRadioButton q5a2;
    private JRadioButton q5a3;
    private JRadioButton q5a4;
    private JLabel q6;
    private JRadioButton q6a1;
    private JRadioButton q6a2;
    private JRadioButton q6a3;
    private JRadioButton q6a4;
    private JLabel q7;
    private JRadioButton q7a1;
    private JRadioButton q7a2;
    private JRadioButton q7a3;
    private JRadioButton q7a4;
    private JPanel mainPanel;
    private JButton backToMenuButton;
    private JButton checkAnswersButton;



    ArrayList<JLabel> qArray = new ArrayList<>(
            Arrays.asList(q1,q2,q3,q4,q5,q6,q7)
    );
    JRadioButton[][] aArray = {
            {q1a1, q1a2, q1a3, q1a4},
            {q2a1, q2a2, q2a3, q2a4},
            {q3a1, q3a2, q3a3, q3a4},
            {q4a1, q4a2, q4a3, q4a4},
            {q5a1, q5a2, q5a3, q5a4},
            {q6a1, q6a2, q6a3, q6a4},
            {q7a1, q7a2, q7a3, q7a4}
    };

    public void setContext(ArrayList<testAssets> testContext, Integer chapterNumber){

        for(int i=0; i<testContext.size();i++){
            testAssets tA = testContext.get(i);
            qArray.get(i).setText(tA.getTestQuestion());
            int j = 0;
            aArray[i][j].setText(tA.getAnswer1()); j++;
            aArray[i][j].setText(tA.getAnswer2()); j++;
            aArray[i][j].setText(tA.getAnswer3()); j++;
            aArray[i][j].setText(tA.getAnswer4());
        }

        switch(chapterNumber){
            case 1:
                mainLabel.setText("History of Poland (966-1569)");
                break;
            case 2:
                mainLabel.setText("History of Poland (1569-1918)");
                break;
            case 3:
                mainLabel.setText("History of Poland (1918 - 1945)");
                break;
        }

    }

    public Tests(String chapterNumber, String logUser, Integer logUserID) throws SQLException {
        String fileName = "D:\\Nauka Programowania\\Java\\Projekty\\itDidacticsTest\\src\\com\\itDidacticsTest\\textAssets\\test"+chapterNumber+".txt";
        System.out.println("test"+chapterNumber+".txt");
        ArrayList<testAssets> tA = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String tokens[] = line.split("#");
                if (tokens.length == 6) {

                    testAssets ctemp = new testAssets(tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5]);
                    tA.add(ctemp);
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("Exception during class initialization");
        }
        setContext(tA, Integer.valueOf(chapterNumber));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(1000, 600);

        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new HomePage(logUser, logUserID).setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        checkAnswersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswersButton.setEnabled(false);
                Integer points = 0;
                for(int i=0; i<tA.size();i++){
                    testAssets checkA = tA.get(i);
                    String correctAnswer = checkA.getCorrectAnswer();
                    for(int j=0;j<4;j++){
                        if(aArray[i][j].isSelected() & aArray[i][j].getText().equals(correctAnswer)){
                            points++;
                            aArray[i][j].setForeground(Color.green);
                        }else{
                            aArray[i][j].setForeground(Color.red);
                        }
                    }
                }

                String jdbcURL = "jdbc:mysql://localhost:3306/test_itdidactics?serverTimezone=UTC&useSSL=false";
                String usernameDB = "root";
                String passwordDB = "K4rOl78qw@";
                Connection connection = null;
                try {
                    connection = (Connection) DriverManager.getConnection(jdbcURL,usernameDB, passwordDB);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                PreparedStatement st = null;
                int numofAttemps = 0;
                int actualBestResult = 0;
                try {
                    st = (PreparedStatement) connection
                            .prepareStatement(
                                    "SELECT numOfAttempts, bestResult FROM statistics WHERE (user_id ="+logUserID+" AND chapter ="+chapterNumber+" AND type = 'Test')"
                            );
                    ResultSet rs = st.executeQuery();

                    if(rs.next()){
                        numofAttemps = rs.getInt(1) + 1;
                        actualBestResult = rs.getInt(2);
                        System.out.println("Liczba podejsc: "+numofAttemps);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                if(points < actualBestResult){
                    JOptionPane.showMessageDialog(checkAnswersButton,"Repeat the lesson, score worse than the best. Your score : "+points+"/"+tA.size());
                    points = actualBestResult;
                }else if(points < 4){
                    JOptionPane.showMessageDialog(checkAnswersButton,"Repeat the lesson. Your score : "+points+"/"+tA.size());
                }else if(points >=4 & points<7){
                    JOptionPane.showMessageDialog(checkAnswersButton,"Close to perfect, but try to improve. Your score : "+points+"/"+tA.size());
                }else if(points == 7){
                    JOptionPane.showMessageDialog(checkAnswersButton,"Congratulations, you have mastered the topic 100%. Your score : "+points+"/"+tA.size());
                }


                try {
                    PreparedStatement st2 = (PreparedStatement) connection
                            .prepareStatement(
                                    "UPDATE statistics SET numOfAttempts ="+numofAttemps+", bestResult ="+points+" WHERE (user_id ="+logUserID+" AND chapter ="+chapterNumber+" AND type = 'Test')"
                            );
                    st2.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
    }
}

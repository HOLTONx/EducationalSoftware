package com.itDidacticsTest;

import com.sun.speech.freetts.VoiceManager;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Locale;
import javax.speech.synthesis.Voice;

public class Lessons extends JFrame{
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JTextPane lessonText;
    private JButton backButton;
    private JButton learnMoreWithVideoButton;
    private JButton listenToTextButton;

    String jdbcURL = "jdbc:mysql://localhost:3306/test_itdidactics?serverTimezone=UTC&useSSL=false";
    String usernameDB = "root";
    String passwordDB = "K4rOl78qw@";

    public void setContext(Integer chapterN, Integer lessonN, String subject) throws FileNotFoundException, BadLocationException {
        //Set subject of Lesson
        mainLabel.setText(subject);
        //Set text of lesson
        String fileName = "D:\\Nauka Programowania\\Java\\Projekty\\itDidacticsTest\\src\\com\\itDidacticsTest\\textAssets\\ch"+chapterN+"lesson"+lessonN+".txt";
        System.out.println("ch"+chapterN+"lesson"+lessonN+".txt");
        FileReader reader = new FileReader(fileName);
        try {
            lessonText.read(reader,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add a photo
        StyledDocument doc2 = (StyledDocument) lessonText.getDocument();
        Style style2 = doc2.addStyle("StyleName", null);
        StyleConstants.setIcon(style2, new ImageIcon("D:\\Nauka Programowania\\Java\\Projekty\\itDidacticsTest\\src\\com\\itDidacticsTest\\pictures\\ch"+chapterN+"l"+lessonN+"photo.png"));
        doc2.insertString(doc2.getLength(), "invisible text", style2);


    }

    public Lessons(String logUser, Integer logUserID, Integer chapterN, Integer lessonN, String lessonSubject, String linkYT) throws SQLException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setSize(740, 800);

        try {
            setContext(chapterN,lessonN, lessonSubject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        Connection connection = (Connection) DriverManager.getConnection(jdbcURL,usernameDB, passwordDB);
        PreparedStatement st = (PreparedStatement) connection
                .prepareStatement(
                        "SELECT numOfAttempts, bestResult FROM statistics WHERE (user_id ="+logUserID+" AND chapter ="+chapterN+" AND type = 'Lesson "+lessonN+"')"
                );
        ResultSet rs = st.executeQuery();
        int numofAttemps = 0;
        if(rs.next()){
            numofAttemps = rs.getInt(1) + 1;
        }

        PreparedStatement st2 = (PreparedStatement) connection
                .prepareStatement(
                        "UPDATE statistics SET numOfAttempts = "+numofAttemps+" WHERE (user_id ="+logUserID+" AND chapter ="+chapterN+" AND type = 'Lesson "+lessonN+"')"
                );
        //Thread.sleep(60000);

        Timer timer = new Timer(30000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    st2.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        timer.setRepeats(false); // Only execute once
        timer.start(); // Go go go!


        backButton.addActionListener(new ActionListener() {
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
        learnMoreWithVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(linkYT));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }

            }
        });


        listenToTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
            // Set property as Kevin Dictionary
            System.setProperty(
                "freetts.voices",
                "com.sun.speech.freetts.en.us"
                    + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                "com.sun.speech.freetts"
                + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer
                = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                lessonText.getText(), null);
            synthesizer.waitEngineState(
                Synthesizer.QUEUE_EMPTY);

            // Deallocate the Synthesizer.
            synthesizer.deallocate();
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }




            }
        });
    }
}

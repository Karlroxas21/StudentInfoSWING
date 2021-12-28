package com.karlmarx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class studentInfo extends JFrame implements ActionListener {



    JTextArea name = new JTextArea("Name");
    JTextArea course = new JTextArea("Course");

    JButton insert = new JButton("INSERT");



    JPanel panel = new JPanel();

    public studentInfo(){

        name.setBounds(0,10, 200, 20);
        course.setBounds(0, 40, 200, 20);
        insert.setBounds(50, 80, 100, 20);



//        panel.setBackground(Color.gray);
        panel.setBounds(10,10, 465, 245);
        panel.add(name);
        panel.add(course);
        panel.add(insert);

        insert.addActionListener(this);




        this.setTitle("Student Information");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.validate();
        panel.setLayout(null);
        panel.updateUI();
        this.add(panel);
    }

    public static void main(String[] args)  {
	new studentInfo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:sqlserver://DESKTOP-C280F8T\\MSSQLSERVER;databaseName=student";
        String user = "papers";
        String password = "papersarewhite";
        if(e.getSource()==insert){
         
            try{
                Connection connection = DriverManager.getConnection(url, user, password);

                
                
                String insertTo = "INSERT INTO testtable (name, course) values(?, ?)";
 

                PreparedStatement statement = connection.prepareStatement(insertTo);
                // ResultSet result = statement.executeQuery(sql);
                statement.setString(1, name.getText());
                statement.setString(2, course.getText());
               
            
                int rows = statement.executeUpdate();
                
                if(rows > 0){
                    System.out.println("Row has been inserted.");
                }
                connection.close();


            }
            catch (SQLException ex) {
                System.out.println("Oops, there's and error");
                ex.printStackTrace();
            }
        }
    }
}

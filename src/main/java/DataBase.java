import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class DataBase {

    public static Connection conn;
    public static Statement statement;
    public static ResultSet resultSet;


    @FXML
    private TextArea out;

    @FXML
    private TextField name;

    @FXML
    private TextField lastName;

    @FXML
    private TextField fatherLastName;

    @FXML
    private TextField dateBirth;

    @FXML
    private TextField group;

    @FXML
    private TextField serchId;


    public static void setConn() throws ClassNotFoundException, SQLException {
        conn = null;

        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:TEST1.s8db");
    }

    public static void createDB() throws SQLException {
        statement = conn.createStatement();
        statement.execute(
                "CREATE TABLE if not exists 'users' " +
                        "( 'id' INTEGER PRIMARY KEy AUTOINCREMENT, 'name' text, 'lastName' text, 'fatherName' text, 'dateBith' text,'group' text ); ");
    }

    public void writeUserInDB() throws SQLException {

        statement.execute("INSERT INTO 'users' ('name', 'lastName', 'fatherName','dateBith','group') VALUES (' "+name.getText()+" ',' "+lastName.getText()+" ',' "+fatherLastName.getText()+" ',' "+dateBirth.getText()+" ',' "+group.getText()+" ')");



    }
    public void deleteSudent() throws SQLException {
        statement.execute("DELETE FROM users WHERE id='"+serchId.getText()+"'");

    }

    public void readBD() throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");

            String name = resultSet.getString("name");
            String lastName = resultSet.getString("lastName");
            String fatherName = resultSet.getString("fatherName");
            String dateBith = resultSet.getString("dateBith");
            String group = resultSet.getString("group");

            out.appendText(id + " " + name + " " + lastName+" "+fatherName+" "+dateBith+" "+group+"\n");

            //System.out.println(id + " " + name + " " + lastName+" "+fatherName+" "+dateBith+" "+group);
        }
    }
    public  boolean checkUserId(int idNumberChek) throws SQLException, ClassNotFoundException {
        setConn();
        createDB();
        resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {

            int id = resultSet.getInt("id");


            if(id==idNumberChek){

                System.out.println("fff");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                String fatherName = resultSet.getString("fatherName");
                String dateBith = resultSet.getString("dateBith");
                String group = resultSet.getString("group");
                out.appendText(id + " " + name + " " + lastName+" "+fatherName+" "+dateBith+" "+group+"\n");
                out.appendText("\n");
               // System.out.println(id + " " + name + " " + lastName+" "+fatherName+" "+dateBith+" "+group);
               // closeDB();
            return true;
        }
    }

        //closeDB();
        return false;

    }

    public static void closeDB() throws SQLException {
        resultSet.close();
        statement.close();
        conn.close();
    }






    public void Registr(ActionEvent actionEvent) {
        try {
            setConn();
            createDB();
            writeUserInDB();

            closeDB();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }




    public void serchStudent() {

        try {
            setConn();
            createDB();
            checkUserId(Integer.parseInt(serchId.getText()));
            closeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void showAllStudent(ActionEvent actionEvent) {
        try {

            setConn();
            createDB();
            readBD();
            closeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void DeleteStudent(ActionEvent actionEvent) {
        try {

            setConn();
            createDB();
           deleteSudent();
            closeDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

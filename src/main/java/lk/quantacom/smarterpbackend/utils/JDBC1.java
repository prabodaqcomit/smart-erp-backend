package lk.quantacom.smarterpbackend.utils;

//import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC1 {

    public static Connection con() throws Exception {
        try {

            String host=Settings.readSettings("MYSQL_SERVER_CON");
            String port=Settings.readSettings("MYSQL_SERVER_PORT");
            String db=Settings.readSettings("MYSQL_SERVER_DATABASE1");
            String user=Settings.readSettings("MYSQL_SERVER_USER");
            String pw=Settings.readSettings("MYSQL_SERVER_PASSWORD");

            String url = "jdbc:mysql://" + host + ":"+port+"/"+db;

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection(url, user, pw);

            return c;
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, e, "DB Problem", JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

}
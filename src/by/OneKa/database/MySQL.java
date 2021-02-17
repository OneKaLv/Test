package by.OneKa.database;

import java.sql.*;

public class MySQL {

    public static Connection conn;
    public static Statement statmt;
    public static String mobs = "mobs";

    private static int numkill;

    private String createTable = "CREATE TABLE IF NOT EXISTS mobs (id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY, player_name varchar(16),mobname varchar (10), timekill TIME)";

    public MySQL(String url, String dbName, String user, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + url + "/" + dbName + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true",
                    user, pass);
            statmt = conn.createStatement();
            statmt.execute(createTable);
            statmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insert(String name, String mobname) {
        try {
            PreparedStatement e = conn.prepareStatement(
                    "INSERT INTO " + mobs + " (player_name,mobname,timekill) VALUES (?,?,?);");
            e.setString(1, name);
            e.setString(2, mobname);
            e.setTime(3,new Time(System.currentTimeMillis()));
            e.executeUpdate();
            e.close();
            System.out.println("work1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package Logica;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    
    public String db = "baseday";
    public String user = "root";
    public String pass = "admin";
    public String url = "jdbc:mysql://127.0.0.1/" + db;
    
    public Conexion(){
    }
    
    public Connection conectar(){
        Connection link = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            link = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return link;
    }
}

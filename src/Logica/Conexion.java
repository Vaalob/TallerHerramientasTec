package Logica;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    
    String db = "basereserva";
    String user = "root";
    String pass = "admin";
    String url = "jdbc:mysql://127.0.0.1/" +db;
    Connection conn;
    Statement stmt;
    ResultSet rs;
    
    public Conexion(){
    }   
    
    public Connection conectar(){
    
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return conn;
    }

}


package Logica;

import Datos.Producto;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Product {
    
    private Conexion mysql = new Conexion();
    private Connection conn = mysql.conectar();
    private String SQL = "";
    public Integer totalregistros;
    
    public DefaultTableModel mostrar(String buscar){
        
        SQL = "SELECT idproducto, nombre, descripcion, precio FROM producto";
        
        DefaultTableModel modelo;
        String [] titulos = {"ID", "Nombre", "Descripcion", "Precio"};
        modelo = new DefaultTableModel(null, titulos);
        String [] registro = new String[4];
        totalregistros = 0;
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idproducto");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("descripcion");
                registro[3] = rs.getString("precio");
                
                modelo.addRow(registro);
                totalregistros = totalregistros + 1;
                
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return modelo;
    }
    
    public boolean insertar(Producto dts){
        
        SQL = "INSERT INTO producto (nombre, descripcion, precio) VALUES (?,?,?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setDouble(3, dts.getPrecio());
            
            int n = pst.executeUpdate();
            
            if(n != 0){
                return true;
            }
            
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
    }
    
    public boolean editar(Producto dts){
        
        SQL = "UPDATE producto SET nombre=?, descripcion=?, precio=? WHERE idproducto=?";
        
        
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setDouble(3, dts.getPrecio());
            pst.setInt(4, dts.getIdproducto());
            
            int n = pst.executeUpdate();
            
            if(n != 0){
                return true;
            }
            
            else{
                return false;
            }
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
    }
    
    public boolean eliminar(Producto dts){
        
        SQL = "DELETE FROM producto WHERE idproducto=?";

        try {
            
            PreparedStatement pst = conn.prepareStatement(SQL);

            pst.setInt(1, dts.getIdproducto());
            
            int n = pst.executeUpdate();
            
            if(n != 0){
                return true;
            }
            
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
    }
    
}

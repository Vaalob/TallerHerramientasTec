package Logica;

import Datos.Servicios;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Services {
    
    private Conexion mysql = new Conexion();
    private Connection conn = mysql.conectar();
    private String SQL = "";
    public Integer totalregistros;
    
    public DefaultTableModel mostrar(String buscar){
        
        SQL = "SELECT idservicio, tipo_servicio, descripcion, precio FROM servicio";
        
        DefaultTableModel modelo;
        String [] titulos = {"ID", "Tipo de Servicio", "Descripcion", "Precio"};
        modelo = new DefaultTableModel(null, titulos);
        String [] registro = new String[4];
        totalregistros = 0;
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idservicio");
                registro[1] = rs.getString("tipo_servicio");
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
    
    public boolean insertar(Servicios dts){
        
        SQL = "INSERT INTO servicio (tipo_servicio, descripcion, precio) VALUES (?,?,?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, dts.getTipo_servicio());
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
    
    public boolean editar(Servicios dts){
        
        SQL = "UPDATE servicio SET tipo_servicio=?, descripcion=?, precio=? WHERE idservicio=?";
        
        
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setString(1, dts.getTipo_servicio());
            pst.setString(2, dts.getDescripcion());
            pst.setDouble(3, dts.getPrecio());
            pst.setInt(4, dts.getIdservicio());
            
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
    
    public boolean eliminar(Servicios dts){
        
        SQL = "DELETE FROM servicio WHERE idservicio=?";

        try {
            
            PreparedStatement pst = conn.prepareStatement(SQL);

            pst.setInt(1, dts.getIdservicio());
            
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
    
    public DefaultTableModel servicios(String buscar){
        
        SQL = "SELECT idservicio, tipo_servicio, descripcion, precio FROM servicio";
        
        DefaultTableModel modelo;
        String [] titulos = {"ID", "Tipo de Servicio", "Descripcion", "Precio"};
        modelo = new DefaultTableModel(null, titulos);
        String [] registro = new String[4];
        totalregistros = 0;
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idservicio");
                registro[1] = rs.getString("tipo_servicio");
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
    
}

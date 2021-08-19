package Logica;

import Datos.Productos;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class Product {
    
    private final Conexion mysql = new Conexion();
    private String sSQL="";
    public Integer totalregistros;
    private final Connection conn;

    public Product(){
        this.conn = mysql.conectar();
    }
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Producto", "Descripcion", "Precio"};
        
        String [] registro = new String [4];
        
        totalregistros = 0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL="select * from producto where nombre like '%" + buscar + "% ' order by idproducto";
        
        try {
            Statement st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idproducto");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("descripcion");
                registro[3] = rs.getString("precio");
                
                totalregistros=totalregistros+1;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    public boolean insertar(Productos dts){
        sSQL="insert into producto (nombre, descripcion, precio)" + "values (?,?,?)";
        
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setDouble(3, dts.getPrecio());
            
            int n = pst.executeUpdate();
            
            return n != 0;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    public boolean editar(Productos dts){
        sSQL = "update producto set nombre=?, descripcion=?, precio=?" + " where idproducto=?";
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setDouble(3, dts.getPrecio());
            pst.setInt(4, dts.getIdproducto());
            
            int n = pst.executeUpdate();
            
            return n != 0;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean eliminar(Productos dts){
        sSQL = "delete from producto where idproducto=?";
        
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdproducto());
            
            int n = pst.executeUpdate();
            
            return n != 0;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}

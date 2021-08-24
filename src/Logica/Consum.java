package Logica;

import Datos.Consumo;
import Datos.Producto;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Consum {
    
    private Conexion mysql = new Conexion();
    private Connection conn = mysql.conectar();
    private String SQL = "";
    public Integer totalregistros;
    public Double totalcompra;
    
    public DefaultTableModel mostrar(String buscar){
        
        SQL = "SELECT c.idconsumo, c.idcita, c.idproducto, p.nombre, c.cantidad, c.precio_consumo precio "+
                " FROM consumo c INNER JOIN producto p on c.idproducto=p.idproducto WHERE c.idcita = " + buscar + " ORDER BY idconsumo DESC";
        
        DefaultTableModel modelo;
        String [] titulos = {"ID", "idcita", "idproducto", "Producto", "Cantidad", "Precio"};
        modelo = new DefaultTableModel(null, titulos);
        String [] registro = new String[6];
        totalregistros = 0;
        totalcompra = 0.0;
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idconsumo");
                registro[1] = rs.getString("idcita");
                registro[2] = rs.getString("idproducto");
                registro[3] = rs.getString("nombre");
                registro[4] = rs.getString("cantidad");
                registro[5] = rs.getString("precio_consumo");
                
                totalcompra = totalcompra +(rs.getInt("cantidad") * rs.getDouble("precio_consumo"));
                modelo.addRow(registro);
                totalregistros = totalregistros + 1;
                
                
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return modelo;
    }
    
    public boolean insertar(Consumo dts){
        
        SQL = "INSERT INTO consumo (idcita, idproducto, cantidad, precio_consumo) VALUES (?,?,?,?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setInt(1, dts.getIdcita());
            pst.setInt(2, dts.getIdproducto());
            pst.setInt(3, dts.getCantidad());
            pst.setDouble(4, dts.getPrecio_consumo());
            
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
    
    public boolean editar(Consumo dts){
        
        SQL = "UPDATE consumo SET idcita=?, idproducto=?, cantidad=?, precio_consumo=? WHERE idconsumo=?";
        
        
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setInt(1, dts.getIdcita());
            pst.setInt(2, dts.getIdproducto());
            pst.setInt(3, dts.getCantidad());
            pst.setDouble(4, dts.getPrecio_consumo());
            pst.setInt(4, dts.getIdconsumo());
            
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
    
    public boolean eliminar(Consumo dts){
        
        SQL = "DELETE FROM consumo WHERE idconsumo=?";

        try {
            
            PreparedStatement pst = conn.prepareStatement(SQL);

            pst.setInt(1, dts.getIdconsumo());
            
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

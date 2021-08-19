package Logica;

import Datos.Servicio;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class Opciones {
    
    private final Conexion mysql = new Conexion();
    private String sSQL="";
    public Integer totalregistros;
    private final Connection conn;

    public Opciones(){
        this.conn = mysql.conectar();
    }
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Tipo Servicio", "Descripcion", "Precio"};
        
        String [] registro = new String [4];
        
        totalregistros = 0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL="select * from servicio where tipo_servicio like '%" + buscar + "% ' order by idservicio";
        
        try {
            Statement st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idservicio");
                registro[1] = rs.getString("tipo_servicio");
                registro[2] = rs.getString("pdescripcion");
                registro[3] = rs.getString("precio_servicio");
                
                totalregistros=totalregistros+1;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    public boolean insertar(Servicio dts){
        sSQL="insert into servicio (tipo_servicio, descripcion, precio_servicio)" + "values (?,?,?)";
        
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo_servicio());
            pst.setString(2, dts.getDescripcion());
            pst.setDouble(3, dts.getPrecio_servicio());
            
            int n = pst.executeUpdate();
            
            if (n != 0){
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
    
    
    public boolean editar(Servicio dts){
        sSQL = "update servicio set tipo_servicio=?, descripcion=?, tipo_precio=?" + " where idservicio=?";
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            pst.setString(1, dts.getTipo_servicio());
            pst.setString(2, dts.getDescripcion());
            pst.setDouble(3, dts.getPrecio_servicio());
            pst.setInt(4, dts.getIdservicio());
            
            int n = pst.executeUpdate();
            
            if (n != 0){
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
    
    public boolean eliminar(Servicio dts){
        sSQL = "delete from servicio where idservicio=?";
        
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdservicio());
            
            int n = pst.executeUpdate();
            
            if (n != 0){
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

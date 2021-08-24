package Logica;

import Datos.Citas;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cit {
    
    private Conexion mysql = new Conexion();
    private Connection conn = mysql.conectar();
    private String SQL = "";
    public Integer totalregistros;
    
    public DefaultTableModel mostrar(String buscar){
        
        SQL = "SELECT c.idcita, c.idservicio, s.tipo_servicio, c.idcliente,"
                + "(SELECT nombre FROM persona WHERE idpersona=c.idcliente)AS clienten, "
                + "(SELECT apellido FROM persona WHERE idpersona=c.idcliente)AS clienteap, " 
                + "c.idtrabajador, (SELECT nombre FROM persona WHERE idpersona=c.idtrabajador)AS trabajadorn, "
                + "(SELECT apellido FROM persona WHERE idpersona=c.idtrabajador)AS trabajadorap, "
                + " c.tipo_cita, c.fecha_cita, c.costo_total FROM cita c INNER JOIN servicio s on c.idservicio=s.idservicio WHERE c.fecha_cita LIKE '%" + buscar + "%' ORDER BY idcita DESC";
        
        DefaultTableModel modelo;
        String [] titulos = {"ID", "idservicio", "Tipo Servicio", "idcliente", "Cliente", "idtrabajador", "Trabajador", "Tipo Cita", "Fecha de Cita", "Costo"};
        modelo = new DefaultTableModel(null, titulos);
        String [] registro = new String[10];
        totalregistros = 0;
        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idcita");
                registro[1] = rs.getString("idservicio");
                registro[2] = rs.getString("tipo_servicio");
                registro[3] = rs.getString("idcliente");
                registro[4] = rs.getString("clienten") + " " + rs.getString("clienteap");
                registro[5] = rs.getString("idtrabajador");
                registro[6] = rs.getString("trabajadorn") + " " + rs.getString("trabajadorap");
                registro[7] = rs.getString("tipo_cita");
                registro[8] = rs.getString("fecha_cita");
                registro[9] = rs.getString("costo_total");
                
                
                
                modelo.addRow(registro);
                totalregistros = totalregistros + 1;
                
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        return modelo;
    }
    
    public boolean insertar(Citas dts){
        
        SQL = "INSERT INTO cita (idservicio, idcliente, idtrabajador, tipo_cita, fecha_cita, costo_total) VALUES (?,?,?,?,?,?)";
        
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setInt(1, dts.getIdservicio());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_cita());
            pst.setDate(5, (Date) dts.getFecha_cita());
            pst.setDouble(6, dts.getCosto_total());
            
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
    
    public boolean editar(Citas dts){
        
        SQL = "UPDATE cita SET idservicio=?, idcliente=?, idtrabajador=?, tipo_cita=?, fecha_cita=?, costo_total=? WHERE idcita=?";
        
        try {
            
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.setInt(1, dts.getIdservicio());
            pst.setInt(2, dts.getIdcliente());
            pst.setInt(3, dts.getIdtrabajador());
            pst.setString(4, dts.getTipo_cita());
            pst.setDate(5, (Date) dts.getFecha_cita());
            pst.setDouble(6, dts.getCosto_total());
            
            pst.setInt(7, dts.getIdcita());
            
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
    
    public boolean eliminar(Citas dts){
        
        SQL = "DELETE FROM cita WHERE idcita=?";

        try {
            
            PreparedStatement pst = conn.prepareStatement(SQL);

            pst.setInt(1, dts.getIdcita());
            
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

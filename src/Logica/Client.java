package Logica;

import Datos.Cliente;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;

public class Client {
    
    private final Conexion mysql = new Conexion();
    private String sSQL="";
    private String sSQL2="";
    public Integer totalregistros;
    private final Connection conn;

    public Client(){
        this.conn = mysql.conectar();
    }
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        
        String [] titulos = {"ID", "Nombre", "Apellido", "Telefono", "Codigo"};
        
        String [] registro = new String [5];
        
        totalregistros = 0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL="select p.idpersona,p.nombre,p.apellido,p.telefono,c.codigo_cliente from persona p inner join cliente c " +
                "on p.idpersona=c.idpersona where nombre like '%" +
                buscar + "% ' order by idpersona desc";
        
        try {
            Statement st = (Statement) conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            
            while(rs.next()){
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("telefono");
                registro[4] = rs.getString("codigo_cliente");
                
                totalregistros=totalregistros+1;
                modelo.addRow(registro);
            }
            
            return modelo;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    public boolean insertar(Cliente dts){
        sSQL="insert into persona (nombre, apellido, telefono)" + "values (?,?,?)";
        sSQL2="insert into cliente (idpersona, codigo_cliente)" + "values ((select idpersona from persona order by idpersona desc limit 1),?)";
        
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            PreparedStatement pst2 = (PreparedStatement) conn.prepareStatement(sSQL2);
            
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTelefono());

            pst2.setString(1, dts.getCodigo_cliente());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                return n2 != 0;

            } else {
                return false;
            }
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    public boolean editar(Cliente dts){
        sSQL = "update persona set nombre=?, apellido=?, telefono=?  where idpersona=?";
        sSQL2 = "update persona set codigo_cliente=? where idpersona=?";
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            PreparedStatement pst2 = (PreparedStatement) conn.prepareStatement(sSQL2);
            
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTelefono());
            pst.setInt(4, dts.getIdpersona());

            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                return n2 != 0;

            } else {
                return false;
            }
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    public boolean eliminar (Cliente dts){
        sSQL = "delete from cliente where idpersona=?";
        sSQL2 = "delete from cliente where idpersona=?";
        
        try {
            
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement(sSQL);
            PreparedStatement pst2 = (PreparedStatement) conn.prepareStatement(sSQL2);
            
            pst.setInt(1, dts.getIdpersona());

            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                return n2 != 0;

            } else {
                return false;
            }
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}
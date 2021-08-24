package Logica;

import Datos.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Client {

    private Conexion mysql = new Conexion();
    private Connection conn = mysql.conectar();
    private String SQL = "";
    private String SQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {

        SQL = "SELECT p.idpersona, p.nombre, p.apellido, p.telefono, p.email, c.codigo_cliente "
                + "FROM persona p INNER JOIN cliente c on p.idpersona=c.idpersona WHERE codigo_cliente LIKE '%" + buscar + "%' ORDER BY idpersona DESC";

        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "Apellido", "Telefono", "Email", "Codigo Cliente"};
        modelo = new DefaultTableModel(null, titulos);
        String[] registro = new String[6];
        totalregistros = 0;

        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("telefono");
                registro[4] = rs.getString("email");
                registro[5] = rs.getString("codigo_cliente");

                modelo.addRow(registro);
                totalregistros = totalregistros + 1;

            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return modelo;
    }

    public boolean insertar(Cliente dts) {

        SQL = "INSERT INTO persona (nombre, apellido, telefono, email) VALUES (?,?,?,?)";

        SQL2 = "INSERT INTO cliente (idpersona, codigo_cliente) VALUES ((SELECT idpersona FROM persona ORDER BY idpersona DESC LIMIT 1),?)";

        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            PreparedStatement pst2 = conn.prepareStatement(SQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTelefono());
            pst.setString(4, dts.getEmail());

            pst2.setString(1, dts.getCodigo_cliente());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public boolean editar(Cliente dts) {

        SQL = "UPDATE persona SET nombre=?, apellido=?, telefono=?, email=? WHERE idpersona=?";

        SQL2 = "UPDATE cliente SET codigo_cliente=? WHERE idpersona=?";

        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            PreparedStatement pst2 = conn.prepareStatement(SQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTelefono());
            pst.setString(4, dts.getEmail());
            pst.setInt(6, dts.getIdpersona());

            pst2.setString(1, dts.getCodigo_cliente());
            pst2.setInt(2, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public boolean eliminar(Cliente dts) {

        SQL = "DELETE FROM cliente WHERE idpersona=?";
        
        SQL2 = "DELETE FROM persona WHERE idpersona=?";

        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            PreparedStatement pst2 = conn.prepareStatement(SQL2);

            
            pst.setInt(1, dts.getIdpersona());

            pst2.setInt(1, dts.getIdpersona());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

}

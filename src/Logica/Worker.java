package Logica;

import Datos.Trabajador;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Worker {

    private Conexion mysql = new Conexion();
    private Connection conn = mysql.conectar();
    private String SQL = "";
    private String SQL2 = "";
    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {

        SQL = "SELECT p.idpersona, p.nombre, p.apellido, p.telefono, p.email, t.acceso, t.login, t.password "
                + "FROM persona p INNER JOIN trabajador t on p.idpersona=t.idpersona WHERE acceso LIKE '%" + buscar + "%' ORDER BY idpersona DESC";

        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "Apellido", "Telefono", "Email", "Acceso", "Login", "Password"};
        modelo = new DefaultTableModel(null, titulos);
        String[] registro = new String[8];
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
                registro[5] = rs.getString("acceso");
                registro[6] = rs.getString("login");
                registro[7] = rs.getString("password");

                modelo.addRow(registro);
                totalregistros = totalregistros + 1;

            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return modelo;
    }

    public boolean insertar(Trabajador dts) {

        SQL = "INSERT INTO persona (nombre, apellido, telefono, email) VALUES (?,?,?,?)";

        SQL2 = "INSERT INTO trabajador (idpersona, acceso, login, password) VALUES ((SELECT idpersona FROM persona ORDER BY idpersona DESC LIMIT 1),?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            PreparedStatement pst2 = conn.prepareStatement(SQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTelefono());
            pst.setString(4, dts.getEmail());

            pst2.setString(1, dts.getAcceso());
            pst2.setString(2, dts.getLogin());
            pst2.setString(3, dts.getPassword());

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

    public boolean editar(Trabajador dts) {

        SQL = "UPDATE persona SET nombre=?, apellido=?, telefono=?, email=? WHERE idpersona=?";

        SQL2 = "UPDATE trabajador SET acceso=?, login=?, password=? WHERE idpersona=?";

        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            PreparedStatement pst2 = conn.prepareStatement(SQL2);

            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTelefono());
            pst.setString(4, dts.getEmail());
            pst.setInt(6, dts.getIdpersona());

            pst2.setString(1, dts.getAcceso());
            pst2.setString(2, dts.getLogin());
            pst2.setString(3, dts.getPassword());
            pst2.setInt(4, dts.getIdpersona());

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

    public boolean eliminar(Trabajador dts) {

        SQL = "DELETE FROM trabajador WHERE idpersona=?";
        
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
    
    
    public DefaultTableModel login(String login, String password) {

        SQL = "SELECT p.idpersona, p.nombre, p.apellido, t.acceso, t.login, t.password "
                + "FROM persona p INNER JOIN trabajador t on p.idpersona=t.idpersona WHERE t.login='" + login + "' and t.password='" 
                + password + "' ";

        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombre", "Apellido", "Acceso", "Login", "Password"};
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
                registro[3] = rs.getString("acceso");
                registro[4] = rs.getString("login");
                registro[5] = rs.getString("password");

                modelo.addRow(registro);
                totalregistros = totalregistros + 1;

            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }

        return modelo;
    }

}

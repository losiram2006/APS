/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.EntidadMaestro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author julve
 */
public class ControlMaestro {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";

    //      C01:MÉTODO  QUE REGISTRA  UN NUEVO MAESTRO
    public boolean registrarMaestro(EntidadMaestro dts) {
        sSQL = "insert into maestro (NumeroEmpleado,Nombre,ApellidoPaterno,ApellidoMaterno,HorasAsignadas)"
                + "values (?,?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getNumeroEmpleado());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApePaterno());
            pst.setString(4, dts.getApeMaterno());
//            pst.setString(5, dts.getFechaIngreso());
            pst.setInt(5, dts.getHorasAsignadas());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
//          HU07  Se debe identificar  cuando un registro nuevo ya existe
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ya  existe el maestro registrado ");

            return false;
        }
    }

    public DefaultTableModel consultarMaestroTabla(int buscar) throws SQLException {
        DefaultTableModel modelo;

        String[] titulos = {"Num. Empleado", "Nombre", "ape Paterno", "ape Materno", "Hrs asignadas"};

        String[] Maestros = new String[5];

        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from maestro where NumeroEmpleado like '%" + buscar + "%' order by NumeroEmpleado";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                Maestros[0] = rs.getString("NumeroEmpleado");
                Maestros[1] = rs.getString("Nombre");
                Maestros[2] = rs.getString("ApellidoPaterno");
                Maestros[3] = rs.getString("ApellidoMaterno");
                Maestros[5] = rs.getString("HorasAsignadas");

                modelo.addRow(Maestros);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
//    SE VISUALIZA  LA información de registros nuevos  y los  existentes

    public DefaultTableModel consultarGeneralMaestroTabla() throws SQLException {
        DefaultTableModel modelo;

        String[] titulos = {"Num. Empleado", "Nombre", "Ape Paterno", "Ape Materno", "Hrs asignadas"};

        String[] Maestros = new String[5];

        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from maestro";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                Maestros[0] = rs.getString("NumeroEmpleado");
                Maestros[1] = rs.getString("Nombre");
                Maestros[2] = rs.getString("ApellidoPaterno");
                Maestros[3] = rs.getString("ApellidoMaterno");
                Maestros[4] = rs.getString("HorasAsignadas");

                modelo.addRow(Maestros);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

    //C01: MÉTODO   QUE  MODIFICA  UN MAESTRO  EXISTENTE
    public boolean modificarMaestro(EntidadMaestro dts, int buscar) {
        sSQL = "update maestro set Nombre=?, ApellidoPaterno=?, ApellidoMaterno=?, HorasAsignadas=? where NumeroEmpleado like '%" + buscar + "%'";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
//          pst.setInt(1, dts.getNumeroEmpleado());
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApePaterno());
            pst.setString(3, dts.getApeMaterno());
            pst.setInt(4, dts.getHorasAsignadas());
            int n = pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "los datos han sido modificados satisfactoriamente");
            System.out.println("n" + n);
            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }
    //C01:MÉTODO  QUE  ELIMINA UN REGISTRO EXISTENTE EN MAESTRO

    public boolean eliminarMaestro(EntidadMaestro dts) {
        sSQL = "delete from maestro where NumeroEmpleado=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getNumeroEmpleado());
            int n = pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "los datos han sido eliminados satisfactoriamente");
            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }
// C02:MÉTODO  QUE HACE  UNA CONSULTA ESPECÍFICA   POR NNUMERO DE EMPLEADO

    public String consultarMaestroEspecifico(int buscar) throws SQLException {
        String cadena = "";
        DefaultTableModel modelo;

        String[] titulos = {"Num. Empleado", "Nombre", "ape Paterno", "ape Materno", "Hrs asignadas"};

        String[] Maestros = new String[5];

        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from maestro where NumeroEmpleado like '%" + buscar + "%' order by NumeroEmpleado";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                String numempl = rs.getString("NumeroEmpleado");
                String nombre = rs.getString("Nombre");
                String aPaterno = rs.getString("ApellidoPaterno");
                String aMaterno = rs.getString("ApellidoMaterno");
                String HAsignacion = rs.getString("HorasAsignadas");

                modelo.addRow(Maestros);
                cadena = cadena + numempl + "-" + nombre + "-" + aPaterno + "-" + aMaterno + "-" + HAsignacion;

//                System.out.println("cadena: " +  cadena);
            }

            return cadena;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
///  C02: MÉTODO QUE VALIDA SI EL  MAESTRO TIENE  UN HORARIO  ASIGNADO PARA NO MODIFICARLO O ELIMINARLO

    public int consultarMaestroTieneHorario(int numEmpleado) throws SQLException {
        int cadena = 0;

        sSQL = "select NumeroEmpleado from horario where NumeroEmpleado like  '%" + numEmpleado + "%';";

        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sSQL);

        while (rs.next()) {
            int numero = rs.getInt("NumeroEmpleado");

            cadena = cadena + numero;
        }

        return cadena;
    }

    public ResultSet consultarMaestroGeneral() throws SQLException {

        String cadena = "";

        DefaultListModel modelo = new DefaultListModel();

        sSQL = "select NumeroEmpleado, Nombre, ApellidoPaterno, ApellidoMaterno from maestro;";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            return rs;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.EntidadSalon;
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
public class ControlSalon {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
//               HU01 Debo registrar un salón
    public boolean RegistrarSalon(EntidadSalon dts) {
        sSQL = "insert into salon (NumSalon)" + "values (?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getNumeroSalon());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }
// HU01 Debo ver si Numero de salón existe
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);

            return false;
        }
    }

    public DefaultTableModel consultarSalon(String buscar) throws SQLException {
        DefaultTableModel modelo;

        String[] titulos = {"Numero de Salon"};

        String[] Grupos = new String[1];

        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select * from salon where NumSalon like '%" + buscar + "%' order by NumSalon";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                Grupos[0] = rs.getString("NumSalon");

                modelo.addRow(Grupos);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
//                HU03 Debe poder eliminar un salón
    public boolean eliminarSalon(EntidadSalon dts) {
        sSQL = "delete from salon where NumSalon=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);

            pst.setInt(1, dts.getNumeroSalon());
            int n = pst.executeUpdate();

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
//                 HU02 Debo poder modificar un salón existente
    public boolean modificarSalon(EntidadSalon dts, int buscar) {
        sSQL = "update Salon set NumSalon=? where NumSalon like '%" + buscar + "%'";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getNumeroSalon());

            int n = pst.executeUpdate();

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
//            HU01 Debo consultar al menos un numero de  salon
    public String consultarSalonEspecifico(String buscar) throws SQLException {
        String cadena = "";

        sSQL = "select * from Salon where NumSalon like '%" + buscar + "%' order by NumSalon";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                String numSalon = rs.getString("NumSalon");

                cadena = cadena + numSalon;
            }

            return cadena;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
// HU03 Debe validar si  tiene un horario  asignado antes de eliminar
    public int consultarSalonTieneHorario(int salon) throws SQLException {
        int cadena = 0;

        sSQL = "select NumSalon  from horario where NumSalon like '%" + salon + "%';";
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sSQL);

        while (rs.next()) {
            int numSalon = rs.getInt("NumSalon");

            cadena = cadena + numSalon;
        }

        return cadena;
    }
    public ResultSet consultarSalonGeneral() throws SQLException{
 
        String cadena="";
        
        DefaultListModel modelo=new DefaultListModel();
        
        sSQL="select NumSalon from salon ;";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
                
            return rs;
            
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marisol
 */
public class ControlReporte {

    Conexion mysql = new Conexion();
    Connection connection = mysql.conectar();
    String sSQL = "";
    PreparedStatement ps = null;

    /*Método que muestra los datos   en la tabla*/
    public DefaultTableModel mostrarDatos(String maestro) {
        int idHorario = 0;
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            modelo.addColumn("idHorario");
            modelo.addColumn("Maestro");
            modelo.addColumn("Materia");
            modelo.addColumn("Grupo");
            modelo.addColumn("Dia");
            modelo.addColumn("Hora");
            
            if (maestro.equals("")) {
                sSQL = "select idHorario, Nombre, ApellidoPaterno, ApellidoMaterno, NombreMateria, Grupo_NumGrupo, Dia, Hora from horario, maestro, materia, grupo, salon WHERE  horario.Maestro_NumeroEmpleado=maestro.NumeroEmpleado and horario.Materia_ClaveMateria=materia.ClaveMateria and horario.Salon_NumSalon=salon.NumSalon and horario.Grupo_NumGrupo=grupo.NumGrupo;";

            } else {
                if (maestro.equals(maestro)) {
                    sSQL = "select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria, Grupo_NumGrupo, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where Maestro_NumeroEmpleado like '%" + maestro + "%';";
                }
            }

            String[] Horarios = new String[6];

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            System.out.println("el primer mostrar" + sSQL);

            while (rs.next()) {
                idHorario = Integer.parseInt(rs.getString(1));
                Horarios[0] = rs.getString("idHorario");
                Horarios[1] = rs.getString("Nombre") + " " + rs.getString("ApellidoPaterno") + " " + rs.getString("ApellidoMaterno") + " ";//  consulta  id  traer
                Horarios[2] = rs.getString("NombreMateria") + " ";
                Horarios[3] = rs.getString("Grupo_NumGrupo") + " ";
                Horarios[4] = rs.getString("Dia");
                Horarios[5] = rs.getString("Hora");
                modelo.addRow(Horarios);
            }

            return modelo;

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showConfirmDialog(null, e);

        }
        return null;
    }

    public DefaultTableModel mostrarDatos2(String grupo) {
        int idHorario = 0;
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            modelo.addColumn("idHorario");
            modelo.addColumn("Maestro");
            modelo.addColumn("Materia");
            modelo.addColumn("Salon");
            modelo.addColumn("Dia");
            modelo.addColumn("Hora");

             if (grupo.equals("")) {
                sSQL = "select idHorario, Nombre, ApellidoPaterno, ApellidoMaterno, NombreMateria, NumGrupo, Dia, Hora from horario, maestro, materia, grupo, salon WHERE  horario.Maestro_NumeroEmpleado=maestro.NumeroEmpleado and horario.Materia_ClaveMateria=materia.ClaveMateria and horario.Salon_NumSalon=salon.NumSalon and horario.Grupo_NumGrupo=grupo.NumGrupo;";

            } else 
            if (grupo.equals(grupo)) {
                sSQL = "select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria, Salon_NumSalon, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where  Grupo_NumGrupo like '%" + grupo + "%';";
            }
            String[] Horarios = new String[6];

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            System.out.println("el primer mostrar" + sSQL);

            while (rs.next()) {
                idHorario = Integer.parseInt(rs.getString(1));
                Horarios[0] = rs.getString("idHorario");
                Horarios[1] = rs.getString("Nombre") + " " + rs.getString("ApellidoPaterno") + " " + rs.getString("ApellidoMaterno") + " ";//  consulta  id  traer
                Horarios[2] = rs.getString("NombreMateria") + " ";
                Horarios[3] = rs.getString("Salon_NumSalon") + " ";
                Horarios[4] = rs.getString("Dia");
                Horarios[5] = rs.getString("Hora");

                modelo.addRow(Horarios);

            }

            return modelo;

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showConfirmDialog(null, e);

        }
        return null;
    }

    public DefaultTableModel mostrarDatos3(String salon) {
        int idHorario = 0;
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            modelo.addColumn("idHorario");
            modelo.addColumn("Maestro");
            modelo.addColumn("Materia");
            modelo.addColumn("Grupo");
            modelo.addColumn("Dia");
            modelo.addColumn("Hora");
 if (salon.equals("")) {
                sSQL = "select idHorario, Nombre, ApellidoPaterno, ApellidoMaterno, NombreMateria, Grupo_NumGrupo, Dia, Hora from horario, maestro, materia, grupo, salon WHERE  horario.Maestro_NumeroEmpleado=maestro.NumeroEmpleado and horario.Materia_ClaveMateria=materia.ClaveMateria and horario.Salon_NumSalon=salon.NumSalon and horario.Grupo_NumGrupo=grupo.NumGrupo;";

            } else 
            if (salon.equals(salon)) {
                sSQL = "select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria, Grupo_NumGrupo, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where  Salon_NumSalon like '%" + salon + "%';";
            }
            String[] Horarios = new String[6];

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            System.out.println("el primer mostrar" + sSQL);

            while (rs.next()) {
                idHorario = Integer.parseInt(rs.getString(1));
                Horarios[0] = rs.getString("idHorario");
                Horarios[1] = rs.getString("Nombre") + " " + rs.getString("ApellidoPaterno") + " " + rs.getString("ApellidoMaterno") + " ";//  consulta  id  traer
                Horarios[2] = rs.getString("NombreMateria") + " ";
                Horarios[3] = rs.getString("Grupo_NumGrupo") + " ";
                Horarios[4] = rs.getString("Dia");
                Horarios[5] = rs.getString("Hora");

                modelo.addRow(Horarios);

            }

            return modelo;

        } catch (SQLException | NumberFormatException e) {
            JOptionPane.showConfirmDialog(null, e);

        }
        return null;
    }
////
////

    /*Metodo  que regresa   los  datos  de la tabla de Maestro */
    public ResultSet cargaComboMaestro() {

        sSQL = "SELECT NumeroEmpleado, Nombre, ApellidoPaterno, ApellidoMaterno FROM maestro;";

        try {
            ps = connection.prepareStatement(sSQL);

        } catch (SQLException ex) {
            Logger.getLogger(ControlHorario.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet resultadoMaestro = null;
        try {
            resultadoMaestro = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ControlReporte.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoMaestro;

    }

    /*Metodo  que regresa   los  datos  de la tabla de grupo */
    public ResultSet cargaComboGrupo() {

        sSQL = "SELECT NumGrupo  FROM grupo;";

        try {
            ps = connection.prepareStatement(sSQL);

        } catch (SQLException ex) {
            Logger.getLogger(ControlReporte.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet resultadoGrupo = null;
        try {
            resultadoGrupo = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ControlReporte.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoGrupo;
    }

    /*Metodo  que regresa   los  datos  de la tabla de salon */
    public ResultSet cargaComboSalon() {

        sSQL = "SELECT NumSalon  FROM salon;";

        try {
            ps = connection.prepareStatement(sSQL);

        } catch (SQLException ex) {
            Logger.getLogger(ControlReporte.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet resultadoSalon = null;
        try {
            resultadoSalon = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(ControlReporte.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoSalon;
    }

    public ResultSet existeDatos1(String maestro) throws SQLException, ClassNotFoundException {
        sSQL = "select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria, Salon_NumSalon, Grupo_NumGrupo, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where Maestro_NumeroEmpleado like '%" + maestro + "%';";
//                select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria,  Grupo_NumGrupo, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where Maestro_NumeroEmpleado like '675';

        Statement st = connection.createStatement();
        ResultSet resultado = st.executeQuery(sSQL);

        System.out.println("existe 1" + sSQL);

        while (resultado.next()) {
            resultado.getString(1);
// if (Integer.parseInt(resultado.getObject(1).toString()) > 0) {
        }

        return resultado;

    }

    public ResultSet existeDatos2(String grupo) throws SQLException, ClassNotFoundException {

        sSQL = "select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria, Salon_NumSalon, Grupo_NumGrupo, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where  grupo.NumGrupo like '%" + grupo + "%';";
//                select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria,  Grupo_NumGrupo, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where Maestro_NumeroEmpleado like '675';

        Statement st = connection.createStatement();
        ResultSet resultado = st.executeQuery(sSQL);

        System.out.println("existe 1" + sSQL);

//        while (resultado.next()) {
//          resultado.getString(1);
//// if (Integer.parseInt(resultado.getObject(1).toString()) > 0) {
//        }
        return resultado;

    }

    public ResultSet existeDatos3(String salon) throws SQLException, ClassNotFoundException {

        sSQL = "select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria, Salon_NumSalon, Grupo_NumGrupo, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where  salon.NumSalon like '%" + salon + "%';";
//                select idHorario, maestro.Nombre, maestro.ApellidoPaterno, maestro.ApellidoMaterno, materia.NombreMateria,  Grupo_NumGrupo, Dia, Hora  from horario inner join materia on materia.ClaveMateria = horario.Materia_ClaveMateria inner join maestro on maestro.NumeroEmpleado=horario.Maestro_NumeroEmpleado where Maestro_NumeroEmpleado like '675';

        Statement st = connection.createStatement();
        ResultSet resultado = st.executeQuery(sSQL);

        System.out.println("existe 1" + sSQL);

//        while (resultado.next()) {
//           resultado.getString(1);
//// if (Integer.parseInt(resultado.getObject(1).toString()) > 0) {
//        }
        return resultado;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.EntidadHorasAsignacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author julve
 */
public class ControlHorasAsignacion {
    private Conexion mysql=new Conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    
    public boolean registrarHoras(EntidadHorasAsignacion dts){
        sSQL="insert into horas_asignadas (Maestro_NumeroEmpleado,horasAsignadas)"+"values (?,?)";
        
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getNumeroEmpleado());
            pst.setInt(2, dts.getHoras());

            
            int n=pst.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Los datos del grupo han sido actualizados");
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ya existe el grupo");
            
            return false;
        }
    }
    public boolean consultarHoras(EntidadHorasAsignacion dts){
        sSQL="select horasAsignadas(Maestro_NumeroEmpleado,horasAsignadas)"+"values (?,?)";
        
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getNumeroEmpleado());
            pst.setInt(2, dts.getHoras());

            
            int n=pst.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Los datos del grupo han sido actualizados");
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ya existe el grupo");
            
            return false;
        }
    }
    public int consultarHorasAsignadas(int noEmpleado) throws SQLException {
        int cadena = 0;
        sSQL = "select horasAsignadas  from horas_asignadas where Maestro_NumeroEmpleado like '%" + noEmpleado + "%';";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                int horas = rs.getInt("horasAsignadas");

                cadena = cadena + horas;
            }

            return cadena;
    }
    
    public boolean modificarHoras(EntidadHorasAsignacion dts){
        sSQL="update horas_asignadas set horasAsignadas=? where Maestro_NumeroEmpleado=?";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            
            pst.setInt(1, dts.getHoras());
            pst.setInt(2, dts.getNumeroEmpleado());
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, "Error en controlador");
            return false;
        }
        
    }
}

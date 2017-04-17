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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author julve
 */
public class ControlMaestro {
    private Conexion mysql=new Conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    
    
    public boolean registrarMaestro(EntidadMaestro dts){
        sSQL="insert into maestro (NumeroEmpleado,Nombre,ApellidoPaterno,ApellidoMaterno,HorasAsignadas)"+
                "values (?,?,?,?,?)";
        
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getNumeroEmpleado());
            pst.setString(2, dts.getNombre());
            pst.setString(3, dts.getApePaterno());
            pst.setString(4, dts.getApeMaterno());
//            pst.setString(5, dts.getFechaIngreso());
            pst.setInt(5, dts.getHorasAsignadas());
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Ya existe el maestro en la base de datos");
            
            return false;
        }
    }
    
    public DefaultTableModel consultarMaestroTabla(int buscar) throws SQLException{
        DefaultTableModel modelo;
        
        String[] titulos={"Num. Empleado","Nombre","ape Paterno","ape Materno","Hrs asignadas"};
        
        String[] Maestros=new String [5];
        
        modelo=new DefaultTableModel(null,titulos);
        
        sSQL="select * from maestro where NumeroEmpleado like '%"+ buscar +"%' order by NumeroEmpleado";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next()){
                Maestros[0]=rs.getString("NumeroEmpleado");
                Maestros[1]=rs.getString("Nombre");
                Maestros[2]=rs.getString("ApellidoPaterno");
                Maestros[3]=rs.getString("ApellidoMaterno");
//                Maestros[4]=rs.getString("FechaIngreso");
                Maestros[5]=rs.getString("HorasAsignadas");
                
                modelo.addRow(Maestros);
                
            }
            return modelo;
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    public DefaultTableModel consultarGeneralMaestroTabla() throws SQLException{
        DefaultTableModel modelo;
        
        String[] titulos={"Num. Empleado","Nombre","ape Paterno","ape Materno","Hrs asignadas"};
        
        String[] Maestros=new String [5];
        
        modelo=new DefaultTableModel(null,titulos);
        
        sSQL="select * from maestro";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next()){
                Maestros[0]=rs.getString("NumeroEmpleado");
                Maestros[1]=rs.getString("Nombre");
                Maestros[2]=rs.getString("ApellidoPaterno");
                Maestros[3]=rs.getString("ApellidoMaterno");
//                Maestros[4]=rs.getString("FechaIngreso");
                Maestros[4]=rs.getString("HorasAsignadas");
                
                modelo.addRow(Maestros);
                
            }
            return modelo;
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    public boolean modificarMaestro(EntidadMaestro dts){
        sSQL="update maestro set Nombre=?,ApellidoPaterno=?,ApellidoMaterno=?,FechaIngreso=?,HorasAsignadas=? where NumeroEmpleado=?";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApePaterno());
            pst.setString(3, dts.getApeMaterno());
//            pst.setString(4, dts.getFechaIngreso());
            pst.setInt(5, dts.getHorasAsignadas());
            pst.setInt(6, dts.getNumeroEmpleado());
            
            
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
    }
    
    public boolean eliminarMaestro(EntidadMaestro dts){
        sSQL="delete from maestro where NumeroEmpleado=?";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            
            
            pst.setInt(1, dts.getNumeroEmpleado());
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
    }
    
    public String consultarMaestroEspecifico(int buscar) throws SQLException{
        String cadena="";
        DefaultTableModel modelo;
        
        String[] titulos={"Num. Empleado","Nombre","ape Paterno","ape Materno","Hrs asignadas"};
        
        String[] Maestros=new String [5];
        
        modelo=new DefaultTableModel(null,titulos);
        
        sSQL="select * from maestro where NumeroEmpleado like '%"+ buscar +"%' order by NumeroEmpleado";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next()){
                String numempl=rs.getString("NumeroEmpleado");
                String nomb=rs.getString("Nombre");
                String aPaterno=rs.getString("ApellidoPaterno");
                String aMaterno=rs.getString("ApellidoMaterno");
//                String FIngreso=rs.getString("FechaIngreso");
                String HAsignacion=rs.getString("HorasAsignadas");
                
                modelo.addRow(Maestros);
                cadena=cadena+numempl+","+nomb+","+aPaterno+","+aMaterno+","+HAsignacion;
                
            }
            
                return cadena;
            
            
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    
    
    
    
    
    public ResultSet consultarMaestroGeneral() throws SQLException{
 
        String cadena="";
        
        DefaultListModel modelo=new DefaultListModel();
        
        sSQL="select NumeroEmpleado, Nombre, ApellidoPaterno, ApellidoMaterno from maestro;";
        
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

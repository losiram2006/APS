/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Conexion;
import Modelo.EntidadMateria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author julve
 */
public class ControlMateria {
    private Conexion mysql=new Conexion();
    private Connection cn=mysql.conectar();
    private String sSQL="";
    //  PBI4:HU10   MÉTODO  QUE REGISTRA LOS  DATOS DE LA MATERIA
    public boolean RegistrarMateria(EntidadMateria dts){
        sSQL="insert into materia (ClaveMateria,NombreMateria,SemestreMateria,Horas)"+
                "values (?,?,?,?)";
        
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getClave());
            pst.setString(2, dts.getNombreMateria());
            pst.setInt(3, dts.getSemestreMateria());
            pst.setInt(4, dts.getCantidadHoras());
            
            int n=pst.executeUpdate();
            
            if(n!=0){
                return true;
            }
            else{
                return false;
            }
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, "La materia ya  esta registrada");
            
            return false;
        }
    }
    
    
    
//MUESTRA  LOS  DATOS  EN UNA  TABLA
    public DefaultTableModel ConsultarMateria(String buscar) throws SQLException{
        DefaultTableModel modelo;
        
        String[] titulos={"Clave","Nombre de Materia","Semestre","Horas"};
        
        String[] Materias=new String [4];
        
        modelo=new DefaultTableModel(null,titulos);
        
        sSQL="select * from materia where NombreMateria like '%"+ buscar +"%' order by ClaveMateria";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next()){
                Materias[0]=rs.getString("ClaveMateria");
                Materias[1]=rs.getString("NombreMateria");
                Materias[2]=rs.getString("SemestreMateria");
                Materias[3]=rs.getString("Horas");
                
                modelo.addRow(Materias);
                
            }
            return modelo;
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
      //MÉTODO QUE MODIFICA UNA MATERIA EXISTENTE
    public boolean modificarMateria(EntidadMateria dts,int buscar){
        sSQL="update materia set NombreMateria=?,SemestreMateria=?,Horas=? where ClaveMateria like '%"+ buscar +"%'";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            pst.setString(1, dts.getNombreMateria());
            pst.setInt(2, dts.getSemestreMateria());
            pst.setInt(3, dts.getCantidadHoras());
            
            
            
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
    ///   MÉTODO QUE VALIDA SI LA MATERIA TIENE  UN HORARIO  ASIGNADO PARA NO MODIFICARLO O ELIMINARLO

    public int consultarMateriaTieneHorario(int claveMateria) throws SQLException {
        int cadena = 0;

        sSQL = "select ClaveMateria from horario where ClaveMateria like  '%" + claveMateria + "%';";

        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sSQL);

        while (rs.next()) {
            int numero = rs.getInt("ClaveMateria");

            cadena = cadena + numero;
        }

        return cadena;
    }

      //MÉTODO QUE ELIMINA UNA MATERIA EXISTENTE
    public boolean eliminarMateria(EntidadMateria dts){
        sSQL="delete from materia where ClaveMateria=?";
        try{
            PreparedStatement pst=cn.prepareStatement(sSQL);
            
            
            pst.setInt(1, dts.getClave());
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
     //MÉTODO QUE  CONSULTA UNA  MATERIA ESPECÍFICA
    public String consultarMateriaEspecifico(String buscar) throws SQLException{
        String cadena="";
            
        sSQL="select * from materia where NombreMateria like '%"+ buscar +"%' order by ClaveMateria";
        
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next()){
                String clav=rs.getString("ClaveMateria");
                String nombmateria=rs.getString("NombreMateria");
                String semestre=rs.getString("SemestreMateria");
                String horasmateria=rs.getString("Horas");

                cadena=cadena+clav+"-"+nombmateria+"-"+semestre+"-"+horasmateria;
                System.out.println("cadena: " + cadena);
            }
            return cadena;
            
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
    }
    
    public ResultSet consultarMateriaGeneral() throws SQLException{
 
        String cadena="";
        
        sSQL="select ClaveMateria, NombreMateria, SemestreMateria, Horas from materia;";
        
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

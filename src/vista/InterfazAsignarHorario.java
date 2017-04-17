/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Controlador.ControlAsignarMateria;
import Controlador.ControlDisponibilidad;
import Controlador.ControlGrupo;
import Controlador.ControlHorario;
import Controlador.ControlMaestro;
import Controlador.ControlMateria;
import Controlador.ControlSalon;
import Modelo.EntidadHorario;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author julve
 */
public class InterfazAsignarHorario extends javax.swing.JInternalFrame {

    /**
     * Creates new form InterfazHorario
     */
    DefaultListModel modelo;
    DefaultTableModel modelo1;
    private String sSQL="";
    
    String datos[]=null;
    String recibe[]=null;
    ControlMaestro controlMaestro=new ControlMaestro();
    ControlAsignarMateria controlAsig=new ControlAsignarMateria();
    ControlMateria controlMateria=new ControlMateria();
    ControlGrupo controlGrupo=new ControlGrupo();
    ControlSalon controlSalon=new ControlSalon();
    ControlDisponibilidad controlDisponibilidad=new ControlDisponibilidad();
    
    EntidadHorario entidadHorario=new EntidadHorario();
    ControlHorario controlHorario=new ControlHorario();
    
    public InterfazAsignarHorario() {
        try {
            initComponents();
            mostrarMaestro();
            inhabilitar();
            deshabilitar();
        } catch (SQLException ex) {
            Logger.getLogger(InterfazAsignarHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deshabilitar(){
        cbxGrupo.setEnabled(false);
        cbxSalon.setEnabled(false);
        cbxActividad.setEnabled(false);
        btnSiguiente.setEnabled(false);
    }
    
    public void mostrarMaestro() throws SQLException{
        cbxMaestro.removeAllItems();
        cbxMaestro.addItem("Selecciona Maestro");

        ResultSet rs = controlMaestro.consultarMaestroGeneral();
        while(rs.next()){
            cbxMaestro.addItem(rs.getString("NumeroEmpleado")+"-"+rs.getString("Nombre") + " " + rs.getString("ApellidoPaterno") + " " + rs.getString("ApellidoMaterno") + " ");

            }
        
    }
    
    public void mostrarGrupos(int busca) throws SQLException{
        cbxGrupo.removeAllItems();
        cbxGrupo.addItem("(Selecciona Grupo)");

        ResultSet rs = controlGrupo.consultarGrupoGeneral(busca);
        while(rs.next()){
            cbxGrupo.addItem(rs.getString("NumGrupo"));

            }
        
    }
    public void mostrarSalon() throws SQLException{
        cbxSalon.removeAllItems();
        cbxSalon.addItem("(Selecciona Salon)");

        ResultSet rs = controlSalon.consultarSalonGeneral();
        while(rs.next()){
            cbxSalon.addItem(rs.getString("NumSalon"));

            }
        
    }
    public void mostrarMaestroEspecifico(int buscar) throws SQLException{
        String cadena=controlMaestro.consultarMaestroEspecifico(buscar);
        recibe=cadena.split(",");
        String numEmpleado=recibe[0].trim();
        String nombreCompleto=recibe[1].trim()+" "+recibe[2].trim()+" "+recibe[3].trim();
        String horasAsignadas=recibe[4].trim();
        ET1.setText(numEmpleado);
        ET2.setText(nombreCompleto);
        ET7.setText(horasAsignadas);
//        consultarGeneral(numEmpleado);
    }
    public void mostrarMateriasAsignadas(int buscar) throws SQLException{
        
            modelo1=controlAsig.consultaEspecificaAsignacion(buscar);
            tbMateria.setModel(modelo1);
    }
    public void inhabilitar(){
        CL1.setEnabled(false);CL2.setEnabled(false);CL3.setEnabled(false);CL4.setEnabled(false);CL5.setEnabled(false);CL6.setEnabled(false);CL7.setEnabled(false);
        CL8.setEnabled(false);CL9.setEnabled(false);CL10.setEnabled(false);CL11.setEnabled(false);CL12.setEnabled(false);CL13.setEnabled(false);CL14.setEnabled(false);
        
        CMA1.setEnabled(false);CMA2.setEnabled(false);CMA3.setEnabled(false);CMA4.setEnabled(false);CMA5.setEnabled(false);CMA6.setEnabled(false);CMA7.setEnabled(false);
        CMA8.setEnabled(false);CMA9.setEnabled(false);CMA10.setEnabled(false);CMA11.setEnabled(false);CMA12.setEnabled(false);CMA13.setEnabled(false);CMA14.setEnabled(false);
        
        CM1.setEnabled(false);CM2.setEnabled(false);CM3.setEnabled(false);CM4.setEnabled(false);CM5.setEnabled(false);CM6.setEnabled(false);CM7.setEnabled(false);
        CM8.setEnabled(false);CM9.setEnabled(false);CM10.setEnabled(false);CM11.setEnabled(false);CM12.setEnabled(false);CM13.setEnabled(false);CM14.setEnabled(false);
        
        CJ1.setEnabled(false);CJ2.setEnabled(false);CJ3.setEnabled(false);CJ4.setEnabled(false);CJ5.setEnabled(false);CJ6.setEnabled(false);CJ7.setEnabled(false);
        CJ8.setEnabled(false);CJ9.setEnabled(false);CJ10.setEnabled(false);CJ11.setEnabled(false);CJ12.setEnabled(false);CJ13.setEnabled(false);CJ14.setEnabled(false);
        
        CV1.setEnabled(false);CV2.setEnabled(false);CV3.setEnabled(false);CV4.setEnabled(false);CV5.setEnabled(false);CV6.setEnabled(false);CV7.setEnabled(false);
        CV8.setEnabled(false);CV9.setEnabled(false);CV10.setEnabled(false);CV11.setEnabled(false);CV12.setEnabled(false);CV13.setEnabled(false);CV14.setEnabled(false);
        
        CS1.setEnabled(false);CS2.setEnabled(false);CS3.setEnabled(false);CS4.setEnabled(false);CS5.setEnabled(false);CS6.setEnabled(false);CS7.setEnabled(false);
        CS8.setEnabled(false);CS9.setEnabled(false);CS10.setEnabled(false);CS11.setEnabled(false);CS12.setEnabled(false);CS13.setEnabled(false);CS14.setEnabled(false);
    }
    
    public void mostrarDisponibilidadMaestro(int clave){
        try {
            sSQL="select * from horario_disponible where Maestro_NumeroEmpleado like '%"+clave+"%' order by Maestro_NumeroEmpleado";
            Statement st=controlDisponibilidad.consultaDisponibilidadEspecifica();
            
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next()){
                String Dia=rs.getString("Dia");
                String Hora=rs.getString("Hora");
                
                if(Hora.equalsIgnoreCase("")){
                }
                else{
                    if(Dia.equals("Lunes")){
                        if(Hora.equalsIgnoreCase("7")){
                            CL1.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CL2.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CL3.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CL4.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CL5.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CL6.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CL7.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CL8.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CL9.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CL10.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CL11.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CL12.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CL13.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CL14.setEnabled(true);
                        }
                    }
                    if(Dia.equals("Martes")){
                        if(Hora.equalsIgnoreCase("7")){
                            CMA1.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CMA2.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CMA3.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CMA4.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CMA5.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CMA6.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CMA7.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CMA8.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CMA9.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CMA10.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CMA11.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CMA12.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CMA13.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CMA14.setEnabled(true);
                        }
                    }
                    if(Dia.equals("Miercoles")){
                        if(Hora.equalsIgnoreCase("7")){
                            CM1.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CM2.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CM3.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CM4.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CM5.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CM6.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CM7.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CM8.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CM9.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CM10.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CM11.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CM12.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CM13.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CM14.setEnabled(true);
                        }
                    }
                    if(Dia.equals("Jueves")){
                        if(Hora.equalsIgnoreCase("7")){
                            CJ1.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CJ2.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CJ3.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CJ4.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CJ5.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CJ6.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CJ7.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CJ8.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CJ9.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CJ10.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CJ11.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CJ12.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CJ13.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CJ14.setEnabled(true);
                        }
                    }
                    if(Dia.equals("Viernes")){
                        if(Hora.equalsIgnoreCase("7")){
                            CV1.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CV2.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CV3.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CV4.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CV5.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CV6.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CV7.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CV8.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CV9.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CV10.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CV11.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CV12.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CV13.setEnabled(true);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CV14.setEnabled(true);
                        }
                    }                   
                }               
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfazAsignarDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void mostrarDisponibilidadGrupo(int nuGrupo){
        try {
            sSQL="select * from horario where NumGrupo like '%"+nuGrupo+"%';";
            Statement st=controlHorario.consultaHorarioEspecificaGrupo();
            
            ResultSet rs=st.executeQuery(sSQL);
            
            while(rs.next()){
                String Dia=rs.getString("Dia");
                String Hora=rs.getString("Hora");
                
                if(Hora.equalsIgnoreCase("")){
                }
                else{
                    if(Dia.equals("Lunes")){
                        if(Hora.equalsIgnoreCase("7")){
                            CL1.setSelected(true);
                            CL1.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CL2.setSelected(true);
                            CL2.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CL3.setSelected(true);
                            CL3.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CL4.setSelected(true);
                            CL4.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CL5.setSelected(true);
                            CL5.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CL6.setSelected(true);
                            CL6.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CL7.setSelected(true);
                            CL7.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CL8.setSelected(true);
                            CL8.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CL9.setSelected(true);
                            CL9.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CL10.setSelected(true);
                            CL10.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CL11.setSelected(true);
                            CL11.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CL12.setSelected(true);
                            CL12.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CL13.setSelected(true);
                            CL13.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CL14.setSelected(true);
                            CL14.setEnabled(false);
                        }
                    }
                    if(Dia.equals("Martes")){
                        if(Hora.equalsIgnoreCase("7")){
                            CMA1.setSelected(true);
                            CMA1.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CMA2.setSelected(true);
                            CMA2.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CMA3.setSelected(true);
                            CMA3.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CMA4.setSelected(true);
                            CMA4.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CMA5.setSelected(true);
                            CMA5.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CMA6.setSelected(true);
                            CMA6.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CMA7.setSelected(true);
                            CMA7.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CMA8.setSelected(true);
                            CMA8.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CMA9.setSelected(true);
                            CMA9.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CMA10.setSelected(true);
                            CMA10.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CMA11.setSelected(true);
                            CMA11.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CMA12.setSelected(true);
                            CMA12.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CMA13.setSelected(true);
                            CMA13.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CMA14.setSelected(true);
                            CMA14.setEnabled(false);
                        }
                    }
                    if(Dia.equals("Miercoles")){
                        if(Hora.equalsIgnoreCase("7")){
                            CM1.setSelected(true);
                            CM1.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CM2.setSelected(true);
                            CM2.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CM3.setSelected(true);
                            CM3.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CM4.setSelected(true);
                            CM4.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CM5.setSelected(true);
                            CM5.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CM6.setSelected(true);
                            CM6.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CM7.setSelected(true);
                            CM7.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CM8.setSelected(true);
                            CM8.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CM9.setSelected(true);
                            CM9.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CM10.setSelected(true);
                            CM10.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CM11.setSelected(true);
                            CM11.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CM12.setSelected(true);
                            CM12.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CM13.setSelected(true);
                            CM13.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CM14.setSelected(true);
                            CM14.setEnabled(false);
                        }
                    }
                    if(Dia.equals("Jueves")){
                        if(Hora.equalsIgnoreCase("7")){
                            CJ1.setSelected(true);
                            CJ1.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CJ2.setSelected(true);
                            CJ2.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CJ3.setSelected(true);
                            CJ3.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CJ4.setSelected(true);
                            CJ4.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CJ5.setSelected(true);
                            CJ5.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CJ6.setSelected(true);
                            CJ6.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CJ7.setSelected(true);
                            CJ7.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CJ8.setSelected(true);
                            CJ8.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CJ9.setSelected(true);
                            CJ9.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CJ10.setSelected(true);
                            CJ10.setEnabled(false);;
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CJ11.setSelected(true);
                            CJ11.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CJ12.setSelected(true);
                            CJ12.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CJ13.setSelected(true);
                            CJ13.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CJ14.setSelected(true);
                            CJ14.setEnabled(false);
                        }
                    }
                    if(Dia.equals("Viernes")){
                        if(Hora.equalsIgnoreCase("7")){
                            CV1.setSelected(true);
                            CV1.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("8")){
                            CV2.setSelected(true);
                            CV2.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("9")){
                            CV3.setSelected(true);
                            CV3.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("10")){
                            CV4.setSelected(true);
                            CV4.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("11")){
                            CV5.setSelected(true);
                            CV5.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("12")){
                            CV6.setSelected(true);
                            CV6.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("13")){
                            CV7.setSelected(true);
                            CV7.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("14")){
                            CV8.setSelected(true);
                            CV8.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("15")){
                            CV9.setSelected(true);
                            CV9.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("16")){
                            CV10.setSelected(true);
                            CV10.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("17")){
                            CV11.setSelected(true);
                            CV11.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("18")){
                            CV12.setSelected(true);
                            CV12.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("19")){
                            CV13.setSelected(true);
                            CV13.setEnabled(false);
                        }
                        if(Hora.equalsIgnoreCase("20")){
                            CV14.setSelected(true);
                            CV14.setEnabled(false);
                        }
                    }                   
                }               
            }
        } catch (SQLException ex) {
            Logger.getLogger(InterfazAsignarDisponibilidad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void registrarHorario(String dia,int hora){
        String NumEmpleado=ET1.getText();
        int empleado=Integer.parseInt(NumEmpleado);
        int seleccActividad=cbxActividad.getSelectedIndex();
        String actividad=cbxActividad.getItemAt(seleccActividad);
        String claveMateria=ET3.getText();
        int materia=Integer.parseInt(claveMateria);
        int seleccGrupo=cbxGrupo.getSelectedIndex();
        String noGrupo=cbxGrupo.getItemAt(seleccGrupo);
        int grupo=Integer.parseInt(noGrupo);
        int seleccSalon=cbxSalon.getSelectedIndex();
        String noSalon=cbxSalon.getItemAt(seleccSalon);
        int salon=Integer.parseInt(noSalon);
        
        entidadHorario.setTipoActividad(actividad);
        entidadHorario.setDia(dia);
        entidadHorario.setHora(hora);
        entidadHorario.setNumEmpleado(empleado);
        entidadHorario.setClaveMateria(materia);
        entidadHorario.setNumGrupo(grupo);
        entidadHorario.setNumSalon(salon);
        controlHorario.RegistrarHorario(entidadHorario);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbxMaestro = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ET1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ET2 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        ET7 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMateria = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ET3 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        ET4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ET5 = new javax.swing.JLabel();
        ET6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        CL1 = new javax.swing.JCheckBox();
        CL2 = new javax.swing.JCheckBox();
        CL3 = new javax.swing.JCheckBox();
        CL4 = new javax.swing.JCheckBox();
        CL5 = new javax.swing.JCheckBox();
        CL6 = new javax.swing.JCheckBox();
        CL7 = new javax.swing.JCheckBox();
        CL8 = new javax.swing.JCheckBox();
        CL9 = new javax.swing.JCheckBox();
        CL10 = new javax.swing.JCheckBox();
        CL11 = new javax.swing.JCheckBox();
        CL12 = new javax.swing.JCheckBox();
        CL13 = new javax.swing.JCheckBox();
        CL14 = new javax.swing.JCheckBox();
        CMA1 = new javax.swing.JCheckBox();
        CMA2 = new javax.swing.JCheckBox();
        CMA3 = new javax.swing.JCheckBox();
        CMA4 = new javax.swing.JCheckBox();
        CMA5 = new javax.swing.JCheckBox();
        CMA6 = new javax.swing.JCheckBox();
        CMA7 = new javax.swing.JCheckBox();
        CMA8 = new javax.swing.JCheckBox();
        CM1 = new javax.swing.JCheckBox();
        CM2 = new javax.swing.JCheckBox();
        CM3 = new javax.swing.JCheckBox();
        CM4 = new javax.swing.JCheckBox();
        CM5 = new javax.swing.JCheckBox();
        CM6 = new javax.swing.JCheckBox();
        CM7 = new javax.swing.JCheckBox();
        CM8 = new javax.swing.JCheckBox();
        CJ1 = new javax.swing.JCheckBox();
        CJ2 = new javax.swing.JCheckBox();
        CJ3 = new javax.swing.JCheckBox();
        CJ4 = new javax.swing.JCheckBox();
        CJ5 = new javax.swing.JCheckBox();
        CJ6 = new javax.swing.JCheckBox();
        CMA10 = new javax.swing.JCheckBox();
        CMA9 = new javax.swing.JCheckBox();
        CS5 = new javax.swing.JCheckBox();
        CS4 = new javax.swing.JCheckBox();
        CV5 = new javax.swing.JCheckBox();
        CV4 = new javax.swing.JCheckBox();
        CS3 = new javax.swing.JCheckBox();
        CV3 = new javax.swing.JCheckBox();
        CM11 = new javax.swing.JCheckBox();
        CV2 = new javax.swing.JCheckBox();
        CS1 = new javax.swing.JCheckBox();
        CV1 = new javax.swing.JCheckBox();
        CS2 = new javax.swing.JCheckBox();
        CJ8 = new javax.swing.JCheckBox();
        CM13 = new javax.swing.JCheckBox();
        CM10 = new javax.swing.JCheckBox();
        CM9 = new javax.swing.JCheckBox();
        CMA13 = new javax.swing.JCheckBox();
        CMA12 = new javax.swing.JCheckBox();
        CMA11 = new javax.swing.JCheckBox();
        CJ9 = new javax.swing.JCheckBox();
        CS8 = new javax.swing.JCheckBox();
        CV8 = new javax.swing.JCheckBox();
        CS7 = new javax.swing.JCheckBox();
        CV7 = new javax.swing.JCheckBox();
        CS6 = new javax.swing.JCheckBox();
        CV6 = new javax.swing.JCheckBox();
        CJ7 = new javax.swing.JCheckBox();
        CV9 = new javax.swing.JCheckBox();
        CJ10 = new javax.swing.JCheckBox();
        CJ11 = new javax.swing.JCheckBox();
        CJ13 = new javax.swing.JCheckBox();
        CJ12 = new javax.swing.JCheckBox();
        CV10 = new javax.swing.JCheckBox();
        CJ14 = new javax.swing.JCheckBox();
        CMA14 = new javax.swing.JCheckBox();
        CM14 = new javax.swing.JCheckBox();
        CM12 = new javax.swing.JCheckBox();
        CS9 = new javax.swing.JCheckBox();
        CS10 = new javax.swing.JCheckBox();
        CV14 = new javax.swing.JCheckBox();
        CV13 = new javax.swing.JCheckBox();
        CV12 = new javax.swing.JCheckBox();
        CV11 = new javax.swing.JCheckBox();
        CS11 = new javax.swing.JCheckBox();
        CS12 = new javax.swing.JCheckBox();
        CS13 = new javax.swing.JCheckBox();
        CS14 = new javax.swing.JCheckBox();
        btnSiguiente = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbxActividad = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbxGrupo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbxSalon = new javax.swing.JComboBox<>();

        setTitle("Asignar Horario");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Maestro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Seleccione Maestro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        cbxMaestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAceptar.setFont(new java.awt.Font("Bodoni MT", 1, 18)); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAceptarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar))
                    .addComponent(cbxMaestro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Informacion del maestro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Numero Empleado");

        ET1.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Nombre");

        ET2.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Horas");

        ET7.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(ET1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ET2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ET7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ET1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ET7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ET2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Seleccione Materia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        tbMateria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Clave", "Nombre Materia"
            }
        ));
        tbMateria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMateriaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbMateria);

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Clave Materia:");

        ET3.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Nombre Materia:");

        ET4.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Horas:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Semestre:");

        ET5.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N

        ET6.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ET3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(ET4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ET5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ET6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ET5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ET3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ET6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ET4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Horario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18))); // NOI18N

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(null);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("10:00-11:00");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("08:00-09:00");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("07:00-08:00");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("09:00-10:00");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("19:00-20:00");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("15:00-16:00");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("14:00-15:00");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("17:00-18:00");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("16:00-17:00");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("11:00-12:00");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("12:00-13:00");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("18:00-19:00");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("13:00-14:00");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("M");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("L");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("20:00-21:00");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("M");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("V");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("J");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("S");

        CL1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL1MouseClicked(evt);
            }
        });

        CL2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL2MouseClicked(evt);
            }
        });

        CL3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL3MouseClicked(evt);
            }
        });

        CL4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL4MouseClicked(evt);
            }
        });

        CL5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL5MouseClicked(evt);
            }
        });

        CL6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL6MouseClicked(evt);
            }
        });

        CL7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL7MouseClicked(evt);
            }
        });

        CL8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL8MouseClicked(evt);
            }
        });

        CL9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL9MouseClicked(evt);
            }
        });

        CL10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL10MouseClicked(evt);
            }
        });

        CL11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL11MouseClicked(evt);
            }
        });

        CL12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL12MouseClicked(evt);
            }
        });

        CL13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL13MouseClicked(evt);
            }
        });

        CL14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CL14MouseClicked(evt);
            }
        });

        CMA1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA1MouseClicked(evt);
            }
        });

        CMA2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA2MouseClicked(evt);
            }
        });

        CMA3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA3MouseClicked(evt);
            }
        });

        CMA4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA4MouseClicked(evt);
            }
        });

        CMA5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA5MouseClicked(evt);
            }
        });

        CMA6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA6MouseClicked(evt);
            }
        });

        CMA7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA7MouseClicked(evt);
            }
        });

        CMA8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA8MouseClicked(evt);
            }
        });

        CM1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM1MouseClicked(evt);
            }
        });

        CM2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM2MouseClicked(evt);
            }
        });

        CM3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM3MouseClicked(evt);
            }
        });

        CM4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM4MouseClicked(evt);
            }
        });

        CM5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM5MouseClicked(evt);
            }
        });

        CM6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM6MouseClicked(evt);
            }
        });

        CM7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM7MouseClicked(evt);
            }
        });

        CM8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM8MouseClicked(evt);
            }
        });

        CJ1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ1MouseClicked(evt);
            }
        });

        CJ2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ2MouseClicked(evt);
            }
        });

        CJ3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ3MouseClicked(evt);
            }
        });

        CJ4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ4MouseClicked(evt);
            }
        });

        CJ5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ5MouseClicked(evt);
            }
        });

        CJ6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ6MouseClicked(evt);
            }
        });

        CMA10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA10MouseClicked(evt);
            }
        });

        CMA9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA9MouseClicked(evt);
            }
        });

        CV5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV5MouseClicked(evt);
            }
        });

        CV4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV4MouseClicked(evt);
            }
        });

        CV3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV3MouseClicked(evt);
            }
        });

        CM11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM11MouseClicked(evt);
            }
        });

        CV2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV2MouseClicked(evt);
            }
        });

        CV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV1MouseClicked(evt);
            }
        });

        CJ8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ8MouseClicked(evt);
            }
        });

        CM13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM13MouseClicked(evt);
            }
        });

        CM10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM10MouseClicked(evt);
            }
        });

        CM9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM9MouseClicked(evt);
            }
        });

        CMA13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA13MouseClicked(evt);
            }
        });

        CMA12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA12MouseClicked(evt);
            }
        });

        CMA11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA11MouseClicked(evt);
            }
        });

        CJ9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ9MouseClicked(evt);
            }
        });

        CV8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV8MouseClicked(evt);
            }
        });

        CV7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV7MouseClicked(evt);
            }
        });

        CV6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV6MouseClicked(evt);
            }
        });

        CJ7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ7MouseClicked(evt);
            }
        });

        CV9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV9MouseClicked(evt);
            }
        });

        CJ10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ10MouseClicked(evt);
            }
        });

        CJ11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ11MouseClicked(evt);
            }
        });

        CJ13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ13MouseClicked(evt);
            }
        });

        CJ12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ12MouseClicked(evt);
            }
        });

        CV10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV10MouseClicked(evt);
            }
        });

        CJ14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CJ14MouseClicked(evt);
            }
        });

        CMA14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CMA14MouseClicked(evt);
            }
        });

        CM14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM14MouseClicked(evt);
            }
        });

        CM12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CM12MouseClicked(evt);
            }
        });

        CV14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV14MouseClicked(evt);
            }
        });

        CV13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV13MouseClicked(evt);
            }
        });

        CV12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV12MouseClicked(evt);
            }
        });

        CV11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CV11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CL2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CL1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(CL14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CJ14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CV3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CS11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CL1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CMA1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CL10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMA10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CM10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CJ10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CV10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CS10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CL11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CMA11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CM11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CV11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CS11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CL12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CMA12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CM12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CV12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CS12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CL13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CMA13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CM13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CV13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CS13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(CL14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CMA14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CM14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CJ14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CV14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CS14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(CJ13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(22, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(CJ12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(CJ11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        btnSiguiente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSiguiente.setText("Agregar Horario");
        btnSiguiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSiguienteMouseClicked(evt);
            }
        });

        jButton1.setText("jButton1");

        jButton2.setText("jButton2");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Tipo de Activdad: ");

        cbxActividad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbxActividad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "(Seleccione Actividad)", "Clases", "Asesoria", "Proyecto" }));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Seleccionar Grupo");

        cbxGrupo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Seleccionar Salon");

        cbxSalon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8)
                                    .addComponent(cbxGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(cbxSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(64, Short.MAX_VALUE)
                        .addComponent(btnSiguiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel9))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbxGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseClicked
        // TODO add your handling code here:
        
        int valor=cbxMaestro.getSelectedIndex();
        String valorSeleccion=cbxMaestro.getItemAt(valor);
        
        if(valorSeleccion.equalsIgnoreCase("Selecciona Maestro")){
            JOptionPane.showMessageDialog(null, "Seleccione a un Maestro");
        }
        else{
            try {
                datos=valorSeleccion.split("-");
                String clave=datos[0].trim();
                int numBuscar=Integer.parseInt(clave);
                mostrarMaestroEspecifico(numBuscar);
                mostrarMateriasAsignadas(numBuscar);
                btnAceptar.setEnabled(false);
//                JOptionPane.showMessageDialog(null, "seleccionaste: "+numBuscar);
            } catch (SQLException ex) {
                Logger.getLogger(InterfazAsignarHorario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnAceptarMouseClicked

    private void tbMateriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMateriaMouseClicked
        try {
            String cadena="";
            String mensaje[]=null;
            // TODO add your handling code here:
            String numEmpleado=ET1.getText();
            
            if(numEmpleado.equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "Seleccione Maestro");
            }
            else{
                String materia=ET3.getText();
                if(materia.equalsIgnoreCase("")){
                    int fila=tbMateria.rowAtPoint(evt.getPoint());
                    String clave=tbMateria.getValueAt(fila, 1).toString();
                    cadena=controlMateria.consultarMateriaEspecifico(clave);
                    mensaje=cadena.split("-");
                    ET3.setText(mensaje[0].trim());
                    ET4.setText(mensaje[1].trim());
                    ET5.setText(mensaje[2].trim());
                    ET6.setText(mensaje[3].trim());
                    String semestre=mensaje[3].trim();
                    int semestreNum=Integer.parseInt(semestre);
            
                    cbxGrupo.setEnabled(true);
                    cbxSalon.setEnabled(true);
                    cbxActividad.setEnabled(true);
                    btnSiguiente.setEnabled(true);
                    mostrarGrupos(semestreNum);
                    mostrarSalon();
                }
                
            }
            
            
//            int claveMateria=Integer.parseInt(clave);
            
            
                
                
            
            
            
//            JOptionPane.showMessageDialog(null, "Recibi de: "+semestreNum);
        } catch (SQLException ex) {
            Logger.getLogger(InterfazAsignarHorario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_tbMateriaMouseClicked

    private void btnSiguienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSiguienteMouseClicked
        // TODO add your handling code here:
        String numeroMaestro=ET1.getText();
        
        int seleccionActividad=cbxActividad.getSelectedIndex();
        String actividad=(String) cbxActividad.getItemAt(seleccionActividad);
        int seleccionGrupo=cbxGrupo.getSelectedIndex();
        String grupo=(String) cbxGrupo.getItemAt(seleccionGrupo);
        
        int seleccionSalon=cbxSalon.getSelectedIndex();
        String salon=(String) cbxSalon.getItemAt(seleccionSalon);
        
        if(numeroMaestro.equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Seleccione a un Maestro");
        }else{
            if(actividad.equalsIgnoreCase("(Seleccione Actividad)")){
            JOptionPane.showMessageDialog(null, "Ingrese Actividad");
            }
            else{
                if(grupo.equalsIgnoreCase("(Selecciona Grupo)")){
                    JOptionPane.showMessageDialog(null, "Seleccione Grupo");
                }
                else{
                    if(salon.equalsIgnoreCase("(Selecciona Salon)")){
                        JOptionPane.showMessageDialog(null, "Seleccione Salon");
                    }
                    else{
                        int numBuscar=Integer.parseInt(numeroMaestro);
                        int nGrupo=Integer.parseInt(grupo);
                        deshabilitar();
                        mostrarDisponibilidadMaestro(numBuscar);
                        mostrarDisponibilidadGrupo(nGrupo);
                    }
                }
            }    
        }
        
        
    }//GEN-LAST:event_btnSiguienteMouseClicked

    private void CL1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL1MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=7;
            boolean valor=CL1.isSelected();
            if(valor==true){
                registrarHorario(Dia,Hora);
            }
    }//GEN-LAST:event_CL1MouseClicked

    private void CL2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL2MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=8;
            boolean valor=CL2.isSelected();
    }//GEN-LAST:event_CL2MouseClicked

    private void CL3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL3MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=9;
            boolean valor=CL3.isSelected();
    }//GEN-LAST:event_CL3MouseClicked

    private void CL4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL4MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=10;
            boolean valor=CL4.isSelected();
    }//GEN-LAST:event_CL4MouseClicked

    private void CL5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL5MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
        int Hora=11;
        boolean valor=CL5.isSelected();
    }//GEN-LAST:event_CL5MouseClicked

    private void CL6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL6MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=12;
            boolean valor=CL6.isSelected();
    }//GEN-LAST:event_CL6MouseClicked

    private void CL7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL7MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=13;
            boolean valor=CL7.isSelected();
    }//GEN-LAST:event_CL7MouseClicked

    private void CL8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL8MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=14;
            boolean valor=CL8.isSelected();
    }//GEN-LAST:event_CL8MouseClicked

    private void CL9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL9MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=15;
            boolean valor=CL9.isSelected();
    }//GEN-LAST:event_CL9MouseClicked

    private void CL10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL10MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=16;
            boolean valor=CL10.isSelected();
    }//GEN-LAST:event_CL10MouseClicked

    private void CL11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL11MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=17;
            boolean valor=CL11.isSelected();
    }//GEN-LAST:event_CL11MouseClicked

    private void CL12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL12MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=18;
            boolean valor=CL12.isSelected();
    }//GEN-LAST:event_CL12MouseClicked

    private void CL13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL13MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=19;
            boolean valor=CL13.isSelected();
    }//GEN-LAST:event_CL13MouseClicked

    private void CL14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CL14MouseClicked
        // TODO add your handling code here:
        String Dia="Lunes";
            int Hora=20;
            boolean valor=CL14.isSelected();
    }//GEN-LAST:event_CL14MouseClicked

    private void CMA1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA1MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=7;
        boolean valor=CMA1.isSelected();
    }//GEN-LAST:event_CMA1MouseClicked

    private void CMA2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA2MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=8;
        boolean valor=CMA2.isSelected();
    }//GEN-LAST:event_CMA2MouseClicked

    private void CMA3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA3MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=9;
        boolean valor=CMA3.isSelected();
    }//GEN-LAST:event_CMA3MouseClicked

    private void CMA4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA4MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=10;
        boolean valor=CMA4.isSelected();
    }//GEN-LAST:event_CMA4MouseClicked

    private void CMA5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA5MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=11;
        boolean valor=CMA5.isSelected();
    }//GEN-LAST:event_CMA5MouseClicked

    private void CMA6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA6MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=12;
        boolean valor=CMA6.isSelected();
    }//GEN-LAST:event_CMA6MouseClicked

    private void CMA7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA7MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=13;
        boolean valor=CMA7.isSelected();
    }//GEN-LAST:event_CMA7MouseClicked

    private void CMA8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA8MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=14;
        boolean valor=CMA8.isSelected();
    }//GEN-LAST:event_CMA8MouseClicked

    private void CMA9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA9MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=15;
        boolean valor=CMA9.isSelected();
    }//GEN-LAST:event_CMA9MouseClicked

    private void CMA10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA10MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=16;
        boolean valor=CMA10.isSelected();
    }//GEN-LAST:event_CMA10MouseClicked

    private void CMA11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA11MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=17;
        boolean valor=CMA11.isSelected();
    }//GEN-LAST:event_CMA11MouseClicked

    private void CMA12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA12MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=18;
        boolean valor=CMA12.isSelected();
    }//GEN-LAST:event_CMA12MouseClicked

    private void CMA13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA13MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=19;
        boolean valor=CMA13.isSelected();
    }//GEN-LAST:event_CMA13MouseClicked

    private void CMA14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CMA14MouseClicked
        // TODO add your handling code here:
        String Dia="Martes";
        int Hora=20;
        boolean valor=CMA14.isSelected();
    }//GEN-LAST:event_CMA14MouseClicked

    private void CM1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM1MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=7;
        boolean valor=CM1.isSelected();
    }//GEN-LAST:event_CM1MouseClicked

    private void CM2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM2MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=8;
        boolean valor=CM2.isSelected();
    }//GEN-LAST:event_CM2MouseClicked

    private void CM3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM3MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=9;
        boolean valor=CM3.isSelected();
    }//GEN-LAST:event_CM3MouseClicked

    private void CM4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM4MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=10;
        boolean valor=CM4.isSelected();
    }//GEN-LAST:event_CM4MouseClicked

    private void CM5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM5MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=11;
        boolean valor=CM5.isSelected();
    }//GEN-LAST:event_CM5MouseClicked

    private void CM6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM6MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=12;
        boolean valor=CM6.isSelected();
    }//GEN-LAST:event_CM6MouseClicked

    private void CM7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM7MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=13;
        boolean valor=CM7.isSelected();
    }//GEN-LAST:event_CM7MouseClicked

    private void CM8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM8MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=14;
        boolean valor=CM8.isSelected();
    }//GEN-LAST:event_CM8MouseClicked

    private void CM9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM9MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=15;
        boolean valor=CM9.isSelected();
    }//GEN-LAST:event_CM9MouseClicked

    private void CM10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM10MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=16;
        boolean valor=CM10.isSelected();
    }//GEN-LAST:event_CM10MouseClicked

    private void CM11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM11MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=17;
        boolean valor=CM11.isSelected();
    }//GEN-LAST:event_CM11MouseClicked

    private void CM12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM12MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=18;
        boolean valor=CM12.isSelected();
    }//GEN-LAST:event_CM12MouseClicked

    private void CM13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM13MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=19;
        boolean valor=CM13.isSelected();
    }//GEN-LAST:event_CM13MouseClicked

    private void CM14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CM14MouseClicked
        // TODO add your handling code here:
        String Dia="Miercoles";
        int Hora=20;
        boolean valor=CM14.isSelected();
    }//GEN-LAST:event_CM14MouseClicked

    private void CJ1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ1MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=7;
        boolean valor=CJ1.isSelected();
    }//GEN-LAST:event_CJ1MouseClicked

    private void CJ2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ2MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=8;
        boolean valor=CJ2.isSelected();
    }//GEN-LAST:event_CJ2MouseClicked

    private void CJ3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ3MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=9;
        boolean valor=CJ3.isSelected();
    }//GEN-LAST:event_CJ3MouseClicked

    private void CJ4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ4MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=10;
        boolean valor=CJ4.isSelected();
    }//GEN-LAST:event_CJ4MouseClicked

    private void CJ5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ5MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=11;
        boolean valor=CJ5.isSelected();
    }//GEN-LAST:event_CJ5MouseClicked

    private void CJ6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ6MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=12;
        boolean valor=CJ6.isSelected();
    }//GEN-LAST:event_CJ6MouseClicked

    private void CJ7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ7MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=13;
        boolean valor=CJ7.isSelected();
    }//GEN-LAST:event_CJ7MouseClicked

    private void CJ8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ8MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=14;
        boolean valor=CJ8.isSelected();
    }//GEN-LAST:event_CJ8MouseClicked

    private void CJ9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ9MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=15;
        boolean valor=CJ9.isSelected();
    }//GEN-LAST:event_CJ9MouseClicked

    private void CJ10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ10MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=16;
        boolean valor=CJ10.isSelected();
    }//GEN-LAST:event_CJ10MouseClicked

    private void CJ11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ11MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=17;
        boolean valor=CJ11.isSelected();
    }//GEN-LAST:event_CJ11MouseClicked

    private void CJ12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ12MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=18;
        boolean valor=CJ12.isSelected();
    }//GEN-LAST:event_CJ12MouseClicked

    private void CJ13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ13MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=19;
        boolean valor=CJ13.isSelected();
    }//GEN-LAST:event_CJ13MouseClicked

    private void CJ14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CJ14MouseClicked
        // TODO add your handling code here:
        String Dia="Jueves";
        int Hora=20;
        boolean valor=CJ14.isSelected();
    }//GEN-LAST:event_CJ14MouseClicked

    private void CV1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV1MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=7;
        boolean valor=CV1.isSelected();
    }//GEN-LAST:event_CV1MouseClicked

    private void CV2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV2MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=8;
        boolean valor=CV2.isSelected();
    }//GEN-LAST:event_CV2MouseClicked

    private void CV3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV3MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=9;
        boolean valor=CV3.isSelected();
    }//GEN-LAST:event_CV3MouseClicked

    private void CV4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV4MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=10;
        boolean valor=CV4.isSelected();
    }//GEN-LAST:event_CV4MouseClicked

    private void CV5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV5MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=11;
        boolean valor=CV5.isSelected();
    }//GEN-LAST:event_CV5MouseClicked

    private void CV6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV6MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=12;
        boolean valor=CV6.isSelected();
    }//GEN-LAST:event_CV6MouseClicked

    private void CV7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV7MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=13;
        boolean valor=CV7.isSelected();
    }//GEN-LAST:event_CV7MouseClicked

    private void CV8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV8MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=14;
        boolean valor=CV8.isSelected();
    }//GEN-LAST:event_CV8MouseClicked

    private void CV9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV9MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=15;
        boolean valor=CV9.isSelected();
    }//GEN-LAST:event_CV9MouseClicked

    private void CV10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV10MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=16;
        boolean valor=CV10.isSelected();
    }//GEN-LAST:event_CV10MouseClicked

    private void CV11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV11MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=17;
        boolean valor=CV11.isSelected();
    }//GEN-LAST:event_CV11MouseClicked

    private void CV12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV12MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=18;
        boolean valor=CV12.isSelected();
    }//GEN-LAST:event_CV12MouseClicked

    private void CV13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV13MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=19;
        boolean valor=CV13.isSelected();
    }//GEN-LAST:event_CV13MouseClicked

    private void CV14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CV14MouseClicked
        // TODO add your handling code here:
        String Dia="Viernes";
        int Hora=20;
        boolean valor=CV14.isSelected();
    }//GEN-LAST:event_CV14MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CJ1;
    private javax.swing.JCheckBox CJ10;
    private javax.swing.JCheckBox CJ11;
    private javax.swing.JCheckBox CJ12;
    private javax.swing.JCheckBox CJ13;
    private javax.swing.JCheckBox CJ14;
    private javax.swing.JCheckBox CJ2;
    private javax.swing.JCheckBox CJ3;
    private javax.swing.JCheckBox CJ4;
    private javax.swing.JCheckBox CJ5;
    private javax.swing.JCheckBox CJ6;
    private javax.swing.JCheckBox CJ7;
    private javax.swing.JCheckBox CJ8;
    private javax.swing.JCheckBox CJ9;
    private javax.swing.JCheckBox CL1;
    private javax.swing.JCheckBox CL10;
    private javax.swing.JCheckBox CL11;
    private javax.swing.JCheckBox CL12;
    private javax.swing.JCheckBox CL13;
    private javax.swing.JCheckBox CL14;
    private javax.swing.JCheckBox CL2;
    private javax.swing.JCheckBox CL3;
    private javax.swing.JCheckBox CL4;
    private javax.swing.JCheckBox CL5;
    private javax.swing.JCheckBox CL6;
    private javax.swing.JCheckBox CL7;
    private javax.swing.JCheckBox CL8;
    private javax.swing.JCheckBox CL9;
    private javax.swing.JCheckBox CM1;
    private javax.swing.JCheckBox CM10;
    private javax.swing.JCheckBox CM11;
    private javax.swing.JCheckBox CM12;
    private javax.swing.JCheckBox CM13;
    private javax.swing.JCheckBox CM14;
    private javax.swing.JCheckBox CM2;
    private javax.swing.JCheckBox CM3;
    private javax.swing.JCheckBox CM4;
    private javax.swing.JCheckBox CM5;
    private javax.swing.JCheckBox CM6;
    private javax.swing.JCheckBox CM7;
    private javax.swing.JCheckBox CM8;
    private javax.swing.JCheckBox CM9;
    private javax.swing.JCheckBox CMA1;
    private javax.swing.JCheckBox CMA10;
    private javax.swing.JCheckBox CMA11;
    private javax.swing.JCheckBox CMA12;
    private javax.swing.JCheckBox CMA13;
    private javax.swing.JCheckBox CMA14;
    private javax.swing.JCheckBox CMA2;
    private javax.swing.JCheckBox CMA3;
    private javax.swing.JCheckBox CMA4;
    private javax.swing.JCheckBox CMA5;
    private javax.swing.JCheckBox CMA6;
    private javax.swing.JCheckBox CMA7;
    private javax.swing.JCheckBox CMA8;
    private javax.swing.JCheckBox CMA9;
    private javax.swing.JCheckBox CS1;
    private javax.swing.JCheckBox CS10;
    private javax.swing.JCheckBox CS11;
    private javax.swing.JCheckBox CS12;
    private javax.swing.JCheckBox CS13;
    private javax.swing.JCheckBox CS14;
    private javax.swing.JCheckBox CS2;
    private javax.swing.JCheckBox CS3;
    private javax.swing.JCheckBox CS4;
    private javax.swing.JCheckBox CS5;
    private javax.swing.JCheckBox CS6;
    private javax.swing.JCheckBox CS7;
    private javax.swing.JCheckBox CS8;
    private javax.swing.JCheckBox CS9;
    private javax.swing.JCheckBox CV1;
    private javax.swing.JCheckBox CV10;
    private javax.swing.JCheckBox CV11;
    private javax.swing.JCheckBox CV12;
    private javax.swing.JCheckBox CV13;
    private javax.swing.JCheckBox CV14;
    private javax.swing.JCheckBox CV2;
    private javax.swing.JCheckBox CV3;
    private javax.swing.JCheckBox CV4;
    private javax.swing.JCheckBox CV5;
    private javax.swing.JCheckBox CV6;
    private javax.swing.JCheckBox CV7;
    private javax.swing.JCheckBox CV8;
    private javax.swing.JCheckBox CV9;
    private javax.swing.JLabel ET1;
    private javax.swing.JLabel ET2;
    private javax.swing.JLabel ET3;
    private javax.swing.JLabel ET4;
    private javax.swing.JLabel ET5;
    private javax.swing.JLabel ET6;
    private javax.swing.JLabel ET7;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JComboBox<String> cbxActividad;
    private javax.swing.JComboBox<String> cbxGrupo;
    private javax.swing.JComboBox<String> cbxMaestro;
    private javax.swing.JComboBox<String> cbxSalon;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbMateria;
    // End of variables declaration//GEN-END:variables
}

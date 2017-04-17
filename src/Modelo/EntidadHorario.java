/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Marisol
 */
public class EntidadHorario {


    private String tipoActividad;
    private String dia;
    private int hora;
    private int ClaveMateria;
    private int NumEmpleado;
    private int NumGrupo;
    private int NumSalon;

    public EntidadHorario() {
    }

    public EntidadHorario(String tipoActividad, String dia, int hora, int ClaveMateria, int NumEmpleado, int NumGrupo, int NumSalon) {
        this.tipoActividad = tipoActividad;
        this.dia = dia;
        this.hora = hora;
        this.ClaveMateria = ClaveMateria;
        this.NumEmpleado = NumEmpleado;
        this.NumGrupo = NumGrupo;
        this.NumSalon = NumSalon;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getClaveMateria() {
        return ClaveMateria;
    }

    public void setClaveMateria(int ClaveMateria) {
        this.ClaveMateria = ClaveMateria;
    }

    public int getNumEmpleado() {
        return NumEmpleado;
    }

    public void setNumEmpleado(int NumEmpleado) {
        this.NumEmpleado = NumEmpleado;
    }

    public int getNumGrupo() {
        return NumGrupo;
    }

    public void setNumGrupo(int NumGrupo) {
        this.NumGrupo = NumGrupo;
    }

    public int getNumSalon() {
        return NumSalon;
    }

    public void setNumSalon(int NumSalon) {
        this.NumSalon = NumSalon;
    }
    
    


    
}

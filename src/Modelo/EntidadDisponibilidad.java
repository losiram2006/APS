/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author julve
 */
public class EntidadDisponibilidad {
    public int idHorarioDisponibilidad;
    public int NumeroEmpleado;
    public String Dia;
    public int Hora;

    public EntidadDisponibilidad() {
    }

    public EntidadDisponibilidad(int idHorarioDisponibilidad, int NumeroEmpleado, String Dia, int Hora) {
        this.idHorarioDisponibilidad = idHorarioDisponibilidad;
        this.NumeroEmpleado = NumeroEmpleado;
        this.Dia = Dia;
        this.Hora = Hora;
    }

    public int getIdHorarioDisponibilidad() {
        return idHorarioDisponibilidad;
    }

    public void setIdHorarioDisponibilidad(int idHorarioDisponibilidad) {
        this.idHorarioDisponibilidad = idHorarioDisponibilidad;
    }

    public int getNumeroEmpleado() {
        return NumeroEmpleado;
    }

    public void setNumeroEmpleado(int NumeroEmpleado) {
        this.NumeroEmpleado = NumeroEmpleado;
    }

    public String getDia() {
        return Dia;
    }

    public void setDia(String Dia) {
        this.Dia = Dia;
    }

    public int getHora() {
        return Hora;
    }

    public void setHora(int Hora) {
        this.Hora = Hora;
    }

    

    
    
    
    
}

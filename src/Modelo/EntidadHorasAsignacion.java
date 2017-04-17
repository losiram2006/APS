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
public class EntidadHorasAsignacion {
    int Horas;
    int numeroEmpleado;

    public EntidadHorasAsignacion() {
    }

    public EntidadHorasAsignacion(int Horas, int numeroEmpleado) {
        this.Horas = Horas;
        this.numeroEmpleado = numeroEmpleado;
    }

    public int getHoras() {
        return Horas;
    }

    public void setHoras(int Horas) {
        this.Horas = Horas;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }


    
    
    
}

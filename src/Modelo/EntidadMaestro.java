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
public class EntidadMaestro {
    public int numeroEmpleado;
    public String Nombre;
    public String apePaterno;
    public String apeMaterno;
    public int horasAsignadas;

    public EntidadMaestro() {
    }

    public EntidadMaestro(int numeroEmpleado, String Nombre, String apePaterno, String apeMaterno, int horasAsignadas) {
        this.numeroEmpleado = numeroEmpleado;
        this.Nombre = Nombre;
        this.apePaterno = apePaterno;
        this.apeMaterno = apeMaterno;
        this.horasAsignadas = horasAsignadas;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public int getHorasAsignadas() {
        return horasAsignadas;
    }

    public void setHorasAsignadas(int horasAsignadas) {
        this.horasAsignadas = horasAsignadas;
    }



    
    
    
    
}

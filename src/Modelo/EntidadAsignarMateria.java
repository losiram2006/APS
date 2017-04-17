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
public class EntidadAsignarMateria {
    public int ClaveMateria;
    public int NumeroEmpleado;

    public EntidadAsignarMateria() {
    }

    public EntidadAsignarMateria(int ClaveMateria, int NumeroEmpleado) {
        this.ClaveMateria = ClaveMateria;
        this.NumeroEmpleado = NumeroEmpleado;
    }

    public int getClaveMateria() {
        return ClaveMateria;
    }

    public void setClaveMateria(int ClaveMateria) {
        this.ClaveMateria = ClaveMateria;
    }

    public int getNumeroEmpleado() {
        return NumeroEmpleado;
    }

    public void setNumeroEmpleado(int NumeroEmpleado) {
        this.NumeroEmpleado = NumeroEmpleado;
    }

    
    
}

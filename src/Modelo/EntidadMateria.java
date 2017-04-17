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
public class EntidadMateria {
    int clave;
    String NombreMateria;
    int SemestreMateria;
    int CantidadHoras;

    public EntidadMateria() {
    }

    public EntidadMateria(int clave, String NombreMateria, int SemestreMateria, int CantidadHoras) {
        this.clave = clave;
        this.NombreMateria = NombreMateria;
        this.SemestreMateria = SemestreMateria;
        this.CantidadHoras = CantidadHoras;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombreMateria() {
        return NombreMateria;
    }

    public void setNombreMateria(String NombreMateria) {
        this.NombreMateria = NombreMateria;
    }

    public int getSemestreMateria() {
        return SemestreMateria;
    }

    public void setSemestreMateria(int SemestreMateria) {
        this.SemestreMateria = SemestreMateria;
    }

    public int getCantidadHoras() {
        return CantidadHoras;
    }

    public void setCantidadHoras(int CantidadHoras) {
        this.CantidadHoras = CantidadHoras;
    }
    
    
    
}

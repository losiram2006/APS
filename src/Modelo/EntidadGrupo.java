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
public class EntidadGrupo {
    
    public int NumeroGrupo;
    public int Semestre;

    public EntidadGrupo() {
    }

    public EntidadGrupo(int NumeroGrupo, int Semestre) {
        this.NumeroGrupo = NumeroGrupo;
        this.Semestre = Semestre;
    }

    public int getNumeroGrupo() {
        return NumeroGrupo;
    }

    public void setNumeroGrupo(int NumeroGrupo) {
        this.NumeroGrupo = NumeroGrupo;
    }

    public int getSemestre() {
        return Semestre;
    }

    public void setSemestre(int Semestre) {
        this.Semestre = Semestre;
    }
    
    
}

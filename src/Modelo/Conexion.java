/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author julve
 */
public class Conexion {
    public String db="modelov5";
    public String url="jdbc:mysql://localhost:3306/"+db;
    public String user="root";
    public String pass="";
    
    
    public Conexion(){
        
    }
    
    public Connection conectar(){
        Connection link=null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            link=(Connection) DriverManager.getConnection(this.url,this.user,this.pass);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null,e);
        }
        return link;
    }
    
}

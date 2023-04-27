/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author saudd
 */
public class Conexion {
        
    public Connection getConexion(){
        
        try{
            
            Connection conexion = DriverManager.getConnection("jdbc:mysql://servidoratitup.mysql.database.azure.com:3306/disennoDB?useSSL=TRUE", "adminNaty", "Coquito2022?");
            System.out.println("Conexion exitosa!!");
            return conexion;
            
        }catch(SQLException e){
            
            System.out.println(e.toString());
            return null;    
        }
    }
}

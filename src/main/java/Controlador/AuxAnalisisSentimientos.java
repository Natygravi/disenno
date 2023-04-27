/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

package Controlador;

import Modelo.AnalisisSentimientos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author grana
 
public class AuxAnalisisSentimientos {
    //Método para crear mi lista de comentarios generales
    public static ArrayList<String> listaComentariosGeneral() {
        
        ArrayList<String> comentariosGeneral = new ArrayList<String>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try (Connection conexion= DriverManager.getConnection("jdbc:mysql://servidoratitup.mysql.database.azure.com:3306/disennoDB?useSSL=TRUE", "adminNaty", "Coquito2022?");){
            pstmt = conexion.prepareStatement("SELECT comentarios FROM valoracion_ejemplo UNION SELECT comentarios FROM valoracion_respuesta");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String comentario = rs.getString("comentarios");
                comentariosGeneral.add(comentario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comentariosGeneral ;
    }
    
    //Método para crear mi lista de comentarios para un item en particular
    public static ArrayList<String> listaComentariosItems(String id) {
        
        ArrayList<String> comentariosGeneral = new ArrayList<String>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try (Connection conexion= DriverManager.getConnection("jdbc:mysql://servidoratitup.mysql.database.azure.com:3306/disennoDB?useSSL=TRUE", "adminNaty", "Coquito2022?");){
            pstmt = conexion.prepareStatement("SELECT comentarios FROM valoracion_ejemplo WHERE item_id =" + "'" + id + "'" + "UNION SELECT comentarios FROM valoracion_respuesta WHERE item_id =" + "'" + id + "'");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String comentario = rs.getString("comentarios");
                comentariosGeneral.add(comentario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comentariosGeneral ;
    }
    
    //Método para crear mi lista de comentarios para una categoria
    public static ArrayList<String> listaComentariosCategoria(String id){
        
        ArrayList<String> comentariosGeneral = new ArrayList<String>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try (Connection conexion= DriverManager.getConnection("jdbc:mysql://servidoratitup.mysql.database.azure.com:3306/disennoDB?useSSL=TRUE", "adminNaty", "Coquito2022?");){
            pstmt = conexion.prepareStatement("SELECT comentarios FROM valoracion_ejemplo INNER JOIN item ON valoracion_ejemplo.item_id = item.id WHERE item.categoria_codigo =" + "'" + id + "'" + "UNION SELECT comentarios FROM valoracion_respuesta INNER JOIN item ON valoracion_respuesta.item_id = item.id WHERE item.categoria_codigo =" + "'" + id + "'");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String comentario = rs.getString("comentarios");
                comentariosGeneral.add(comentario);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comentariosGeneral ;
    }
}
 */
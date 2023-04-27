/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author saudd
 */

import Controlador.RegistrarItemServlet;
import java.sql.Connection;
import config.Conexion;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaDesplegable {
    private String valor;
    private String texto;
    //private Connection Conexion;

    public ListaDesplegable(String valor, String texto) {
        this.valor = valor;
        this.texto = texto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    public ListaDesplegable(String texto) {
        this.texto = texto;
    }

    public static ArrayList<ListaDesplegable> obtenerOpcionesDesdeBD(String tabla, String campoValor, String campoTexto) {
        
        ArrayList<ListaDesplegable> opciones = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
  
        try (Connection conexion= DriverManager.getConnection("jdbc:mysql://servidoratitup.mysql.database.azure.com:3306/disennoDB?useSSL=TRUE", "adminNaty", "Coquito2022?");){
            pstmt = conexion.prepareStatement("SELECT " + campoValor + ", " + campoTexto + " FROM " + tabla);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String valor = rs.getString(campoValor);
                String texto = rs.getString(campoTexto);
                ListaDesplegable opcion = new ListaDesplegable(valor, texto);
                opciones.add(opcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return opciones;
    }
    public static ArrayList<ListaDesplegable> obtenerOpcionesDesdeBDDos(String tabla1, String tabla2, String campoTexto) {
        
        ArrayList<ListaDesplegable> opciones = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        PreparedStatement pstmt = null;
        ResultSet rs = null;
  
            try (Connection conexion= DriverManager.getConnection("jdbc:mysql://servidoratitup.mysql.database.azure.com:3306/disennoDB?useSSL=TRUE", "adminNaty", "Coquito2022?");){
            pstmt = conexion.prepareStatement("SELECT " + campoTexto + " FROM " + tabla1 + " UNION SELECT " + campoTexto + " FROM " + tabla2);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String texto = rs.getString(campoTexto);
                ListaDesplegable opcion = new ListaDesplegable(texto);
                opciones.add(opcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return opciones;
    }
    public static void main (String args[]){
        ArrayList<ListaDesplegable> x=new ArrayList<>();
        x=ListaDesplegable.obtenerOpcionesDesdeBD("Cursos", "id", "nombre");
        for(int i=0; i<x.size(); i++){
            ListaDesplegable y=x.get(i);
            System.out.println(y.valor);
        }
    }
   
}
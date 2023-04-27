/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Modelo.ListaDesplegable;
import java.util.ArrayList;

/**
 *
 * @author saudd
 */
public class UsuarioDAO {
    
    public UsuarioDAO(){
}
    
    ArrayList<ListaDesplegable> usuarios=ListaDesplegable.obtenerOpcionesDesdeBD("usuarios", "tipo", "email");
      
    private boolean existeUsuario(String correo){
        for(ListaDesplegable usuario:usuarios){
            if(usuario.getTexto().equals(correo)){
                return true;
            }
        }
        return false;
    }
    public int getUsuario(String correo){ 
            for(ListaDesplegable usuario:usuarios){
                if(usuario.getTexto().equals(correo)){
                return Integer.parseInt(usuario.getValor());
                }
                
    }
        return 0;
    }
    
        
public static void main (String Args[]){
    ArrayList<ListaDesplegable> usuarios=ListaDesplegable.obtenerOpcionesDesdeBD("usuarios", "tipo", "email");
        for(ListaDesplegable usuario:usuarios){
            System.out.print(usuario.getTexto());
            System.out.println(usuario.getValor());
        }
    }
  
  

}

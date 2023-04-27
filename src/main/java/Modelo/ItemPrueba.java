/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author sauddiel
 */
public class ItemPrueba {
    
    private int id;
    private String prompt;
    private String respuesta;
    private String fuente;
    private String ejemploRelacionado;
    private String fuenteEjemplo;
    private ArrayList<Valoracion> valoraciones;
    
    public ItemPrueba(int id,String prompt, String respuesta, String fuente, String ejemploRelacionado, String fuenteEjemplo) {
        this.prompt = prompt;
        this.respuesta = respuesta;
        this.fuente = fuente;
        this.id = id;
        this.ejemploRelacionado = ejemploRelacionado;
        this.fuenteEjemplo = fuenteEjemplo;
        this.valoraciones = new ArrayList<>();
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getEjemploRelacionado() {
        return ejemploRelacionado;
    }

    public void setEjemploRelacionado(String ejemploRelacionado) {
        this.ejemploRelacionado = ejemploRelacionado;
    }

    public String getFuenteEjemplo() {
        return fuenteEjemplo;
    }

    public void setFuenteEjemplo(String fuenteEjemplo) {
        this.fuenteEjemplo = fuenteEjemplo;
    }

    public ArrayList<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(ArrayList<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }
    
    // Método para agregar un curso a la lista de cursos
    public void agregarValoracion(Valoracion var) {
        valoraciones.add(var);
    }

    // Método para eliminar un curso de la lista de cursos
    public void eliminarValoracion(Valoracion var) {
        valoraciones.remove(var);
    }
}


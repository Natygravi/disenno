/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import Modelo.Valoracion;

/**
 *
 * @author saudd
 */
public class Item implements Comparable{
    private int codigo;
    private String prompt;
    private String respuesta;
    private String fuente;
    private String ejemploRelacionado;
    private String fuenteEjemplo;
    private ArrayList<Valoracion> valoracionesRespuesta;
    private ArrayList<Valoracion> valoracionesEjemplo;
    
    
    public Item(String prompt, String respuesta, String fuente, String ejemploRelacionado, String fuenteEjemplo) {
        
        this.prompt = prompt;
        this.respuesta = respuesta;
        this.fuente = fuente;
        this.ejemploRelacionado = ejemploRelacionado;
        this.fuenteEjemplo = fuenteEjemplo;
        this.valoracionesRespuesta = new ArrayList<>();
        this.valoracionesEjemplo = new ArrayList<>();
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public ArrayList<Valoracion> getValoracionesRes() {
        return valoracionesRespuesta;
    }
    
    public ArrayList<Valoracion> getValoracionesEjm() {
        return valoracionesEjemplo;
    }
    
    


    public void agregarValoracionRes(int estrellas,String comentario) {
        Valoracion nuevaValoracion = new Valoracion(estrellas,comentario);
        valoracionesRespuesta.add(nuevaValoracion);
    }
    
    public void agregarValoracionEjm(int estrellas,String comentario) {
        Valoracion nuevaValoracion = new Valoracion(estrellas,comentario);
        valoracionesEjemplo.add(nuevaValoracion);
    }

    // Método para eliminar un curso de la lista de cursos
   /* public void eliminarValoracion(Valoracion var) {
        valoraciones.remove(var);
    }*/
    
    @Override
    public boolean menorQue(Comparable obj){
        return (this.prompt.compareTo(((Item)obj).prompt)<0);
    }
    
double calcularPromedio(ArrayList<Valoracion> valoracionesItem) {
        if(valoracionesItem.size() == 0){
            return 0;
        }
        double suma = 0;
        double promedio;
        for (Valoracion valoracion:valoracionesItem){
            suma += valoracion.getEstrellas();
        }
        promedio = suma/valoracionesItem.size();
        return promedio;
    }
    public double promedioValoracionesRes() {
        if (valoracionesRespuesta.isEmpty()) {
            return 0;
        }
        int totalEstrellas = 0;
        for (Valoracion v : valoracionesRespuesta) {
            totalEstrellas += v.getEstrellas();
        }
        return (double) totalEstrellas / valoracionesRespuesta.size();
    }
    
    public double promedioValoracionesEjm() {
        if (valoracionesEjemplo.isEmpty()) {
            return 0;
        }
        int totalEstrellas = 0;
        for (Valoracion v : valoracionesEjemplo) {
            totalEstrellas += v.getEstrellas();
        }
        return (double) totalEstrellas / valoracionesRespuesta.size();
    }

    public ArrayList<Valoracion> getValoracionesRespuesta() {
        return valoracionesRespuesta;
    }

    public ArrayList<Valoracion> getValoracionesEjemplo() {
        return valoracionesEjemplo;
    }

    public void agregarValoracionRes(Valoracion valoracion) {
        
        valoracionesRespuesta.add(valoracion);
    }
    
    public void agregarValoracionEjm(Valoracion valoracion) {
        
        valoracionesEjemplo.add(valoracion);
    }
    
    
    
@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prompt: ").append(prompt).append("\n");
        sb.append("Respuesta: ").append(respuesta).append("\n");
        sb.append("Fuente: ").append(fuente).append("\n");
        sb.append("Ejemplo relacionado: ").append(ejemploRelacionado).append("\n");
        sb.append("Fuente del ejemplo: ").append(fuenteEjemplo).append("\n");
        sb.append("Valoraciones del ejemplo: ").append(valoracionesEjemplo).append("\n");
        sb.append("Valoraciones de la respuesta: ").append(valoracionesRespuesta).append("\n");
        return sb.toString();
    }


}

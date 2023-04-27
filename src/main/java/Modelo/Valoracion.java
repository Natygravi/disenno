/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Properties;
//import edu.stanford.nlp.pipeline.Annotation;
//import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.sentiment.SentimentCoreAnnotations

/**
 *
 * @author saudd
 */
public class Valoracion implements Comparable{
    private int estrellas;
    private String comentarios;

    public Valoracion(int estrellas, String comentarios) {
        this.estrellas = estrellas;
        this.comentarios = comentarios;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    
    @Override
    public boolean menorQue(Comparable obj){
        return (this.estrellas < ((Valoracion)obj).estrellas);
    }

    @Override
    public String toString() {
        return "Valoracion{" + "estrellas=" + estrellas + ", comentarios=" + comentarios + '}';
    }

}

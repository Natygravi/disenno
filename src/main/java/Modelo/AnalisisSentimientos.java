/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
package Modelo;

/**
 *
 * @author grana
 

import Controlador.RegistrarItemServlet;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.sentiment.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalisisSentimientos {
    
    public static String analizarSentimiento(String text) {
        // Configuración del pipeline de CoreNLP
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Análisis de sentimiento del texto
        Annotation annotation = new Annotation(text);
        pipeline.annotate(annotation);
        CoreDocument document = new CoreDocument(annotation);
        String sentiment = document.sentences().get(0).sentiment(); //.toString().
        
        String res = "->" + text + " .El sentimiento es: " + sentiment;
        return res;
    }
    
    public static String analizarSentimientosLista(ArrayList<String> textos) {
    // Configuración del pipeline de CoreNLP
    Properties props = new Properties();
    props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

    StringBuilder resultado = new StringBuilder();
    for (String texto : textos) {
        // Análisis de sentimiento del texto
        Annotation annotation = new Annotation(texto);
        pipeline.annotate(annotation);
        CoreDocument document = new CoreDocument(annotation);
        String sentiment = document.sentences().get(0).sentiment(); //.toString().

        String res = "->" + texto + " .El sentimiento es: " + sentiment + "\n";
        resultado.append(res).append("\n");
    }
    return resultado.toString();
}
    
 

}
*/
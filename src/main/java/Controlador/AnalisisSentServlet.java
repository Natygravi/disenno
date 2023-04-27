/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 
package Controlador;

import Modelo.AnalisisSentimientos;
import Modelo.Catalogo;
import Modelo.Categoria;
import Modelo.ListaDesplegable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author grana
 
public class AnalisisSentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    Catalogo catalogo;
    //List<Categoria> categorias;
    
    
    ArrayList <String> comentariosCategoria;
    ArrayList <String> comentariosItem;
    
    @Override
    public void init() throws ServletException {
        try {  
            catalogo = Catalogo.obtenerInstancia();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AnalisisSentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AnalisisSentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        ArrayList<ListaDesplegable> opcionesListaCategorias = ListaDesplegable.obtenerOpcionesDesdeBD("categoria", "codigo", "nombre");
        ArrayList<ListaDesplegable> opcionesListaItems = ListaDesplegable.obtenerOpcionesDesdeBD("item", "id", "prompt");
        ArrayList<ListaDesplegable> opcionesListaComentarios = ListaDesplegable.obtenerOpcionesDesdeBDDos("valoracion_ejemplo", "valoracion_respuesta", "comentarios");
       
        
        String botonPresionado = request.getParameter("boton");
        AnalisisSentimientos analisis = new AnalisisSentimientos();
        
        if (botonPresionado.equals("Analizar categoria")) {
        String categoria = request.getParameter("categoria");
        
        ArrayList<String> comentarios = AuxAnalisisSentimientos.listaComentariosCategoria(categoria);
        String sentimientos = AnalisisSentimientos.analizarSentimientosLista(comentarios);
        request.setAttribute("sentimiento", sentimientos);
        
    } else if (botonPresionado.equals("Analizar item")) {
        String item = request.getParameter("item");
        
        ArrayList<String> comentarios = AuxAnalisisSentimientos.listaComentariosItems(item);
        String sentimientos = AnalisisSentimientos.analizarSentimientosLista(comentarios);
        request.setAttribute("sentimiento", sentimientos);
        
        
    } else if (botonPresionado.equals("Analizar comentario")) {
        String comentario = request.getParameter("comentario");
        String sentimiento = analisis.analizarSentimiento(comentario);
        request.setAttribute("sentimiento", sentimiento);
    }
        request.setAttribute("opcionesListaCategorias", opcionesListaCategorias);
        request.setAttribute("opcionesListaItems", opcionesListaItems);
        request.setAttribute("opcionesListaComentarios", opcionesListaComentarios);
        request.getRequestDispatcher("/vistas/AnalisisSent.jsp").forward(request, response);
        
    }
    
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           
        //Estos son los arraylists de listas desplegables para que aparezcan ahí
        ArrayList<ListaDesplegable> opcionesListaCategorias = ListaDesplegable.obtenerOpcionesDesdeBD("categoria", "codigo", "nombre");
        ArrayList<ListaDesplegable> opcionesListaItems = ListaDesplegable.obtenerOpcionesDesdeBD("item", "id", "prompt");
        ArrayList<ListaDesplegable> opcionesListaComentarios = ListaDesplegable.obtenerOpcionesDesdeBDDos("valoracion_ejemplo", "valoracion_respuesta", "comentarios");
        
        //Acá le pongo set attribute a las cosas
        request.setAttribute("opcionesListaCategorias", opcionesListaCategorias);
        request.setAttribute("opcionesListaItems", opcionesListaItems);
        request.setAttribute("opcionesListaComentarios", opcionesListaComentarios);
        
    
        request.getRequestDispatcher("/vistas/AnalisisSent.jsp").forward(request, response);
    }
    
    
}
*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import DAO.ItemDAO;
import Modelo.Catalogo;
import Modelo.Categoria;
import Modelo.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author saudd
 */
public class consultarTopServlet extends HttpServlet {

    private List<Item> items = new ArrayList<>();
    List<Categoria> categorias = new ArrayList<>();
    //Sprivate final List<Categoria> categorias = new ArrayList<>();
    private Catalogo main;
    
    @Override
    public void init() throws ServletException {

        try {
            main = Catalogo.obtenerInstancia();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Categoria categoria1 = new Categoria("Refactoring","Descripcion 1");
        Categoria categoria2 = new Categoria("Principios básicos de diseño","Descripcion 2");
        Categoria categoria3 = new Categoria("Olores de software","Descripcion 3");       
        Categoria categoria4 = new Categoria("Deuda técnica","Descripcion 4");
        Categoria categoria5 = new Categoria("Principios de diseño SOLID","Descripcion 5");
        
        Item item1 = new Item("Qué son los olores de software?", "Java es un lenguaje de programación de propósito general.", "admin", "Ejemplo de Java en acción", "admin");
        Item item2 = new Item("¿Cuál es la diferencia entre Java y JavaScript?", "Java es un lenguaje de programación orientado a objetos, mientras que JavaScript es un lenguaje de programación interpretado.", "chatGPT", "Ejemplo de JavaScript en acción", "chatGPT");
        Item item3 = new Item("¿Qué es un bucle en Java?", "Un bucle en Java es una estructura de control que permite repetir una serie de instrucciones varias veces.", "chatGPT", "Ejemplo de bucle for en Java", "chatGPT");
        Item item4 = new Item("¿Cómo se crea una clase en Java?", "Para crear una clase en Java, se utiliza la palabra clave 'class' seguida del nombre de la clase y las llaves que encierran el cuerpo de la clase.", "chatGPT", "Ejemplo de clase en Java", "chatGPT");

        item1.agregarValoracionRes(4, "");
        item1.agregarValoracionRes(5, "");
        item1.agregarValoracionEjm(5, "");
        item1.agregarValoracionEjm(4, "");
        item1.agregarValoracionRes(3, "");
        
        item2.agregarValoracionRes(4, "");
        item2.agregarValoracionRes(2, "");
        item2.agregarValoracionEjm(2, "");
        item2.agregarValoracionEjm(2, "");
        
        item3.agregarValoracionRes(4, "");
        item3.agregarValoracionRes(4, "");
        item3.agregarValoracionEjm(5, "");
        item3.agregarValoracionEjm(5, "");
        
        item4.agregarValoracionRes(3, "");
        item4.agregarValoracionEjm(5, "");
        
        
        categoria1.agregarItem(item1); // Agrega item1 a la primera categoría de la lista
        categoria1.agregarItem(item2); // Agrega item2 a la primera categoría de la lista
        categoria1.agregarItem(item3);
        categoria1.agregarItem(item4);
        
        categoria2.agregarItem(item1); // Agrega item1 a la primera categoría de la lista
        categoria2.agregarItem(item2); // Agrega item2 a la primera categoría de la lista
        categoria2.agregarItem(item3);
        categoria2.agregarItem(item4);
        
        
        
                
        main.agregarCategoria(categoria1);
        main.agregarCategoria(categoria2);
        main.agregarCategoria(categoria3);
        main.agregarCategoria(categoria4);
        main.agregarCategoria(categoria5);
        
       
        categorias = main.obtenerCategorias();
        //System.out.println(categorias);
        request.setAttribute("categorias", categorias);

        request.getRequestDispatcher("vistas/consultarTop.jsp").forward(request, response);
    }
    
    @Override
     public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         categorias = main.obtenerCategorias();
         
         //Cargar las categorias
         //Cargar los items
         //Realizar los filtros
         //filtro respuesta/ejemplo/ambas
         //filtro fuente respuesta admin/chatGPT/ambas
         //filtro fuente ejemplo admin/chatGPT/ambas
         //filtro cantidad items
         
         
        //List<Item> items = null;
        
        //Parametros Filtros
        boolean considerarRespuestas = true;
        boolean considerarEjemplos = true;
        boolean considerarAdminRes = true;
        boolean considerarChatGPTRes = true;
        boolean considerarAdminEjm = true;
        boolean considerarChatGPTEjm = true;
        
        String nombreCategoria = request.getParameter("categoria");
        String tipoValoraciones = request.getParameter("valoraciones");
        String filtroFuente = request.getParameter("fuente");
        String filtroFuenteEjm = request.getParameter("fuente-ejemplos");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                
        
        if (tipoValoraciones.equals("respuestas")){
            considerarEjemplos = false;
        }
        else if(tipoValoraciones.equals("ejemplos")){
            considerarRespuestas =false;
        }
        
        if (filtroFuente.equals("chatGPT")){
            considerarAdminRes = false;
        }
        else if(filtroFuente.equals("admin")){
            considerarChatGPTRes =false;
        }
        
        if (filtroFuenteEjm.equals("chatGPT")){
            considerarAdminEjm = false;
        }
        else if(filtroFuenteEjm.equals("admin")){
            considerarChatGPTEjm =false;
        }
        
        List<Map<String, Object>> listaTop = new ArrayList<>();
        Categoria categoria = main.obtenerCategoria(nombreCategoria);
        if (request.getParameter("boton").equals("variasCategorias")){
            //logicaVarias
            listaTop = main.realizarTopGeneral(cantidad, considerarRespuestas, considerarEjemplos, considerarAdminRes, considerarChatGPTRes, considerarAdminEjm, considerarChatGPTEjm);
        }
        
        if (request.getParameter("boton").equals("unaCategoria")){
            listaTop = main.realizarTop(categoria, cantidad, considerarRespuestas, considerarEjemplos, considerarAdminRes, considerarChatGPTRes, considerarAdminEjm, considerarChatGPTEjm);
        }

        
        request.setAttribute("lista", listaTop);
        request.setAttribute("categoria", categoria);
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("vistas/consultarTop.jsp").forward(request, response);
        items.clear();
       
    }
     
    
     
     /*public List<Item> consultarTopItems(ArrayList<Item> itemsRegistrados,int x, boolean considerarRespuestas, boolean considerarEjemplos, boolean considerarAdmin, boolean considerarChatGPT) {
        List<Item> itemsFiltrados = new ArrayList<Item>();

        for (Item item : itemsRegistrados) {
            boolean incluirItem = false;

            // Filtrar por valoraciones iguales o superiores a 4
            double valoracionPromedio = item.calcularPromedioValoraciones(considerarEjemplos);
            if (valoracionPromedio >= 4) {
                // Filtrar por tipo de valoraciones
                if (considerarRespuestas && considerarEjemplos) {
                    incluirItem = true;
                } else if (considerarRespuestas && !considerarEjemplos) {
                    if (!item.getValoracionesRespuesta().isEmpty()) {
                        incluirItem = true;
                    }
                } else if (!considerarRespuestas && considerarEjemplos) {
                    if (!item.getValoracionesEjemplo().isEmpty()) {
                        incluirItem = true;
                    }
                }
                // Filtrar por fuente de respuestas
                if (incluirItem && (considerarAdmin || considerarChatGPT)) {
                    boolean incluirAdmin = considerarAdmin && item.getRespuestaAdmin() != null;
                    boolean incluirChatGPT = considerarChatGPT && item.getRespuestaChatGPT() != null;
                    incluirItem = incluirAdmin || incluirChatGPT;
                }
                // Filtrar por fuente de ejemplos
                if (incluirItem && (considerarAdmin || considerarChatGPT)) {
                    boolean incluirAdmin = considerarAdmin && item.getEjemploAdmin() != null;
                    boolean incluirChatGPT = considerarChatGPT && item.getEjemploChatGPT() != null;
                    incluirItem = incluirAdmin || incluirChatGPT;
                }
            }

            if (incluirItem) {
                itemsFiltrados.add(item);
            }
        }

        return itemsFiltrados;
    }*/
    
}
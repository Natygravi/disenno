/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.ItemPrueba;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saudd
 */
@WebServlet(name = "Controlador.ItemControlador", urlPatterns = {"/ItemControlador"})
public class ItemControlador extends HttpServlet {
    
    private final List<ItemPrueba> items = new ArrayList<>();
    
    @Override
    public void init() throws ServletException {
        // Cargar algunos items de ejemplo
        items.add(new ItemPrueba(1, "¿Cuál es la capital de Francia?", "París", "Wikipedia", "París es la capital de Francia", "Wikipedia"));
        items.add(new ItemPrueba(2, "¿Cuál es la capital de Alemania?", "Berlín", "Wikipedia", "Berlín es la capital de Alemania", "Wikipedia"));
        items.add(new ItemPrueba(3, "¿En qué año se descubrió América?", "1492", "Wikipedia", "Cristóbal Colón descubrió América en 1492", "Wikipedia"));
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener el idioma seleccionado por el usuario (por defecto, español)
        /*String lang = request.getParameter("lang");
        if (lang == null || lang.isEmpty()) {
            lang = "es";
        }
        
        // Cargar el archivo de propiedades correspondiente al idioma seleccionado
        ResourceBundle messages = ResourceBundle.getBundle("messages", new Locale(lang));*/
        
        // Agregar los items al objeto HttpServletRequest
        request.setAttribute("items", items);
        
        // Agregar los mensajes al objeto HttpServletRequest
        //request.setAttribute("msg", messages);
        
        // Enviar la respuesta
        request.getRequestDispatcher("vistas/ItemCRUD.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los parámetros enviados por el formulario de agregar item
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");
        String fuente = request.getParameter("fuente");
        String descripcion = request.getParameter("descripcion");

        // Agregar el nuevo item a la lista
        int newId = items.size() + 1;
        items.add(new ItemPrueba(newId, pregunta, respuesta, fuente, descripcion, "Agregado por usuario"));

        // Redirigir al usuario a la página principal, para que vea el nuevo item agregado
        response.sendRedirect(request.getContextPath() + "/items?lang=" + request.getParameter("lang"));
    }
}

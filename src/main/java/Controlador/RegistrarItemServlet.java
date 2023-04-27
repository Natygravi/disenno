/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Catalogo;
import Modelo.Categoria;
import Modelo.Item;
import DAO.ItemDAO;
import Modelo.ItemPrueba;
import config.ConexionGPT;
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
 * @author saudd
 */
public class RegistrarItemServlet extends HttpServlet {
    
    private final List<Item> items = new ArrayList<>();
    List<Categoria> categorias;
    //Sprivate final List<Categoria> categorias = new ArrayList<>();
    private Catalogo main;
    
    @Override
    public void init() throws ServletException {
        // Cargar algunos items de ejemplo
        items.add(new Item("¿Cuál es la capital de Francia?", "París", "Wikipedia", "París es la capital de Francia", "Wikipedia"));
        items.add(new Item("¿Cuál es la capital de Alemania?", "Berlín", "Wikipedia", "Berlín es la capital de Alemania", "Wikipedia"));
        items.add(new Item("¿En qué año se descubrió América?", "1492", "Wikipedia", "Cristóbal Colón descubrió América en 1492", "Wikipedia"));
        //categorias.add(new Categoria("Refactoring","Descripcion 1"));
        //categorias.add(new Categoria("Principios básicos de diseño","Descripcion 2"));
        //categorias.add(new Categoria("Olores de software","Descripcion 3"));
        //categorias.add(new Categoria("Deuda técnica","Descripcion 4"));
        Categoria nuevaCat = new Categoria("Principios de diseño SOLID","Descripcion 5");
        
        try {
            main = Catalogo.obtenerInstancia();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(main.getCantidadCategorias());
        main.agregarCategoria(nuevaCat);
        System.out.println(main.getCantidadCategorias());
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
        categorias = main.obtenerCategorias();
        request.setAttribute("items", items);
        request.setAttribute("categorias", categorias);
        
        // Agregar los mensajes al objeto HttpServletRequest
        //request.setAttribute("msg", messages);
        
        // Enviar la respuesta
       request.getRequestDispatcher("vistas/RegistrarItem.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Obtener los parámetros enviados desde el formulario de registro de ítems
        String pregunta = request.getParameter("pregunta");
        String respuesta = request.getParameter("respuesta");
        String respuesta_gpt = request.getParameter("respuesta_gpt");
        String ejemplo = request.getParameter("ejemplo");
        String ejemplo_gpt = request.getParameter("ejemplo_gpt");
        String usar_respuesta_gpt = request.getParameter("usar_respuesta_gpt");
        String usar_ejemplo_gpt = request.getParameter("usar_ejemplo_gpt");
        String fuente = "admin";
        String fuenteEjemplo = "admin";
        
        // Comprobar si el usuario decidió utilizar la respuesta o el ejemplo del ChatGPT
        if ("on".equals(usar_respuesta_gpt)) {
            respuesta = respuesta_gpt;
            fuente = "chatGPT";
            //respuesta = ConexionGPT.conexion(pregunta);
            
        }
        if ("on".equals(usar_ejemplo_gpt)) {
            ejemplo = ejemplo_gpt;
            fuenteEjemplo = "chatGPT";
            //String prompt = "Give me a example of: "+respuesta + "(Only an example)";
            //ejemplo = ConexionGPT.conexion(prompt);
        }
        
        int index = Integer.parseInt(request.getParameter("categoria"));
        Categoria categoriaSeleccionada = categorias.get(index);
        categoriaSeleccionada.agregarItem(pregunta,respuesta, fuente, ejemplo,fuenteEjemplo);
        try {
            main.guardarCategorias();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        for (Item item: categoriaSeleccionada.getItems()){
            System.out.println(item.toString());
        }
            
        // Crear un objeto Item con los datos recibidos
        
        //Item item = new Item(pregunta,respuesta, fuente, ejemplo,fuenteEjemplo);
        //items.add(item);
        
        //System.out.println(item.toString());
        
        System.out.println("HOla");
        
        // Obtener una instancia de la clase DAO para interactuar con la base de datos
        /*ItemDAO itemDAO = null;
        try {
            itemDAO = new ItemDAO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Guardar el nuevo ítem en la base de datos
            itemDAO.agregarItem(item);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarItemServlet.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        // Enviar una respuesta al navegador del usuario
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Registro de Ítems</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Ítem registrado exitosamente</h1>");
        out.println("<a href='RegistrarItemServlet'>Registrar otro ítem</a>");
        out.println("</body>");
        out.println("</html>");
    }
    
    private void cargarRespuestaChatGPT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //
  }
}

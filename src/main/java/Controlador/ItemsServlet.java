package Controlador;

import DAO.ItemDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ItemsServlet", urlPatterns = {"/items"})
public class ItemsServlet extends HttpServlet {
    private ItemDAO itemDAO;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
       //request.getRequestDispatcher("vistas/mostrarPrompts.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> items = null;
        String categoria = "";
        try {
            // Obtener el valor del parámetro categoria
            categoria = request.getParameter("categoria");
            
            
            // Establecer la conexión con la base de datos MySQL
            
            itemDAO = new ItemDAO();
            // Consultar la base de datos para obtener los items de la categoría seleccionada
            items = itemDAO.getItemsByCategoria(categoria);
            
            } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ItemsServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
        request.setAttribute("items", items);
        request.setAttribute("categoria", categoria);
        request.getRequestDispatcher("vistas/mostrarPrompts.jsp").forward(request, response);
           /* 
            
            // Crear el código HTML para la lista de items y agregar los nombres de los items
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<h1>Prompts de la categoría " + categoria + "</h1>");
            out.println("<form method='post' action='PromptsServlet'>");
            out.println("<select name='prompt'>");
            for (String prompt : items) {
                out.println("<option value='" + prompt + "'>" + prompt + "</option>");
            }
            out.println("</select>");
            out.println("<br><br>");
            out.println("<input type='submit' value='Mostrar los datos de los items '>");
            out.println("</form>");
        */
    }
}

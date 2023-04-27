package Controlador;

import DAO.CategoriaDAO;
import Modelo.Catalogo;
import Modelo.Categoria;
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

//WebServlet(name = "CategoriasServlet", urlPatterns = {"/categorias"})
public class CategoriasServlet2 extends HttpServlet {
    List<Categoria> categorias;
    Catalogo catalogo;
    
    @Override
    public void init() throws ServletException {

        try {
            catalogo = Catalogo.obtenerInstancia();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriasServlet2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriasServlet2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoriasServlet2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        categorias = catalogo.obtenerCategorias();
        request.setAttribute("categorias", categorias);
        
        request.getRequestDispatcher("/vistas/listaCategorias.jsp").forward(request, response);

      // System.out.println(request.getAttribute("categorias"));
        //System.out.println(categorias.size());
        // Crear el código HTML para la lista desplegable y agregar los nombres de las categorías
        /*PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html>");
        out.println("<body>");
        out.println("<form method='post' action='CategoriasServlet'>");
        out.println("<select name='categoria'>");
        for (String nombre : categorias) {
            out.println("<option value='" + nombre + "'>" + nombre + "</option>");
        }
        out.println("</select>");
        out.println("<input type='submit' value='Listar Preguntas'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        
        
       request.setAttribute("categorias", categorias); 
       //request.getRequestDispatcher("listaCategorias.jsp").forward(request, response);
       System.out.println(request.getAttribute("categorias"));
       System.out.println(categorias.size());
       
        */// Cerrar la conexión con la base de datos MySQL al final del servlet
    }

}

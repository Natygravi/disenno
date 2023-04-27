/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Catalogo;
import Modelo.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class valoracionesEjm extends HttpServlet {

    private Catalogo catalogo;


    @Override
    public void init() throws ServletException {
        try {
            catalogo = Catalogo.obtenerInstancia();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(valoracionesEjm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(valoracionesEjm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String prompt = request.getParameter("prompt");
        String comentarios = request.getParameter("comentariosEjm");
        int estrellas = Integer.parseInt(request.getParameter("estrellas"));
        List<Categoria> categorias = catalogo.obtenerCategorias();// es la que ocupo que funcione porque no me está cargando los datos en el array
        //Agregar metodo para encontrar el item de la categoria seleccionada,
        //catalogo.insertarValoracionEjem(prompt,estrellas,comentarios);

    }
}

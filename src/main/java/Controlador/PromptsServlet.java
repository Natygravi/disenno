/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Item;
import DAO.ItemDAO;
import Modelo.Valoracion;
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
/**
 *
 * @author nayel
 */
@WebServlet(name = "PromptsServlet", urlPatterns = {"/prompts"})
public class PromptsServlet extends HttpServlet {
 //private ItemDAO itemDAO;
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String prompt = request.getParameter("prompt");
            String comentarios = request.getParameter("comentarios");
            String estrellasParam = request.getParameter("estrellas");
            int estrellas =  Integer.parseInt(estrellasParam);
            ItemDAO itemDAO = new ItemDAO();
            Item itemBuscado = itemDAO.buscarItemPorPrompt(prompt);
            //Valoracion valoracion = new Valoracion(estrellas, comentarios);
            itemBuscado.agregarValoracionRes(estrellas, comentarios);
            itemDAO.actualizarItem(itemBuscado);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PromptsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
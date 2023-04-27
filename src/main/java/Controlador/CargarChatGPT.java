/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import config.ConexionGPT;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author saudd
 */
public class CargarChatGPT extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pregunta = request.getParameter("pregunta"); // obtiene la pregunta del usuario
        System.out.println(pregunta);
        String respuesta = ConexionGPT.conexion(pregunta);
        String prompt = "Give me a example of: "+respuesta + "(Only an example)";
        String ejemplo = ConexionGPT.conexion(pregunta);// genera el ejemplo usando la clase ChatGPT
        
        // crea un objeto JSON con la respuesta y el ejemplo
        JSONObject obj = new JSONObject();
        obj.put("respuesta", respuesta);
        obj.put("ejemplo", ejemplo);
        
        // configura la respuesta como JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // escribe el objeto JSON como respuesta
        response.getWriter().write(obj.toString());
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hola");
        // Obtener los parámetros enviados desde el formulario de registro de ítems
        String pregunta = request.getParameter("pregunta");
        String respuestaGpt = ConexionGPT.conexion(pregunta);    
        String prompt = "Give me a example of: "+respuestaGpt+"(Only an example)";

        // Hacer la pregunta a Chat GPT

        String ejemploGpt = ConexionGPT.conexion(prompt);

        // Crear un objeto JSON con la respuesta y el ejemplo
        JSONObject responseJson = new JSONObject();
        responseJson.put("respuesta", respuestaGpt);
        responseJson.put("ejemplo", ejemploGpt);

        // Enviar la respuesta en formato JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseJson.toString());
        
        
    }

        
}



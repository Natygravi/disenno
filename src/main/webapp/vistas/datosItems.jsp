<%@page import="Modelo.Item"%>
<%@page import="DAO.ItemDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Datos del Item</title>
    <style>
        body {
            background-color: #ffe6e6;
            color: #333;
            font-family: Arial, sans-serif;
            font-size: 16px;
            line-height: 1.5;
            text-align: center; /* Añadimos esta línea para centrar el contenido */
        }
        .item-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.2);
            display: inline-block;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Datos del Item</h1>
    <% String prompt = request.getParameter("prompt");
    if (prompt != null) {
        ItemDAO itemDAO = new ItemDAO();
        Item itemBuscado = itemDAO.buscarItemPorPrompt(prompt);
    %>
        <div class="item-container">
            <p>Prompt: <%= itemBuscado.getPrompt() %></p>
            <p>Respuesta: <%= itemBuscado.getRespuesta() %></p>
            <p>Fuente: <%= itemBuscado.getFuente() %></p>
            <p>Ejemplo relacionado: <%= itemBuscado.getEjemploRelacionado() %></p>
            <p>Fuente del ejemplo: <%= itemBuscado.getFuenteEjemplo() %></p>
        </div>

        <h2>Agregar valoración</h2>
        <form method='post' action='AgregarValoracionServlet'>
            <input type='hidden' name='prompt' value='<%= prompt %>'>
            <br><br>
            <textarea name='comentarios' placeholder='Escriba sus comentarios aquí'></textarea>
            <br><br>
            <select name='estrellas'>
                <option value='1'>1 estrella</option>
                <option value='2'>2 estrellas</option>
                <option value='3'>3 estrellas</option>
                <option value='4'>4 estrellas</option>
                <option value='5'>5 estrellas</option>
            </select>
            <input type='submit' value='Agregar valoración Respuesta'>
        </form>
        <form method='post' action='AgregarValoracionServlets'>
            <input type='hidden' name='prompt' value='<%= prompt %>'>
            <select name='estrellas'>
                <option value='1'>1 estrella</option>
                <option value='2'>2 estrellas</option>
                <option value='3'>3 estrellas</option>
                <option value='4'>4 estrellas</option>
                <option value='5'>5 estrellas</option>
            </select>
            <br><br>
            <textarea name='comentarios' placeholder='Escriba sus comentarios aquí'></textarea>
            <br><br>
            <input type='submit' value='Agregar valoración Ejemplo'>
        </form>
        
        <br><br>
        <form method='post' action='GenerarPDFServlet'>
            <input type='hidden' name='prompt' value='<%= prompt %>'>
            <input type='submit' value='Enviar y generar PDF'>
        </form>
    <% } %>
</body>
</html>

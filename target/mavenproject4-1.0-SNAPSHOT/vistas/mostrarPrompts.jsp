<%@page import="java.util.List"%>
<%@page import="DAO.ItemDAO"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Prompts</title>
        <meta charset="UTF-8">
        <style>
            body {
                background-color: #ffe6e6;
                color: #333;
                font-family: Arial, sans-serif;
                font-size: 16px;
                line-height: 2.0;
                text-align: center; /* Añadimos esta línea para centrar el contenido */
            }
            form {
                background-color: #fff;
                padding: 50px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                width: 800px; /* Cambiar el ancho a 600px */
                margin: 0 auto; /* Agregamos esta línea para centrar el formulario */
            }
            label {
                display: block;
                margin-bottom: 10px;
            }
            select {
                padding: 8px;
                font-size: 16px;
                border-radius: 5px;
                border: none;
                background-color: #f2f2f2;
                box-shadow: 0 0 5px rgba(0,0,0,0.2);
            }
            input[type="submit"] {
                background-color: #4CAF50;
                color: #fff;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
            }
            input[type="submit"]:hover {
                background-color: #3e8e41;
            }
        </style>
    </head>
    <body>
        <%
            List<String> items = (List<String>) request.getAttribute("items");
            String categoria = (String) request.getAttribute("categoria");
            if (items != null) {
        %>
        <h1>Prompts de la categoría <%= categoria %></h1>
        <form method='post' action='datosItems.jsp'>
            <label for="prompt">Seleccione un prompt:</label>
            <select name='prompt' id="prompt">
                <% for (String prompt : items) { %>
                    <option value='<%= prompt %>'><%= prompt %></option>
                <% } %>
            </select>
            <br><br>
            <input type='submit' value='Mostrar los datos de los items'>
        </form>
                
        <% } %>
    </body>
</html>
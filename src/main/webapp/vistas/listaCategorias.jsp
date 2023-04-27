<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Categorías</title>
        <style>
            body {
                background-color: #ffe6e6;
                color: #333;
                font-family: Arial, sans-serif;
                font-size: 16px;
                line-height: 1.5;
                text-align: center; /* Añadimos esta línea para centrar el contenido */
            }
            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.2);
                width: 400px; /* Agregamos un ancho fijo para centrar el formulario */
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
        <form method="post" action="ItemsServlet">
            <label for="categoria">Seleccione una categoría:</label>
            <select name="categoria" id="categoria">
                <c:forEach items="${categorias}" var="categoria" varStatus="status">
                    <option value="${categoria.nombre}">${categoria.nombre}</option>
                </c:forEach>
            </select>
            <br>
            <input type="submit" value="Listar Preguntas"> 
        </form>
    </body>
</html>

<%-- 
    Document   : consultarTop
    Created on : 22 abr. 2023, 16:46:06
    Author     : saudd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="messages_${sessionScope.language}" var="msg" />
<!DOCTYPE html>
<jsp:include page="../nabvar.jsp"/>
<html lang="${sessionScope.language}">
    <head>
        <meta charset="UTF-8">
        <title>Consulta de ítems con valoraciones iguales o superiores a cuatro</title>
        <!-- Agregamos los archivos de estilos de Bootstrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa;
            }
            h1 {
                color: #2c3e50;
                margin-top: 30px;
            }
            form {
                background-color: #ffffff;
                border-radius: 5px;
                padding: 20px;
                margin-top: 30px;
                box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            }
            label {
                color: #2c3e50;
            }
            button {
                margin-top: 20px;
            }
            table {
                margin-top: 30px;
            }
            th {
                background-color: #2c3e50;
                color: #ffffff;
            }
            td {
                color: #2c3e50;
            }
            .table {
                background-color: #FFFFFF;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                overflow: hidden;
            }
            .table th, .table td {
                padding: 12px 15px;
                text-align: center;
                vertical-align: middle;
            }
            .table thead th {
                background-color: #F8F9FA;
                border-bottom: 2px solid #EBEEF0;
                font-weight: 600;
                text-transform: uppercase;
            }
            .table tbody tr {
                border-bottom: 1px solid #EBEEF0;
            }
            .table tbody tr:last-child {
                border-bottom: none;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Consulta de ítems con valoraciones iguales o superiores a cuatro</h1>

            <!-- Formulario para seleccionar criterios de búsqueda -->
            <form method="post" action="consultarTopServlet" id="topForm">
                <div class="form-group">
                    <label for="categoria">Tipo de categorias:</label>
                    <select class="form-control" name="categoria" id="categoria">
                        <c:forEach items="${categorias}" var="categoria" varStatus="status">
                            <option value="${categoria.nombre}">${categoria.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="valoraciones">Tipo de valoraciones a considerar:</label>
                    <select class="form-control" name="valoraciones" id="valoraciones">
                        <option value="respuestas">Solo valoraciones de respuestas</option>
                        <option value="ejemplos">Solo valoraciones de ejemplos</option>
                        <option value="ambas" selected>Ambas</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="fuente">Fuente de respuestas a considerar:</label>
                    <select class="form-control" name="fuente" id="fuente">
                        <option value="admin">Solo respuestas del admin</option>
                        <option value="chatGPT">Solo respuestas del chatGPT</option>
                        <option value="ambas" selected>Ambas</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="fuente-ejemplos">Fuente de ejemplos a considerar:</label>
                    <select class="form-control" name="fuente-ejemplos" id="fuente-ejemplos">
                        <option value="admin">Solo ejemplos del admin</option>
                        <option value="chatGPT">Solo ejemplos del chatGPT</option>
                        <option value="ambas" selected>Ambas</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="cantidad">Cantidad de ítems a mostrar:</label>
                    <input type="number" class="form-control" name="cantidad" id="cantidad" value="10">
                </div>

                <button type="submit" name="boton" value= unaCategoria class="btn btn-primary">Top Categoria Seleccionada</button>
                <button type="submit" name="boton" value= variasCategorias class="btn btn-primary">Top Categorias</button>
            </form>

            <!-- Resultados de la búsqueda -->
            <table class="table">
                <thead>
                    <tr>
                        <th>Ítem</th>
                        <th>Promedio de valoraciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${lista}" var="lista" >
                        <tr>
                            <td>${lista['prompt']}</td>
                            <td>${lista['valoracion']}</td>
                        </tr>
                    </c:forEach>
                    
                    <!-- etc. -->
                    </tbody>
            </table>
        </div>
    </body>
</html>
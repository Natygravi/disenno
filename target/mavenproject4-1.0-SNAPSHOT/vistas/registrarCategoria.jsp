<%-- 
    Document   : registrarCategoria
    Created on : 26 abr. 2023, 10:43:48
    Author     : saudd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <title>Registrar Categorías</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>
        <h1>Registrar Categorías</h1>
        <form action="RegistrarCategoriaServlet" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" id="nombre"><br><br>
            <label for="descripcion">Descripción:</label>
            <textarea name="descripcion" id="descripcion"></textarea><br><br>
            <label for="listaDesplegable">Cursos Disponibles:</label>
            <select class="form-select" name="curso" id="curso" multiple>
                        <option selected disabled>Seleccione un curso</option>
                        <c:forEach items="${opcionesListaCursos}" var="curso">
                          <option value="${curso.valor}">${curso.valor} ${curso.texto}</option>
                        </c:forEach>
                      </select><br><br>
          <br><br>
            <input type="submit" value="Registrar">
        </form>
        <br>
        <div id="contenedor-listas-desplegables"></div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    </body>
</html>

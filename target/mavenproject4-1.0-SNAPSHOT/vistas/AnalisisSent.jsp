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
    <title>Analisis de sentimientos</title>
    </head>
    <body>
        <h1>Análisis de sentimientos</h1>
        <form action="AnalisisSentServlet" method="post">
            <label for="listaDesplegableCategoria">Analizar Categoria Conceptual</label>
            <select class="form-select" name="categoria" id="categoria">
                        <option selected disabled>Seleccione una categoria</option>
                        <c:forEach items="${opcionesListaCategorias}" var="categoria">
                          <option value="${categoria.valor}">${categoria.valor} ${categoria.texto}</option>
                        </c:forEach>
                      </select>
          <br><br>
            <input type="submit" name="boton" value="Analizar categoria">
            <br><br>
            <label for="listaDesplegableItem">Analizar Item</label>
            <select class="form-select" name="item" id="item">
                        <option selected disabled>Seleccione un item</option>
                        <c:forEach items="${opcionesListaItems}" var="item">
                          <option value="${item.valor}">${item.valor} ${item.texto}</option>
                        </c:forEach>
                      </select>
          <br><br>
            <input type="submit" name="boton" value="Analizar item">
            <br><br>
            <label for="listaDesplegableComentario">Analizar comentario</label>
            <select class="form-select" name="comentario" id="comentario">
                        <option selected disabled>Seleccione un comentario</option>
                        <c:forEach items="${opcionesListaComentarios}" var="comentario">
                          <option value="${comentario.texto}">${comentario.texto}</option>
                        </c:forEach>
                      </select>
          <br><br>
            <input type="submit" name="boton" value="Analizar comentario">
        </form>
        <p>Resultado del análisis de sentimiento: ${sentimiento}</p>   
    </body>
</html>


<%-- 
    Document   : RegistrarItem
    Created on : 12 abr. 2023, 10:43:18
    Author     : saudd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="messages_${sessionScope.language}" var="msg" />
<!DOCTYPE html>
<jsp:include page="../nabvar.jsp"/>
<html lang="${sessionScope.language}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Registro de Ítems</h1>
            <button onclick="cargarChatGPT()">Cargar Chat GPT</button>
            <form method="post" action="RegistrarItemServlet">
                <div class="form-group">
                    <label for="categoria"><fmt:message key="navbar.categories" bundle="${msg}"/></label>
                    <select class="form-select" name="categoria" id="categoria">
                        <option selected disabled>Seleccione una categoría</option>
                        <option value="1">Refactoring</option>
                        <option value="2">Principios básicos de diseño</option>
                        <option value="3">Olores de software</option>
                        <option value="4">Deuda técnica</option>
                        <option value="5">Principios de diseño SOLID</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="pregunta"><fmt:message key="question" bundle="${msg}"/></label>
                    <input type="text" class="form-control" name="pregunta" id="pregunta" required>
                </div>
                <div class="form-group">
                    <label for="respuesta"><fmt:message key="answer" bundle="${msg}"/></label>
                    <textarea class="form-control" name="respuesta" id="respuesta" rows="3" required></textarea>
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="usar_respuesta_gpt" id="usar_respuesta_gpt">
                        <label class="form-check-label" for="usar_respuesta_gpt">
                            Usar respuesta del chatGPT
                        </label>
                    </div>
                    <label for="respuesta_gpt">Respuesta del chatGPT</label>
                    <textarea class="form-control" name="respuesta_gpt" id="respuesta_gpt" rows="3 " readonly></textarea>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="navbar.register" bundle="${msg}"/></button>
            </form>
        </div>
        <script>
            function cargarChatGPT() {
                var pregunta = document.getElementById("pregunta").value;
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "CargarChatGPTServlet?pregunta=" + pregunta, true);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        var respuestaGPT = JSON.parse(xhr.responseText).respuesta;
                        var ejemploGPT = JSON.parse(xhr.responseText).ejemplo;
                        document.getElementById("respuesta_gpt").value = respuestaGPT;
                    }
                };
                xhr.send();
            }
        </script>
    </body>
</html>

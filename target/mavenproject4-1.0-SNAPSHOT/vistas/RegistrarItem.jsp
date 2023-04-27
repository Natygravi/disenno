<%-- 
    Document   : RegistrarItem
    Created on : 12 abr. 2023, 10:43:18
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Registro de Ítems</h1>
            <form method="post" action="RegistrarItemServlet" id="itemForm" >
                <div class="form-group">
                    <label for="categoria"><fmt:message key="navbar.categories" bundle="${msg}"/></label>
                
                <select class="form-select" name="categoria" id="categoria">
                    <option selected disabled>Seleccione una categoría</option>
                    <c:forEach items="${categorias}" var="categoria" varStatus="status">
                      <option value="${status.index}">${categoria.nombre}</option>
                    </c:forEach>
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
                <textarea class="form-control" name="respuesta_gpt" id="respuesta_gpt" rows="3" disabled></textarea>
              </div>
              <div class="form-group">
                <label for="ejemplo"><fmt:message key="example" bundle="${msg}"/></label>
                <textarea class="form-control" name="ejemplo" id="ejemplo" rows="3" required></textarea>
              </div>
              <div class="form-group">
                <div class="form-check">
                  <input class="form-check-input" type="checkbox" name="usar_ejemplo_gpt" id="usar_ejemplo_gpt">
                  <label class="form-check-label" for="usar_ejemplo_gpt">
                    Usar ejemplo del chatGPT
                  </label>
                </div>
                <label for="ejemplo_gpt">Ejemplo del chatGPT</label>
                <textarea class="form-control" name="ejemplo_gpt" id="ejemplo_gpt" rows="3" disabled></textarea>
              </div>
              <button type="submit" class="btn btn-primary">Registrar ítem</button>
              <input type="button" id="cargarChatGptBtn" value="Cargar Chat GPT"/>
            </form>
        </div>        
        <script>
            // Obtener los elementos del formulario
            var usarRespuestaGpt = document.getElementById("usar_respuesta_gpt");
            var respuestaGpt = document.getElementById("respuesta_gpt");
            var usarEjemploGpt = document.getElementById("usar_ejemplo_gpt");
            var ejemploGpt = document.getElementById("ejemplo_gpt");

            // Habilitar o deshabilitar los campos de respuesta y ejemplo del ChatGPT según sea necesario
            /*function habilitarCampos() {
                if (usarRespuestaGpt.checked) {
                    respuestaGpt.disabled = false;
                } else {
                    respuestaGpt.disabled = true;
                }
                if (usarEjemploGpt.checked) {
                    ejemploGpt.disabled = false;
                } else {
                    ejemploGpt.disabled = true;
                }
            }*/
            
            function cargarChatGPT() {
               
                var pregunta = document.getElementById("pregunta").value;
    
                var xhr = new XMLHttpRequest();
                xhr.open("POST", "CargarChatGPT");
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function() {
                    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                        var responseJson = JSON.parse(this.responseText);
                        var respuesta_gpt = document.getElementById("respuesta_gpt");
                        var ejemplo_gpt = document.getElementById("ejemplo_gpt");
                        respuesta_gpt.value = responseJson.respuesta;
                        ejemplo_gpt.value = responseJson.ejemplo;
                    }
                };
                xhr.send("pregunta=" + pregunta);
            }
                
            // Agregar un listener para el evento de cambio en los checkboxes
            //usarRespuestaGpt.addEventListener("change", habilitarCampos);
            //usarEjemploGpt.addEventListener("change", habilitarCampos);
            
            var cargarChatGptBtn = document.getElementById("cargarChatGptBtn");
            cargarChatGptBtn.addEventListener("click", cargarChatGPT);
            
            var form = document.getElementById("itemForm");

            // Agrega un event listener para el evento "submit" del formulario
            form.addEventListener("submit", function(event) {
              // Evita que se envíe el formulario por defecto
              event.preventDefault();

              // Obtén los valores de respuesta_gpt y ejemplo_gpt
              var respuestaGpt = document.getElementById("respuesta_gpt").value;
              var ejemploGpt = document.getElementById("ejemplo_gpt").value;

              // Crea un elemento <input> para cada valor
              var respuestaGptInput = document.createElement("input");
              respuestaGptInput.type = "hidden";
              respuestaGptInput.name = "respuesta_gpt";
              respuestaGptInput.value = respuestaGpt;

              var ejemploGptInput = document.createElement("input");
              ejemploGptInput.type = "hidden";
              ejemploGptInput.name = "ejemplo_gpt";
              ejemploGptInput.value = ejemploGpt;

              // Agrega los elementos <input> al formulario
              form.appendChild(respuestaGptInput);
              form.appendChild(ejemploGptInput);

              // Envía el formulario
              form.submit();
            });

            
            
        </script>
    </body>
</html>


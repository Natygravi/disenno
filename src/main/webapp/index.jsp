<%-- 
    Document   : Principal
    Created on : 7 abr. 2023, 21:25:34
    Author     : saudd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PROMPS</title>
        <!-- Agregar los enlaces a los archivos CSS de Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <form action="LanguageServlet" method="post">
            <label for="language">Selecciona un idioma:</label>
            <select name="language" id="language">
                <option value="es">Español</option>
                <option value="en">Inglés</option>
                <option value="fr">Francés</option>
                <option value="de">Alemán</option>
            </select>
            <button type="submit">Enviar</button>
        </form>
        <div class="container my-5">
            <h1 class="text-center">Título principal</h1>
            <p class="lead text-center">Este es un ejemplo de página principal utilizando Bootstrap 5.</p>
        </div>

        <!-- Agregar los enlaces a los archivos JS de Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>

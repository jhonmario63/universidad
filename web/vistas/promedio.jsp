<%-- 
    Document   : promedio
    Created on : 3/07/2023, 02:50:50 PM
    Author     : Jhon Mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="./js/js.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Promedio</h1>
        <form id="myForm" action="ControllerUniversidad" method="POST">
            <button class="btn btn-primary" type="submit" name="accion" value="promedio">Promedio de universidad</button>
            <label id="mensajeLabel">El promedio de edades de la universidad es de: ${promedioUni}</label>
            <br>
            <br>
            <br>
            <a href="ControllerUniversidad?accion=index">Regresar</a>
        </form>
    </body>
</html>

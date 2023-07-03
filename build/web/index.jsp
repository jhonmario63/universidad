<%-- 
    Document   : index
    Created on : 2/07/2023, 05:22:30 PM
    Author     : Jhon Mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <br>
        <div class="container">
            <a class="btn btn-success btn-lg" href="ControllerUniversidad?accion=addEstudiante">Agregar Estudiante</a><br><br>
            <a class="btn btn-success btn-lg" href="ControllerUniversidad?accion=addEstCurso">Agregar Estudiante a Curso</a><br><br>
            <a class="btn btn-success btn-lg" href="ControllerUniversidad?accion=promedio">Es promedio Mayor</a><br><br>
        </div>
    </body>
</html>

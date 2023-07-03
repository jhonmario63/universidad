<%-- 
    Document   : agregar
    Created on : 2/07/2023, 06:46:04 PM
    Author     : Jhon Mario
--%>
<%@page import="Model.Estudiantes"%>
<%@page import="DAO.UniversidadDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%UniversidadDAO daoUniversidad = new UniversidadDAO();%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="./js/js.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <h1>Agregar Estudiante a curso</h1>
                <form id="myForm" action="ControllerUniversidad">
                    Estudiante: <br>
                    <select class="form-control" name="txtEstudiante" onchange="loadCursos()" id="txtEstudiante">
                        <option value="" disabled>Seleccionar</option>
                        <%
                            List<Estudiantes> listaEstudiantes = daoUniversidad.listarEstudiante();
                            for (Estudiantes estudiante : listaEstudiantes) {
                        %>
                        <option value="<%= estudiante.getCodigo()%>"><%= estudiante.getCodigo()%> - <%= estudiante.getNombre()%></option>
                        <% }%>
                    </select>
                    Curso:<br>
                    <select id="txtCurso" class="form-control" name="txtCurso">
                    </select>
                    <br> 
                    <button class="btn btn-primary" type="submit" name="accion" value="AgregarEstCurso">Agregar Estudiante a Curso</button>
                    <a href="ControllerUniversidad?accion=index">Regresar</a>
                    <br>
                    <br>
                    <label id="mensajeLabel">${mensaje}</label>
                </form>
            </div>

        </div>
    </body>
</html>

<%-- 
    Document   : agregar
    Created on : 2/07/2023, 06:46:04 PM
    Author     : Jhon Mario
--%>
<%@page import="DAO.UniversidadDAO"%>
<%@page import="Model.Programa"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <h1>Agregar Estudiante</h1>
                <form action="ControllerUniversidad">
                    Codigo: <br>
                    <input class="form-control" type="number" name="txtCodigo"><br>
                    Nombre:<br>
                    <input class="form-control" type="text" name="txtNombre"><br>
                    Edad:<br>
                    <input class="form-control" type="number" name="txtEdad"><br>
                    Fecha Nacimiento:<br>
                    <input class="form-control" type="date" name="txtFechaNacimiento"><br>
                    Genero:<br>
                    <select class="form-control" name="txtGenero">
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                    </select><br>
                    Programa:<br>
                    <select  class="form-control" name="txtPrograma">
                        <option value="" disabled="">Seleccionar</option>
                        <%
                            UniversidadDAO daoUniversidad = new UniversidadDAO();
                            List<Programa> list = daoUniversidad.listarPrograma();
                            Iterator<Programa> iterPrograma = list.iterator();
                            Programa v = null;
                            while (iterPrograma.hasNext()) {
                                v = iterPrograma.next();
                        %>
                        <option value="<%=v.getCodigo()%>"><%=v.getNombre()%> </option>
                        <%}%>
                    </select>
                    <br> 
                    <input class="btn btn-primary" type="submit" name="accion" value="Agregar">
                    <a href="ControllerUniversidad?accion=index">Regresar</a>
                </form>
            </div>

        </div>
    </body>
</html>

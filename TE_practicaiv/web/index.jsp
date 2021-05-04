<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Tarea"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    List<Tarea> lista = (List<Tarea>) request.getAttribute("lista");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de tareas</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Prioridad</th>
                <th>Completado</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var ="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.tarea}</td>
                    <td>${item.prioridad}</td>
                    <td><input type="checkbox" name="completado" >${item.completado}</td>
                    
                    <td><a href="MainController?op=eliminar&id=${item.id}">Eliminar</a></td>
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

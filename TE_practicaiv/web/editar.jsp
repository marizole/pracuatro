<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.emergentes.modelo.Tarea"%>
<%
    Tarea tar = (Tarea)request.getAttribute("tar");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nueva lista</h1>
        <form action="MainController" method="post">
            <table>               
                <input type="hidden" name="id" value="${tar.id}">
                <tr>
                    <td>Tarea</td>
                    <td><input type="text" name="tarea" value="${tar.tarea}"></td>
                </tr>
                <tr>
                    <td>Prioridad</td>
                    <td>
                        <select name="prioridad">
                            <option value="Alta"
                                <c:if test="${tar.prioridad == 'Alta'}" var="res" scope="request">
                                    selected 
                                </c:if>>Alta</option>
                            <option value="Media"
                                <c:if test="${tar.prioridad == 'Media'}" var="res" scope="request">
                                    selected 
                                </c:if>>Media</option>
                            <option value="Baja"
                                <c:if test="${tar.prioridad == 'Baja'}" var="res" scope="request">
                                    selected 
                                </c:if>>Baja</option>
                        </select>
                    </td>
                </tr>
                    <tr>
                    <td>Completado</td>
                    <td>
                        <input type="checkbox" name="tarea" value="${tar.completado}">
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>

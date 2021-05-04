package com.emergentes.controlador;

import com.emergentes.ConexionDB;
import com.emergentes.modelo.Tarea;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

@Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
            ArrayList<Tarea> lista = new ArrayList<Tarea>();
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (op.equals("list")) {
                //para listar los datos 
                String sql = "select * from tareas";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Tarea tar = new Tarea();
                    tar.setId(rs.getInt("id"));
                    tar.setTarea(rs.getString("tarea"));
                    tar.setPrioridad(rs.getString("prioridad"));
                    tar.setCompletado(rs.getString("completado"));
                    lista.add(tar);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            if (op.equals("nuevo")) {
                Tarea ta = new Tarea();
                System.out.println(ta.toString());
                request.setAttribute("tar", ta);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }

            if (op.equals("eliminar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "delete from tarea where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar:" + ex.getMessage());
        }
    }
    

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Valor de ID" +id);
            String tarea = request.getParameter("tarea");
            String prioridad = request.getParameter("prioridad");
            String completado = request.getParameter("completado");
            //System.out.println("Valor de ID" +completado);
            Tarea tar = new Tarea();

            tar.setId(id);
            tar.setTarea(tarea);
            tar.setPrioridad(prioridad);
            tar.setCompletado(completado);

            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (id == 0) {
                //try{
                String sql = "insert into tareas (tareas,prioridad,completado) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, tar.getTarea());
                ps.setString(2, tar.getPrioridad());
                ps.setString(3, tar.getCompletado());
                ps.executeUpdate();
            } else {
            }
            response.sendRedirect("MainController");
        } catch (SQLException ex) {
            System.out.println("Error en SQL:" + ex.getMessage());
        }
    }
}

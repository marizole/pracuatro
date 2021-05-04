package com.emergentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    //variables para la conexion a la base de datos
    static String url = "jdbc:mysql://localhost:3306/bd_proyectos";
    static String usuario = "root";
    static String password = "";

    protected Connection conn = null;

    public ConexionDB() {
        try {
            //especificaciones del driver para la conexion
            Class.forName("com.mysql.jdbc.Driver");
            //realizar la conexion
            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Falta Driver:" + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error de SQL:" + ex.getMessage());
        }
    }

    //metodo que devuelve la variable para conectar
    public Connection conectar() {
        return conn;
    }

    //metodo para realizar la desconexion de la base de datos
    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar la conexion:" + ex.getMessage());
        }
    }
}

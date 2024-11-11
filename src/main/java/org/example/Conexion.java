package org.example;

import java.sql.*;

public class Conexion {
    String url = "jdbc:mysql://localhost:3306/ejercicio_paises";
    String user = "root";
    String password = "root";
    Connection conexion;
    Statement st;

    public Conexion() {

        try{
            this.conexion = DriverManager.getConnection(url, user, password);
            st = conexion.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet consulta(String query) {
        ResultSet respuesta;
        try{
            respuesta = st.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    public int update(String query) {
        int respuesta;
        try {
            respuesta= st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    public void closeConection(){

    }
}

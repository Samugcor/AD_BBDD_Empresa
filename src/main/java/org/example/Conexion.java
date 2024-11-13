package org.example;

import java.sql.*;

public class Conexion {
    String url = "jdbc:mysql://localhost:3306/bdempresa";
    String user = "root";
    String password = "";
    Connection conexion;

    public Conexion() {

        try{
            this.conexion = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet consulta(String query) {
        ResultSet respuesta;
        try {
            Statement st = conexion.createStatement();
            respuesta = st.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    public int update(String query) {
        int respuesta;
        try(Statement st = conexion.createStatement()) {
            respuesta= st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    public void closeConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Se ha cerrado la conexión");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

package org.example;

public class Main {
    public static void main(String[] args) {
        eje2_1();

    }

    public static void eje2_1(){
        Conexion con=new Conexion();

        //ActualizacionDatos.subirSalarioDepartamento(50, "PERSOAL",con);
        //ActualizacionDatos.engadirDepartamento(7,"MANTENIMIENTO", "0010010", con);

        con.closeConexion();
    }
}
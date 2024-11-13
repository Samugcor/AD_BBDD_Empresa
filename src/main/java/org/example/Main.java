package org.example;

import org.example.Ej2.Ej2_2;
import org.example.Ej2.Ej2_1;

public class Main {
    public static void main(String[] args) {
        //eje2_1();
        eje2_2();

    }

    public static void eje2_1(){
        Conexion con=new Conexion();

        Ej2_2.subirSalarioDepartamento(50, "PERSOAL",con);
        Ej2_2.engadirDepartamento(7,"MANTENIMIENTO", "0010010", con);
        Ej2_2.borrarEmpregadoDeProxecto("0010010", 8, con);

        con.closeConexion();
    }

    public static void eje2_2(){
        Conexion con = new Conexion();
        Ej2_1.getDatosEmpleadosByLocalidad("Lugo",con);
        con.closeConexion();
    }
}
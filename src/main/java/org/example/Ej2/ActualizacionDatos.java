package org.example.Ej2;

import org.example.Conexion;

import java.time.LocalDate;

public class ActualizacionDatos {

    public static void subirSalarioDepartamento(int cantidad, String nombreDepartamento, Conexion con) {
        String query="UPDATE empregado INNER JOIN departamento ON empregado.Num_departamento =departamento.Num_departamento " +
                "SET salario = salario + " + cantidad +
                " WHERE departamento.nome_departamento = \""+nombreDepartamento+"\";";
        System.out.println(query);
        int respuesta = con.update(query);
        System.out.println("Se han modificado "+ respuesta + " registros");
    }

    public static void engadirDepartamento(int numero, String nome, String nss,Conexion con) {
        String query="Insert into departamento values ("+numero+",'"+nome+"','"+nss+"','"+ LocalDate.now() +"')";
        System.out.println(query);
        int respuesta = con.update(query);
        System.out.println("Se ha añadido "+ respuesta + " registros");
    }

    public static void borrarEmpregadoDeProxecto(String nss, int numProxecto,Conexion con) {

    }

}

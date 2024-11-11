package org.example.Ej1;

import org.example.Conexion;

import java.sql.ResultSet;

public class ActualizacionDatos {

    public static void subirSalarioDepartamento(int cantidad, String nombreDepartamento){
        Conexion con=new Conexion();
        String query="UPDATE empregados SET salario = salario +" + cantidad +
                "FROM empregados INNER JOIN departamento ON empregados.Num_departamento =" +
                "departamento.Num_departamento WHERE departamento.nome_departamento ="+nombreDepartamento;

        int respuesta = con.update(query);
        System.out.println(respuesta);
    }
}

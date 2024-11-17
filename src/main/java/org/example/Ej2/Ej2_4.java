package org.example.Ej2;

import org.example.Conexion;
import org.example.Proxecto;

import java.util.List;

public class Ej2_4 {
    public static  void  printProxectos (String nomeDepartamento, Conexion con){
        List<Proxecto> proxectos= con.obterProxectosPorDepartamento(nomeDepartamento);
        proxectos.forEach(proxecto -> {
            System.out.println("\n"+proxecto +"\n__________________________");
                }
        );
    }
}

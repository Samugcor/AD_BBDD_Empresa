package org.example.Ej2;

import org.example.Conexion;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Ej2_2 {
    public static void getDatosEmpleadosByLocalidad(String localidade, Conexion con){
        String query = "Select e.nombre as 'Nombre', e.apelido_1 as 'Apellido 1', e.apelido_2 as 'Apellido 2', \n" +
                "e.localidade as 'Localidade', e.salario as 'Salario', e.data_nacimento as 'Fecha nacimiento', \n" +
                "x.nombre as 'Nombre Jefe', departamento.nome_departamento as 'Nombre Departamento' from empregado e\n" +
                "left join empregado x on e.NSS_supervisa = x.NSS\n" +
                "inner join departamento on e.Num_departamento = departamento.Num_departamento \n" +
                "where e.localidade = \""+localidade+"\";";

        try (ResultSet res = con.consultaSt(query)){
            ResultSetMetaData metaData = res.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Print each row of data
            while (res.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metaData.getColumnLabel(i) +": "+(res.getString(i) ==null?"-":res.getString(i)) + "\t");
                }
                System.out.println();
            }

            res.getStatement().close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

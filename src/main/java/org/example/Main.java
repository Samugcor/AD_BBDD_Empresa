package org.example;

import org.example.Ej2.Ej2_1;
import org.example.Ej2.Ej2_2;
import org.example.Ej2.Ej2_4;

public class Main {
    public static void main(String[] args) {
        //eje2_1();
        //eje2_2();
        //eje2_3();
        //eje2_4();
        //eje2_5();
        //eje3_1();
        eje3_2();

    }

    public static void eje2_1(){
        Conexion con=new Conexion();

        Ej2_1.subirSalarioDepartamento(50, "PERSOAL",con);
        Ej2_1.engadirDepartamento(7,"MANTENIMIENTO", "0010010", con);
        Ej2_1.borrarEmpregadoDeProxecto("0010010", 8, con);

        con.closeConexion();
    }

    public static void eje2_2(){
        Conexion con = new Conexion();
        Ej2_2.getDatosEmpleadosByLocalidad("Lugo",con);
        con.closeConexion();
    }

    public static void  eje2_3(){
        Conexion con = new Conexion();
        System.out.println("Se ha cambiado " + con.cambiarDepartamentoProxecto("PERSOAL", "XESTION DE PERSOAL") + " proxecto");
        System.out.println("Se ha añadido " + con.nuevoProxecto(new Proxecto(11,"PROXECTO A","CANGAS",3)) + " nuevo proyecto");
        System.out.println("Se ha eliminado "+ con.borrarProxecto(11)+ " proyecto");
        con.closeConexion();
    }

    public static void eje2_4(){
        Conexion con = new Conexion();
        Ej2_4.printProxectos("PERSOAL", con);
        con.closeConexion();
    }

    public static void eje2_5(){
        Conexion con = new Conexion();
        con.cambioDomicilio(3,"Reboredo",17,"d","39303","Cangas");
        con.obterDatosProxecto(2);
        con.departamentosControlanProxectos(3);
        System.out.println("El departamento persoal ten "+ con.obterNumeroEmpregadosDepartamento("PERSOAL") +" empleados");;

        con.closeConexion();
    }

    public static void eje3_1(){
        Conexion con = new Conexion();
        con.mostrarInformacionBaseDatos();
        con.closeConexion();
    }

    public static void eje3_2(){
        Conexion con = new Conexion();
        //con.mostrarInfoTablas();
        //con.mostrarColumnasTabla("BDempresa","empregado");
        //con.mostrarProcedementos();
        con.mostrarClavePrimaria("BDempresa", "empregado");
        con.mostrarClaveForanea("bdempresa", "departamento");
        con.closeConexion();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamientonatural;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author edzzn
 */
public class OrdenamientoNatural {

    public static Scanner sc = new Scanner(System.in);

    public static Scanner txt = new Scanner(System.in);
    
    public static void main(String[] args) {

    
    //String dato=""+arc.getPath();
        System.out.println("----------------------------Campos a ordenar------------------------------");

        System.out.println("0)Numerico\n1)Texto\n2)Booleano\n3)Fecha");
        System.out.print("Seleccionar Campo a ordenar : ");
        //-----------------tiempo de ejecucion de un algoritmo
        //System.currentMillis();
        //long inicioTienPC = System.currentTimeMillis();
        int campo = sc.nextInt();
        
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Ingrese carpeta de ubicacion de archivo: \nEjemplo C:\\Users\\Usuario-1\\Desktop\\OrdenamientoNatural\\archivo.csv");
        String direccion = txt.nextLine();
        long inicioTienPC = System.currentTimeMillis();
        File archivo = new File(direccion);

        //File archivo = new File("C:\\Users\\Usuario-1\\Desktop\\OrdenamientoNatural\\" + arc.getPath());
        long inicioTienAl = System.currentTimeMillis();
        MezclaNatural mezcla = new MezclaNatural(archivo,campo);

        switch (campo) {
            case 0:
                System.out.print("Numerico");
                mezcla.setCampo(campo);
                mezcla.ordenar();
                break;
            case 1:
                System.out.print("Texto");
                mezcla.setCampo(campo);
                mezcla.ordenar();
                break;

            case 2:
                System.out.print("Booleano");
                mezcla.setCampo(campo);
                mezcla.ordenar();
                break;

            case 3:
                System.out.print("Fecha");
                mezcla.setCampo(campo);
                mezcla.ordenar();
                break;
        }
        long finTiepPC = System.currentTimeMillis();
        long finEjeAl = System.currentTimeMillis();
        long tiempoPC = (finTiepPC - inicioTienPC) / 1000;
        long tiempoEjecucionAlg = (finEjeAl - inicioTienAl) / 1000;
        System.out.println("----------Timepo de ejecucion algoritmo----------");
        System.out.println("Tiempo de ejecucion milisegundos: " + (finEjeAl - inicioTienAl));
        System.out.println("Tiempo de ejecucion segundos: " + tiempoEjecucionAlg);
        System.out.println("----------Timepo de ejecucion PC----------");
        System.out.println("Tiempo de ejecucion milisegundos: " + (finTiepPC - inicioTienPC));
        System.out.println("Tiempo de ejecucion segundos: " + tiempoPC);

    }
}
    

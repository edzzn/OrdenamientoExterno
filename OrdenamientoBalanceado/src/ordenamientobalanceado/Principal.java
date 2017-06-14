/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamientobalanceado;

import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Dayana Agudo G
 */
public class Principal {
    
    public static Scanner sc = new Scanner(System.in);

    public static Scanner txt = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("\nCampos a ordenar\n1. Numerico\n2. Texto\n3. Booleano\n4. Fecha\nSeleccione un campo a ordenar : ");
        int campo = sc.nextInt();
        System.out.println("\n\nIngrese la ubicacion del archivo: \nEjemplo C:\\Users\\Usuario-1\\Desktop\\O_Externo_Directo\\archivo.csv\n");
        String direccion = txt.nextLine();
        long inicioTienPC = System.currentTimeMillis();
        File archivo = new File(direccion);

        
        long inicioTienAl = System.currentTimeMillis();
        Ordenamiento_Balanceado ordenar = new Ordenamiento_Balanceado();
        switch (campo) {
            case 0:
                System.out.print("Numerico");

                ordenar.Procesar(campo, archivo);
                break;
            case 1:
                System.out.print("Texto");

                ordenar.Procesar(campo, archivo);
                break;

            case 2:
                System.out.print("Booleano");

                ordenar.Procesar(campo, archivo);
                break;

            case 3:
                System.out.print("Fecha");

                ordenar.Procesar(campo, archivo);
                break;
        }
        long finTiepPC = System.currentTimeMillis();
        long finEjeAl = System.currentTimeMillis();
        long tiempoPC = (finTiepPC - inicioTienPC) / 1000;
        long tiempoEjecucionAlg = (finEjeAl - inicioTienAl) / 1000;
        System.out.println("\nTimepo de ejecucion algoritmo: ");
        System.out.println("Tiempo de ejecucion milisegundos: " + (finEjeAl - inicioTienAl));
        System.out.println("Tiempo de ejecucion segundos: " + tiempoEjecucionAlg);
        System.out.println("\nTimepo de ejecucion PC: ");
        System.out.println("Tiempo de ejecucion milisegundos: " + (finTiepPC - inicioTienPC));
        System.out.println("Tiempo de ejecucion segundos: " + tiempoPC);
  
    }
    
}

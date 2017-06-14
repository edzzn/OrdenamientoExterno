package ordenamientopolifasico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edzzn
 */
public class OrdenamientoPolifasico {

    public static Scanner sc = new Scanner(System.in);

    public static Scanner txt = new Scanner(System.in);

    public static void main(String[] args) throws IOException, FileNotFoundException {

        try {
            //String dato=""+arc.getPath();
            System.out.println("----------------------------Campos a ordenar------------------------------");

            System.out.println("0)Numerico\n1)Texto\n2)Booleano\n3)Fecha");
            System.out.print("Seleccionar Campo a ordenar : ");
            //-----------------tiempo de ejecucion de un algoritmo
            //System.currentMillis();
            //long inicioTienPC = System.currentTimeMillis();
            int campo = sc.nextInt();

            System.out.println("-----------------------------------------------------------------");
            System.out.println("Ingrese carpeta de ubicacion de archivo: \nEjemplo C:\\Users\\Usuario-1\\Desktop\\OrdenamientoPoliface\\archivo.csv");
            String direccion = txt.nextLine();
            long inicioTienPC = System.currentTimeMillis();
            // File archivo = new File(direccion);

            long inicioTienAl = System.currentTimeMillis();
            OrdenamientoPolifase ordenar = new OrdenamientoPolifase();

            switch (campo) {
                case 0:
                    System.out.print("Numerico");
                    ordenar.Procesar(campo, direccion);

                    break;
                case 1:
                    System.out.print("Texto");
                    ordenar.Procesar(campo, direccion);

                    break;

                case 2:
                    System.out.print("Booleano");
                    ordenar.Procesar(campo, direccion);

                    break;

                case 3:
                    System.out.print("Fecha");
                    ordenar.Procesar(campo, direccion);

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
        } catch (ParseException ex) {
            Logger.getLogger(OrdenamientoPolifasico.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

package ordenamientobalanceado;

import com.csvreader.CsvReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Dayana Agudo G
 */
public class Ordenamiento_Balanceado {
    
    public static void Procesar(int campo, File archivo) {
        
        MezclaBalanceada mezcla = new MezclaBalanceada(archivo,campo); 
        mezcla.mezclaBalanceada(archivo.getAbsolutePath());
    
   
    }
    
}

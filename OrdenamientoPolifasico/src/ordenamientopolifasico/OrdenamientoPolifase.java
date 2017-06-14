package ordenamientopolifasico;

import com.csvreader.CsvWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class OrdenamientoPolifase {
    
    public int Procesar(int campo, String directorio) throws IOException, FileNotFoundException, ParseException {
        
        String rutaF1="F1.csv";
        String rutaF2 = "F2.csv";
        String rutaF3="F3.csv";
        
        int nroPasada = 1;
        int z=1,w=1;
        
        while(z!=0 && w!=0){
           ProcesosPolifase.Particion(directorio, rutaF1, rutaF2, campo);
           ProcesosPolifase.Fusiona(directorio, rutaF1, rutaF2, campo);
           w=(int) nroRegistros(rutaF2);
           z=(int) nroRegistros(rutaF1);
           nroPasada++;
        }
        
        ProcesosPolifase.eliminarAuxFile(rutaF1, rutaF2);
        
        return nroPasada;
    }
    
    
    public static long nroRegistros(String ruta) throws IOException{
            long count=0;
            FileReader fr = new FileReader(ruta);
            BufferedReader bf = new BufferedReader(fr);
            boolean sCad;
            while (sCad = bf.readLine()!=null)
            {
                count++;
            }
            fr.close();
            bf.close();
            return count; 
    }   
}


package ordenamientobalanceado;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dayana Agudo G
 */
public class MezclaBalanceada {
    public String directorio;
    public String nombreArchivo;
    public int campo;
    
    static final int N = 6; //número de archivos auxliares
    static final int N2 = N/2; //números de archivos de entrada e índice inicial de los archivos de salida
    static File []archivos = new File[N]; 
    static File f0; 
    static final int NumReg = 149; 
    static String TOPE = null; 
    static String[] nomAuxs = {"arch1", "arch2", "arch3", "arch4", "arch5", "arch6"}; 

    MezclaBalanceada(File archivo, int campo) {
        directorio = archivo.getParent();
        nombreArchivo = archivo.getName();
        this.campo = campo;
    }
    //método de ordenación
    
    public void mezclaBalanceada(String archivo_leer){
        //  crea N (N=6) archivos con los nombres : arch1,....arch6
        for (int i = 0; i < N; i++)   
            archivos[i] = new File(nomAuxs[i]);
        int i, j, k, k1, t;
        String anterior;
        int []c = new int[N];
        int []cd = new int[N];
        String []r = new String[N2];
        
        Object [] flujos = new Object[N];
        CsvReader flujoEntradaActual = null;
        PrintWriter flujoSalidaActual = null;
        boolean [] actvs = new boolean[N2];
        //Distribucion inicial en tramos desde archivo origen
        try{
            t = distribuir(archivo_leer);
            for(i = 0; i < N; i++)
                c[i] = i;
            //bucle hasta numero de tramos igual a uno: archivo ordenado
            do{
                k1 = (t < N2) ? t : N2;
                System.out.println((t < N2) ? t : N2);
                for(i = 0; i < k1; i++){
                    flujos[c[i]] = new CsvReader(nomAuxs[c[i]]);
                    cd[i] = c[i];
                }
                j = N2; //indice de archivo de salida
                t = 0;
                for(i = j; i < N;i++)
                    flujos[c[i]] = new PrintWriter(nomAuxs[c[i]]);
                    // entrada de una clave de cada flujo
                for( int n = 0; n < k1; n++){
                    flujoEntradaActual = (CsvReader)flujos[cd[n]];
                    flujoEntradaActual.readRecord();
                    r[n] = flujoEntradaActual.get(campo);
                }
                while(k1 > 0){
                    t++; // mezcla de otro tramo
                    for(i = 0; i < k1; i++){
                        actvs[i] = true;
                    }
                    flujoSalidaActual = (PrintWriter)flujos[c[j]];
                    while (!finDeTramos(actvs,k1)){
                        int n;
                        n = minimo(r, actvs, k1);
                        
                        flujoEntradaActual = (CsvReader)flujos[cd[n]];
                        
                            flujoSalidaActual.println(flujoEntradaActual.getRawRecord());
                       
                        anterior = r[n];
                        flujoEntradaActual.readRecord();
                        if(flujoEntradaActual.get(2) != "") {
                            //System.out.println("anterior: " + anterior);
                            r[n] = flujoEntradaActual.get(campo);
                            //System.out.println("r"+r[n]);
                            if (CompararCampos(anterior, r[n]) == false) // fin de tramo
                                actvs[n] = false;
                        }
                        else{
                            k1--; 
                            flujoEntradaActual.close(); 
                            cd[n] = cd[k1]; 
                            r[n] = r[k1];
                            actvs[n] = actvs[k1]; 
                            actvs[k1] = false;// no se accede a posiciÃ³n k1
                        }
                    }
                    j = (j < N-1) ? j+1 : N2; // Siguiente flujo e salida
                }
                for(i = N2; i < N; i++){
                    flujoSalidaActual = (PrintWriter)flujos[c[i]];
                    flujoSalidaActual.close();
                }
                /*     
                    Cambio de finalidad de los flujos: entrada<->salida    
                */ 
                for (i = 0; i < N2; i++){
                    int a; 
                    a = c[i];
                    c[i] = c[i+N2]; 
                    c[i+N2] = a; 
                    File archBor = new File(nomAuxs[c[i+N2]]);
                    archBor.delete();
                }
             //   System.out.println("VALORES DE T: "+t);
            }while (t > 1);
            //System.out.print("Archivo ordenado ...  ");
            //System.out.println("Nombre del archivo: "+nomAuxs[c[0]]);
            finalizarArchivo(nomAuxs[c[0]]);
            for(i = 0; i < N; i++){
                File archBor = new File(nomAuxs[i]);
                archBor.delete();
            }
             
        }
        catch (IOException er) {}
    }
        //distribuye tramos de flujos de entrada en flujos de salida 
    private int distribuir(String archivo_leer) throws IOException{
        int j, nt; 
        String anterior = null;
        String clave; 
        CsvReader archivoPrincipal = new CsvReader(archivo_leer);
        //  crea 3 archivos de escritura
        PrintWriter []flujoSalida= new PrintWriter[N2];
        if(campo == 0){
            anterior = "-99999";
        }
        else if(campo == 1){
            anterior = "@" ;
        }
        else if(campo == 2){
            anterior = "false";
        }
        else if(campo == 3){
            anterior = "11/12/1100";
        }
        for (j = 0; j < N2; j++) {
            flujoSalida[j] = new PrintWriter(archivos[j].getAbsolutePath());          
        }
        j = 0;   // indice del flujo de salida 
        nt = 0; 
        int contador_pasadas = 0;
        // bucle termina con la excepciÃ³n fin de fichero 
        while (archivoPrincipal.readRecord()) {
            contador_pasadas++;
            clave =  archivoPrincipal.get(campo);
            //System.out.println("Anterior y Clave: "+anterior +"/"+ clave);
            while(CompararCampos(anterior, clave) == true) {
              //  System.out.println("Anterior y Clave1: "+anterior +"/"+ clave);
                contador_pasadas++;
                
                    flujoSalida[j].println(archivoPrincipal.getRawRecord());
                
                anterior = clave;
                archivoPrincipal.readRecord();
                if(archivoPrincipal.get(0) == ""){
                    break;
                }
                clave = archivoPrincipal.get(campo);
                //System.out.println("clave: "+clave);
            }
            //      si existe clave por extraer
            if(archivoPrincipal.get(0) != ""){
                nt++; // nuevo tramo 
                j = (j < N2-1) ? j+1 : 0; // siguiente archivo
                
                    flujoSalida[j].println(archivoPrincipal.getRawRecord());
                
                anterior = clave;
            }
        }
        //System.out.println("Contador de pasadas: "+contador_pasadas);
        nt++;  // cuenta ultimo tramo 
        //System.out.println("\n*** NÃºmero de tramos: " + nt + " ***"); 
        archivoPrincipal.close();
        for (j = 0; j < N2; j++)  
            flujoSalida[j].close(); 
        
        return nt;
    }
    //devuelve true si no hay tramo activo 
    private static boolean finDeTramos(boolean [] activo, int n) {
        boolean s = true;
        for(int k = 0; k < n; k++) {
            if (activo[k])  s = false; 
        }
        return s; 
    }
    //devuelve el Ã­ndice del menor valor del array de claves   
    private int minimo(String [] r, boolean [] activo, int n) {
        int i, indice;  
        String m = null; 
        
        i = indice = 0; 
        if(campo == 0){
            m = "10000"; 
        }
        else if(campo == 1){
            m ="zzzzzzzzzzzzz";
        }
        else if(campo == 2){
            m = "true";
        }
        else if(campo == 3){
            m = "31/12/2016";
        }
        for ( ; i < n; i++) {
            if (activo[i] && (CompararCampos(r[i], m) == true)) {
                m = r[i];
                indice = i;     
            }
        }
        return indice; 
    }
    
 
    private void finalizarArchivo(String archivo_leer) {
        try { 
            PrintWriter archivo = new PrintWriter(directorio+"\\"+nombreArchivo);
            CsvReader leer_datos = new CsvReader(archivo_leer);
            while (leer_datos.readRecord()) {
                archivo.println(leer_datos.getRawRecord());
            }
            
            leer_datos.close();
            archivo.close();
            
        }catch (IOException e) {
            System.out.println("Error entrada/salia durante el proceso "+ "de ordenacion " );
            e.printStackTrace();
        }  
    } 
    
    private boolean CompararCampos(String camp1, String camp2){
           int a=0, b=0;
           if(campo==0){ // para cuando los campos deban ser numericos
               a=Integer.parseInt(camp1);
               b=Integer.parseInt(camp2);
           }
           else if(campo==3){ //para cuando los campos sonde formaot fecha
               try{
                   SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
                   Date fecha1 = fecha.parse(camp1);
                   Date fecha2 = fecha.parse(camp2);
                    if ( fecha1.before(fecha2)   ||  (fecha1.equals(fecha2))){
                       a=0;
                       b=1;
                    }  
                    else{
                       a=1;
                       b=0;
                   }

               }
              catch (ParseException e) {
              }

           }
           else{//para cuando los campos sea letras(string mismo)
               if(camp1.compareTo(camp2)<=0){
                   a=0;
                   b=1;
               }
               else{
                   a=1;
                   b=0;
               }
           }
           if (a <= b){//compara las condiciones para cada campo
               return true;
           }
               return false;     
    }
    
}

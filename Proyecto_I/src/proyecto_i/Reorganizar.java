/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_i;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.Scanner;

/**
 *
 * @author Jose Mansilla
 */
public class Reorganizar {
    
    private static final String DEFAULT_TEMP_DIRECTORY = "C:\\MEIA\\Temp.txt";
    private static final String USER_PATH = "C:\\MEIA\\Usuario.txt";              //MASTER
    private static final String LOGBOOK_PATH = "C:\\MEIA\\Bitacora.txt";          //APILO
    protected static int L;
    
    public static void ordenar()throws IOException{
        RandomAccessFile A=new RandomAccessFile(DEFAULT_TEMP_DIRECTORY,"rw" );
        L = Integer.parseInt("150");//largo registro(en bytes)
        final int n = (int) A.length()/L;//numero registros
        quicksort(A,0,n-1);
        A.close();
    }
    public static void quicksort(RandomAccessFile x,int ip,int iu)
    throws IOException{
      if( ip >= iu ) return;
      int i = particionar(x,ip,iu);
      quicksort(x,ip,i-1);
      quicksort(x,i+1,iu);
    }
    public static int particionar(RandomAccessFile x,int ip,int iu)
    throws IOException{
      String pivote = leerRegistro(x,ip);
      int i=ip;
      for(int j=ip+1; j<=iu; ++j){
        String reg = leerRegistro(x,j);
        if(reg.compareTo(pivote) < 0) intercambiar(x,++i,j);
      }
      intercambiar(x,ip,i);
      return i;
    }
    public static void intercambiar(RandomAccessFile x,int i,int j)
    throws IOException{
      String aux1=leerRegistro(x,i), aux2=leerRegistro(x,j);
      escribirRegistro(x,aux1,j); escribirRegistro(x,aux2,i);	
    }
    static public String leerRegistro(RandomAccessFile x,int y)
    throws IOException{
      x.seek(y*L);
      String aux = "";
      for(int i=1; i<=L/2; ++i) aux += x.readChar();
      return aux;
    }
    static public void escribirRegistro(RandomAccessFile x,String y,
    int z)throws IOException{
      x.seek(z*L);
      for(int i=0; i<y.length(); ++i) x.writeChar(y.charAt(i));
    }	
    
    public static void reordenarMaster() throws FileNotFoundException, IOException{
        File bitacora = new File(LOGBOOK_PATH); 
        File usuarios = new File(USER_PATH);
        String users = "";
        //Buscarlo en la bitacora
        if (bitacora.exists()) {
            Scanner scanner = new Scanner(LOGBOOK_PATH);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                users = users+line+System.getProperty("line.separator");
            }
            scanner.close();
        }
        //buscarlo en el master
        if (usuarios.exists()) {
            Scanner scanner = new Scanner(USER_PATH);
            File archivo = new File(scanner.nextLine());
            scanner = new Scanner(archivo);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                users = users+line+System.getProperty("line.separator");
            }
            scanner.close();
        }
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(DEFAULT_TEMP_DIRECTORY), "utf-8"));
            writer.write(users);
        } catch (IOException ex) {
            // report
        } finally {
            try {writer.close();} catch (IOException ex) {/*ignore*/}
        }
        ordenar();
    }
}

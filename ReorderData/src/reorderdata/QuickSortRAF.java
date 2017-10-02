/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorderdata;

/**
 *
 * @author Jose Mansilla
 */
import java.io.*;
class QuicksortRAF{
protected static int L;   
private static final String USER_PATH = "C:\\Test\\Usuarios.txt";   
public static void ordenar()throws IOException{
  RandomAccessFile A=new RandomAccessFile(USER_PATH,"rw" );
  L = Integer.parseInt("5");//largo registro(en bytes)
  final int n = (int) A.length()/L;//numero registros
  quicksort(A,0,n-1);
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
}

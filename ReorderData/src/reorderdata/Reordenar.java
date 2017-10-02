/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorderdata;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Jose Mansilla
 */
public class Reordenar {
    
    private final String DEFAULT_TEMP_DIRECTORY = "C:\\MEIA\\Temp.txt";
    private final String USER_PATH = "C:\\Test\\Usuarios.txt";              //MASTER
    private final String LOGBOOK_PATH = "C:\\MEIA\\Bitacora.txt";          //APILO
    private final String DESC_USER_PATH = "C:\\MEIA\\Desc_Usuario.txt"; 
    private final String DESC_LOGBOOK_PATH = "C:\\MEIA\\Desc_Bitacora.txt"; 
    
    public void Reorder() throws FileNotFoundException, IOException{
        
    RandomAccessFile raf = new RandomAccessFile(USER_PATH, "rw");

    int registros = ((int)raf.length()/48); //(int)

    //Empleamos dos array para ordenar y otro como apoyo para este fin
    int[] tempArrayData = new int[registros];
    int[] tempArrayPos  = new int[registros];

    int count = 0;

    for (long i = 44; i < raf.length() -4; i = i+48){

     raf.seek(i);
     int valorI = raf.readInt();

     tempArrayData[count] = valorI;
     tempArrayPos[count]  = (int)i;

     count ++;
    }

    //en este punto es donde ordenamos los arrays   

    int a       = 0;
    int tmpData = 0;
    int tmpPos  = 0;
    boolean swa = true;

    while (swa) {

        swa = false;
        a++;

        for (int i = 0; i < tempArrayData.length - i; i++) {
            if (tempArrayData[i] > tempArrayData[i + 1]) {

                tmpData              = tempArrayData[i];
                tempArrayData[i]     = tempArrayData[i + 1];
                tempArrayData[i + 1] = tmpData;

                int tempPos = tempArrayPos[i];
                tempArrayPos[i]     = tempArrayPos[i + 1];
                tempArrayPos[i + 1] = tmpPos;

                swa = true;
            }
        }
    }
    //el array tempArrayPos contiene las posiciones ordenadas con respecto a los registros de menor a mayor, si el registro 4 es el de menor puntuacion estara en la posicion 0 del array
    //el array tempArrayData contiene las puntuaciones ordenadas como las anteriores 

    //en este punto, la pregunta que me surje es si usted tiene que guardar las puntuaciones ordenadas con sus ID ect asociadas en un nuevo archivo.dat,
    //o en el .dat existente,
    //o basta con que en este punto se muestren ordenadas con un println por ejemplo.

    //Muestra
    for (int i = tempArrayPos.length; i > -1; i--){ 

        raf.seek((tempArrayPos[i]) - 44);
        System.out.println(raf.readInt()); 

        raf.seek((tempArrayPos[i]) - 40);
        System.out.println(raf.readUTF());

        raf.seek((tempArrayPos[i]) - 20);
        System.out.println(raf.readUTF());

        raf.seek(tempArrayPos[i]);
        System.out.println(raf.readInt());
    }
    //crea un nuevo dat
    RandomAccessFile rafNew = new RandomAccessFile("PartidesOrdenades.dat", "rw");
    int posNew = 0;   

    for (int i = tempArrayPos.length; i > -1; i--){ 

    rafNew.seek(posNew);
    raf.seek((tempArrayPos[i]) - 44);

    rafNew.writeInt(raf.readInt());
    posNew += 4;
    //
    rafNew.seek(posNew);
    raf.seek((tempArrayPos[i]) - 40);

    rafNew.writeUTF(raf.readUTF());
    posNew += 20;
    //
    rafNew.seek(posNew);
    raf.seek((tempArrayPos[i]) - 20);

    rafNew.writeUTF(raf.readUTF());
    posNew += 20;
    //
    rafNew.seek(posNew);
    raf.seek(tempArrayPos[i]);

    rafNew.writeInt(raf.readInt());
    posNew += 4;
    }

    rafNew.close();
    raf.close();
    }
}


package antcolony_tsp;

import java.io.*;

/**
 *
 * @author wilfredojose
 */
public class Archivos {
    
    /*EN ESTA CLASE SE ENCUENTRA TODO LO RELATIVO A METODOS Y VARIABLES QUE HAGAN POSIBLE EL GUARDAR Y ABRIR ARCHIVOS TXT*/
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;

    public Archivos() {
    }
    
    //metodo para abrir un archivo de texto
    public String abrirTexto(File archivo){
        String contenido = "";
        
        try{
            
            entrada = new FileInputStream(archivo);
            int ascci;
            while((ascci=entrada.read())!= -1){
                char caracter = (char) ascci;
                contenido+=caracter;
            }
        }catch(Exception e){
        }
        
        return contenido;              
    }
    
    //metodo para guardar un archivo de texto
    public String guardarTexto(File archivo, String contenido){
        String respuesta = null;
        
        try{
            salida = new FileOutputStream(archivo);
            byte[] bytes = contenido.getBytes();
            salida.write(bytes);
            respuesta = "El archivo se ha guardado correctamente";
      
        }catch(Exception e){
        }
        
        return respuesta;
    }
    
}

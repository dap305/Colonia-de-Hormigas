/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolony_tsp;

/**
 *
 * @author wilfredojose
 */
public class conversionesDeTexto {
    
/*ESTA CLASE ES CREADA CON EL FIN DE CONTENER TODOS LOS METODOS RELATIVOS A GUARDAR DATOS Y LEER DATOS EN FORMATO TXT*/
    
    //metodo que lee una cadena de Strings y me devuelve un en forma de double, los numeros que se encuentran en esa cadena
    public static double capturarNum(String cadena){
        char[] vectorCadena = cadena.toCharArray();
        String aux = "";
        for (int i = 0; i < vectorCadena.length; i++) {
            if(Character.isDigit(vectorCadena[i])){
                aux = aux+vectorCadena[i];
            } else if(Character.toString(vectorCadena[i]).equals(".")){
                    aux = aux+vectorCadena[i];
            }     
        }
            return Double.parseDouble(aux);       
    }
    
    //metodo que me crea una matriz de adyacencia(matriz simetrica con la diagonal nula), se usara para crear la matriz de distancias
    public static double[][] addEdges(double[][] mat, double[] distancias, int numCities){
        int aux =0;
        for (int i = 0; i < numCities; i++){
            for (int j = 0; j < numCities; j++){   
                if ( i == j ){
                    mat[i][j] = 0;
                }else if ( j < i){                   
                    mat[i][j] = distancias[aux];
                    mat[j][i] = distancias[aux];                      
                    aux+=1;
                }
            }
        }  
        return mat;
    }
    
    //cantcities empieza en 0, lee en el texto que se le pasa el numero acompañado del * que corresponde al numero de ciudades
    public static int obtenerCiudades(String texto,int cantCities){

        String[] txt = texto.split("\n");
        
        for (int i = 0; i < txt.length; i++) {
            
            if(txt[i].contains("*")){
                cantCities = (int) capturarNum(txt[i]);
            }
        }
        return cantCities;
    }
    
     //cantvertices empieza en 0, lee en el texto que se le pasa el numero acompañado del + que corresponde al numero de aristas(si, me equivoque,
    //escribi vertices cuando quise decir aristas y cuando me di cuenta fue muy tarde(de ahora en adelante todo lo que diga vertices, es aristas)
    public static int obtenerVertices(String texto, int cantVertices){

        String[] txt = texto.split("\n");
        
        for (int i = 0; i < txt.length; i++) {
            
            if(txt[i].contains("+")){
                cantVertices = (int) capturarNum(txt[i]);
            }
        }
        
        return cantVertices;
    }
    
    
    
    //nombresciudades es un vector de string que empieza con tamaño de las ciudades ya establecidos con el metodo anterior. lee un texto y regresa un vector
    //con el nombre de las ciudades
    public static String[] obtenerNombresCiudades(String texto, String[] nombresCiudades){
        int j = 0;
        String[] txt = texto.split("\n");       
       
        for (int i = 0; i < txt.length; i++) {                   
            if(!txt[i].contains("*") && !txt[i].contains("+") && !txt[i].contains("-") && !txt[i].contains("§")){               
                nombresCiudades[j] = txt[i];
                j+=1;
                
            }            
        }
        
        return nombresCiudades;
                         
    }
    
    //distancias es una matriz que empieza con tamaño de las ciudades ya establecidos con el metodo anterior. lee un texto y regresa una matriz
    //con las distancias que encontro (identificadas con "-")
    public static double[][] obtenerMatrizDistancias(String texto,int cantCities, int cantVertices, double[][] distancias){
        
        int j=0;
        String[] txt = texto.split("\n");       
        double[]  distancias2 =  new double[cantVertices];
       
        for (int i = 0; i < txt.length; i++) {        
            
             if(txt[i].contains("-")){               
                distancias2[j] = (int) capturarNum(txt[i]);
                j+=1;                
            }                   
        }
        
        distancias = conversionesDeTexto.addEdges(distancias, distancias2, cantCities);
        
        return  distancias;
    }
    
    //metodo que recibe una matriz de distancias, un vector de nombres, y valores relevantes para convertir una simulacion en un archivo txt que se pueda guardar
    //y posteriormente leer correctamente por los metodos ya establecidos
    public static String convertirATxt(double[][] distancias, String[] nombres,int cantCities, int cantVertices){
        
        String aux = "";
        for (int i = 0; i < distancias.length; i++) {
            for (int j = 0; j < distancias.length; j++) {
                if(i!=j){
                    if(j>i){
                        if(i==0 && j==1){
                            String lasDis = "-"+distancias[i][j];
                            aux = aux + lasDis;
                            
                        }else{
                            String lasDis = "\n-"+ distancias[i][j];
                            aux = aux + lasDis;
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < nombres.length; i++) {
            String losNom = "\n"+ nombres[i];
            aux = aux + losNom;
        }
        
        aux = aux + "\n*"+cantCities + "\n+"+cantVertices + "\n"+"§"; //el simbolito raro al final es para que al crear un txt, siempre se guarde con eso
                                                                      // y al momento de abrir un nuevo archivo, si no tiene ese simbolo que de error.
                                                                      //Asi logro que solo pueda cargar archivos que el programa pueda leer(que son los mismos que el crea)
        return aux;

    }
    
    


    
}

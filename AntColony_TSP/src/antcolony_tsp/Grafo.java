/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolony_tsp;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Daniel
 */
public class Grafo // Grafo completo
{ 
    private DecimalFormat format = new DecimalFormat("#.####");
    private int vertices;
    private int aristas; 
    private double[][] matriz_adj;
    private String[] nombres; //vector 
    private Scanner sc = new Scanner(System.in);
    
    

    
    //constructor del grafo cuando se carga desde un archivo txt
    public Grafo(int v, double[][] matriz_adj, String[] nombres){
            this.vertices = v;
        this.matriz_adj = matriz_adj;
        this.nombres = new String [v];
        this.aristas = (v*(v-1))/2;
        


        this.nombres = nombres;

    }
    
    
    public void agregar(){
        
        boolean flag = true, verificar = true;
        String nombre = "";
        int tamaño = vertices+1;
        double l = 0;
        double nuevo[][] = new double[tamaño][tamaño];
        String[] nombresN = new String[nombres.length+1];
        for (int i = 0; i < nombres.length; i++) {
            nombresN[i] = nombres[i];
        }
        
         while( flag )
            {
            try{    
            String pregunta = "¿Como se llamara la ciudad?";
            nombre = JOptionPane.showInputDialog(null, pregunta).trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ponerle un nombre a la ciuadad");
                    verificar = false;
                }
            } catch( NullPointerException e){
                JOptionPane.showMessageDialog(null, "Debe ponerle un nombre a la ciuadad");
                verificar = false;
            }
            if (verificar){
            for (int j = 0; j < nombre.length(); j++) 
            {
                    if(Character.isDigit(nombre.charAt(j))){
                        JOptionPane.showMessageDialog(null, "Los nombres no pueden tener numeros");
                        verificar = false;
                        break;
                    }    
            }
                if(verificar){
                    nombresN[nombresN.length-1] = nombre;
                    flag = false;
                }    
            }
                verificar = true;
            }
         
         flag = true;
         setNombres(nombresN);
        
        for (int i = 0; i < matriz_adj.length; i++) {
            for (int j = 0; j < matriz_adj.length; j++) {
                
                if ( i == j )
                    nuevo[i][j] = 0;
                
                else if ( j > i)
                {
                    nuevo[i][j] = matriz_adj[j][i];
                    nuevo[j][i] = matriz_adj[j][i];
                }
            }
        }
        
        for (int i = 0; i < nombres.length; i++) {
             if ( nombres.length-1 == i )
                    nuevo[nombres.length-1][i] = 0;
                
                else 
                {
                    while ( flag ){
                    try{
                    String titulo = "Cual es la distancia entre las ciudades "+ nombres[nombres.length-1] +" y "+ nombres[i];
                    l = Double.parseDouble(JOptionPane.showInputDialog(null, titulo));
                        if (l <= 0) {
                            verificar = false;
                            JOptionPane.showMessageDialog(null, "Ingrese un valor numerico válido");
                        }
                    } catch( NumberFormatException | NullPointerException e ){
                        verificar = false;
                        JOptionPane.showMessageDialog(null, "Ingrese un valor númerico");
                    }
                    if( verificar )
                    {
                    nuevo[nombres.length-1][i] = l;
                    nuevo[i][nombres.length-1] = l;
                    flag = false;
                    }
                    verificar = true;
                    }
                    flag = true;
                }
        }
        setMatriz_adj(nuevo);
    }
    
    public void eliminar(){
        
        boolean flag = true, verificar = true;
        int tamaño = vertices-1, eliminar = 0, n = 0, m = 0;
        double nuevo[][] = new double[tamaño][tamaño];
        String[] nombresN = new String[tamaño];
        
        //ELIMINAR
        while( flag ){
        try{
        eliminar = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Diga el Indice de la ciudad que desea eliminar?").trim());
            if (eliminar < 0 && eliminar > vertices-1) {
                verificar = false;
                JOptionPane.showMessageDialog(null, "Ingrese un indice valido");
            }
        } catch(NumberFormatException | NullPointerException e){
            JOptionPane.showMessageDialog(null, "Ingrese un indice valido");
            verificar = false;
        }
        if(verificar){
            flag = false;
        }
        verificar = true;
        }
        //ELIMINAR 
        
        
        for (int i = 0; i < nombres.length; i++) {
            if ( i != eliminar) {
            nombresN[n] = nombres[i];
            n++;
            }
        }
        
        
        n = 0;
        setNombres(nombresN);
        
        for (int i = 0; i < matriz_adj.length; i++) {
            if (i == eliminar) {
                i++;
            }
            for (int j = 0; j < matriz_adj.length; j++) {
                
                if ( j != eliminar) {
                    nuevo[n][m] = matriz_adj[i][j];
                    m++;
                }
            }
            m=0;
            n++;
        }
        setMatriz_adj(nuevo);
    }
    
    public void print()
    {
        System.out.println("\nMatriz de distancias entre ciudades:");
        for (int i = 0; i < vertices; i++) 
        {
            System.out.print(nombres[i]+": ");
            
            for (int j = 0; j < vertices; j++) 
            {
                System.out.print(matriz_adj[i][j]+", ");
            }
            System.out.println("");
        }
        System.out.println("");
    }        

    public DecimalFormat getFormat() 
    {
        return format;
    }

    public void setFormat(DecimalFormat format) 
    {
        this.format = format;
    }

    public int getVertices() 
    {
        return vertices;
    }

    public void setVertices(int vertices) 
    {
        this.vertices = vertices;
    }

    public double[][] getMatriz_adj() 
    {
        return matriz_adj;
    }

    public void setMatriz_adj(double[][] matriz_adj) 
    {
        this.matriz_adj = matriz_adj;
    }
    
    public String[] getNombres() 
    {
        return nombres;
    }

    public void setNombres(String[] nombres) 
    {
        this.nombres = nombres;
    }

    public Scanner getSc() 
    {
        return sc;
    }

    public void setSc(Scanner sc) 
    {
        this.sc = sc;
    }

    public int getAristas() {
        return aristas;
    }

    public void setAristas(int aristas) {
        this.aristas = aristas;
    }
    
}

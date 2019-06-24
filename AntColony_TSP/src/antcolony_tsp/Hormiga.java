/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolony_tsp;

/**
 *
 * @author Daniel
 */
public class Hormiga {
    
    private double caminoTamaño;
    private int camino[];
    private boolean visitado[];

    public Hormiga(int ciudades) 
    {
        this.visitado = new boolean[ciudades];
        this.camino = new int[ciudades];
    }
    
    public void addCamino( int nuevo) 
    {
        for (int i = 0; i < camino.length; i++)
        {    
            if(camino[i] == -1)
            {
            camino[i] = nuevo;
            break;
            }
        }    
    }

    public void clear() 
    {
        for (int i = 0; i < visitado.length; i++)
        {    
            visitado[i] = false;
            camino[i] = -1;
        }    
    }
    
    public void caminoTamaño(double[][] caminos)
    {
        double suma = 0;
        
        for (int i = 0; i < camino.length-1; i++) {
            
            suma = suma + caminos[camino[i]][camino[i+1]];
        }
        
        suma = suma + caminos[camino[0]][camino[camino.length-1]];
        setCaminoTamaño(suma);
    }
        

    public double getCaminoTamaño() {
        return caminoTamaño;
    }

    public void setCaminoTamaño(double caminoTamaño) 
    {
        this.caminoTamaño = caminoTamaño;
    }

    public boolean[] getVisitado() 
    {
        return visitado;
    }

    public void setVisitado(boolean[] visited) 
    {
        this.visitado = visited;
    }

    public int[] getCamino() {
        return camino;
    }

    public void setCamino(int[] camino) {
        this.camino = camino;
    }
    
}

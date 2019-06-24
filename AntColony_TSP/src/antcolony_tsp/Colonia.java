/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antcolony_tsp;

import java.text.DecimalFormat;

/**
 *
 * @author Daniel
 */
public class Colonia {
    
    private final DecimalFormat format = new DecimalFormat("#.####"); //Para imprimir max. dos decimales
    public String s = "";
    private int alpha; 
    private int numIteraciones;
    private int numCiudades;
    private double evaporacion;
    private double beta;
    private double ro;
    private Grafo grafo;
    private double feromonas[][];
    private double caminos[][];
    
    private int currentIndex;
    private Hormiga hormigas[];
    private int mejorCamino[];
    private double mejorDistancia;

    public Colonia(int alpha, int beta, double ro, Grafo grafo, int numAnts, int numIteraciones) //Constructor
    {
        int n = grafo.getMatriz_adj().length;
        this.alpha = alpha;
        this.numCiudades = n;
        this.evaporacion = (1-ro);
        this.beta = beta;
        this.ro = ro;
        this.grafo = grafo;
        this.caminos = grafo.getMatriz_adj();
        this.hormigas = new Hormiga[numAnts];
        this.feromonas = new double[n][n];
        this.mejorCamino = new int[n];
        this.numIteraciones = numIteraciones;
        
        for (int i = 0; i < hormigas.length; i++) {
            hormigas[i] = new Hormiga(n);
        }
    }
    
    public void startSolucion()
    {
        int ciudadP;
        fillFeromonas();
        
        s += "Matriz de distancias: \n";
        for (int i = 0; i < caminos.length; i++) {
            for (int j = 0; j < caminos.length; j++) {
                s += caminos[i][j]+", ";
            }
            s += "\n ";
        }
        s += "\nMatriz de feromonas inicial: \n";
        s += printFeromonas();
        for (int i = 0; i < numIteraciones; i++) 
        {
            
            s += "Camino por hormigas: \n";
            
            for(Hormiga a : hormigas)
            {
                a.clear();
                ciudadP = ciudadComienzo(a);
                
                for (int j = 0; j < numCiudades-1; j++) 
                {
                    moveAnts( sumarDenomidorFormula(ciudadP, a), ciudadP, a);
                    ciudadP = getCurrentIndex();
                }
                for (int j = 0; j < a.getCamino().length; j++) 
                {
                    s += a.getCamino()[j]+", ";
                }
                s += "\n";
                a.caminoTamaño(caminos);
                actualizarFeromonas(a);
                a.clear();
            }
            
            s +="\nMatriz de feromonas: \n";
            s += printFeromonas();
            MejorOrden();
            mejorDistancia();
            s += "Mejor camino: ";
            s += printCamino();
            s += "\nMejor distancia: "+getMejorDistancia()+"\n";
            evaporarFeromonas();
        }
    }
    
    public int ciudadComienzo(Hormiga ant)
    {
        int rand =  (int)(Math.random() * (ant.getVisitado().length));
           
        ant.getVisitado()[rand] = true;
        ant.addCamino(rand);
        
        return rand;
    }
    
    public void moveAnts( double denominador, int ciudadP, Hormiga ant)
    {
        int destino = 0, rand;
        double valores[] = new double[numCiudades]; 
        double mayor = 0, q, t, valor;
        boolean listo = false;
        
        for (int i = 0; i < ant.getVisitado().length; i++) 
        {
            if( !(ant.getVisitado()[i]) )
            {        
                q = Math.pow(1/caminos[ciudadP][i], beta);
                t = Math.pow(feromonas[ciudadP][i], alpha);
                valor = (q*t)/denominador;
                valores[i] = valor;
            }
        }
        
        while ( !listo ) 
        {
            rand = (int)(Math.random()*(ant.getVisitado().length));
            
            if( !(ant.getVisitado()[rand]) )
            {        
                if( valores[rand] > Math.random()){
                    destino = rand;
                    listo = true;
                }
            }
        }
        
        ant.getVisitado()[destino] = true;
        ant.addCamino(destino);
        setCurrentIndex(destino);
    }
    
    public double sumarDenomidorFormula(int ciudadP, Hormiga ant)
    {
        
        double suma = 0, q, t;
        for (int i = 0; i < ant.getVisitado().length; i++) 
        {
            if( !(ant.getVisitado()[i]) )
            {        
                q = Math.pow((1/caminos[ciudadP][i]), beta);
                t = Math.pow(feromonas[ciudadP][i], alpha);
                suma = suma + (q*t);
            }
        }
        return suma;
    }
    
    public void fillFeromonas()
    {
       for (int i = 0; i < numCiudades; i++) 
        {
            for (int j = 0; j < numCiudades; j++) 
            {   
                if ( i == j )
                    feromonas[i][j] = 0;
                
                else if ( j > i)
                {   
                    feromonas[i][j] = 1.0/(double)(numCiudades);
                    feromonas[j][i] = 1.0/(double)(numCiudades);

                }
            }
        } 
    }
    
    public void actualizarFeromonas(Hormiga a)
    {
            for (int i = 0; i < numCiudades-1; i++) {
            
            feromonas[a.getCamino()[i]][a.getCamino()[i+1]] += 1/a.getCaminoTamaño();
            feromonas[a.getCamino()[i+1]][a.getCamino()[i]] += 1/a.getCaminoTamaño();
        }
            feromonas[a.getCamino()[0]][a.getCamino()[a.getCamino().length-1]] += 1/a.getCaminoTamaño();
            feromonas[a.getCamino()[0]][a.getCamino()[a.getCamino().length-1]] += 1/a.getCaminoTamaño();
    }
    
    public void MejorOrden()
    {
        int escogidos[] = new int[numCiudades];
        for (int i = 0; i < escogidos.length; i++) 
        {
            escogidos[i] = 0;
        }
        boolean flag = false;
        double mayorF = 0;
        int auxJ = 0, n = 0, aux = 1, x = 0;
       
        while ( n < numCiudades-1 ) 
        {
            for (int j = 0; j < numCiudades; j++) 
            {   
                for (int k = 0; k < escogidos.length; k++)
                {
                    if (escogidos[k] == j)
                    {    
                        flag = true;
                        break;
                    }
                }
                
                if (flag)
                {
                    flag = false;
                }
                else if( feromonas[x][j] > mayorF)
                {
                    mayorF = feromonas[x][j];
                    auxJ = j;
                }    
            }
            escogidos[aux] = auxJ;
            x = auxJ;
            aux++;
            mayorF = 0;
            n++;
        }
        
        setMejorCamino(escogidos);
    }
    
    public void mejorDistancia()
    {
        double suma = 0;
        
        for (int i = 0; i < numCiudades-1; i++) {
            
            suma = suma + caminos[mejorCamino[i]][mejorCamino[i+1]];
        }
        
        suma = suma + caminos[mejorCamino[0]][mejorCamino[mejorCamino.length-1]];
        setMejorDistancia(suma);
    }
    
    public String printCamino()
    {
        String f = "";
        for (int i = 0; i < mejorCamino.length; i++) {
            f += grafo.getNombres()[mejorCamino[i]]+", ";
        }
        return f;
    }
    
    public void evaporarFeromonas()
    {
        for (int i = 0; i < feromonas.length; i++) 
        {
            for (int j = 0; j < feromonas.length; j++) 
            {
                if (i != j)
                feromonas[i][j] *= evaporacion;
            }
        }
    }
    
     public String printFeromonas()
    {
        String f = "";
        for (int i = 0; i < numCiudades; i++) 
        {
            f += grafo.getNombres()[i]+": ";
            
            for (int j = 0; j < numCiudades; j++) 
            {
                f += format.format(feromonas[i][j])+", ";
            }
            f += "\n";
        }
        f += "\n";
        return f;
    }
     
    //Getters y Setters 
    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getNumIteraciones() {
        return numIteraciones;
    }

    public void setNumIteraciones(int numIteraciones) {
        this.numIteraciones = numIteraciones;
    }

    public int getNumCiudades() {
        return numCiudades;
    }

    public void setNumCiudades(int numCiudades) {
        this.numCiudades = numCiudades;
    }

    public double getEvaporacion() {
        return evaporacion;
    }

    public void setEvaporacion(double evaporacion) {
        this.evaporacion = evaporacion;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getRo() {
        return ro;
    }

    public void setRo(double ro) {
        this.ro = ro;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public double[][] getFeromonas() {
        return feromonas;
    }

    public void setFeromonas(double[][] feromonas) {
        this.feromonas = feromonas;
    }

    public double[][] getCaminos() {
        return caminos;
    }

    public void setCaminos(double[][] caminos) {
        this.caminos = caminos;
    }

    public Hormiga[] getHormigas() {
        return hormigas;
    }

    public void setHormigas(Hormiga[] hormigas) {
        this.hormigas = hormigas;
    }
    
    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public double getMejorDistancia() {
        return mejorDistancia;
    }

    public void setMejorDistancia(double mejorDistancia) {
        this.mejorDistancia = mejorDistancia;
    }
    
    public void setMejorCamino(int[] mejorCamino) 
    {
        this.mejorCamino = mejorCamino;
    }
    
    public int[] getMejorCamino() 
    {
        return mejorCamino;
    }
}

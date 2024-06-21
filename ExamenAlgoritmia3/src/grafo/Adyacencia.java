package grafo;

import java.util.ArrayList;

public class Adyacencia {
    Arco First, Last;

    public Adyacencia() {
        First = null;
        Last = null;
    }
    
    public boolean Conectado(Nodo destino){//Revisa si la conexion al destino ya esta hecha
        Arco Actual = First;
        
        while(Actual != null && destino.getDato() != Actual.destino.getDato()){
            Actual = Actual.siguiente;
        }
        
        return Actual != null;
    }
    
    public boolean Vacia(){ //Revisa si no hay conexiones
        return First == null;
    }
    
    public void insertar(Arco newArco, Nodo destino){
        if(Vacia()){
            First = newArco;
            Last = newArco;
        }else if(destino.getDato()< First.destino.getDato()){
            newArco.siguiente = First;
            First = newArco;
        }else if(destino.getDato()>First.destino.getDato()){
            Last.siguiente = newArco;
            Last = newArco;
        }else{
            Arco Posiciones = First;
            
            while(destino.getDato() < Posiciones.destino.getDato()){
                Posiciones = Posiciones.siguiente;
            }
            
            newArco.siguiente = Posiciones.siguiente;
            Posiciones.siguiente = newArco;
        }
    }
    
    public void newAdy(Nodo destino, int peso){
        if(!Conectado(destino)){
            Arco newArco = new Arco(destino, peso);
            insertar(newArco, destino);
        }
    }

    public ArrayList NumArcos(){
        ArrayList<Arco> Arcos = new ArrayList <Arco>();
        Arco ArcoAvanza = First;
        
        while(ArcoAvanza != null){
            Arcos.add(ArcoAvanza);
            ArcoAvanza = ArcoAvanza.siguiente;
        }
        return Arcos;
    }
    
    public String mostrar(Nodo primero){
        String conexiones = "";
        Arco ArcoAvanza = First;
        Nodo NodoAvanza = primero;
        
        while(ArcoAvanza!= null){
            while(NodoAvanza != null ){
                if(Conectado(NodoAvanza)){
                    conexiones += 1+ " ";
                }else{
                    conexiones += 0+ " ";
            }
                NodoAvanza = NodoAvanza.siguiente;
            }
                ArcoAvanza = ArcoAvanza.siguiente;
        }
        
    return conexiones;    
    }
}



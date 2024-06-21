package grafo;

import java.util.LinkedList;
import java.util.List;


public class Nodo {
    int dato;
    int costo;
    Adyacencia listaAdy;
    Nodo siguiente;
    List <Nodo> shotWay = new LinkedList<>();

    public Nodo(int dato) {
        this.dato = dato;
        costo = Integer.MAX_VALUE;
        listaAdy = new Adyacencia();
        siguiente = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public List<Nodo> getShotWay() {
        return shotWay;
    }

    public void setShotWay(List<Nodo> shotWay) {
        this.shotWay = shotWay;
    }

    public Adyacencia getListaAdy() {
        return listaAdy;
    }

    public void setListaAdy(Adyacencia listaAdy) {
        this.listaAdy = listaAdy;
    }
    
    
    
}

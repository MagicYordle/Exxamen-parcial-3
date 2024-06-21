package grafo;

public class Arco {
    Nodo destino;
    int peso;
    Arco siguiente;

    public Arco(Nodo destino, int peso) {
        this.destino = destino;
        this.peso = peso;
        siguiente = null;
    }

    public Nodo getDestino() {
        return destino;
    }

    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Arco getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Arco siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}

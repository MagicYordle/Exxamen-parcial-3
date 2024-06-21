package grafo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.Timer;

public class Grafo {
    Nodo first, last;
    main ui;
    public Grafo(main ui) {
        this.ui = ui;
        first = null;
        last = null;
    }
    
    public boolean vacio(){
        return first == null;
    }
    
    public boolean nodoExiste(Nodo nodo1){//Ver si ya existe un nodo
        boolean existe = false;
        
        if(!vacio()){
           Nodo nodoAvanza = first;
           while(nodoAvanza != null && !existe){
               if(nodoAvanza.dato == nodo1.getDato()){
                   existe = true;
               }
               nodoAvanza = nodoAvanza.siguiente;
           }
        }
        
        return existe;
    }
    
    public void newArista( Nodo destino, Nodo origen, int peso){//Crea dayacencia desde punto origen hasta punto destino
        Nodo recorre = first;
        
        while(recorre.dato != origen.getDato()){
            recorre = recorre.siguiente;
        }
        
        recorre.listaAdy.newAdy(destino, peso); 
    }
    
    public void inserta(Nodo nodo){
        if(!nodoExiste(nodo)){
            if(vacio()){
                first = nodo;
                last = nodo;
            }else if(nodo.getDato() < first.dato){
                nodo.siguiente = first;
                first = nodo;
            }else if(nodo.getDato() > first.dato){
                last.siguiente = nodo;
                last = nodo;
            }else{
                Nodo recorre = first;
                
                while(nodo.getDato() < recorre.dato){
                    recorre = recorre.siguiente;
                }
                
                nodo.siguiente = recorre.siguiente;
                recorre.siguiente = nodo;
            }
        }
    }
    
    public String mostrar(){
        String valor = "";
        
        Nodo recorre = first;
        
        while(recorre != null){
            valor += recorre.getDato();
            recorre = recorre.siguiente;
        }
        
        valor += "\n";
        recorre = first;
        
        while(recorre != null){
            valor += recorre.getDato() + "->" + recorre.listaAdy.mostrar(first) + "\n"; 
            recorre = recorre.siguiente;
        }
        
        return valor;
    }
    
    public Nodo menorDistancia(Set<Nodo> noVisitado){
        Nodo mensDistancia = null;
        int lessDistancia = Integer.MAX_VALUE;
        for(Nodo recorre4: noVisitado){
            int costoNodo = recorre4.getCosto();
            
            if (costoNodo < lessDistancia){
                lessDistancia = costoNodo;
                mensDistancia = recorre4;
            }
        }
        
        return mensDistancia;
    }
    
    public Grafo calculaShortWay(Grafo grafo, Nodo origen){
        origen.setCosto(0);
        Set<Nodo> visitados = new HashSet<>();
        Set<Nodo> noVisitados = new HashSet<>();
        
        noVisitados.add(origen);
        
        while(noVisitados.size() != 0){
        Nodo actual = menorDistancia(noVisitados);
        
        noVisitados.remove(actual);
        ArrayList<Arco> ListaArcos = actual.getListaAdy().NumArcos();
        for(Arco recorrer3: ListaArcos){
            Nodo nodoAdy = recorrer3.getDestino();
            int costoConexion = recorrer3.getPeso();
            
            if(!visitados.contains(nodoAdy)){
                calcularMinDistancia(nodoAdy, costoConexion, actual);
                noVisitados.add(nodoAdy);
            }
        }
            visitados.add(actual);
        }    return grafo;
    }
    
    public void calcularMinDistancia(Nodo destino, int costo, Nodo actual){
        int distancia = actual.getCosto();
        if( distancia + costo < destino.getCosto()){
            destino.setCosto(distancia + costo);
            LinkedList<Nodo> caminoCorto = new LinkedList<>(actual.getShotWay());
            caminoCorto.add(actual);
            destino.setShotWay(caminoCorto);
        }
    }
    
    public String busca(Grafo grafo, Nodo origen, Nodo destino){
        String valor = "";
        Nodo recorre = first;
        changeColorGrafo(grafo, origen, ()->{
            changeResult(destino);
        });
        
        grafo = calculaShortWay(grafo, origen);
        valor += "ruta mas corta entre " + origen.getDato()+ " y " + destino.getDato() + "\n";
        
        while(recorre != null){
            if(recorre.getDato() == destino.getDato()){
                for(Nodo recorre2: recorre.getShotWay()){
                    valor += recorre2.getDato() + " ";
                }
            }
            recorre = recorre.siguiente;
        }
        
        valor += destino.getDato() + "\n";
        valor += "El costo fue " + destino.getCosto();
        
        return valor;
    }
    private void changeColor(Nodo nodo, String color){
        if(ui != null){
            JButton boton = ui.obtenerBoton(nodo.getDato());
            if (boton != null){
                if(color.equals("GREEN")){
                    boton.setBackground(Color.GREEN);
                }else{
                    boton.setBackground(Color.BLUE);
                }
            }else{
                System.out.println("boton no encontrado");
            }
        }
    }
    public void changeResult(Nodo destino){
        Nodo temp = first;
        while(temp != null){
            if(temp.getDato() == destino.getDato()){
                for(Nodo nodo : temp.getShotWay()){
                    changeColor(nodo, "YELLOW");
                }
            }
            temp = temp.siguiente;
        }
        changeColor(destino, "YELLOW");
    }
    
    public void changeColorGrafo (Grafo grafo, Nodo origen, Runnable onFinish){
        origen.setCosto(0);
        Set<Nodo> visitados = new HashSet<>();
        Set<Nodo> noVisitados = new HashSet<>();
        
        noVisitados.add(origen);
        
        Timer timer = new Timer(500, e->{
            if(!noVisitados.isEmpty()){
                
            
            Nodo actual = menorDistancia(noVisitados);        
            noVisitados.remove(actual);
            changeColor(actual, "GREEN");
            ArrayList<Arco> ListaArcos = actual.getListaAdy().NumArcos();
            for(Arco recorrer3: ListaArcos){
                Nodo nodoAdy = recorrer3.getDestino();
                int costoConexion = recorrer3.getPeso();
            
                if(!visitados.contains(nodoAdy)){
                    calcularMinDistancia(nodoAdy, costoConexion, actual);
                    noVisitados.add(nodoAdy);
                }
            }
                visitados.add(actual);
            }else{
                ((Timer) e.getSource()).stop();
                    if(onFinish != null){
                        onFinish.run();
                    }
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}

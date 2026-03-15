public class Nodo {
    
    Pizza dato; // Atributo que almacena la pizza en el nodo
    Nodo siguiente; // Atributo que apunta al siguiente nodo en la pila

    //constructor que recibe una pizza y la asigna al atributo dato, además inicializa el siguiente nodo como null
    public Nodo(Pizza dato) {
        this.dato = dato;
        this.siguiente = null; // Inicializa el siguiente nodo como null
    }
}
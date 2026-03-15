// Esta clase representa una pila de pizzas utilizando una estructura de nodos
public class PilaPizza {

    private Nodo cima; // Atributo que representa la cima de la pila
    // Constructor que inicializa la pila con la cima como null
    public PilaPizza() {
        cima = null;
    }

    // push() agrega una nueva pizza a la cima de la pila
    public void push(Pizza pizza) {
        Nodo nuevo = new Nodo(pizza);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    // pop() elimina y devuelve la pizza en la cima de la pila. Si la pila está vacía, devuelve null.
    public Pizza pop() {

        if (isEmpty()) {
            return null;
        }

        Pizza pizza = cima.dato; // Guarda la pizza en la cima de la pila en una variable temporal
        cima = cima.siguiente; // Actualiza la cima de la pila para que apunte al siguiente nodo, eliminando así el nodo que contenía la pizza que se va a eliminar

        return pizza;
    }

    // peek() devuelve la pizza en la cima de la pila sin eliminarla. Si la pila está vacía, devuelve null.
    public Pizza peek() {

        if (isEmpty()) {
            return null;
        }

        return cima.dato;
    }

    // isEmpty() verifica si la pila está vacía, es decir, si la cima es null. Devuelve true si la pila está vacía y false en caso contrario.
    public boolean isEmpty() {
        return cima == null;
    }
}
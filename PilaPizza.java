public class PilaPizza {

    private Nodo cima;

    public PilaPizza() {
        cima = null;
    }

    // push()
    public void push(Pizza pizza) {
        Nodo nuevo = new Nodo(pizza);
        nuevo.siguiente = cima;
        cima = nuevo;
    }

    // pop()
    public Pizza pop() {

        if (isEmpty()) {
            return null;
        }

        Pizza pizza = cima.dato;
        cima = cima.siguiente;

        return pizza;
    }

    // peek()
    public Pizza peek() {

        if (isEmpty()) {
            return null;
        }

        return cima.dato;
    }

    // isEmpty()
    public boolean isEmpty() {
        return cima == null;
    }
}
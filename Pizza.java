public class Pizza {

    private String nombre;
    private String[] ingredientes;

    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public String[] getIngredientes() {
        return ingredientes;
    }

    public void mostrarPizza() {
        System.out.println("Pizza: " + nombre);
        System.out.println("Ingredientes:");

        for (int i = 0; i < ingredientes.length; i++) {
            System.out.println("- " + ingredientes[i]);
        }
    }
}
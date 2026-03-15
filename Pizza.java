// * Esta clase representa una pizza dentro del sistema.
// Contiene el nombre de la pizza y un arreglo con 3 ingredientes.
public class Pizza {

    private String nombre;
    private String[] ingredientes; // Arreglo que almacena los ingredientes de la pizza

    /*
     * Constructor de la clase Pizza
     * Recibe el nombre y el arreglo de ingredientes
     * y los asigna a los atributos de la clase.
     */
    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }
    // Método getter para obtener el nombre de la pizza
    public String getNombre() {
        return nombre;
    }

    // Método getter para obtener los ingredientes
    public String[] getIngredientes() {
        return ingredientes;
    }
      
    //Método que muestra en consola la información de la pizza
    //(nombre e ingredientes).
     
    public void mostrarPizza() {
        System.out.println("Pizza: " + nombre);
        System.out.println("Ingredientes:");
        
        // Recorre el arreglo de ingredientes y los muestra uno por uno
        for (int i = 0; i < ingredientes.length; i++) {
            System.out.println("- " + ingredientes[i]);
        }
    }
}
import java.util.Scanner;

//Esta clase contiene el método main y controla
// toda la lógica del sistema Pizza-Track.
public class GestionPedidos {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        PilaPizza pilaPrincipal = new PilaPizza();   // Pila principal donde se guardan los pedidos actuales (Undo)
        PilaPizza pilaSecundaria = new PilaPizza();  // Pila secundaria donde se guardan los pedidos deshechos (Redo)

        int opcion;

        // Ciclo principal del menú
        do {

            System.out.println("\n=== MENÚ PIZZA TRACK ===");
            System.out.println("1. Registrar Pizza");
            System.out.println("2. Deshacer pedido anterior");
            System.out.println("3. Rehacer pedido");
            System.out.println("4. Mostrar Pedido Actual");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Salto de línea después de leer el número

            switch (opcion) {

                case 1: //Crea una nueva pizza y la inserta en la pila.

                    System.out.print("Nombre de la pizza: ");
                    String nombre = sc.nextLine();

                    String[] ingredientes = new String[3]; // Arreglo para almacenar los 3 ingredientes de la pizza
                    
                    // Solicita al usuario que ingrese los ingredientes de la pizza
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Ingrediente " + (i + 1) + ": ");
                        ingredientes[i] = sc.nextLine();
                    }

                    Pizza nuevaPizza = new Pizza(nombre, ingredientes); //Se crea el ojeto pizza

                    pilaPrincipal.push(nuevaPizza); // Se agrega la pizza a la pila principal

                    pilaSecundaria = new PilaPizza(); // Si se registra una nueva pizza se limpia la pila de redo

                    System.out.println("Pedido registrado correctamente.");

                    break;

                case 2: // Undo: Deshace el último pedido registrado, moviéndolo a la pila secundaria para permitir un posible redo.
                    
                    // Verifica si la pila principal no está vacía antes de intentar deshacer un pedido
                    if (!pilaPrincipal.isEmpty()) {

                        Pizza pizzaDeshecha = pilaPrincipal.pop(); // Se elimina la pizza de la pila principal y se guarda en una variable temporal
                        pilaSecundaria.push(pizzaDeshecha); // Se agrega la pizza deshecha a la pila secundaria para permitir un posible redo

                        System.out.println("Se deshizo el último pedido.");

                    } else {

                        System.out.println("No hay pedidos para deshacer.");
                    }

                    break;

                case 3: // Redo: Rehace el último pedido deshecho, moviéndolo de la pila secundaria de vuelta a la pila principal.

                     // Verifica si la pila secundaria no está vacía antes de intentar rehacer un pedido
                    if (!pilaSecundaria.isEmpty()) {

                        Pizza pizzaRecuperada = pilaSecundaria.pop(); // Se elimina la pizza de la pila secundaria y se guarda en una variable temporal
                        pilaPrincipal.push(pizzaRecuperada); // Se agrega la pizza recuperada de vuelta a la pila principal

                        System.out.println("Pedido recuperado.");

                    } else {

                        System.out.println("No hay pedidos para rehacer.");
                    }

                    break;

                case 4: // Muestra el pedido actual en la cima de la pila principal sin eliminarlo. Si no hay pedidos, 
                        // muestra un mensaje indicando que no hay pedidos registrados.

                    // Verifica si la pila principal no está vacía antes de intentar mostrar el pedido actual
                    if (!pilaPrincipal.isEmpty()) {

                        System.out.println("\nPedido actual listo para producción:");
                        pilaPrincipal.peek().mostrarPizza(); // Muestra la pizza en la cima de la pila principal sin eliminarla

                    } else {

                        System.out.println("No hay pedidos registrados.");
                    }

                    break;

                case 0: // Opción para salir del sistema

                    System.out.println("Sistema finalizado.");

                    break;

                default:

                    System.out.println("Opción inválida.");

            }

        } while (opcion != 0); // El ciclo se repite hasta que el usuario elija la opción de salir (0)

        sc.close(); 
    }
}

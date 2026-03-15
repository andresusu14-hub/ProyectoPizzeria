import java.util.Scanner;

public class GestionPedidos {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PilaPizza pilaPrincipal = new PilaPizza();   // Undo
        PilaPizza pilaSecundaria = new PilaPizza();  // Redo

        int opcion;

        do {

            System.out.println("\n=== MENÚ PIZZA TRACK ===");
            System.out.println("1. Registrar Pizza");
            System.out.println("2. Deshacer pedido anterior");
            System.out.println("3. Rehacer pedido");
            System.out.println("4. Mostrar Pedido Actual");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:

                    System.out.print("Nombre de la pizza: ");
                    String nombre = sc.nextLine();

                    String[] ingredientes = new String[3];

                    for (int i = 0; i < 3; i++) {
                        System.out.print("Ingrediente " + (i + 1) + ": ");
                        ingredientes[i] = sc.nextLine();
                    }

                    Pizza nuevaPizza = new Pizza(nombre, ingredientes);

                    pilaPrincipal.push(nuevaPizza);

                    // Si se registra una nueva pizza se limpia la pila de redo
                    pilaSecundaria = new PilaPizza();

                    System.out.println("Pedido registrado correctamente.");

                    break;

                case 2: // Undo

                    if (!pilaPrincipal.isEmpty()) {

                        Pizza pizzaDeshecha = pilaPrincipal.pop();
                        pilaSecundaria.push(pizzaDeshecha);

                        System.out.println("Se deshizo el último pedido.");

                    } else {

                        System.out.println("No hay pedidos para deshacer.");
                    }

                    break;

                case 3: // Redo

                    if (!pilaSecundaria.isEmpty()) {

                        Pizza pizzaRecuperada = pilaSecundaria.pop();
                        pilaPrincipal.push(pizzaRecuperada);

                        System.out.println("Pedido recuperado.");

                    } else {

                        System.out.println("No hay pedidos para rehacer.");
                    }

                    break;

                case 4: // Mostrar pedido actual

                    if (!pilaPrincipal.isEmpty()) {

                        System.out.println("\nPedido actual listo para producción:");
                        pilaPrincipal.peek().mostrarPizza();

                    } else {

                        System.out.println("No hay pedidos registrados.");
                    }

                    break;

                case 0:

                    System.out.println("Sistema finalizado.");

                    break;

                default:

                    System.out.println("Opción inválida.");

            }

        } while (opcion != 0);

        sc.close();
    }
}

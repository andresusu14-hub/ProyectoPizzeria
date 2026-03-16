 # Estudiante: Andrés Felipe Usuga Espinosa

 # Sistema de Gestión de Pedidos - Pizza Track

 ## Objetivo del programa
 El objetivo de este programa es simular un sistema básico de gestión de pedidos de una pizzería mediante el uso del lenguaje de programación Java y la estructura de datos Pila. El sistema permite registrar pedidos de pizza, deshacer el último pedido realizado (Undo), rehacer un pedido previamente deshecho (Redo) y visualizar el pedido actual que se encuentra listo para producción.

## Estructura del programa
El programa está compuesto por cuatro clases principales:
- Pizza: Representa una pizza dentro del sistema. Contiene el nombre de la pizza y un arreglo de tres ingredientes.
- Nodo: Representa un nodo dentro de la estructura de la pila. Cada nodo contiene un objeto Pizza y una referencia al siguiente nodo.
- PilaPizza: Implementa la estructura de datos tipo pila utilizando nodos enlazados. Contiene los métodos push (insertar), pop (eliminar), peek (consultar) e isEmpty (verificar si la pila está vacía).
- GestionPedidos: Contiene el método principal (main) y gestiona la interacción con el usuario a través de un menú en consola.

## Funcionamiento del sistema
El sistema utiliza dos pilas:

1. Pila Principal: almacena los pedidos activos.
2. Pila Secundaria: almacena los pedidos que han sido deshechos para permitir rehacerlos.
Cuando se registra una nueva pizza, esta se inserta en la pila principal. Si el usuario selecciona la opción de deshacer, el último pedido se elimina de la pila principal y se mueve a la pila secundaria. Si se selecciona rehacer, el pedido se recupera de la pila secundaria y vuelve a la pila principal.

## Instrucciones de ejecución
Para ejecutar el programa correctamente, siga los siguientes pasos:

1. Instalar el JDK (Java Development Kit) en el equipo.
2. Guardar las clases del programa en archivos con el mismo nombre de cada clase:
   - Pizza.java
   - Nodo.java
   - PilaPizza.java
   - GestionPedidos.java
3. Abrir una terminal o consola en la carpeta donde se encuentran los archivos.
4. Ejecutar el programa
5. Una vez iniciado el programa, aparecerá un menú interactivo donde el usuario podrá seleccionar diferentes opciones para registrar pizzas, deshacer pedidos, rehacer pedidos o visualizar el pedido actual.

## Opciones del menú del sistema
El programa presenta las siguientes opciones:

1. Registrar Pizza: permite ingresar el nombre de la pizza y tres ingredientes.
2. Deshacer pedido anterior: elimina el último pedido registrado.
3. Rehacer pedido: recupera el último pedido deshecho.
4. Mostrar pedido actual: muestra la pizza que está en la cima de la pila.
0. Salir: finaliza la ejecución del sistema

## Link del vídeo sustentativo (Youtube)
https://www.youtube.com/watch?v=sgQlKsIbQ4s


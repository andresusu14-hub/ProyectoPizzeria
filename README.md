# Lista de Reproduccion Musical

Un programa de consola en Java que simula una **lista de reproduccion de canciones** usando una **lista doblemente ligada (o doblemente enlazada)**.

---

## Conceptos clave

### Que es una estructura de datos?

Es una forma de **organizar informacion en la memoria** para poder acceder a ella y modificarla de manera eficiente. Piensa en ella como un sistema para guardar cosas: asi como usas una mochila, un estante o una fila, en programacion existen distintas formas de almacenar datos.

### Que es un nodo?

Un **nodo** es el "contenedor" basico de una estructura de datos enlazada. Cada nodo guarda:

1. Un **dato** (en nuestro caso, el titulo de una cancion).
2. Una o mas **referencias** (flechas) que apuntan a otros nodos.

```
┌─────────────────────────────────┐
│            Nodo                 │
├─────────────────────────────────┤
│  anterior ← | titulo | → siguiente  │
└─────────────────────────────────┘
```

### Que es una lista doblemente ligada?

Es una cadena de nodos donde **cada nodo conoce al que esta antes y al que esta despues**:

```
cabeza                                              cola
  ↓                                                  ↓
[Cancion A]  ↔  [Cancion B]  ↔  [Cancion C]  ↔  [Cancion D]
```

- **Cabeza**: el primer nodo de la lista.
- **Cola**: el ultimo nodo de la lista.
- La flecha `↔` significa que los nodos se apuntan mutuamente (doble enlace).

Comparacion rapida:

| Caracteristica              | Lista simple (→)       | Lista doble (↔)              |
|-----------------------------|------------------------|------------------------------|
| Cada nodo apunta a...       | Solo al siguiente      | Al siguiente **y** al anterior |
| Puedes recorrerla hacia...  | Adelante               | Adelante **y** atras         |
| Eliminar un nodo requiere...| Conocer el anterior    | El nodo ya lo sabe solo      |

### Que es una referencia `null`?

`null` significa **"no apunta a nada"**. El `anterior` de la cabeza es `null` (no hay nada antes) y el `siguiente` de la cola es `null` (no hay nada despues).

---

## Enunciado del problema

> **Contexto:** Imagina que estas creando tu propio reproductor de musica. Necesitas una estructura que permita:
>
> - **Agregar** canciones al final de la lista.
> - **Mostrar** todas las canciones en orden.
> - **Borrar** una cancion por su nombre, sin importar si esta al inicio, en medio o al final.
> - **Buscar** una cancion y saber cuales estan antes y despues de ella.
>
> Un arreglo (`array`) no es ideal porque tiene tamano fijo y borrar un elemento del medio es costoso. Una **lista doblemente ligada** resuelve estos problemas: crece y se encoge dinamicamente y borrar cualquier nodo solo requiere "reconectar" a sus vecinos.

---

## Estructura del proyecto

```
src/
 ├── Nodo.java               → Define que es una cancion (el contenedor)
 ├── ListaReproduccion.java   → La lista doblemente ligada con todas las operaciones
 └── Principal.java           → El menu de consola que interactua con el usuario
```

---

## Explicacion de cada clase y sus metodos

### 1. `Nodo.java` — El contenedor de cada cancion

```java
public class Nodo {
    String titulo;      // el dato que guardamos
    Nodo   anterior;    // referencia al nodo de atras
    Nodo   siguiente;   // referencia al nodo de adelante
}
```

**Constructor `Nodo(String titulo)`**

- Recibe el nombre de la cancion.
- Inicializa `anterior` y `siguiente` en `null` porque cuando se crea, el nodo todavia no esta conectado a ninguna lista.

---

### 2. `ListaReproduccion.java` — La lista doblemente ligada

Tiene tres atributos privados:

| Atributo   | Tipo  | Para que sirve                  |
|------------|-------|---------------------------------|
| `cabeza`   | Nodo  | Apunta al primer nodo           |
| `cola`     | Nodo  | Apunta al ultimo nodo           |
| `cantidad` | int   | Lleva la cuenta de canciones    |

#### `agregar(String titulo)`

Agrega una cancion **al final** de la lista.

**Paso a paso:**

1. Crea un nuevo nodo con el titulo recibido.
2. Si la lista esta vacia (`cabeza == null`), el nuevo nodo se convierte en cabeza **y** cola al mismo tiempo.
3. Si ya hay canciones:
   - El nuevo nodo apunta hacia atras a la cola actual (`nuevo.anterior = cola`).
   - La cola actual apunta hacia adelante al nuevo nodo (`cola.siguiente = nuevo`).
   - La cola se actualiza para que sea el nuevo nodo (`cola = nuevo`).
4. Incrementa el contador `cantidad`.

```
Antes:   ... ↔ [Cola actual]
Despues: ... ↔ [Cola actual] ↔ [Nuevo nodo]  ← nueva cola
```

#### `mostrar()`

Recorre la lista **de cabeza a cola** e imprime cada cancion.

**Paso a paso:**

1. Si la cabeza es `null`, imprime "La lista esta vacia" y termina.
2. Crea una variable `cursor` que empieza en la cabeza.
3. Mientras `cursor` no sea `null`:
   - Imprime el titulo del cursor.
   - Avanza al siguiente nodo (`cursor = cursor.siguiente`).
4. Imprime el total de canciones.

> **Concepto**: esta forma de recorrer nodo por nodo se llama **recorrido lineal** o **iteracion**.

#### `borrar(String titulo)`

Elimina una cancion buscandola por nombre.

**Paso a paso:**

1. Si la lista esta vacia, muestra un mensaje y termina.
2. Busca el nodo recorriendo desde la cabeza hasta encontrar uno cuyo titulo coincida (ignorando mayusculas/minusculas con `equalsIgnoreCase`).
3. Si no lo encuentra, muestra un mensaje de error.
4. Si lo encuentra, repara los enlaces de sus vecinos:

```
Antes:   [Anterior] ↔ [aBorrar] ↔ [Siguiente]
Despues: [Anterior] ↔ [Siguiente]
```

   - **Si tiene nodo anterior**: el anterior ahora apunta al siguiente (`aBorrar.anterior.siguiente = aBorrar.siguiente`).
   - **Si NO tiene anterior** (es la cabeza): la cabeza se mueve al siguiente nodo.
   - **Si tiene nodo siguiente**: el siguiente ahora apunta al anterior (`aBorrar.siguiente.anterior = aBorrar.anterior`).
   - **Si NO tiene siguiente** (es la cola): la cola se mueve al nodo anterior.

5. Desconecta el nodo borrado poniendo sus referencias en `null`.
6. Decrementa `cantidad`.

> **Tip**: Este es el metodo mas complejo. Dibuja los nodos en papel y simula cada caso (borrar la cabeza, borrar la cola, borrar uno del medio) para entenderlo bien.

#### `buscar(String titulo)`

Busca una cancion y muestra su posicion y vecinos.

**Paso a paso:**

1. Si la lista esta vacia, muestra un mensaje y termina.
2. Recorre desde la cabeza contando la posicion (empieza en 1).
3. Si encuentra la cancion, imprime:
   - En que posicion esta (por ejemplo, "posicion 3 de 5").
   - Cual es la cancion anterior (o "ninguna" si es la primera).
   - Cual es la cancion siguiente (o "ninguna" si es la ultima).

---

### 3. `Principal.java` — El menu interactivo

Es la clase que contiene el metodo `main`. Su trabajo es:

1. Crear un objeto `Scanner` para leer lo que escribe el usuario.
2. Crear un objeto `ListaReproduccion` (la lista empieza vacia).
3. Mostrar un menu con 5 opciones en un bucle `while`:
   - **[1] Agregar** → pide el nombre y llama a `lista.agregar(...)`.
   - **[2] Mostrar** → llama a `lista.mostrar()`.
   - **[3] Borrar** → pide el nombre y llama a `lista.borrar(...)`.
   - **[4] Buscar** → pide el nombre y llama a `lista.buscar(...)`.
   - **[5] Salir** → termina el bucle.
4. Cierra el `Scanner` al salir.

> **Nota sobre `teclado.nextLine()` despues de `teclado.nextInt()`**: `nextInt()` solo lee el numero pero deja un salto de linea pendiente. El `nextLine()` extra en la linea 30 "consume" ese salto para que las lecturas siguientes funcionen correctamente. Este es un error clasico de principiantes con `Scanner`.

---

## Como compilar y ejecutar

Abre una terminal dentro de la carpeta del proyecto y ejecuta:

```bash
# Compilar los tres archivos
javac src/Nodo.java src/ListaReproduccion.java src/Principal.java

# Ejecutar el programa
java -cp src Principal
```

---

## Ejemplo de uso

```
╔══════════════════════════════╗
║   🎵 Lista de Reproduccion   ║
╚══════════════════════════════╝
┌─────────────────────────────┐
│ [1] Agregar cancion         │
│ [2] Mostrar lista           │
│ [3] Borrar cancion          │
│ [4] Buscar cancion          │
│ [5] Salir                   │
└─────────────────────────────┘
  Opcion: 1
  Nombre de la cancion: Bohemian Rhapsody
  ✓ "Bohemian Rhapsody" agregada.

  Opcion: 1
  Nombre de la cancion: Hotel California
  ✓ "Hotel California" agregada.

  Opcion: 2

  ♫ Lista de reproduccion:
  Bohemian Rhapsody  ↔  Hotel California
  Total: 2 cancion(es)
```

---

## Ejercicios sugeridos para practicar

Si ya entendiste el codigo, intenta agregar estas funcionalidades:

1. **Insertar en una posicion especifica** (no solo al final).
2. **Mostrar la lista al reves** (recorriendo de cola a cabeza).
3. **Contar cuantas veces aparece una cancion repetida**.
4. **No permitir canciones duplicadas** al momento de agregar.

---

## Como subir este proyecto a GitHub

Sigue estos pasos **en orden** desde una terminal abierta en la carpeta del proyecto.

### Paso 1 — Configurar tu identidad en Git (solo la primera vez)

Esto le dice a Git quien eres. Usa el **mismo correo** con el que creaste tu cuenta de GitHub.

```bash
git config --global user.name "Tu Nombre"
git config --global user.email "tu-correo@ejemplo.com"
```

> Estos datos aparecen en cada commit que hagas. Solo necesitas ejecutarlos **una vez** en tu computadora.

### Paso 2 — Inicializar el repositorio local

Convierte la carpeta actual en un repositorio de Git.

```bash
git init
```

> Esto crea una carpeta oculta `.git/` donde Git guarda todo el historial de cambios.

### Paso 3 — Agregar los archivos al area de preparacion (staging)

Le dices a Git **cuales archivos** quieres incluir en el siguiente commit.

```bash
git add .
```

> El punto `.` significa "todos los archivos de esta carpeta". Tambien puedes agregar archivos individuales con `git add src/Nodo.java`.

### Paso 4 — Crear el primer commit

Un commit es una "foto" del estado actual de tus archivos, con un mensaje que describe que hiciste.

```bash
git commit -m "Primer commit: lista de reproducción con lista doblemente ligada"
```

> El texto entre comillas es el **mensaje del commit**. Debe ser breve y describir los cambios.

### Paso 5 — Crear el repositorio en GitHub

1. Ve a [github.com](https://github.com) e inicia sesion.
2. Haz clic en el boton **"+"** (esquina superior derecha) → **"New repository"**.
3. Ponle un nombre, por ejemplo: `lista-reproduccion-java`.
4. **NO** marques "Add a README" (ya tenemos uno).
5. Haz clic en **"Create repository"**.
6. GitHub te mostrara una URL que se ve asi: `https://github.com/tu-usuario/lista-reproduccion-java.git`. Copiala.

### Paso 6 — Conectar tu repositorio local con GitHub

Usa la URL que copiaste en el paso anterior:

```bash
git remote add origin https://github.com/tu-usuario/lista-reproduccion-java.git
```

> Esto le dice a Git: "cuando quiera subir cambios, envialos a este repositorio de GitHub". `origin` es el nombre por defecto que se le da al servidor remoto.

### Paso 7 — Subir los archivos a GitHub

```bash
git branch -M main
git push -u origin main
```

> - `git branch -M main` renombra la rama actual a `main` (el estandar de GitHub).
> - `git push -u origin main` sube tus commits al repositorio remoto. La bandera `-u` hace que Git recuerde la conexion para futuros push.
> - La primera vez te pedira tu **usuario y contraseña** de GitHub (o un token de acceso personal).

### Paso 8 — Para futuros cambios

Cada vez que modifiques archivos y quieras subir los cambios:

```bash
git add .
git commit -m "Descripción breve de lo que cambiaste"
git push
```

> Ya no necesitas repetir los pasos de configuracion ni el `-u`. Solo: **add → commit → push**.

### Resumen visual del flujo

```
[Tu código]  →  git add .  →  git commit -m "mensaje"  →  git push  →  [GitHub]
   (local)      (staging)        (historial local)        (servidor remoto)
```

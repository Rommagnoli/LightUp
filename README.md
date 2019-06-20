# LightUp #

Light Up es un interesante juego de ingenio, que consiste en posicionar, en un tablero de 7×7 celdas, un numero de lamparas, 
de acuerdo a restricciones indicadas en el propio tablero, de manera de iluminar todo el tablero.
El tablero esta inicialmente ocupado por celdas blancas y negras.
Las lamparas pueden ser ubicadas solo en celdas blancas, y las celdas negras pueden contener restricciones numericas: valores 
numericos alojados en las celdas que indican cuantas celdas vecinas (horizontales y verticales) deben contener lamparas.
La luz de una lampara se propaga horizontal y verticalmente, hasta el limite del tablero, o hasta chocar con una celda negra.
Finalmente, no pueden alojarse dos lamparas de manera tal que sus respectivos haces de luz se choquen.

Como setear un Tablero para probar el Algorito:

Para poder setear un tablero base para el juego sin el agregado de un menu, se debe ingresar en la clase Tablero y, dentro de ella,
buscar la función setTableroBase(), donde se estan seteando manualmente las celdas negras con dos funciones, las cuales sus perfiles son:
	
	- La primera tiene el siguiente perfil: public void nuevaCeldaNegra(int tabIndex, Integer valor)
	Esta funcion setea una celda negra con valor, su primer parametro, "tabIndex", es la posicion en la que se agrega la celda negra y el 
	parametro "valor", es el valor que va a tener la celda negra ingresada.

	- La segunda tiene el siguiente perfil: public void nuevaCeldaNegra(int tabIndex)
	Esta funcion setea una celda negra sin valor, donde su parametro es la posicion en el arreglo donde se va a setear

Una vez ubicada la funcion setTableroBase(), se cambian los parametros de las funciones y se pueden agregar mas lineas o eliminar algunas que no se requieran. Notar que en las funciones que setea nuevas celdas negras, dentro tienen una funcion que se llama transformarCoord(fila, columna) que recibe dos parametros para que la insercion en el arreglo sea mas amigable. 

------------------------------------------------------------------------------------
Proyecto LightUp para Diseños de Algoritmos de la Universidad Nacional de Rio Cuarto
------------------------------------------------------------------------------------

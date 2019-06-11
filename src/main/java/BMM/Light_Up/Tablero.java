package BMM.Light_Up;

/**
 * Clase que repersenta el tablero de juego
 *
 */
public class Tablero {
    
    /**
     * Asigna el numero de Filas del tablero
     */
	private static final Integer FIL = 7;
	
	/**
	 * Asigna el numero de Columas del tablero
	 */
	private static final Integer COL = 7;
	
	/**
	 * Matriz en la que se guarda el tablero de juego
	 */
	private Celdas[][] elementos;

	/**
	 * Crea un tablero predeterminado de 7 x 7 
	 * para el juego Light Up
	 */
	public Tablero() {
        this.elementos = new Celdas[FIL][COL];
		for (Integer filas = 0; filas < FIL; filas++) {
            for(Integer columnas = 0; columnas < COL; columnas++) {
                Celdas celda = new Celdas();
				this.elementos [FIL][COL] = celda;
			}
		}
			
		elementos [3][1].setCeldaNegra();
		elementos [6][4].setCeldaNegra();		
		elementos [0][2].setCeldaNegra(1);
		elementos [1][3].setCeldaNegra(1);
		elementos [4][0].setCeldaNegra(3);
		elementos [5][3].setCeldaNegra(0);
		elementos [3][5].setCeldaNegra(2);
		elementos [2][6].setCeldaNegra(3);
	}
}

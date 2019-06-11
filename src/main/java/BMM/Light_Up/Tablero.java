package BMM.Light_Up;

/**
 * Clase que repersenta el tablero de juego
 *
 */
public class Tablero {
    
    /**
     * Asigna el numero de Filas del tablero.
     */
	private static final Integer FIL = 7;
	
	/**
	 * Asigna el numero de Columas del tablero.
	 */
	private static final Integer COL = 7;
	
	/**
	 * Matriz en la que se guarda el tablero de juego.
	 */
	private Celdas[][] elementos;
	
	/**
	 * Representa la cantidad de celdas con luz en la matriz. 
	 */
	private int cantCeldasLuz;

	/**
	 * Crea un tablero predeterminado de 7 x 7 
	 * para el juego Light Up
	 */
	public Tablero() {
        this.elementos = new Celdas[FIL][COL];
        this.cantCeldasLuz = 0;
		for (Integer filas = 0; filas < FIL; filas++) {
            for(Integer columnas = 0; columnas < COL; columnas++) {
                Celdas celda = new Celdas();
				this.elementos [FIL][COL] = celda;
			}
		}
			
		/*elementos [3][1].setCeldaNegra();
		elementos [6][4].setCeldaNegra();		
		elementos [0][2].setCeldaNegra(1);
		elementos [1][3].setCeldaNegra(1);
		elementos [4][0].setCeldaNegra(3);
		elementos [5][3].setCeldaNegra(0);
		elementos [3][5].setCeldaNegra(2);
		elementos [2][6].setCeldaNegra(3);*/
	}
	
	/**
	 * Funcion que retorna la matriz de celdas.
	 * @return la matriz que simboliza el tablero.
	 */
	public Celdas[][] getTablero(){
		return elementos;
	}
	
	/**
	 * Funcion que dice si un tablero no es null.
	 * @return true si es valido, falso si es invalido.
	 */
	public boolean tableroValido() {
		return (this.getTablero() != null);
	}
	
	/**
	 * Funcion que retorna la cantidad de celdas con luz.
	 * @return cantidad de celdas con luz.
	 */
	public int getCantCeldasLuz() {
		return (this.cantCeldasLuz);
	}
	
	/**
	 * Funcion que setea una celda negra en la matriz si la posicion es valida.
	 * @param posFila es el indice que representa una fila en la matriz.
	 * @param posCol es el indice que representa una columna en la matriz.
	 */
	public void nuevaCeldaNegra(int posFila, int posCol) {
		if (this.tableroValido())
			throw new IllegalArgumentException("El tablero es nulo");
		if ((posFila >= FIL || posFila < 0) || (posCol <= 0 || posCol >= COL)) {
			throw new IllegalArgumentException("Posiciones invalidas");
		}
		if (this.elementos[posFila][posCol].esNegra())
			throw new IllegalArgumentException("La celda en la posicion " + posFila + posCol + "ya es negra");
		this.elementos[posFila][posCol].setCeldaNegra(); 
	}
	
	/**
	 * Funcion que setea una celda negra con valor en la matriz si la posicion es valida
	 * @param posFila es el indice que representa una fila en la matriz.
	 * @param posCol es el indice que representa una columna en la matriz.
	 * @param valor es el valor que tendra la celda negra.
	 */
	public void nuevaCeldaNegra(int posFila, int posCol, Integer valor) {
		if (this.tableroValido())
			throw new IllegalArgumentException("El tablero es nulo");
		if ((posFila >= FIL || posFila < 0) || (posCol <= 0 || posCol >= COL)) {
			throw new IllegalArgumentException("Posiciones invalidas");
		}
		if (this.elementos[posFila][posCol].esNegra())
			throw new IllegalArgumentException("La celda en la posicion " + posFila + posCol + "ya es negra");
		this.elementos[posFila][posCol].setCeldaNegra(valor); 
	}
	
	/**
	 * Funcion que dice si poner una lampara en la posicion es valida 
	 * teniendo una celda negra con valor adyacente.
	 * @param posFila indica la fila en una matriz.
	 * @param posCol indica la columna en una matriz.
	 * @return true si la posicion es valida, falso si no lo es.
	 */
	public boolean adyCeldaNegra(int posFila, int posCol) {
		int valCelda = 0;
		int cantLamparasCelda = 0;
		
		if ((posFila - 1 >= 0) && this.elementos[posFila - 1][posCol].esNegraValor()) {
			valCelda = this.elementos[posFila - 1][posCol].getValorCelda();
			cantLamparasCelda = this.cantLamparasAdy(posFila-1, posCol);
			if (valCelda >= cantLamparasCelda)
				return false;
		}
		if ((posFila + 1 < FIL) && this.elementos[posFila + 1][posCol].esNegraValor()) {
			valCelda = this.elementos[posFila + 1][posCol].getValorCelda();
			cantLamparasCelda = this.cantLamparasAdy(posFila + 1, posCol);
			if (valCelda >= cantLamparasCelda)
				return false;
		}
		if ((posCol - 1 >= 0) && this.elementos[posFila][posCol - 1].esNegraValor()) {
			valCelda = this.elementos[posFila][posCol - 1].getValorCelda();
			cantLamparasCelda = this.cantLamparasAdy(posFila, posCol - 1);
			if (valCelda >= cantLamparasCelda)
				return false;
		}
		if ((posCol + 1 < COL) && this.elementos[posFila][posCol + 1].esNegraValor()) {
			valCelda = this.elementos[posFila][posCol + 1].getValorCelda();
			cantLamparasCelda = this.cantLamparasAdy(posFila, posCol + 1);
			if (valCelda >= cantLamparasCelda)
				return false;
		}
		return true;
	}
	
	/**
	 * Funcion que dice si una posicion es valida para poner una lampara en la matriz.
	 * @param posFila es el indice que representa una fila en la matriz.
	 * @param posCol es el indice que representa una columna en la matriz.
	 * @return true si es valida la posicion, false si es invalida.
	 */
	public boolean posValidaNuevaLamp(int posFila, int posCol) {
		if (this.tableroValido())
			throw new IllegalArgumentException("El tablero es nulo");
		if ((posFila >= FIL || posFila < 0) || (posCol <= 0 || posCol >= COL)) {
			throw new IllegalArgumentException("Posiciones invalidas");
		}
		if (this.elementos[posFila][posCol].esNegra())
			return false;
		else {
			if (this.elementos[posFila][posCol].esLuz())
				return false;
			else {
				if (!(this.adyCeldaNegra(posFila, posCol)))
					return false;
				else
					return true;
			}
		}
	}
	
	/**
	 * Funcion que setea en la matriz una lampara si y solo si la posicion es valida.
	 * @param posFila es el indice que representa una fila en la matriz.
	 * @param posCol es el indice que representa una columna en la matriz.
	 */
	public void nuevaLampara(int posFila, int posCol) {
		if (!(this.posValidaNuevaLamp(posFila, posCol)))
			throw new IllegalArgumentException("Posicion invalida para una lampara");
		else {
			this.elementos[posFila][posCol].setLampara();
			this.cantCeldasLuz++;
			int i = posFila - 1;
			while ((i>=0) && (!(this.elementos[i][posCol].esNegra()))) {
				if (this.elementos[i][posCol].esLuz())
					i--;
				else {
					this.elementos[i][posCol].setCeldaLuz();
					this.cantCeldasLuz++;
					i--;
				}		
			}
			i = posFila + 1;
			while ((i<FIL) && (!(this.elementos[i][posCol].esNegra()))){
				if (this.elementos[i][posCol].esLuz())
					i++;
				else {
					this.elementos[i][posCol].setCeldaLuz();
					this.cantCeldasLuz++;
					i++;
				}
			}
			i = posCol - 1;
			while ((i>=0) && ((!this.elementos[posFila][i].esNegra()))) {
				if (this.elementos[posFila][i].esLuz())
					i--;
				else {
					this.elementos[posFila][i].setCeldaLuz();
					this.cantCeldasLuz++;
					i--;
				}
			}
			i = posCol + 1;
			while ((i<COL) && (!(this.elementos[posFila][i].esNegra()))) {
				if (this.elementos[posFila][i].esLuz())
					i++;
				else {
					this.elementos[posFila][i].setCeldaLuz();
					this.cantCeldasLuz++;
					i++;
				}
			}
		}
	}
	
	/**
	 * Funcion privada que dice la cantidad de lamaparas que 
	 * tiene adyacente una celda negra con valor
	 * @param posFila representa la fila de una matriz.
	 * @param posCol representa la columna de una matriz.
	 * @return cantidad de lamparas adyacentes.
	 */
	private int cantLamparasAdy(int posFila, int posCol) {
		int cantLamparas = 0;
		if (posFila-1>=0 && (this.elementos[posFila-1][posCol].esLampara())) {
			cantLamparas++;
		}
		if (posFila+1<FIL && (this.elementos[posFila+1][posCol].esLampara())) {
			cantLamparas++;
		}
		if (posCol-1>=0 && (this.elementos[posFila][posCol-1].esLampara())) {
			cantLamparas++;
		}
		if (posCol+1<COL && (this.elementos[posFila][posCol+1].esLampara())) {
			cantLamparas++;
		}
		return cantLamparas;
	}
}	
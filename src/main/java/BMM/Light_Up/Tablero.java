package BMM.Light_Up;


public class Board {

	private static final int FIL = 7;
	private static final int COL = 7;
	private static Celdas[][] elementos;

	public Board () {
		this.elementos = new Celdas[FIL][COL];
		for (int filas = 0; filas < FIL; filas++) {
			for(int columnas = 0; columnas < COL; columnas++) {
				Celdas celda = new Celdas();
				this.elementos [FIL][COL] = celda;
			}
		}
	}
}

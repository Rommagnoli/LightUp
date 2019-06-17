package BMM.Light_Up;

import org.jgap.*;

public class Fitness extends FitnessFunction{
	
	/**
	 * Variable que lleva el tablero original. 
	 */
	private Tablero tablero;
	
	/**
	 * Constructor de la clase Fitness.
	 * @param t representa el tablero origiinal con las celdas negras.
	 */
	public Fitness(Tablero t) {
		tablero = t;
	}
	
	/**
	 * Funcion que evalua que tan apto es un Cromosoma
	 * @param individuo representa un cromosoma.
	 * @return un valor el cual dice que tan apto es el individuo, si es apto retorna un numero
	 * muy alto de aptitud.
	 */
	public double evaluate(LightUpChromosome individuo) {
		double res = 0;
		int posicion = 0;
		for (Gene gen : individuo.getGenes()) {
			Integer lamparaONo = (Integer) gen.getAllele();
			if (lamparaONo == 1) {
				try {
					tablero.nuevaLampara(posicion);
				}
				catch(IllegalArgumentException excepcion) {
					res = res - 500;
				}
				res = res + 500;
			}
			posicion++;
		}
		return res;	
	}
	
}

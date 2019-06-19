package BMM.Light_Up;

import java.util.*;
import org.jgap.*;

public class LightUpFitnessFunction extends FitnessFunction {

  private static final long serialVersionUID = 1L;
  
  /**
   * Variable que lleva el tablero original.
   */
  private Tablero tablero;

  /**
   * Constructor de la clase Fitness.
   * @param t representa el tablero origiinal con las celdas negras.
   */
  public LightUpFitnessFunction(Tablero t) {
    tablero = t;
  }

  /**
   * Funcion que evalua que tan apto es un Cromosoma.
   * @param individuo representa un cromosoma.
   * @return un valor el cual dice que tan apto es el individuo.
   */
  public double evaluate(IChromosome individuo) {
    double res = 0;
        int posicion = 0;
        ArrayList<Integer> posCeldasNegras = Tablero.posCeldasNegrasValor(tablero);
        Boolean isCatch = false;
        for (Gene gen : individuo.getGenes()) {
            Integer lamparaONo = (Integer) gen.getAllele();
            if (lamparaONo == 1) {
                try {
                    tablero.nuevaLampara(posicion);
                } catch (IllegalArgumentException excepcion) {
                    isCatch = true;
                }
                if (!isCatch){
                    res = 0;
                    isCatch = false;
                }
            }
            posicion++;
        }
        ArrayList<Integer> celdasNValor = Tablero.posCeldasNegrasValor(tablero);
        for (Integer pos : celdasNValor){
            if (tablero.reglaCantidadLamparasAdy(pos)){
                res = res + 100;
            }
        }
        if (tablero.reglaTableroCompletoLuz())
            res = res + 200;
        return res;
  }
}
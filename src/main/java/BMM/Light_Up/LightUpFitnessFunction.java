package BMM.Light_Up;

import java.util.*;
import org.jgap.*;

/**
 * Clase en la que se implementa la Fitness Function y algunos metodos usados por ella. 
 * @author Boaglio Agustin.
 * @author Menendez Josue.
 * @author Magnoli Roman.
 */
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
   * Metodo que evalua un cromosoma un cromosoma y le setea un valor.
   * @return La valoracion del tablero.
   */
  public double evaluate(IChromosome individuo) {
    double res = 49;
    int conflictos = cantConflictos(individuo, tablero);
    if (conflictos == 0) {
      Gene[] genActual = individuo.getGenes();
      Tablero aux = new Tablero();
      aux.setTableroPorDefecto();
      for (int j = 0; j < 49; j++) {
        aux.setMejorTablero(j, (Integer) genActual[j].getAllele());  
      }
      int cantidadLuz = aux.getCantCeldasLuz();
      res = res + cantidadLuz;
    }
    return res - conflictos;
  }
  
  /**
   * Metodo que calcula la cantidad de conflictos que se generan en cromosoma
   * teniendo en cuenta la representacion del tablero.
   * @param individuo Es el cromosoma que guarda las lamparas. 
   * @param tab Es el tablero base.
   * @return La cantidad de conflictos generados.
   */
  private int cantConflictos(IChromosome individuo, Tablero tab) {
    int conflictos = 0;
    int pos = 0;
    for (Gene gen: individuo.getGenes()) {
      Integer lampara = (Integer) gen.getAllele();
      if (lampara == 1) {
        if (!tab.posValidaNuevaLamp(pos)) conflictos++;
      } 
      pos++;
    }
    Gene[] genActual = individuo.getGenes();
    Tablero aux = new Tablero();
    aux.setTableroPorDefecto();
    for (int j = 0; j < 49; j++) {
      aux.setMejorTablero(j, (Integer) genActual[j].getAllele());  
    }
    conflictos = conflictos + cantConflictosTab(aux);
    return conflictos;
  }
  
  /**
   * Calcula la cantidad de conflictos que se generan al no completar el requerimiento
   * de una celda negra valuada.
   * @param tab Es el tablero para calcularle los conflictos.
   * @return La cantidad de conflictos generados.
   */
  private static int cantConflictosTab(Tablero tab) {
    int conflictos = 0;
    for (int i = 0; i < tab.getTablero().length; i++) {
      if (tab.getTablero()[i].esNegraValor()) {
        if (tab.getTablero()[i].getValorCelda() != tab.cantLamparasAdy(i)) conflictos++;
      }
    } 
    return conflictos;
  }
}
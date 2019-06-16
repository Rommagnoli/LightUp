package BMM.Light_Up;

import org.jgap.BaseGene;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Gene;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SetGene;

/**
 * Clase que repersenta el tablero de juego.
 *
 */
public class Tablero {
  
  /**
   * Asigna el numero maximo del arreglo.
   */
  private static final Integer MAX = 49;
  
  /**
   * Arreglo en el que se guarda el tablero de juego.
   */
  private Celdas[] elementos;
	
  /**
   * Representa la cantidad de celdas con luz en la matriz. 
   */
  private int cantCeldasLuz;

  /**
   * Representa la cantidad de celdas negras en la matriz.
   */
  private int cantCeldasNegras;

  /**
   * Crea un tablero predeterminado de 7 x 7 para el juego Light Up.
   */
  public Tablero() {
    this.elementos = new Celdas[MAX];
    this.cantCeldasLuz = 0;
    for (Integer tabIndex = 0; tabIndex < MAX; tabIndex++) {
      Celdas celda = new Celdas();
      this.elementos [tabIndex] = celda;
    }
  }
  
  public void setTableroPorDefecto() {
    this.elementos [22].setCeldaNegra();
    this.elementos [46].setCeldaNegra();     
    this.elementos [2].setCeldaNegra(1);
    this.elementos [10].setCeldaNegra(1);
    this.elementos [28].setCeldaNegra(3);
    this.elementos [38].setCeldaNegra(0);
    this.elementos [26].setCeldaNegra(2);
    this.elementos [20].setCeldaNegra(3);
    
  }
    /*
    */
  
  /**
   * Funcion que retorna la matriz de celdas.
   * @return la matriz que simboliza el tablero.
   */
  public Celdas[] getTablero(){
    return elementos;
  }

  /**
   * Funcion que dada la posiscion en un arreglo, devuelve la columna correspondiente
   * de la matriz donde se encontraria.
   * @param tabIndex es el indice del arreglo
   * @return el indice de la columna a la que hace referencia la posicion
   */
  private static int transformarColumna(int tabIndex) {
    int col = tabIndex;
    while (col >= 7) {
      col = col - 7;
    }
    return col;
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
   * Funcion que retorna la cantidad de celdas negras.
   * @return la cantidad de celdas negras del tablero.
   */
  public int getCantCeldasNegras() {
    return this.cantCeldasNegras;
  }

  /**
   * Funcion que retorna la cantidad de celdas sin luz del tablero.
   * @return cantidad de celdas sin luz.
   */
  public int getCantidadSinLuz() {
    return (MAX - this.cantCeldasNegras - this.cantCeldasLuz);
  }

  /**
   * Funcion que setea una celda negra en la matriz si la posicion es valida.
   * @param tabIndex es el indice de movimiento para el arreglo.
   */
  public void nuevaCeldaNegra(int tabIndex) {
    if (!this.tableroValido()) {
      throw new IllegalArgumentException("El tablero es nulo");
    }
    
    if (tabIndex < 0 || tabIndex > 48) {
      throw new IllegalArgumentException("Posiciones invalidas");
    }  
    
    if (this.elementos[tabIndex].esNegra()) {
      throw new IllegalArgumentException("La celda en la posicion " + tabIndex + "ya es negra");
    }

    this.elementos[tabIndex].setCeldaNegra();
    this.cantCeldasNegras++;
  }

  /**
   * Funcion que setea una celda negra con valor en la matriz si la posicion es valida.
   * @param tabIndex es el indice de movimiento para el arreglo.
   * @param valor es el valor que tendra la celda negra.
   */
  public void nuevaCeldaNegra(int tabIndex, Integer valor) {
    if (this.tableroValido()) {
      throw new IllegalArgumentException("El tablero es nulo");
    }

    if (tabIndex < 0 || tabIndex > MAX) {
      throw new IllegalArgumentException("Posiciones invalidas");
    }

    if (this.elementos[tabIndex].esNegra()) {
      throw new IllegalArgumentException("La celda en la posicion " + tabIndex + "ya es negra");
    }

    this.elementos[tabIndex].setCeldaNegra(valor);
    this.cantCeldasNegras++;
  }

  /**
   * Funcion que dice si poner una lampara en la posicion es valida 
   * teniendo una celda negra con valor adyacente.
   * @param tabIndex es el indice de movimiento para el arreglo.
   * @return true si la posicion es valida, falso si no lo es.
   */
  public boolean adyCeldaNegra(int tabIndex) {
    int valCelda = 0;
    int cantLamparasCelda = 0;

    if ((tabIndex - 7 >= 0) && this.elementos[tabIndex - 7].esNegraValor()) {      // Elemento de arriba (representacion en matriz)
      valCelda = this.elementos[tabIndex - 7].getValorCelda();
      cantLamparasCelda = this.cantLamparasAdy(tabIndex - 7);
      if (valCelda <= cantLamparasCelda) {
        return false;
      }
	}
    if ((tabIndex + 7 < MAX) && this.elementos[tabIndex + 7].esNegraValor()) {     // Elemento de abajo (representacion en matriz)
      valCelda = this.elementos[tabIndex + 7].getValorCelda();
      cantLamparasCelda = this.cantLamparasAdy(tabIndex + 7);
      if (valCelda <= cantLamparasCelda) {
        return false;
      }
	}
	if ((tabIndex - 1 >= 0) && this.elementos[tabIndex - 1].esNegraValor()) {      // Elemento de la Izquierda (representacion en matriz)
	  valCelda = this.elementos[tabIndex - 1].getValorCelda();
	  cantLamparasCelda = this.cantLamparasAdy(tabIndex - 1);
	  if (valCelda <= cantLamparasCelda) {
        return false;
      }
	}
	if ((tabIndex + 1 < MAX) && this.elementos[tabIndex + 1].esNegraValor()) {     // Elemento de la derecha (representacion en matriz)
	  valCelda = this.elementos[tabIndex + 1].getValorCelda();
	  cantLamparasCelda = this.cantLamparasAdy(tabIndex + 1);
	  if (valCelda <= cantLamparasCelda) {
        return false;
      }
	}
	return true;
  }
	
  /**
   * Funcion que dice si una posicion es valida para poner una lampara en la matriz.
   * @param tabIndex es el indice de movimiento para el arreglo.
   * @return true si es valida la posicion, false si es invalida.
   */
  public boolean posValidaNuevaLamp(int tabIndex) {
    if (!(this.tableroValido())) {
      throw new IllegalArgumentException("El tablero es nulo");
    }
	if (tabIndex < 0 || tabIndex > MAX) {
	  throw new IllegalArgumentException("Posiciones invalidas");
	}
	if (this.elementos[tabIndex].esNegra()) {
      return false;
    } else {
	  if (this.elementos[tabIndex].esLuz()) {
        return false;
      } else {
	    if (!(this.adyCeldaNegra(tabIndex))) {
          return false;
        } else {
          return true;
        }
	  }
	}
  }
	
  /**
   * Funcion que setea en la matriz una lampara si y solo si la posicion es valida.
   * @param tabIndex es el indice de movimiento para el arreglo.
   */
  public void nuevaLampara(int tabIndex) {
    if (!(this.posValidaNuevaLamp(tabIndex))) {
      throw new IllegalArgumentException("Posicion invalida para una lampara");
    } else {
	  this.elementos[tabIndex].setLampara();
	  this.cantCeldasLuz++;
	  
	  int i = tabIndex - 7;
	  while ((i>=0) && (!(this.elementos[i].esNegra()))) {         //Illuminar el tablero hacia arriba
	    if (this.elementos[i].esLuz()) {
          i = i - 7;
        } else {
          this.elementos[i].setCeldaLuz();
          this.cantCeldasLuz++;
          i = i - 7;
        }		
	  }
	  
	  i = tabIndex + 7;
	  while ((i < 49) && (!(this.elementos[i].esNegra()))){         //Illuminar el tablero hacia abajo
	    if (this.elementos[i].esLuz()) {
          i = i + 7;
        } else {
          this.elementos[i].setCeldaLuz();
          this.cantCeldasLuz++;
          i = i + 7;
        }
	  }
	  
	  int col = (Tablero.transformarColumna(tabIndex));
	  if (!(col == 0 || col % 7 == 0)) {
	    col = col - 1;
	    i = tabIndex - 1;
	    while ((col >= 0) && ((!this.elementos[i].esNegra()))) {     //Illuminar el tablero hacia la izquierda
	      if (this.elementos[i].esLuz()) {
            i--;
          } else {
            this.elementos[i].setCeldaLuz();
            this.cantCeldasLuz++;
            i--;
          }
	      col--;
	    }
	  }
	  
	  col = (Tablero.transformarColumna(tabIndex));
	  if (!(col == 6)) {
	    i = tabIndex + 1;
	    while ((col < 6) && (!(this.elementos[i].esNegra()))) {      //Illuminar el tablero hacia la derecha
	      if (this.elementos[i].esLuz()){
            i++;
          } else {
            this.elementos[i].setCeldaLuz();
            this.cantCeldasLuz++;
            i++;
          }
	      col++;
	    }
	  }
	}
  }
	
  /**
   * Funcion privada que dice la cantidad de lamaparas que 
   * tiene adyacente una celda negra con valor.
   * @param tabIndex representa el indice del arregla.
   * @return cantidad de lamparas adyacentes.
   */
  private int cantLamparasAdy(int tabIndex) {
    int cantLamparas = 0;
    if (tabIndex - 7 >= 0 && (this.elementos[tabIndex - 7].esLampara())) {
      cantLamparas++;
    }
	if (tabIndex + 7 < MAX && (this.elementos[tabIndex + 7].esLampara())) {
	  cantLamparas++;
	}
	if (tabIndex - 1 >= 0 && (this.elementos[tabIndex - 1].esLampara())) {
	  cantLamparas++;
	}
	if (tabIndex + 1 < MAX && (this.elementos[tabIndex + 1].esLampara())) {
	  cantLamparas++;
	}
	return cantLamparas;
  }
  
  /**
   * Set the tablero to the chromosome
   * @param tablero
   * @throws InvalidConfigurationException 
   */
  public LightUpChromosome setChromosome() throws InvalidConfigurationException {
    int i = 0;
    //Configuration g_config = new DefaultConfiguration();
    LightUpChromosome gene = new LightUpChromosome();
    for (Celdas c : this.getTablero()) {
      IntegerGene g = new IntegerGene();
      g.newGene();
      if (c.esLampara()) {
        g.setAllele(1);
      } else {
        g.setAllele(0);
      }
      gene.setEnChromosoma(i, g);
      i++;
    }
    LightUpChromosome chromosome = new LightUpChromosome();
    chromosome.setChromosome(gene);
    return chromosome;
  }
  
  public void setMejorTablero(int pos, Integer value) {
    if (value == 1) {
      try {
        this.nuevaLampara(pos);
      } catch (IllegalArgumentException exe) {
        
      }
    }
  }    
  
  /**
   * 
   */
  public String toString() {
    String tab = "";
    for (int index = 0; index < MAX; index++) {
      if ((index % 7) == 0) {
        tab = tab + "\n" + " | ";
      }
      tab = tab + this.elementos[index].toString() + "| ";
    }
    return tab;
  }
}
package BMM.Light_Up;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 * Clase que se representa nuestro cromosoma.
 * @author Boaglio Agustin.
 * @author Menendez Josue.
 * @author Magnoli Roman.
 */
public class LightUpChromosome extends Chromosome{
	
  /**
   * Arreglo que representa el cromosoma
   */
  private Gene[] chromosome;
  
  private static final long serialVersionUID = 1L;

  /**
   * Contructor de la clase.
   * @throws InvalidConfigurationException
   */
  public LightUpChromosome() throws InvalidConfigurationException{
    chromosome = new Gene[49];
    for (int gene = 0; gene < 49; gene++) {
      chromosome[gene] = new IntegerGene();
    }
  }
	
  /**
   * Constructor de la clase que setea una configuracion.
   * @param g_config Configuracion a setear.
   * @throws InvalidConfigurationException
   */
  public LightUpChromosome(Configuration g_config) throws InvalidConfigurationException{
    chromosome = new Gene[49];
    for (int gene = 0; gene < 49; gene++) {
      chromosome[gene] = new IntegerGene(g_config,0,gene);
    }
  }
	
  /**
   * Setea en la variable privada un cromosoma reciibido por parametro.
   * @param gene
   */
  public void setChromosome(LightUpChromosome gene) {
    this.chromosome = gene.getChromosome();
  }
	
  /**
   * Setea un Gen en el cromosoma en la posicion dada.
   * @param pos Es la posicion en la que se setea el gen.
   * @param g Gen que se quiere setear en el cromosoma.
   */
  public void setEnChromosoma (int pos, Gene g) {
    this.chromosome[pos] = g;
  }
	
  /**
   * Metodo que retorna la variable cromosoma.
   * @return El arreglo que representa el cromosoma.
   */
  public Gene[] getChromosome() {
    return this.chromosome;
  }
	
  /**
   * Metodo para printear un cromosoma por pantalla.
   * @return La string donde esta el cromosoma a imprimir.
   */
  public String toString () {
    String chromoString = "";
    Gene[] arr = this.chromosome;
	for (int index = 0; index < arr.length; index++) {
	  chromoString = chromoString + " | " +(String) arr[index].getAllele();
	}
	return chromoString;
  }
}
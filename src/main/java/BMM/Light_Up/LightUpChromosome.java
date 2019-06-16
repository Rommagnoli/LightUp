package BMM.Light_Up;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;


public class LightUpChromosome extends Chromosome{
	
	private Gene[] chromosome;
	private static final long serialVersionUID = 1L;

	
	
	public LightUpChromosome() throws InvalidConfigurationException{
      chromosome = new Gene[49];
      for (int gene = 0; gene < 49; gene++) {
        chromosome[gene] = new IntegerGene();
      }
    }
	
	public LightUpChromosome(Configuration g_config) throws InvalidConfigurationException{
      chromosome = new Gene[49];
      for (int gene = 0; gene < 49; gene++) {
        chromosome[gene] = new IntegerGene(g_config,0,gene);
      }
	}
	
	public void setChromosome(LightUpChromosome gene) {
	  this.chromosome = gene.getChromosome();
	}
	
	public void setEnChromosoma (int pos, Gene g) {
	  this.chromosome[pos] = g;
	}
	
	public Gene[] getChromosome() {
	  return this.chromosome;
	}
	
	public Tablero setTablero(Tablero t) {
		int i = 0;
        for (int index = 0; index < 49; index++) {
		  Gene g = chromosome[i];
 		  Integer comp = (Integer) g.getAllele();
		  if (comp == 1)
		    t.nuevaLampara(i);
		  i++;
		}
		return t;
	}
	
	public String toString () {
	  String chromoString = "";
	  Gene[] arr = this.chromosome;
	  for (int index = 0; index < arr.length; index++) {
	    chromoString = chromoString + " | " +(String) arr[index].getAllele();
	  }
	  return chromoString;
	}
}
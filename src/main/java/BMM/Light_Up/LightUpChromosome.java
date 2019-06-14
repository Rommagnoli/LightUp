package BMM.Light_Up;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SetGene;


public class LightUpChromosome extends Chromosome{
	
	private Chromosome chromosome;
	private static final long serialVersionUID = 1L;
	
	public LightUpChromosome() throws InvalidConfigurationException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Tablero setTablero(Tablero t) {
		int i = 0;
		for (Gene g : this.chromosome.getGenes()) {
			Integer comp = (Integer) g.getAllele();
			if (comp == 1)
				t.nuevaLampara(i);
			i++;
		}
		return t;
	}
}
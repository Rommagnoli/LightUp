package BMM.Light_Up;

import org.jgap.*;
import org.jgap.impl.IntegerGene;

public class LightUpChromosome extends Chromosome{
	private static final long serialVersionUID = 1L;
	public LightUpChromosome() throws InvalidConfigurationException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Put the lamps from a chromosome in the current Tablero
	 * @param t
	 * @return t 
	 */
	public Tablero toTablero(Tablero t) {
		IntegerGene[] aux = (IntegerGene[]) this.getGenes();
		for(IntegerGene g : aux)
			t.nuevaLampara(g.getLowerBounds(), g.getUpperBounds());
		return t;	
	}

}
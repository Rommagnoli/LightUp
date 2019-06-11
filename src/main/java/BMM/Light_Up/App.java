package BMM.Light_Up;
import org.jgap.*;
import org.jgap.event.EventManager;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.impl.BestChromosomesSelector;
import org.jgap.impl.ChromosomePool;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.GreedyCrossover;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.StockRandomGenerator;
import org.jgap.impl.SwappingMutationOperator;
/**
 * Hello world!
 *
 */
public class App {
	private static Configuration m_config;
	
	public static Configuration createConfiguration(final Object a_initial_data)
		      throws InvalidConfigurationException {
		    // This is copied from DefaultConfiguration.
		      // -----------------------------------------
		      Configuration config = new Configuration();
		      BestChromosomesSelector bestChromsSelector =
		          new BestChromosomesSelector(config, 1.0d);
		      bestChromsSelector.setDoubletteChromosomesAllowed(false);
		      config.addNaturalSelector(bestChromsSelector, true);
		      config.setRandomGenerator(new StockRandomGenerator());
		      config.setMinimumPopSizePercent(0);
		      config.setEventManager(new EventManager());
		      config.setFitnessEvaluator(new DefaultFitnessEvaluator());
		      config.setChromosomePool(new ChromosomePool());
		      // These are different:
		      // --------------------
		      config.addGeneticOperator(new GreedyCrossover(config));
		      config.addGeneticOperator(new SwappingMutationOperator(config, 20));
		      return config;
		  }
	
	public static void main( String[] args ) throws InvalidConfigurationException {
        System.out.println( "Hello World!" );
    
        Gene[] prueba = new Gene[5];
        int i = 0;
        int j = prueba.length;
		m_config = new DefaultConfiguration();
		
		//m_config.setFitnessFunction();
        while (i < j) {
        	prueba[i] = new IntegerGene(m_config,i,j-i);
        	i++;
        }
       //Gene aux = new IntegerGene();
		BaseChromosome Origen = new Chromosome(m_config ,prueba);
        System.out.println(Origen.toString());
       
        m_config.setSampleChromosome( Origen );
        m_config.setPopulationSize( 20 );
        Genotype population = Genotype.randomInitialGenotype( m_config );
        
        for( int k = 0; k <  10; k++ )
        {
        population.evolve();
        }
        System.out.println("Evolucion:");
        System.out.println(population.toString());
	}	
}

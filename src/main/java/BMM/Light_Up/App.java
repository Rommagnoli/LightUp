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

  public static void main (String[] args) throws InvalidConfigurationException {
    Tablero tab = new Tablero();
    //System.out.println(tab.toString());         //Printea el tablero base
    
    //System.out.println("------------------------");
    
    //tab.nuevaLampara(8);                        //Printea el tablero con una lampara hubicada (prueba)
    //System.out.println(tab.toString());

    Configuration config = new DefaultConfiguration();  //Setea una config por default
    config.setPreservFittestIndividual(true);
    Fitness fitfun = new Fitness(tab);                  //Setea nuestra FitnessFunction
    config.setFitnessFunction(fitfun);
    
    Gene[] sampleGene = new Gene[49];                   //Creacion del SampleGene
    for (int i = 0; i < 49; i++) {
      sampleGene[i] = new IntegerGene(config, 0,1);
    }
    
    IChromosome sampleChromosome = new Chromosome(config, sampleGene); //Setea el SampleGene creado antes
    config.setSampleChromosome(sampleChromosome);
    //System.out.println(config.toString());        //Printea la configuracion
    
    config.setPopulationSize(1000);                 //Setea el tamaño maximo de poblacion
    Genotype Poblacion;
    Poblacion = Genotype.randomInitialGenotype(config); //Genera una poblacion inicial en base al SampleChromosome (Creo)
    Poblacion.evolve();                                 //Inicia la evolucion de la poblacion
    //System.out.println(Poblacion.toString());         //Printea la Poblacion
    IChromosome fittest = Poblacion.getFittestChromosome(); //Captura el mejor cromosoma y lo guarda en la variable
    Gene[] chromo = fittest.getGenes();
    //for (int i = 0; i < 49; i++){                     //Printea el mejor chromosoma
    //  System.out.println("Posicion " + i + ": " + chromo[i].toString());
    //}
    Tablero mejorTablero = new Tablero();
    for (int i = 0; i < 49; i++) {                      //Setea en un tablero el mejor cromosoma obtenido
      mejorTablero.setMejorTablero(i,(Integer) chromo[i].getAllele());  
    }
    //System.out.println(mejorTablero.toString());    //Prientea el mejor tablero
  }
}
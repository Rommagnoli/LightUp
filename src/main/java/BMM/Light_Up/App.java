package BMM.Light_Up;
import java.util.Scanner;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 * Clase principal de la aplicacion.
 *
 */
public class App { 
  
  /**
   * Metodo "main" donde se ejecuta la app.
   * @throws InterruptedException 
   */
  public static void main(String[] args) throws InvalidConfigurationException, InterruptedException {
    Tablero tab = new Tablero();
    tab.setTableroPorDefecto();
    System.out.println("        LIGHT UP");
    System.out.println("  (Algoritmo Genetico)\n");
    System.out.println("Tablero base:" + tab.toString() +"\n");
    System.out.println("------------------------\n");
    
    //Creacion de una configuracion
    //Configuration config = iniciarConfiguracion(tab);
    
    //Setea una config por default
    Configuration config = new DefaultConfiguration();
    //config.setPreservFittestIndividual(true);
    
    //Setea nuestra FitnessFunction
    LightUpFitnessFunction fitfun = new LightUpFitnessFunction(tab);
    config.setFitnessFunction(fitfun);

    //Creacion del SampleGene
    Gene[] sampleGene = new Gene[49];
    for (int i = 0; i < 49; i++) {
      sampleGene[i] = new IntegerGene(config, 0, 1);
    }

    //Setea el SampleGene creado antes
    IChromosome sampleChromosome = new Chromosome(config, sampleGene);
    config.setSampleChromosome(sampleChromosome);
    

    //Setea el tamaño maximo de poblacion
    config.setPopulationSize(1000);
    
    //Genera una poblacion inicial en base al SampleChromosome (creo)
     Genotype Poblacion = Genotype.randomInitialGenotype(config);     
    
    int generaciones = 500;
    Boolean encontrado = false;
    
    System.out.println("Maxima cantidad de puntos del tablero: " + tab.evalTablero());
    
    for (int i = 0; i < generaciones && !encontrado; i++) { 
      Poblacion.evolve();
      
      IChromosome fittest = Poblacion.getFittestChromosome(); 
      
      if ((config.getFitnessFunction().getFitnessValue(fittest)) >= tab.evalTablero()) {
        encontrado = true;
      }
      
      
      Thread.sleep(500);

      //Setea en un tablero el mejor cromosoma obtenido
      Gene[] chromo = fittest.getGenes();
      for (int j = 0; j < 49; j++) {
        tab.setMejorTablero(j, (Integer) chromo[j].getAllele());  
      }
      System.out.println("Mejor tablero conseguido: " + tab.toString() + "\n");
      System.out.println("Puntuacion: " + config.getFitnessFunction().getFitnessValue(fittest));
    }
  }
}
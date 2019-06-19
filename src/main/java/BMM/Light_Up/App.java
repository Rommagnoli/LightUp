package BMM.Light_Up;
import java.util.Scanner;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 * Clase principal de la aplicacion.
 *
 */
public class App { //IntelliJ

  private static final int MAXFITVALUE = 1400;
  
  /**
   * Metodo para setear un objeto Configuration por default para un algoritmo genético.
   * @param tab Tablero para setear la Fitness Function.
   * @return Objeto tipo Configuracion ya seteado.
   * @throws InvalidConfigurationException Cuando la configuracion es invalida.
   */
  private static Configuration iniciarConfiguracion(Tablero tab) throws InvalidConfigurationException {
    //Setea una config por default
    Configuration config = new DefaultConfiguration();
    config.setPreservFittestIndividual(true);
    
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
    
    //Printea la configuracion
    //System.out.println(config.toString());
    return config;
  }

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
    Configuration config = iniciarConfiguracion(tab);

    //Setea el tamaño maximo de poblacion
    config.setPopulationSize(500);
    
    //Genera una poblacion inicial en base al SampleChromosome (creo)
     Genotype Poblacion = Genotype.randomInitialGenotype(config);     
    
    int generaciones = 200;
    Boolean encontrado = false;
    for (int i = 0; i < generaciones && !encontrado; i++) { 
      //Inicia la evolucion de la poblacion
      Poblacion.evolve();
      
      //Printea la Poblacion
      //System.out.println(Poblacion.toString());             
      
      //Captura el mejor cromosoma y lo guarda en la variable
      IChromosome fittest = Poblacion.getFittestChromosome(); 
      Gene[] chromo = fittest.getGenes();
      System.out.println(config.getFitnessFunction().getFitnessValue(fittest));
      if ((config.getFitnessFunction().getFitnessValue(fittest)) >= 1000) {
        encontrado = true;
      }
      
      //Printea el mejor cromosoma
      /*
      for (int i = 0; i < 49; i++){                           
        System.out.println("Posicion " + i + ": " + chromo[i].toString());
      }
       */
      Thread.sleep(500);
      //Setea en un tablero el mejor cromosoma obtenido
      for (int j = 0; j < 49; j++) {
        tab.setMejorTablero(j, (Integer) chromo[j].getAllele());  
      }
      System.out.println("Mejor tablero conseguido: " + tab.toString() + "\n");
    }
  }
}
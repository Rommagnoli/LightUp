package BMM.Light_Up;
import java.util.Scanner;

import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 * Clase principal de la aplicacion.
 * @author Boaglio Agustin.
 * @author Menendez Josue.
 * @author Magnoli Roman.
 */
public class App { 
  
  /**
   * Metodo "main" donde se ejecuta la app.
   * @throws InterruptedException 
   */
  public static void main(String[] args) throws InvalidConfigurationException {
    Tablero tab = new Tablero();
    tab.setTableroBase();
    System.out.println("Cantidad celdas negras: " + tab.getCantCeldasNegras());
    int celdasBlancas = 49 - tab.getCantCeldasNegras();
    System.out.println("Celdas negras: " + tab.getCantCeldasNegras());
    System.out.println("        LIGHT UP");
    System.out.println("  (Algoritmo Genetico)\n");
    System.out.println("Tablero base:" + tab.toString() +"\n");
    System.out.println("------------------------\n");
    
    //Setea una config por default
    Configuration config = new DefaultConfiguration();
    
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
    config.setPopulationSize(2000);
    
    //Genera una poblacion inicial en base al SampleChromosome (creo)
     Genotype Poblacion = Genotype.randomInitialGenotype(config);     
    
    int generaciones = 100;
    Boolean encontrado = false;

    for (int i = 0; i < generaciones && !encontrado; i++) { 
      Poblacion.evolve();
      
      IChromosome fittest = Poblacion.getFittestChromosome(); 
      
      if ((config.getFitnessFunction().getFitnessValue(fittest)) >= (49 + celdasBlancas)) {
        encontrado = true;
      }
      
      try {
        Thread.sleep(250);
      } catch (Exception e) { }
      
      Tablero mejorTab = new Tablero();
      mejorTab.setTableroBase();
      
      //Setea en un tablero el mejor cromosoma obtenido
      Gene[] chromo = fittest.getGenes();
      for (int j = 0; j < 49; j++) {
        mejorTab.setMejorTablero(j, (Integer) chromo[j].getAllele());  
      }
      System.out.println("Mejor tablero conseguido: " + mejorTab.toString());
      System.out.println("Generacion: " + i);
      System.out.println("Puntuacion: " + config.getFitnessFunction().getFitnessValue(fittest) + "\n");
    }
  }
}
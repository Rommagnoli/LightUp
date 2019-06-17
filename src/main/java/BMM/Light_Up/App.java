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
   * Metodo para setear un objeto Configuration por default para un algoritmo genético.
   * @param tab Tablero para setear la Fitness Function.
   * @return Objeto tipo Configuracion ya seteado.
   * @throws InvalidConfigurationException Cuando la configuracion es invalida.
   */
  private static Configuration iniciarConfiguracion(Tablero tab) throws InvalidConfigurationException {
    Configuration config = new DefaultConfiguration();                  //Setea una config por default
    config.setPreservFittestIndividual(true);
    LightUpFitnessFunction fitfun = new LightUpFitnessFunction(tab);    //Setea nuestra FitnessFunction
    config.setFitnessFunction(fitfun);
    
    Gene[] sampleGene = new Gene[49];                                   //Creacion del SampleGene
    for (int i = 0; i < 49; i++) {
      sampleGene[i] = new IntegerGene(config, 0,1);
    }
    
    IChromosome sampleChromosome = new Chromosome(config, sampleGene);  //Setea el SampleGene creado antes
    config.setSampleChromosome(sampleChromosome);
    //System.out.println(config.toString());                            //Printea la configuracion
    return config;
  }

  /**
   * Metodo que da comienzo al algoritmo genetico.
   * @param tab el tablero con el cual iniciar el algortimo.
   * @param config configuracion para el algoritmo genetico.
   * @return Tablero con el mejor individuo.
   * @throws InvalidConfigurationException Cuando se da una configuracion invalida.
   */
  private static Tablero iniciarAlgoritmoGenetico(Tablero tab, Configuration config) throws InvalidConfigurationException {
    config.setPopulationSize(5000);                         //Setea el tamaño maximo de poblacion
    Genotype Poblacion;
    Poblacion = Genotype.randomInitialGenotype(config);     //Genera una poblacion inicial en base al SampleChromosome (creo)
    Poblacion.evolve();                                     //Inicia la evolucion de la poblacion
    //System.out.println(Poblacion.toString());             //Printea la Poblacion
    IChromosome fittest = Poblacion.getFittestChromosome(); //Captura el mejor cromosoma y lo guarda en la variable
    Gene[] chromo = fittest.getGenes();
    /*
    for (int i = 0; i < 49; i++){                           //Printea el mejor cromosoma
      System.out.println("Posicion " + i + ": " + chromo[i].toString());
    }
    */
    for (int i = 0; i < 49; i++) {                          //Setea en un tablero el mejor cromosoma obtenido
      tab.setMejorTablero(i,(Integer) chromo[i].getAllele());  
    }
    return tab;

  }
  
  /**
   * Motodo para imprimira el menu principal.
   */
  private static void imprimirMenuPrincipal() {
    System.out.println("                   LIGHT UP");
    System.out.println("      Resuelto con un Algoritmo Genético");
    System.out.println(" _______________________________________________");
    System.out.println("|                                               |");
    System.out.println("| 1 - Ver resultado con un tablero por defecto  |");
    System.out.println("| 2 - Setear un nuevo tablero base              |");
    System.out.println("| 3 - Salir                                     |");
    System.out.println("|_______________________________________________|");
  }
  
  /**
   * Metodo "main" donde se ejecuta la app.
   */
  public static void main(String[] args) throws InvalidConfigurationException {
    Scanner scan = new Scanner(System.in);
    int opcion = 0;
    while (opcion != 3) {
      imprimirMenuPrincipal();
      System.out.println("Ingrese una opcion: ");
      opcion = scan.nextInt();
      while (opcion < 1 || opcion > 3) {
        System.out.println("Opcion Invalida, intente nuevamente.");
        opcion = scan.nextInt();
      }
      switch (opcion) {
        case 1:
          Tablero tab = new Tablero();
          tab.setTableroPorDefecto();
          System.out.println("Tablero base: ");
          System.out.println(tab.toString());                     //Printea el tablero base
          System.out.println("------------------------");
          Configuration config = iniciarConfiguracion(tab);       //Creacion de una configuracion
          tab = iniciarAlgoritmoGenetico(tab, config);
          System.out.println("Mejor tablero conseguido: \n" + tab.toString() + "\n");
          Configuration.reset();
          break;
        case 2:             //Falta dejar que sete celdas negras con valor
          tab = new Tablero();
          System.out.println("Insertando celdas negras...\n");
          char salir = '0';
          while (true) {
            Boolean capturado = false;
            Scanner inScanner = new Scanner(System.in);
            System.out.println("Fila: ");
            int fila = inScanner.nextInt();
            while (fila < 0 || fila > 7) {
              System.out.println("Fila fuera de los limites, ingrese una fila válida: ");
              fila = inScanner.nextInt();
            }
            System.out.println("Columna: ");
            int columna = inScanner.nextInt();
            while (columna < 0 || columna > 7) {
              System.out.println("Columna fuera de los limites, ingrese una columna válida: ");
              columna = inScanner.nextInt();
            }
            int tabIndex = Tablero.transformarCoord(fila, columna); 
            System.out.println(fila + ", " + columna + ": " + tabIndex);
            try {
              tab.nuevaCeldaNegra(tabIndex);
            } catch (IllegalArgumentException excepcion) {
              System.out.println("La posicion no es valida, intente con otra.");
              capturado = true;
            }
            if (!capturado) {
              System.out.println("Celda negra ubicada en (" + fila + ", " + columna + ")");
              System.out.println(tab.toString());
            }
            System.out.println("Ingresar otra celda? (S/N)");
            salir = inScanner.next().charAt(0);
            if (salir == 'n' || salir == 'N') {
              config = iniciarConfiguracion(tab);
              tab = iniciarAlgoritmoGenetico(tab, config);
              Configuration.reset();
              System.out.println("Mejor tablero conseguido: \n" + tab.toString() + "\n");
              break;
            }
          }
        case 3:
          break;
      }
    }
  }
}
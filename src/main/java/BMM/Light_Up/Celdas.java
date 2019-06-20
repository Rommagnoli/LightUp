package BMM.Light_Up;

/**
 * Clase que representa las celdas que son contenidas en el tablero.
 *
 * @author Boaglio Agustin.
 * @author Menendez Josue.
 * @author Magnoli Roman.
 */
public class Celdas {

    /**
     * Contiene el valor que indica la cantidad de lamparas que debe haber
     * alrededor de una casilla negra.
     */
    private Integer valorCelda;

    /**
     * Informa si una casilla es negra.
     */
    private boolean esNegra;

    /**
     * Informa si en una celda hay una lampara.
     */
    private boolean esLampara;

    /**
     * Informa si una casilla esta iluminada por una lampara.
     */
    private boolean esLuz;

    /**
     * Constructor que setea una celda completamente vacia.
     */
    public Celdas() {
        valorCelda = null;
        esNegra = false;
        esLuz = false;
        esLampara = false;
    }

    /**
     * Setea una celda vacia como una celda negra.
     */
    public void setCeldaNegra() {
        this.esNegra = true;
    }

    /**
     * Setea una celda negra con un valor especifico.
     *
     * @param valor es el que indica la cantidad de lamparas que tiene que haber
     * a su alrededor.
     * @throws IllegalArgumentException si el valor es inferior a cero o mayor
     * que 4.
     */
    public void setCeldaNegra(int valor) throws IllegalArgumentException {
        if ((valor < 0) || (valor > 4)) {
            throw new IllegalArgumentException("El valor de una celda negra debe ser mayor o igual que cero y menor o igual que 4");
        }
        this.valorCelda = valor;
        this.esNegra = true;
    }

    /**
     * Setea en una celda, una lampara.
     */
    public void setLampara() {
        this.esLampara = true;
    }

    /**
     * Iluminia una celda si esta es alcanzada por una lampara.
     */
    public void setCeldaLuz() {
        this.esLuz = true;
    }

    /**
     * Informa el valor de una celda negra.
     *
     * @return valorCelda con el numero de lamparas que deben encontrarse
     * alrededor de la celda.
     */
    public Integer getValorCelda() {
        return this.valorCelda;
    }

    /**
     * Informa si una celda es negra.
     *
     * @return true si la celda es negra.
     */
    public boolean esNegra() {
        return this.esNegra;
    }

    /**
     * Funcion que dice si una celda es negra y con valor asignado.
     *
     * @return true si es negra con numero, false si no lo es.
     */
    public boolean esNegraValor() {
        return (this.esNegra && this.valorCelda != null);
    }

    /**
     * Informa si una celda esta iluminada.
     *
     * @return true si la celda es alcanzada por una lampara.
     */
    public boolean esLuz() {
        return this.esLuz;
    }

    /**
     * Informa si una celda contiene una lampra.
     *
     * @return true si la celda tiene una lampara.
     */
    public boolean esLampara() {
        return this.esLampara;
    }

    /**
     * Muestra por pantalla una celda.
     */
    public String toString() {
        String celda = " ";
        if (this.esLampara) {
            celda = "@";
        }
        if (this.esLuz) {
            celda = "L";
        }
        if (this.esNegra) {
            celda = "N";
        }
        if (this.esNegraValor()) {
            int valor = this.getValorCelda();
            celda = String.valueOf(valor);
        }
        return celda;
    }

    /**
     * Funcion que dice si dos celdas son iguales.
     * @param celda representa una celda.
     * @return true si las celdas son iguales, en caso contrario false.
     */
    public Boolean equals(Celdas celda) {
        Boolean negra, negra2, luz, luz2, lamp, lamp2, resVal;
        resVal = true;
        Integer val, val2;
        negra = this.esNegra();
        negra2 = celda.esNegra();
        luz = this.esLuz();
        luz2 = celda.esLuz();
        lamp = this.esLampara();
        lamp2 = celda.esLampara();
        val = this.getValorCelda();
        val2 = celda.getValorCelda();
        if (val == null && val2 == null) {
            resVal = true;
        } else if (val != val2) {
            resVal = false;
        }
        return (resVal && negra.equals(negra2) && luz.equals(luz2) && lamp.equals(lamp2));
    }
}

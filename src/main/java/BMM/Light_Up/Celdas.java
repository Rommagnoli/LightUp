package BMM.Light_Up;

public class Celdas {

    private Integer valorCelda;
    private boolean esNegra;
    private boolean esLuz;

    public Celdas(){
        valorCelda = null;
        esNegra = false;
        esLuz = false;
    }

    public void setCeldaNegra(){
        this.valorCelda = null;
        this.esNegra = true;
        this.esLuz = false;
    }

    public void setCeldaNegra(int valor){
        this.valorCelda = valor;
        this.esNegra = true;
        this.esLuz = false;
    }

    public void setCeldaLuz(){
        this.valorCelda = null;
        this.esNegra = false;
        this.esLuz = true;
    }

    public Integer getValorCelda(){
        return this.valorCelda;
    }

    public boolean esNegra(){
        return this.esNegra;
    }

    public boolean esLuz(){
        return this.esLuz;
    }
}

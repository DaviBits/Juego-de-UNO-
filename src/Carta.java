public class Carta {
    private String color;
    private int numero;
    private String funcion;

    public Carta(String color, int numero, String funcion){
        this.color=color;
        this.numero=numero;
        this.funcion=funcion;
    }

    public String getColor(){
        return color;
    }

    public int getNumero(){
        return numero;
    }

    public String getFuncion(){return funcion;}

    public void setColor(String color){
        this.color=color;
    }

    public void setNumero(int numero){
        this.numero=numero;
    }

    public void setFuncion(String funcion){
        this.funcion=funcion;
    }

}

public class Carta {
    private String color;
    private int numero;
    private String funcion;
    private boolean esEspecial;

    public Carta(String color, int numero, String funcion){
        this.color=color;
        this.numero=numero;
        this.funcion=funcion;
        esEspecial(funcion);
    }

    public void esEspecial(String funcion){
        if (funcion.equals(" ")){
            this.esEspecial=false;
        }else{
            this.esEspecial=true;
        }

    }

    public boolean noTieneValor(){
        if(esEspecial){
            return true;
        }
        return false;
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

    public void imprimirCarta(){
        if(!esEspecial) {
            System.out.println("+-+-+");
            System.out.println("| " + numero + " |");
            System.out.println("|" + color + "|");
            System.out.println("|   |");
            System.out.println("+-+-+");
        }else{
            System.out.println("+-+-+");
            System.out.println("|   |");
            System.out.println("|" +color+"|");
            System.out.println("|"+funcion+"|");
            System.out.println("+-+-+");
        }
    }

    public String toString(){
        if(!esEspecial){
            return "["+ color+ numero+ funcion+ "]";
        }
        return "["+ color+ funcion+ "]";
    }

}

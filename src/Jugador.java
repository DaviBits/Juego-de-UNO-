public class Jugador {
    private String nombre;
    private int contadorDeCartas;

    public Jugador(String nombre, int numeroDeCartas){
        this.contadorDeCartas=numeroDeCartas;
        this.nombre=nombre;
    }

    public void sumarCartas(int numero){
        this.contadorDeCartas++;
    }

    public void restarCartas(int numero){
        this.contadorDeCartas-=numero;
    }

    public int getNumeroCartas(){
        return contadorDeCartas;
    }

    public String getNombre(){
        return nombre;
    }
}

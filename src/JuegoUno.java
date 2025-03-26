import java.util.ArrayList;
import java.util.Scanner;

public class JuegoUno {
    private Jugador jugador1;
    private Jugador jugador2;
    private ArrayList <Carta> mazoJuego;
    private Mazo mazoUno;
    private ArrayList <Carta> cartasJ1;
    private ArrayList <Carta> cartasJ2;

    public JuegoUno(){
        definirJugadores();
        mazoUno= new Mazo();
        ArrayList <Carta> mazoJuego= new ArrayList<>();
        mazoUno.mezclarCartas();
        definirMazo();
        cartasJ1= new ArrayList<>();
        cartasJ2=new ArrayList<>();
        repartirCartas("J1");
        repartirCartas("J2");
        imprimirManos("J1");
        imprimirManos("J2");



    }

    public void definirMazo(){
        mazoJuego= mazoUno.getMazoUno();
        System.out.println("Cartas en el mazo:");
        System.out.println(mazoJuego);
    }

    public void definirJugadores(){
        Scanner scan = new Scanner(System.in);
        System.out.println("ingrese el nombre del Jugador 1:");
        String nombreJ1=scan.nextLine();
        jugador1= new Jugador(nombreJ1, 0);
        System.out.println("ingrese el nombre del Jugador 2:");
        String nombreJ2= scan.nextLine();
        jugador2= new Jugador(nombreJ2, 0);
        scan.close();
    }

    public void repartirCartas(String jugador){
        //Collections.shuffle(mazoUno);
        if(jugador.equals("J1")){
            for(int i=0; i<7; i++){
                cartasJ1.add(mazoJuego.get(0));
                jugador1.sumarCartas(1);
                mazoJuego.remove(0);
            }
        }else if(jugador.equals("J2")){
            for(int i=0; i<7; i++){
                cartasJ2.add(mazoJuego.get(0));
                jugador2.sumarCartas(1);
                mazoJuego.remove(0);
            }
        }
    }

    public void imprimirManos(String jugador){
        if(jugador.equals("J1")){
            System.out.println("CARTAS DE: "+ jugador1.getNombre());
            System.out.println(cartasJ1);
        }else if(jugador.equals("J2")) {
            System.out.println("CARTAS DE: "+ jugador2.getNombre());
            System.out.println(cartasJ2);
        }
    }

}

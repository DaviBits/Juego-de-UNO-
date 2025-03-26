import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JuegoUno {

    private Jugador jugador1;
    private Jugador jugador2;
    private ArrayList <Carta> mazoJuego;
    private Mazo mazoUno;
    private ArrayList <Carta> cartasJ1;
    private ArrayList <Carta> cartasJ2;
    private String primerTurno;
    private Carta cartaEnTablero;
    private String jugadorEnTurno;

    public JuegoUno(){
        definirJugadores();
        mazoUno= new Mazo();
        ArrayList <Carta> mazoJuego= new ArrayList<>();
        mazoUno.mezclarCartas();
        definirMazo();
        definirPrimerTurno();
        jugadorEnTurno="";
        cartasJ1= new ArrayList<>();
        cartasJ2=new ArrayList<>();
        repartirCartas("J1");
        repartirCartas("J2");
        imprimirManos("J1");
        imprimirManos("J2");
        jugar();


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
        //scan.close();
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

    public void definirPrimerTurno(){
        Random rnd = new Random();
        Carta cartaTurno1Jugador1= mazoJuego.get(rnd.nextInt(mazoJuego.size()));
        Carta cartaTurno1Jugador2= mazoJuego.get(rnd.nextInt(mazoJuego.size()));
        if(cartaTurno1Jugador1.noTieneValor()){
            definirPrimerTurno();
            return;
        }else if(cartaTurno1Jugador2.noTieneValor()){
            definirPrimerTurno();
            return;
        }
        System.out.println("LA CARTA ASIGNADA A "+jugador1.getNombre()+ " ES: ");
        System.out.println(cartaTurno1Jugador1);
        System.out.println("LA CARTA ASIGNADA A "+jugador2.getNombre()+ " ES: ");
        System.out.println(cartaTurno1Jugador2);
        if(cartaTurno1Jugador1.getNumero()>cartaTurno1Jugador2.getNumero()){
            System.out.println("EL PRIMER JUGADOR EN PONER VA A SER: "+ jugador1.getNombre());
            primerTurno="J1";
        }else if(cartaTurno1Jugador1.getNumero()<cartaTurno1Jugador2.getNumero()){
            System.out.println("EL PRIMER JUGADOR EN PONER VA A SER: "+ jugador2.getNombre());
            primerTurno="J2";
        }else{
            System.out.println("EMPATE!!! SE VUELVEN A SACAR CARTAS AL AZAR");
        }
    }

    public void jugar(){
        int indicePrimeraCarta;
        Random rnd = new Random();
        indicePrimeraCarta= rnd.nextInt(mazoJuego.size()-1);
        cartaEnTablero= mazoJuego.get(indicePrimeraCarta);
        mazoJuego.remove(indicePrimeraCarta);
        do{
            if(jugador1.getNumeroCartas()>0&&jugador2.getNumeroCartas()>0){
                System.out.println("ULTIMA CARTA PUESTA: ");
                System.out.println(cartaEnTablero);
                this.jugadorEnTurno="J1";
                lanzarJ1();
            }
            if(jugador1.getNumeroCartas()>0&&jugador2.getNumeroCartas()>0){
                System.out.println("ULTIMA CARTA PUESTA: ");
                System.out.println(cartaEnTablero);
                this.jugadorEnTurno="J2";
                lanzarJ2();
            }
        }while (jugador1.getNumeroCartas()>0&&jugador2.getNumeroCartas()>0);
    }

    public void lanzarJ1(){
        Scanner scan = new Scanner(System.in);
        System.out.println("TURNO DE: "+jugador1.getNombre());
        System.out.println("INGRESE QUE CARTA DE SU MASO DESEA TIRAR: *presiona 100 si quieres saltar turno*");
        System.out.println(cartasJ1);
        int indicedeCartaPuesta= scan.nextInt()-1;
        scan.nextLine();
        if(indicedeCartaPuesta==99){
            return;
        }
        if(verificarUltimaCarta(cartasJ1.get(indicedeCartaPuesta))){
            System.out.println("ES UNO?(si/no)");
            String esUno = scan.nextLine();
            esUno=esUno.toLowerCase();
            if(esUno.equals("si")){
                if(!verificarSiEsUno("J1")){
                    tomarCartas(jugadorEnTurno, 4);
                }
            }
            cartaEnTablero=cartasJ1.get(indicedeCartaPuesta);
            cartasJ1.remove(indicedeCartaPuesta);
            jugador1.restarCartas(1);
        }else{
            System.out.println("ESA CARTA NO SE PUEDE PONER ");
            lanzarJ1();
        }

       // scan.close();
    }

    public void lanzarJ2(){
        Scanner scan = new Scanner(System.in);
        System.out.println("TURNO DE: "+jugador2.getNombre());
        System.out.println("INGRESE QUE CARTA DE SU MASO DESEA TIRAR: *presiona 100 si quieres saltar turno*");
        System.out.println(cartasJ2);
        int indicedeCartaPuesta= scan.nextInt()-1;
        scan.nextLine();
        if(indicedeCartaPuesta==99){
            return;
        }
        if(verificarUltimaCarta(cartasJ2.get(indicedeCartaPuesta))){
            System.out.println("ES UNO?(si/no)");
            String esUno = scan.nextLine();
           esUno= esUno.toLowerCase();
            if(esUno.equals("si")){
                if(!verificarSiEsUno("J2")){
                    tomarCartas(jugadorEnTurno, 4);
                }
            }
            cartaEnTablero=cartasJ2.get(indicedeCartaPuesta);
            cartasJ2.remove(indicedeCartaPuesta);
            jugador2.restarCartas(1);
        }else{
            System.out.println("ESA CARTA NO SE PUEDE PONER ");
            lanzarJ2();
        }

       // scan.close();
    }

    public void tomarCartas(String JugadorEnTurno, int NumeroDeCartas){
        if(jugadorEnTurno.equals("J1")){
            for(int i=0; i<NumeroDeCartas; i++){
                cartasJ1.add(mazoJuego.get(0));
                mazoJuego.remove(0);
            }
        }else if(jugadorEnTurno.equals("J2")) {
            for (int i = 0; i < NumeroDeCartas; i++) {
                cartasJ2.add(mazoJuego.get(0));
                mazoJuego.remove(0);
            }
        }
    }

    public boolean verificarSiEsUno(String jugadorEnTurno) {
        if (jugadorEnTurno.equals("J1")) {
            if (jugador1.getNumeroCartas() == 1) {
                return true;
            }
        } else if (jugadorEnTurno.equals("J2")) {
            if (jugador2.getNumeroCartas() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarUltimaCarta(Carta cartaPuesta){
        if(cartaPuesta.getNumero()==cartaEnTablero.getNumero()){
            return true;
        }else if(cartaPuesta.getColor()==cartaEnTablero.getColor()){
            return true;
        }else if(cartaPuesta.noTieneValor()&&cartaEnTablero.noTieneValor()&&cartaPuesta.getFuncion()==cartaEnTablero.getFuncion()){
            return true;
        }
        return false;
    }



}

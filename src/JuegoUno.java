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
    private int masCuatroAcumulados;

    public JuegoUno(){
        definirJugadores();
        mazoUno= new Mazo();
        ArrayList <Carta> mazoJuego= new ArrayList<>();
        masCuatroAcumulados=0;
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
       // System.out.println("Cartas en el mazo:");
        //System.out.println(mazoJuego);
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

    public Carta seleccionarPrimeraCarta(){
        Carta cartaIncial;
        Random rnd = new Random();
        int indicePrimeraCarta= rnd.nextInt(mazoJuego.size()-1);
        if(mazoJuego.get(indicePrimeraCarta).saberSiEsEspecial()){
            seleccionarPrimeraCarta();
        }
            cartaIncial=mazoJuego.get(indicePrimeraCarta);
            mazoJuego.remove(cartaIncial);

        return cartaIncial;
    }

    public void jugar(){

        int indicePrimeraCarta;
        Random rnd = new Random();
        indicePrimeraCarta= rnd.nextInt(mazoJuego.size()-1);


        cartaEnTablero=mazoJuego.get(indicePrimeraCarta);
        if(cartaEnTablero.getFuncion()!=" "){
            jugar();
            return;
        }

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
        System.out.println("JUEGO ACABADO!");
        System.out.println("EL JUGADOR "+jugador1.getNombre()+" TIENE "+jugador1.getNumeroCartas()+" CARTAS");
        System.out.println("EL JUGADOR "+jugador2.getNombre()+" TIENE "+jugador2.getNumeroCartas()+" CARTAS");
        if(jugador1.getNumeroCartas()==0){
            System.out.println("EL GANADOR ES "+jugador1.getNombre());
        }else{
            System.out.println("EL GANADOR ES "+jugador2.getNombre());
        }
    }

    public void lanzarJ1(){
        Scanner scan = new Scanner(System.in);
        if(cartaEnTablero.getFuncion().equals("🌈4️⃣")){
            System.out.println("La ultima carta em el tablero es un +4");
            if(verificarMasCuatro(jugadorEnTurno)){
                System.out.println("Tienes un +4, quieres poner o comer(poner/comer)?");
                String desicion = scan.nextLine();
                desicion= desicion.toLowerCase();
                if(desicion.equals("poner")){
                    for(int i=0; i<cartasJ1.size(); i++){
                      if(cartasJ1.get(i).getFuncion().equals("🌈4️⃣")){
                          if(this.masCuatroAcumulados==0){
                              this.masCuatroAcumulados+=8;
                          }else{
                              this.masCuatroAcumulados+=4;
                          }
                          System.out.println("CAMBIO DE COLOR!!!");
                          System.out.println("Escoja un color: rojo, naranja, verde o azul: ");
                          String color= scan.nextLine();
                          color=color.toLowerCase();
                          cambiarColorCartaTablero(color);
                         cartasJ1.remove(i);
                      }
                    }
                } else if (desicion.equals("comer")) {
                    if(this.masCuatroAcumulados==0){
                        this.masCuatroAcumulados+=4;
                    }

                    tomarCartas(jugadorEnTurno, masCuatroAcumulados);
                    this.masCuatroAcumulados=0;
                    jugador1.sumarCartas(masCuatroAcumulados);
                }
            }
        }

        int numeroCartasJ1= cartasJ1.size();
        System.out.println("TURNO DE: "+jugador1.getNombre());
        System.out.println("INGRESE QUE CARTA DE SU MASO DESEA TIRAR (1-"+numeroCartasJ1+"): *presiona 0 SI QUIERES SALTAR TURNO O 100 SI QUIERES ROBAR CARTA*");

        System.out.println(cartasJ1);
        int indicedeCartaPuesta= scan.nextInt()-1;
        scan.nextLine();
        if(indicedeCartaPuesta>=cartasJ1.size()&&indicedeCartaPuesta<99){
            System.out.println("no tienes tantas cartas");
            lanzarJ1();
        }
        if(indicedeCartaPuesta==-1){
            return;
        }
        if(indicedeCartaPuesta==99){
            tomarCartas(jugadorEnTurno, 1);
            System.out.println("Tomaste la carta: "+cartasJ1.get(cartasJ1.size()-1));
            jugador1.sumarCartas(1);
            return;
        }
        if(verificarUltimaCarta(cartasJ1.get(indicedeCartaPuesta))){
            System.out.println("ES UNO?(si/no)");
            String esUno = scan.nextLine();
            esUno=esUno.toLowerCase();
            if(esUno.equals("si")){
                if(!verificarSiEsUno("J1")){
                    System.out.println("NO ERA UNO!!");
                    tomarCartas(jugadorEnTurno, 4);
                    jugador1.sumarCartas(4);
                }
            }

            if(cartasJ1.get(indicedeCartaPuesta).getFuncion().equals(" 2️⃣")){
                System.out.println("EL JUGADOR 2 TOMA 2 CARTAS!!");
                tomarCartas("J2", 2);
                jugador2.sumarCartas(2);
            }
            cartaEnTablero=cartasJ1.get(indicedeCartaPuesta);
            cartasJ1.remove(indicedeCartaPuesta);
            jugador1.restarCartas(1);
            if(cartaEnTablero.getFuncion().equals(" 🚫")){
                System.out.println("SE SALTO EL TURNO DE "+jugador2.getNombre()+", "+jugador1.getNombre()+" PONE DE NUEVO");
                lanzarJ1();
            }
            if(cartaEnTablero.getFuncion().equals(" 🌈")||cartaEnTablero.getFuncion().equals("🌈4️⃣")){
                System.out.println("CAMBIO DE COLOR!!");
                System.out.println("ingrese el color que quiere: rojo, naranja, verde o azul");
                String color = scan.nextLine();
                color=color.toLowerCase();
                cambiarColorCartaTablero(color);
            }

            if(cartaEnTablero.getFuncion().equals(" 🔄")){
                System.out.println("GIRO DE TURNO");

            }
        }else{
            System.out.println("ESA CARTA NO SE PUEDE PONER ");
            lanzarJ1();
        }

       // scan.close();
    }

    public void lanzarJ2(){
        Scanner scan = new Scanner(System.in);
        if(cartaEnTablero.getFuncion().equals("🌈4️⃣")){
            System.out.println("La ultima carta em el tablero es un +4");
            if(verificarMasCuatro(jugadorEnTurno)){
                System.out.println("Tienes un +4, quieres poner o comer(poner/comer)?");
                String desicion = scan.nextLine();
                desicion= desicion.toLowerCase();
                if(desicion.equals("poner")){
                    for(int i=0; i<cartasJ2.size(); i++){
                        if(cartasJ2.get(i).getFuncion().equals("🌈4️⃣")){
                            if(this.masCuatroAcumulados==0){
                                this.masCuatroAcumulados+=8;
                            }else{
                                this.masCuatroAcumulados+=4;
                            }
                            System.out.println("CAMBIO DE COLOR!!!");
                            System.out.println("Escoja un color: rojo, naranja, verde o azul: ");
                            String color= scan.nextLine();
                            color=color.toLowerCase();
                            cambiarColorCartaTablero(color);
                            cartasJ2.remove(i);
                        }
                    }
                } else if (desicion.equals("comer")) {
                    if(this.masCuatroAcumulados==0){
                        this.masCuatroAcumulados+=4;
                    }

                    tomarCartas(jugadorEnTurno, masCuatroAcumulados);
                    this.masCuatroAcumulados=0;
                    jugador2.sumarCartas(masCuatroAcumulados);
                }

            }

        }
        int numeroCartasJ2= cartasJ2.size();

        System.out.println("TURNO DE: "+jugador2.getNombre());
        System.out.println("INGRESE QUE CARTA DE SU MASO DESEA TIRAR(1-"+numeroCartasJ2+"): *presiona 0 SI QUIERES SALTAR TURNO O 100 SI QUIERES ROBAR CARTA*");

        System.out.println(cartasJ2);
        int indicedeCartaPuesta= scan.nextInt()-1;
        scan.nextLine();
        if(indicedeCartaPuesta>=cartasJ2.size()&&indicedeCartaPuesta<99){
            System.out.println("no tienes tantas cartas");
            lanzarJ2();
        }
        if(indicedeCartaPuesta==-1){
            return;
        }
        if(indicedeCartaPuesta==99){
            tomarCartas(jugadorEnTurno, 1);
            System.out.println("Tomaste la carta: "+cartasJ2.get(cartasJ2.size()-1));
            jugador2.sumarCartas(1);
            return;
        }
        if(verificarUltimaCarta(cartasJ2.get(indicedeCartaPuesta))){
            System.out.println("ES UNO?(si/no)");
            String esUno = scan.nextLine();
           esUno= esUno.toLowerCase();
            if(esUno.equals("si")){
                if(!verificarSiEsUno("J2")){
                    System.out.println("NO ERA UNO!!");
                    tomarCartas(jugadorEnTurno, 4);
                    jugador2.sumarCartas(4);
                }
            }


            if(cartasJ2.get(indicedeCartaPuesta).getFuncion().equals(" 2️⃣")){
                System.out.println("EL JUGADOR 1 TOMA 2 CARTAS!!");
                tomarCartas("J1", 2);
                jugador1.sumarCartas(2);
            }
            cartaEnTablero=cartasJ2.get(indicedeCartaPuesta);
            cartasJ2.remove(indicedeCartaPuesta);
            jugador2.restarCartas(1);
            if(cartaEnTablero.getFuncion().equals(" 🚫")){
                System.out.println("SE SALTO EL TURNO DE "+jugador1.getNombre()+", "+jugador2.getNombre()+" PONE DE NUEVO");
                lanzarJ2();
            }
            if(cartaEnTablero.getFuncion().equals(" 🔄")){
                System.out.println("GIRO DE TURNO");

            }
            if(cartaEnTablero.getFuncion().equals(" 🌈")||cartaEnTablero.getFuncion().equals("🌈4️⃣")){
                System.out.println("CAMBIO DE COLOR!!");
                System.out.println("ingrese el color que quiere: rojo, naranja, verde o azul");
                String color = scan.nextLine();
                color=color.toLowerCase();
                cambiarColorCartaTablero(color);
            }
        }else{
            System.out.println("ESA CARTA NO SE PUEDE PONER ");
            lanzarJ2();
        }

       // scan.close();
    }

    public void cambiarColorCartaTablero(String color){
        Scanner scan = new Scanner(System.in);
        switch (color){
            case "rojo":
                cartaEnTablero.setColor(" 🔴");
                break;
            case "naranja":
                cartaEnTablero.setColor(" 🟠");
                break;
            case "verde":
                cartaEnTablero.setColor(" 🟢");
                break;
            case "azul":
                cartaEnTablero.setColor(" 🔵");
                break;
            default:
                System.out.println("COLOR INVALIDO!!!");
                System.out.println("Escoja un color: rojo, naranja, verde o azul: ");
                String colorCarta= scan. nextLine();
                cambiarColorCartaTablero(colorCarta);
                break;
        }
    }

    public void tomarCartas(String JugadorEnTurno, int NumeroDeCartas){
        if(mazoJuego.size()==0){
            definirMazo();
        }
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
                System.out.println("UNO!!!");
                return true;
            }
        } else if (jugadorEnTurno.equals("J2")) {
            if (jugador2.getNumeroCartas() == 1) {
                System.out.println("UNO!!!");
                return true;
            }
        }
        return false;
    }

    public boolean verificarUltimaCarta(Carta cartaPuesta){
        if(cartaPuesta.getNumero()==cartaEnTablero.getNumero()){
            return true;
        }else if(cartaPuesta.getColor().equals(cartaEnTablero.getColor())){
            return true;
        }else if(cartaPuesta.noTieneValor()&&cartaEnTablero.noTieneValor()&&cartaPuesta.getFuncion().equals(cartaEnTablero.getFuncion())){
            return true;
        }else if(cartaPuesta.getColor().equals(" ⚫")){
            return true;
        }
        return false;
    }

    public boolean verificarMasCuatro(String jugador){
        switch (jugador){
            case "J1":
                for(int i=0; i<cartasJ1.size(); i++){
                    if(cartasJ1.get(i).getFuncion().equals("🌈4️⃣")){
                        return true;
                    }
                }
                break;
            case "J2":
                for(int i=0; i<cartasJ2.size(); i++){
                    if(cartasJ2.get(i).getFuncion().equals("🌈4️⃣")){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

}

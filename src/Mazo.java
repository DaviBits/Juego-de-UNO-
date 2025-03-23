import java.util.ArrayList;

public class Mazo {

    private ArrayList<Carta> mazoUno;

    public Mazo(){
        mazoUno= new ArrayList<>();
        llenar();
        mostrarCartas();
    }

    public void llenar(){
        String [] colores= {" 🔴", " 🟠", " 🟢", " 🔵"};
        String [] especiales={" 🚫", " 🔄", "➕2️⃣"};
        for(int i=0; i<4; i++){
            for(int j=0; j<=9 ;j++){
                Carta cartaNueva= new Carta(colores[i], j, " ");
                mazoUno.add(cartaNueva);
            }
            for(int k=0; k<3; k++){
                Carta cartaNueva=new Carta(colores[i], 0, especiales[k]);
                mazoUno.add(cartaNueva);
            }
            Carta carta = new Carta("⚫", 0, "🌈" );
            mazoUno.add(carta);
        }

        for(int i=0; i<4; i++){
            for(int j=1; j<=9 ;j++){
                Carta cartaNueva= new Carta(colores[i], j, " ");
                mazoUno.add(cartaNueva);
            }
            for(int k=0; k<3; k++){
                Carta cartaNueva=new Carta(colores[i], 0, especiales[k]);
                mazoUno.add(cartaNueva);
            }
            Carta carta = new Carta("⚫", 0, "🌈+4" );
            mazoUno.add(carta);
        }

    }

    public void mostrarCartas(){
        for(int i=0; i<mazoUno.size(); i++){
            Carta carta= mazoUno.get(i);
            carta.imprimirCarta();
        }
    }

}

import java.util.ArrayList;

public class Mazo {

    private ArrayList<Carta> mazoUno;

    public Mazo(){
        mazoUno= new ArrayList<>();
        llenar();
        mostrarCartas();
    }

    public void llenar(){
        String [] colores= {"rojo", "naranja", "verde", "amarillo"};
        String [] especiales={"saltar", "girar", "+2"};
        for(int i=0; i<4; i++){
            for(int j=0; j<=9 ;j++){
                Carta cartaNueva= new Carta(colores[i], j, " ");
                mazoUno.add(cartaNueva);
            }
            for(int k=0; k<3; k++){
                Carta cartaNueva=new Carta(colores[i], 0, especiales[k]);
                mazoUno.add(cartaNueva);
            }
        }
        for(int i=1; i<4; i++){
            for(int j=0; j<=9 ;j++){
                Carta cartaNueva= new Carta(colores[i], j, " ");
                mazoUno.add(cartaNueva);
            }
            for(int k=0; k<3; k++){
                Carta cartaNueva=new Carta(colores[i], 0, especiales[k]);
                mazoUno.add(cartaNueva);
            }
        }

    }

    public void mostrarCartas(){
        for(int i=0; i<mazoUno.size(); i++){
            Carta carta= mazoUno.get(i);
            carta.imprimirCarta();
        }
    }

}

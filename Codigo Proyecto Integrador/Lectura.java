import java.util.Date;

public class Lectura extends Dispositivo {
    private static int lecturaID;
    private static float voltaje;
    private static float consumo;
    private static Date fecha_hora;
    private static int potencia;

    public static void registrarLectura(int lecturaID, int dispositivoID, float voltaje, float consumo, Date fecha_hora, int potencia){
        
    }

    public static int traducirWatts(float consumo){
        return 0;
    }

    public static float calcularWatts(){
        return 0.0f;
    }
}

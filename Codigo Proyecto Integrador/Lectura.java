import java.util.Date;

public class Lectura extends Dispositivo {
    /*
    NOTA:
        Todos los valores de las variables son generados de manera aleatoria, para simular la lectura de un dispositivo real
    */
    private static int lecturaID;
    protected static float voltaje = 10.5f;
    protected static float consumo;
    protected static Date fecha_hora;
    private static int potencia;
    private static int dispositivo;

    public int getLecturaID(){
        return lecturaID;
    }

    public static void registrarLectura(int lecturaID, int dispositivoID, float voltaje, float consumo, Date fecha_hora, int potencia){
        Lectura.lecturaID = lecturaID;
        Lectura.voltaje = voltaje;
        Lectura.consumo = consumo;
        Lectura.fecha_hora = fecha_hora;
        Lectura.potencia = potencia;
        Lectura.dispositivo = Dispositivo.getDispositivoID();
    }

    public static int traducirWatts(float consumo){
        return (int) (consumo * 100);
    }

    public static float calcularWatts(){
        voltaje = (float) (100 + Math.random() * 50);
        consumo = (float) (1 + Math.random() * 5);
        potencia = (int) (voltaje * consumo);
        return potencia;
    }
}

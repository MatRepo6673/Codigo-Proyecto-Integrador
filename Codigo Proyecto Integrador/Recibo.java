import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Recibo {
    private static int reciboID;
    private static float Kilowats;
    private static boolean Estado;
    protected static Date fecha_limite, inicio, fin;

    public static void generarRecibo() {
        Recibo.reciboID = (int) (Math.random() * 1000) + 1;

        new Pagos().ActualizarPago(
            Recibo.reciboID, new Date(),
            (int) (Math.random() * 10000) + 1,
            (int) (Math.random() * 5000) + 1
        );

        Kilowats = new Propiedad().calcularConsumoTotal();
        Estado = new Propiedad().verificarAdeudos();
        fecha_limite = new Date(System.currentTimeMillis() + ThreadLocalRandom.current().nextLong(10_000_000_000L));
        inicio = new Date();
        fin = new Date(ThreadLocalRandom.current().nextLong(inicio.getTime(), fecha_limite.getTime()));

    }

    public static void leerLectura() {

    }

    public static boolean actualizarEstado() {
        return false;
    }
}

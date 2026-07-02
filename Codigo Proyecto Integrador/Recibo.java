import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Recibo {
    private static int reciboID;
    private static float Kilowats;
    private static boolean Estado;
    private static Date fecha_limite, inicio, fin;

    public static void generarRecibo() {
        Recibo.reciboID = (int) (Math.random() * 1000) + 1;
        new Pagos().ActualizarPago(Recibo.reciboID, new Date(), (int) (Math.random() * 10000) + 1,
                (int) (Math.random() * 5000) + 1);
        Kilowats = new Propiedad().calcularConsumoTotal();
        Estado = new Propiedad().verificarAdeudos();
        inicio = new Date();
        fin = new Date(System.currentTimeMillis() + ThreadLocalRandom.current().nextLong(10_000_000_000L));
        fecha_limite = new Date(ThreadLocalRandom.current().nextLong(inicio.getTime(), fin.getTime()));
        try{
            new Main().limpiarEntrada();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("================================");
        System.out.println("           RECIBO CFE           ");
        System.out.println("================================\n");
        System.out.println("FECHA DE HOY: " + Pagos.fecha_recibo);
        System.out.println("FECHA DE INICIO: " + inicio);
        System.out.println("FECHA DE FIN: " + fin);
        System.out.println("FECHA LIMITE: " + fecha_limite);
        System.out.println("KILOWATS: " + Kilowats);
        System.out.println("ESTADO: " + (Estado ? "ADEUDO" : "PAGADO"));
        
    }

    public static void leerLectura() {

    }

    public static boolean actualizarEstado() {
        return false;
    }
}

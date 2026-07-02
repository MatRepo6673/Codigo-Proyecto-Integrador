import java.util.Date;

public class Pagos extends Recibo{
    private static int pagoID;
    protected static Date fecha_recibo = new Date();
    private static int restante;
    private static int monto;

    public static void ActualizarPago(int pagoID, Date fecha_recibo, int restante, int monto){
        Pagos.pagoID = pagoID;
        Pagos.fecha_recibo = fecha_recibo;
        Pagos.restante = restante;
        Pagos.monto = monto;
    }
}

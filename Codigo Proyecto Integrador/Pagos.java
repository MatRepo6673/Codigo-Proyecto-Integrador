import java.util.Date;

public class Pagos extends Recibo{
    private static int pagoID;
    protected static Date fecha_recibo = new Date();
    private static int restante;
    private static int monto;

    public int getPagoID(){
        return pagoID;
    }

    public int getRestante(){
        return restante;
    }

    public void setRestante(int restante){
        Pagos.restante = restante;
    }

    public int getMonto(){
        return monto;
    }

    public void setMonto(int monto){
        Pagos.monto = monto;
    }

    public Pagos(int pagoID, ){

    }



    public static void ActualizarPago(int pagoID, Date fecha_recibo, int restante, int monto){
        Pagos.pagoID = pagoID;
        Pagos.fecha_recibo = fecha_recibo;
        Pagos.restante = restante;
        Pagos.monto = monto;
    }
}

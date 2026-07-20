public class Propiedad{
    private static int propiedadID;
    private static String tipo_tarifa;
    private static String direccion;

    public static float calcularConsumoTotal() {
        return new Lectura().calcularWatts();
    }

    public int getPropiedadID(){
        return propiedadID;
    }

    public String getTipoTarifa(){
        return tipo_tarifa;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public static boolean verificarAdeudos() {
        // es true por ahora para simular que el usuario tiene adeudos, pero en un futuro se implementaremos la logica para verificar si el usuario tiene adeudos o no
        return true;
    }

}

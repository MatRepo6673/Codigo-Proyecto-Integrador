public class Usuario {
    private static int usuarioID;
    private static String contrasena;
    private static String Correo;
    private static int Telefono;

    public int getUsuarioID(){
        return usuarioID;
    }

    public String getContrasena(){
        return contrasena;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public String getCorreo(){
        return Correo;
    }

    public void setCorreo(String correo){
        this.Correo = correo;
    }

    public int getTelefono(){
        return Telefono;
    }

    public void setTelefono(int telefono){
        this.Telefono = telefono;
    }

    public Usuario(int usuarioID, String cotrasena, String correo, int telefono){
        this.usuarioID = usuarioID;
        this.contrasena = contrasena;
        this.Correo = correo;
        this.Telefono = telefono;
    }

    public static boolean iniciarSesion(){
        return false;
    }
}

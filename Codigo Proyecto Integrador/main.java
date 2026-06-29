import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class main {
    private static Scanner sc = new Scanner(System.in);
    protected static String usuario, usuario2, contrasena, contrasena2;

    private static final void limpiarEntrada() throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static final void iniciarSesion() throws Exception {
        System.out.println("==================================");
        System.out.println("         Iniciar sesion          ");
        System.out.println("==================================\n");
        System.out.println("Ingrese usuario: ");
        usuario2 = leerEntrada();
        while (usuario2.equals(usuario) == false) {
            System.out.println("Usuario no existe, intente otra vez: ");
            usuario2 = leerEntrada();
        }

        System.out.println("Ingrese contrasena: ");
        contrasena2 = leerEntrada();
        while (contrasena2.equals(contrasena) == false) {
            System.out.println("Contrasena incorrecta, intente otra vez: ");
            contrasena2 = leerEntrada();
        }
        limpiarEntrada();

    }

    private static final void inicializarNombres() throws Exception {
        File usernames = new File("usuarios.txt");
        Scanner userScanner = new Scanner(usernames);
        final String input = userScanner.nextLine().toString();
        List<String> names = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
        ArrayList<String> nameList = new ArrayList<>();
        for (String name : names) {
            nameList.add(name.trim());
        }
    }

    private static final void inicializarContrasenas() throws Exceptions {
        File passwords = new File("contrasenas.txt");
        Scanner passScanner = new Scanner(passwords);
        final String input = passScanner.nextLine().toString();
        List<String> pass = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
        ArrayList<String> passList = new ArrayList<>();
        for (String password : pass) {
            passList.add(password.trim());
        }
    }

    public static void main(String args[]) throws Exception {

        File usernames = new File("usuarios.txt");
        File passwords = new File("contrasenas.txt");
        Scanner userScanner = new Scanner(usernames);
        Scanner passScanner = new Scanner(passwords);

        inicializarNombres();

        if (userScanner.hasNextLine() && passScanner.hasNextLine()) {
            usuario = userScanner.nextLine().toString();
            contrasena = passScanner.nextLine().toString();
            System.out.println(usuario);
            System.out.println(contrasena);
            iniciarSesion();
            menu();

        } else {
            System.out.println("Error: No se encontraron usuarios o contraseñas en los archivos.");
        }

    }

    static String leerEntrada() {
        while (!sc.hasNextLine()) {
            System.out.println("Error, ese no es un dato valido, intente otra vez:\n");
            sc.next();
        }
        return sc.nextLine();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int seleccion;
        System.out.println("================================");
        System.out.println("              MENU             ");
        System.out.println("================================");
        System.out.println("1. Dispositivos");
        System.out.println("2. Recibos");
        System.out.println("3. Consejos");
        System.out.println("4. Agregar catalogo");
        seleccion = sc.nextInt();
        switch (seleccion) {
            case 1:
                System.out.println("entraste a dispositivos");
                break;
            case 2:
                System.out.println("entraste a recibos");
                break;
            case 3:
                System.out.println("entraste a consejos");
                break;
            case 4:
                System.out.println("entraste a agregar catalogo");
                break;
        }

    }

    public static void propiedades() {
        int opcion;
        System.out.println("===================================");
        System.out.println("        BIENVENIDO: " + usuario);
        System.out.println("     Que propiedad quieres ver");
        System.out.println("===================================");
        ArrayList<String> propiedades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("0. Agregar propiedad");
        for (int i = 0; i < propiedades.size(); i++) {
            System.out.println((i + 1) + ". " + propiedades.get(i));
        }
        opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 0) {
            System.out.println();
        }

    }
}

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileWriter;


public class main {
    private static Scanner sc = new Scanner(System.in);
    protected static String usuario, contrasena, input;
    protected static List<String> names, passwords;
    protected static ArrayList<String> nameList, passList;

    private static final boolean verifyUser(String user) throws Exception {
        File usernames = new File("usuarios.txt");
        Scanner userScanner = new Scanner(usernames);
        input = userScanner.nextLine().toString();
        names = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
        nameList = new ArrayList<>();
        for (String name : names) {
            nameList.add(name.trim());
        }
        if (nameList.indexOf(user) < 0) {
            userScanner.close();
            return false;
        } else {
            userScanner.close();
            return true;
        }
    }

    private static final boolean verifyPass(String pass) throws Exception {
        File passwordsFile = new File("contrasenas.txt");
        Scanner passScanner = new Scanner(passwordsFile);
        input = passScanner.nextLine().toString();
        passwords = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
        passList = new ArrayList<>();
        for (String passs : passwords) {
            passList.add(passs.trim());
        }
        if (passList.indexOf(pass) < 0) {
            passScanner.close();
            return false;
        } else {
            passScanner.close();
            return true;
        }
    }

    private static final void limpiarEntrada() throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static final void iniciarSesion() throws Exception {
        System.out.println("==================================");
        System.out.println("         Iniciar sesion          ");
        System.out.println("==================================\n");
        System.out.println("Ingrese usuario: ");
        usuario = leerEntrada();

        while (verifyUser(usuario) == false) {
            System.out.println("Error, usuario no existe, intente otra vez: ");
            usuario = leerEntrada();
        }

        System.out.println("Ingrese contrasena: ");
        contrasena = leerEntrada();

        while (verifyPass(contrasena) == false) {
            System.out.println("Error, contrasena incorrecta, intente otra vez: ");
            contrasena = leerEntrada();
        }

        System.out.println("\n\nSesion iniciada! \n\n");
        Thread.sleep(500);
        limpiarEntrada();

    }

    private static final void crearCuenta() throws Exception {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new FileWriter("usuarios.txt"));
        PrintWriter passOut = new PrintWriter(new FileWriter("contrasenas.txt"));
        String usuario, contrasena;
        System.out.println("=======================================");
        System.out.println("              CREAR CUENTA             ");
        System.out.println("=======================================\n");
        System.out.println(" --- Usuario: ");
        usuario = verificarEntrada();
        out.print(usuario + ", ");
        out.close();
        System.out.println(" --- Contrasena: ");
        contrasena = verificarEntrada();
        passOut.print(contrasena + ", ");
        passOut.close();
        sc.close();
        System.out.println("\nCuenta creada con exito!\nAbriendo menu principal...");
        Thread.sleep(1500);
        limpiarEntrada();
        menu();
    }

    private static final void inicializarNombres() throws Exception {
        File usernames = new File("usuarios.txt");
        Scanner userScanner = new Scanner(usernames);
        if (userScanner.hasNextLine()) {
            final String input = userScanner.nextLine().toString();
            List<String> names = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
            ArrayList<String> nameList = new ArrayList<>();
            for (String name : names) {
                nameList.add(name.trim());
            }
            userScanner.close();
        }
    }

    private static final void inicializarContrasenas() throws Exception {
        File passwords = new File("contrasenas.txt");
        Scanner passScanner = new Scanner(passwords);
        if (passScanner.hasNextLine()) {
            final String input = passScanner.nextLine().toString();
            List<String> pass = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
            ArrayList<String> passList = new ArrayList<>();
            for (String password : pass) {
                passList.add(password.trim());
            }
            passScanner.close();
        }

    }

    public static void main(String args[]) throws Exception {

        File usernames = new File("usuarios.txt");
        File passwords = new File("contrasenas.txt");
        Scanner userScanner = new Scanner(usernames);
        Scanner passScanner = new Scanner(passwords);

        inicializarNombres();
        inicializarContrasenas();

        if (userScanner.hasNextLine() && passScanner.hasNextLine()) {
            iniciarSesion();
            userScanner.close();
            passScanner.close();
            menu();

        } else {
            System.out.println("Error: No se encontraron usuarios o contraseñas en los archivos\n\nAbriendo menu de crear cuenta...");
            Thread.sleep(2500);
            limpiarEntrada();
            crearCuenta();
        }

    }

    protected static String leerEntrada() {
        while (!sc.hasNextLine()) {
            System.out.println("Error, ese no es un dato valido, intente otra vez:");
            sc.next();
        }
        return sc.nextLine();
    }

    protected static String verificarEntrada(){
        while(sc.nextLine().toString().length() > 15){
            System.out.println("Error, usuario o contrasena muy larga || limite: 15, ingrese otra vez: ");
            sc.next();
        }
        return sc.nextLine().toString();
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int seleccion;
        System.out.println("================================");
        System.out.println("              MENU             ");
        System.out.println("================================\n");
        System.out.println("1.- Dispositivos");
        System.out.println("2.- Recibos");
        System.out.println("3.- Consejos");
        System.out.println("4.- Agregar catalogo");
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
        sc.close();

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

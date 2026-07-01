import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
/*

NOTA: 
Convertir menu principal a menu de propiedad y quitar la opcion de crear cuenta y cerrar sesion y modificar la opcion de salir
( Menu propiedad n ) 

*/

    private static Scanner sc = new Scanner(System.in);
    protected static String usuario, contrasena, input, sesionActual;
    protected static List<String> names, passwords;
    protected static ArrayList<String> nameList = new ArrayList<>(), passList = new ArrayList<>();
    protected static Map<String, String> accountsList = new HashMap<>();
    protected static Path relativePath = Paths.get("database");
    protected static Path absolutePath = relativePath.toAbsolutePath().normalize();

    private static final void inicializarBD() throws Exception {
        File usernames = new File(absolutePath + "/usuarios.txt");
        Scanner userScanner = new Scanner(usernames);
        File passwords = new File(absolutePath + "/contrasenas.txt");
        Scanner passScanner = new Scanner(passwords);
        if (userScanner.hasNextLine() && passScanner.hasNextLine()) {
            final String input = userScanner.nextLine().toString();
            List<String> names = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
            for (String name : names) {
                nameList.add(name.trim());
            }
            userScanner.close();

            final String passInput = passScanner.nextLine().toString();
            List<String> pass = Arrays.stream(passInput.split("\\s*,\\s*")).collect(Collectors.toList());
            for (String password : pass) {
                passList.add(password.trim());
            }
            passScanner.close();
            int i = 0;

            for (String name : nameList) {
                accountsList.put(name, passList.get(i));
                i++;
            }
        }

    }

    private static final boolean verifyUser(String user) throws Exception {
        /*
         * File usernames = new File("usuarios.txt");
         * Scanner userScanner = new Scanner(usernames);
         * input = userScanner.nextLine().toString();
         * names = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
         * nameList = new ArrayList<>();
         * for (String name : names) {
         * nameList.add(name.trim());
         * }
         */
        if (accountsList.containsKey(user) == false) {
            // userScanner.close();
            return false;
        }
            else {
            // userScanner.close();
            return true;
        }
    }

    private static final boolean verifyPass(String pass, String user) throws Exception {
        /*
         * File passwordsFile = new File("contrasenas.txt");
         * Scanner passScanner = new Scanner(passwordsFile);
         * input = passScanner.nextLine().toString();
         * passwords =
         * Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
         * passList = new ArrayList<>();
         * for (String passs : passwords) {
         * passList.add(passs.trim());
         * }
         */

        if (accountsList.get(user).equals(pass) == false) {
            // passScanner.close();
            return false;
        } else {
            // passScanner.close();
            return true;
        }
    }

    private static final void limpiarEntrada() throws Exception {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static final void iniciarSesion() throws Exception {
        inicializarBD();
        System.out.println("==================================");
        System.out.println("         Iniciar sesion          ");
        System.out.println("==================================\n");
        System.out.println("Ingrese usuario: ");
        usuario = sc.next().trim().toString();

        while (verifyUser(usuario) == false) {
            System.out.println("Error, usuario no existe, intente otra vez: ");
            System.out.println("Account exists: " + accountsList.containsKey(usuario));
            System.out.println("Account list: " + accountsList);
            usuario = sc.next().trim().toString();
            sesionActual = usuario;
        }
        sesionActual = usuario;

        System.out.println("Ingrese contrasena: ");
        contrasena = sc.next().trim().toString();
        while (verifyPass(contrasena, usuario) == false) {
            System.out.println("Error, contrasena incorrecta, intente otra vez: ");
            contrasena = sc.next().trim().toString();
        }

        System.out.println("\n\nSesion iniciada! \n\n");

        Thread.sleep(500);
        limpiarEntrada();
        //propiedadesMenu();
        menu();

    }

    private static final void crearCuenta() throws Exception {
        PrintWriter out = new PrintWriter(new FileWriter(absolutePath + "/usuarios.txt", true));
        PrintWriter passOut = new PrintWriter(new FileWriter(absolutePath + "/contrasenas.txt", true));
        String usuario, contrasena;
        System.out.println("=======================================");
        System.out.println("              CREAR CUENTA             ");
        System.out.println("=======================================\n");
        System.out.println(" --- Usuario: ");
        usuario = sc.next().trim().toString();
        out.print(usuario + ", ");
        out.close();
        System.out.println(" --- Contrasena: ");
        contrasena = sc.next().trim().toString();
        passOut.print(contrasena + ", ");
        passOut.close();
        System.out.println("\nCuenta creada con exito!");
        Thread.sleep(1500);
        limpiarEntrada();
        //propiedadesMenu();
        menu();
    }

    public static void main(String[] args) throws Exception {

        File usernames = new File(absolutePath + "/usuarios.txt");
        File passwords = new File(absolutePath + "/contrasenas.txt");
        if (!usernames.exists()) {
            usernames.createNewFile();
        }
        if (!passwords.exists()) {
            passwords.createNewFile();
        }
        Scanner userScanner = new Scanner(usernames);
        Scanner passScanner = new Scanner(passwords);

        inicializarBD();

        if (userScanner.hasNextLine() && passScanner.hasNextLine()) {
            iniciarSesion();
            userScanner.close();
            passScanner.close();
            menu();

        } else {
            System.out.println(
                    "Error: No se encontraron usuarios o contraseñas en la computadora\n\nAbriendo menu de crear cuenta...");
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

    protected static int checarNumero() {
        while (!sc.hasNextInt()) {
            System.out.println("Error, ese no es un numero, intente otra vez:");
            sc.next();
        }

        return sc.nextInt();
    }

    /*
     * protected static String verificarEntrada() {
     * while (sc.next().toString().length() > 15) {
     * System.out.
     * println("Error, usuario o contrasena muy larga || limite: 15, ingrese otra vez: "
     * );
     * sc.next();
     * }
     * return sc.nextLine();
     * }
     */

    protected static final void menu() throws Exception {
        int seleccion;
        System.out.println("================================");
        System.out.println("              MENU              ");
        System.out.println("================================\n");
        System.out.println("1.- Dispositivos");
        System.out.println("2.- Recibos");
        System.out.println("3.- Consejos");
        System.out.println("4.- Agregar catalogo");
        System.out.println("5.- Agregar cuenta");
        System.out.println("6.- Cerrar sesion");
        System.out.println("7.- Salir");

        seleccion = checarNumero();

        switch (seleccion) {
            case 1:
                System.out.println("entraste a dispositivos");
                limpiarEntrada();
                propiedadesMenu();
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
            case 5:
                limpiarEntrada();
                crearCuenta();
                break;
            case 6:
                System.out.println("\nCerrando sesion...");
                Thread.sleep(700);
                limpiarEntrada();
                Thread.sleep(10);
                iniciarSesion();
                break;
            case 7:
                System.out.println("Saliendo del sistema...");
                Thread.sleep(700);
                System.exit(0);
                break;
        }

    }

    protected static final void propiedadesMenu() {
        int opcion;
        ArrayList<String> propiedades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("===================================");
        if (Main.sesionActual.charAt(Main.sesionActual.length() - 1) == 'a'
                || Main.sesionActual.charAt(Main.sesionActual.length() - 1) == 'A') {
            System.out.println("        BIENVENIDA: " + Main.sesionActual.toUpperCase());
        } else
            System.out.println("        BIENVENIDO: " + Main.sesionActual.toUpperCase());
        System.out.println("     Que propiedad quieres ver");
        System.out.println("===================================");

        System.out.println("0. Agregar propiedad");
        for (int i = 0; i < propiedades.size(); i++) {
            System.out.println((i + 1) + ". " + propiedades.get(i));
        }
        opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 0) {
            System.out.println();
        }
        scanner.close();

    }
}
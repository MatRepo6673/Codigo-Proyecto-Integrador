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
      
    Todo el codigo de la clase Main y las otras clases estan sujeto a cambiar y
    mejorar, este es un progreso del proyecto,
    por lo que se implementaran nuevas funciones y se mejoraran las ya existentes
      
     */

    private static Scanner sc = new Scanner(System.in);
    private static String usuario, contrasena, input, sesionActual, direccionPropiedad, tipoTarifaPropiedad,
            nombrePropiedad;
    private static ArrayList<String> nameList = new ArrayList<>(), passList = new ArrayList<>();
    private static Map<String, String> accountsList = new HashMap<>(), propiedadesList = new HashMap<>();
    private static Path relativePath = Paths.get("database");
    private static Path absolutePath = relativePath.toAbsolutePath().normalize();

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
            passScanner.close();;
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
        } else {
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

    protected static final void limpiarEntrada() throws Exception {
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
        propiedadesMenu();

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
        iniciarSesion();
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

    private static final void menu() throws Exception {
        int seleccion;
        limpiarEntrada();
        System.out.println("================================");
        System.out.println("              MENU              ");
        System.out.println("================================\n");
        System.out.println("1.- Dispositivos");
        System.out.println("2.- Recibos");
        System.out.println("3.- Consejos");
        System.out.println("4.- Agregar catalogo");
        System.out.println("5.- Salir");

        seleccion = checarNumero();

        switch (seleccion) {
            case 1:
                System.out.println("entraste a dispositivos...");
                break;
            case 2:
                limpiarEntrada();
                new Recibo().generarRecibo();
                Thread.sleep(7000);
                break;
            case 3:
                System.out.println("entraste a consejos...");
                break;
            case 4:
                System.out.println("entraste a agregar catalogo...");
                break;

            case 5:
                System.out.println("Saliendo...");
                Thread.sleep(700);
                propiedadesMenu();
                break;
        }

    }

    private static final void inicializarPropiedades() throws Exception {
        File propiedadesFile = new File(absolutePath + "/propiedades/propiedades.txt");
        File duenosFile = new File(absolutePath + "/propiedades/duenos.txt");

        propiedadesList.clear();
        Scanner propiedadScanner = new Scanner(propiedadesFile);
        Scanner duenosScanner = new Scanner(duenosFile);

        if (propiedadScanner.hasNextLine() && duenosScanner.hasNextLine()) {
            String input = propiedadScanner.nextLine().toString();
            List<String> propiedades = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
            String passInput = duenosScanner.nextLine().toString();
            List<String> duenos = Arrays.stream(passInput.split("\\s*,\\s*")).collect(Collectors.toList());

            for (int i = 0; i < propiedades.size(); i++) {
                String propiedad = propiedades.get(i).trim();
                if (!propiedad.isEmpty()) {
                    String dueno = "";
                    if (i < duenos.size()) {
                        dueno = duenos.get(i).trim();
                    }
                    propiedadesList.put(propiedad, dueno);
                }
            }
        }

        propiedadScanner.close();
        duenosScanner.close();
    }

    private static final void propiedadesMenu() throws Exception {
        int opcion;
        inicializarPropiedades();
        limpiarEntrada();
        System.out.println("===================================");
        System.out.println("        BIENVENIDO: " + Main.sesionActual.toUpperCase());
        System.out.println("===================================");
        System.out.println("1.- Agregar propiedad");
        System.out.println("2.- Ver propiedades");
        System.out.println("3.- Entrar a una propiedad");
        System.out.println("4.- Salir");

        opcion = checarNumero();

        switch (opcion) {
            case 1:
                agregarPropiedadMenu();
                Thread.sleep(700);
                limpiarEntrada();
                propiedadesMenu();
                break;
            case 2:
                verPropiedades();
                Thread.sleep(1500);
                limpiarEntrada();
                propiedadesMenu();
                break;
            case 3:
                entrarPropiedad();
                break;
            case 4:
                System.out.println("Saliendo...");
                Thread.sleep(700);
                limpiarEntrada();
                System.exit(0);
                break;
            default:
                System.out.println("Opcion invalida");
                Thread.sleep(700);
                limpiarEntrada();
                propiedadesMenu();
                break;
        }
    }

    private static final void verPropiedades() throws Exception {
        limpiarEntrada();
        inicializarPropiedades();
        System.out.println("===================================");
        System.out.println("        TUS PROPIEDADES            ");
        System.out.println("===================================\n");
        boolean exiten = false;

        for (Map.Entry<String, String> entry : propiedadesList.entrySet()) {
            if (entry.getValue().equals(sesionActual)) {
                System.out.println("- " + entry.getKey());
                exiten = true;
            }
        }

        if (!exiten) {
            System.out.println("No tienes propiedades aun.");
        }
    }

    private static final void entrarPropiedad() throws Exception {
        System.out.println("Ingrese el nombre de la propiedad: ");
        String propiedad = sc.next().trim();

        if (propiedadesList.containsKey(propiedad) && propiedadesList.get(propiedad).equals(sesionActual)) {
            System.out.println("Entraste a la propiedad: " + propiedad);
            Thread.sleep(1000);
            limpiarEntrada();
            menu();
        } else {
            System.out.println("No tienes acceso a esa propiedad.");
            Thread.sleep(1000);
            limpiarEntrada();
            propiedadesMenu();
        }
    }

    private static final void agregarPropiedadMenu() throws Exception {
        File propiedadesFolder = new File(absolutePath + "/propiedades");
        if (!propiedadesFolder.exists()) {
            propiedadesFolder.mkdirs();
        }

        PrintWriter propiedadOut = new PrintWriter(new FileWriter(absolutePath + "/propiedades/propiedades.txt", true));
        PrintWriter duenosOut = new PrintWriter(new FileWriter(absolutePath + "/propiedades/duenos.txt", true));
        System.out.println("===================================");
        System.out.println("         AGREGAR PROPIEDAD        ");
        System.out.println("===================================\n");

        sc.nextLine();
        System.out.println("Ingrese el nombre de la propiedad: ");
        nombrePropiedad = leerEntrada().trim().toString();
        System.out.println("Ingrese la direccion de la propiedad: ");
        direccionPropiedad = sc.next().trim().toString();

        propiedadOut.print(nombrePropiedad + ", ");
        duenosOut.print(Main.sesionActual + ", ");
        propiedadOut.close();
        duenosOut.close();
        inicializarPropiedades();

        System.out.println("\nPropiedad agregada con exito!");
        Thread.sleep(1500);
        limpiarEntrada();
    }

        public static void main(String[] args) throws Exception {

        File usernames = new File(absolutePath + "/usuarios.txt");
        File passwords = new File(absolutePath + "/contrasenas.txt");
        Scanner userScanner = new Scanner(usernames);
        Scanner passScanner = new Scanner(passwords);

        inicializarBD();
        inicializarPropiedades();

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
}
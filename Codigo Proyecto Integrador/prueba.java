import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class prueba {
    public static void main(String[] args) throws Exception{
        File usuarios = new File("usuarios.txt");
        Scanner userScanner = new Scanner(usuarios);
        final String input = userScanner.nextLine().toString();
        List<String> names = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
        ArrayList<String> nameList = new ArrayList<>();
        for (String name : names) {
            nameList.add(name.trim());
        }
        System.out.println(nameList);
        System.out.println(nameList.get(nameList.indexOf("waka")));
    }
}

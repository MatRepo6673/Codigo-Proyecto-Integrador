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
        String input = userScanner.nextLine().toString();
        int user;
        List<String> names = Arrays.stream(input.split("\\s*,\\s*")).collect(Collectors.toList());
        ArrayList<String> nameList = new ArrayList<>();
        for (String name : names) {
            nameList.add(name.trim());
        }
        System.out.println(nameList);
        user = nameList.indexOf("mathi");
        if(user < 0){
            System.out.println("error, name does not exist");
        }else System.out.println(nameList.get(user) + nameList.indexOf("mathi"));

        userScanner.close();
    }
}

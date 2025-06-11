import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LerPerguntas {

    public void perguntas() throws FileNotFoundException {
        Scanner in = new Scanner(new FileReader("src/formulario.txt"));
        Scanner sc = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(line);

        }
        in.close();

    }
}

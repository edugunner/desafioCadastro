import java.io.FileNotFoundException;
import java.util.Scanner;

public class PetCLI {
    public static void main(String[] args) throws FileNotFoundException {

        while (true) {
            System.out.println("1-Cadastrar um novo pet");
            System.out.println("2-Alterar os dados do pet cadastrado");
            System.out.println("3-Deletar um pet cadastrado");
            System.out.println("4-Listar todos os pets cadastrados");
            System.out.println("5-Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6-Sair");

            Scanner sc = new Scanner(System.in);
            int opcao = sc.nextInt();

            if(opcao <= 0 || opcao >= 7) {
                System.out.println("Escolha uma opção válida");
            }


            switch (opcao) {
                case 1:
                    LerPerguntas ler = new LerPerguntas();
                    ler.perguntas();
            }

        }
    }
}

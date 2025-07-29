import model.Pet;
import repository.FileRepository;
import service.Answers;
import utils.CadastrarPet;
import utils.Validator;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Answers answers = new Answers();
        Validator validator = new Validator();
        Pet pet = new Pet();
        CadastrarPet cadastrarPet = new CadastrarPet();
        FileRepository fileRepository = new FileRepository();
        fileRepository.createFile();
        while (true) {
            System.out.println("1. Cadastrar um novo pet\n" +
                    "2. Alterar os dados do pet cadastrado\n" +
                    "3. Deletar um pet cadastrado\n" +
                    "4. Listar todos os pets cadastrados\n" +
                    "5. Listar pets por algum critério (idade, nome, raça)\n" +
                    "6. Sair");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarPet.cadastrarPet();
            }


        }
    }
}

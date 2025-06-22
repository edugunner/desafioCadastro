import service.Perguntas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        while (true) {
            System.out.println("1. Cadastrar um novo pet\n" +
                    "2. Alterar os dados do pet cadastrado\n" +
                    "3. Deletar um pet cadastrado\n" +
                    "4. Listar todos os pets cadastrados\n" +
                    "5. Listar pets por algum critério (idade, nome, raça)\n" +
                    "6. Sair");

            switch (opcao = sc.nextInt()) {
                case 1: Perguntas pr = new Perguntas();
                pr.printPerguntas();
            }

        }
    }
}
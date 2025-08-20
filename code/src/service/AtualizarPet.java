package service;

import model.Pet;
import utils.Validator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AtualizarPet {

    final Pet pet = new Pet();
    final Scanner sc = new Scanner(System.in);
    final File pasta = new File("petsCadastrados");
    final Validator validator = new Validator();
    final BuscarPet buscarPet = new BuscarPet();



    public void alterarPet() {
        ArrayList<Pet> resultados = new ArrayList<>();
        resultados = buscarPet.iniciarBusca();
        if (resultados.isEmpty()) {
            System.out.println("Não há pets na lista de resultados para alterar.");
            return;
        }

        while (true) {
            imprimeResultados(resultados);
            System.out.print("Digite o número do pet que deseja alterar (ou 0 para cancelar): ");
            int numeroPet = 0;
            try {
                numeroPet = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Digite um número.");
                sc.nextLine();
                continue;
            }

            if (numeroPet == 0) {
                System.out.println("Alteração cancelada.");
                return;
            }

            if (numeroPet < 1 || numeroPet > resultados.size()) {
                System.err.println("Número de pet inválido. Por favor, digite um número da lista.");
            } else {
                Pet petParaAlterar = resultados.get(numeroPet - 1);
                System.out.println("\n--- Alterando o pet: " + petParaAlterar.getNome() + " ---");

                String nomeOriginal = petParaAlterar.getNome();
                String sobrenomeOriginal = petParaAlterar.getSobrenome();
                String nomeArquivoOriginal = nomeOriginal + sobrenomeOriginal;
                File[] arquivos = pasta.listFiles();

                System.out.print("Novo nome: ");
                petParaAlterar.setNome(validator.validaNome());
                System.out.print("Novo sobrenome: ");
                petParaAlterar.setSobrenome(validator.validaNome());
                System.out.print("Nova idade: ");
                petParaAlterar.setIdade(validator.validaIdade());
                System.out.print("Novo peso: ");
                petParaAlterar.setPeso(validator.validaPeso());
                System.out.print("Nova raça: ");
                petParaAlterar.setRaca(validator.validaRaca());

                petParaAlterar.setPetAddress(validator.validaAddress());

                for (File arquivo : arquivos) {
                    if (arquivo.isFile() && arquivo.getName().endsWith(".txt")) {
                        if (arquivo.getName().contains(nomeArquivoOriginal)) {
                            arquivo.delete();
                            petParaAlterar.savePet();
                            System.out.println("Pet alterado e salvo com sucesso!");
                        }
                    }
                }

                return;
            }
        }
    }


    public static void imprimeResultados(ArrayList<Pet> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("Nenhum pet encontrado com os critérios fornecidos.");
        } else {
            System.out.println("### Resultados da Busca ###");
            for (int i = 0; i < resultados.size(); i++) {
                System.out.println((i + 1) + ". " + resultados.get(i));
            }
        }
    }
}

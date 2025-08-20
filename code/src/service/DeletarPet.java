package service;

import model.Pet;
import utils.Validator;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeletarPet {
    final Scanner sc = new Scanner(System.in);
    final File pasta = new File("petsCadastrados");
    final Validator validator = new Validator();
    final BuscarPet buscarPet = new BuscarPet();

    public void deletarPet() {
        ArrayList<Pet> resultados = buscarPet.iniciarBusca();
        if (resultados.isEmpty()) {
            System.out.println("Não há pets na lista de resultados para deletar.");
            return;
        }

        while (true) {
            imprimeResultados(resultados);
            System.out.print("Digite o número do pet que deseja deletar (ou 0 para cancelar): ");
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
                System.out.println("Deleção cancelada.");
                return;
            }

            if (numeroPet < 1 || numeroPet > resultados.size()) {
                System.err.println("Número de pet inválido. Por favor, digite um número da lista.");
            } else {
                Pet petParaDeletar = resultados.get(numeroPet - 1);


                String nomePetFormatado = (petParaDeletar.getNome() + petParaDeletar.getSobrenome())
                        .replaceAll(" ", "")
                        .toUpperCase();

                File[] arquivos = pasta.listFiles();
                boolean arquivoEncontrado = false;

                if (arquivos != null) {
                    for (File arquivo : arquivos) {
                        if (arquivo.getName().toUpperCase().contains(nomePetFormatado)) {
                            if (arquivo.delete()) {
                                System.out.println("Pet deletado com sucesso!");
                                arquivoEncontrado = true;
                                break;
                            } else {
                                System.err.println("Erro ao deletar o arquivo do pet.");
                                arquivoEncontrado = true;
                                break;
                            }
                        }
                    }
                }

                if (!arquivoEncontrado) {
                    System.out.println("Arquivo do pet não encontrado.");
                }

                return;
            }
        }
    }

    public void imprimeResultados(ArrayList<Pet> resultados) {
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

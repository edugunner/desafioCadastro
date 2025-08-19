package service;

import model.Pet;
import model.PetAddress;
import model.PetGender;
import model.PetType;
import utils.Validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BuscarPet {
    static Path path = Paths.get("petsCadastrados");
    File pasta = new File(path.toFile().getAbsolutePath());
    final Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();

    public ArrayList<Pet> buscarPets() {
        if (!pasta.exists() || !pasta.isDirectory()) {
            System.err.println("O caminho fornecido não é uma pasta válida.");
            return new ArrayList<>();
        }

        ArrayList<Pet> petsCadastrados = new ArrayList<>();
        File[] arquivos = pasta.listFiles();

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("A pasta está vazia.");
            return new ArrayList<>();
        }

        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.getName().toLowerCase().endsWith(".txt")) {
                try (FileReader fr = new FileReader(arquivo); BufferedReader br = new BufferedReader(fr)) {
                    String nome = br.readLine().substring(2).trim();
                    String[] nomeCompleto = nome.split(" ");
                    String tipo = br.readLine().substring(2).trim();
                    String genero = br.readLine().substring(2).trim();
                    String endereco = br.readLine().substring(6).trim();
                    String idade = br.readLine().substring(2).trim();
                    String peso = br.readLine().substring(2).trim();
                    String raca = br.readLine().substring(2).trim();

                    Pet pet = new Pet();
                    PetAddress petAddress = new PetAddress();
                    String[] enderecoCompleto = endereco.split(",");
                    petAddress.setStreet(enderecoCompleto[0].trim());
                    petAddress.setNumHouse(enderecoCompleto[1].trim());
                    petAddress.setCity(enderecoCompleto[2].trim());
                    pet.setPetAddress(petAddress);

                    pet.setNome(nomeCompleto[0]);
                    pet.setSobrenome(nomeCompleto[1]);
                    pet.setType(PetType.valueOf(tipo.toUpperCase()));
                    pet.setGender(PetGender.valueOf(genero.toUpperCase()));
                    pet.setIdade(idade);
                    pet.setPeso(peso);
                    pet.setRaca(raca);

                    petsCadastrados.add(pet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return petsCadastrados;
    }

    public ArrayList<Pet> iniciarBusca() {

        ArrayList<Pet> resultados = buscarPets();

        if (resultados.isEmpty()) {
            System.out.println("Não há pets cadastrados para realizar a busca.");
            return resultados;
        }


        System.out.println("\n--- Passo 1: Escolha o Tipo de Animal ---");
        System.out.println("Digite 1 para GATO ou 2 para CACHORRO");
        int opcaoTipo = validator.lerUmDois();
        resultados = filtrarPorTipo(resultados, opcaoTipo);


        while (true) {
            if (resultados.isEmpty()) {
                System.out.println("A busca atual não retornou resultados. Não é possível adicionar mais filtros.");
                break;
            }

            System.out.println("\nDeseja adicionar mais algum critério de busca? Digite 1 para SIM e 2 para NÃO");
            int escolhaCriterio = validator.lerUmDois();

            if (escolhaCriterio == 2) {
                break;
            }

            imprimeMenuCriterios();
            int opcao = -1;
            try {
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Opção inválida. Digite apenas números.");
                sc.nextLine();
                continue;
            }

            ArrayList<Pet> novosResultados = new ArrayList<>();
            switch (opcao) {
                case 1:
                    novosResultados = filtrarPorNome(resultados);
                    break;
                case 2:
                    novosResultados = filtrarPorSexo(resultados);
                    break;
                case 3:
                    novosResultados = filtrarPorIdade(resultados);
                    break;
                case 4:
                    novosResultados = filtrarPorPeso(resultados);
                    break;
                case 5:
                    novosResultados = filtrarPorRaca(resultados);
                    break;
                case 6:
                    novosResultados = filtrarPorEndereco(resultados);
                    break;
                case 7:
                    System.out.println("Saindo do menu de busca de critérios adicionais...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
            }
            resultados = novosResultados;
        }
        imprimeResultados(resultados);
        return resultados;
    }

    public void listarPets() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        for (int i = 0; i < petsCadastrados.size(); i++) {
            System.out.println(petsCadastrados.get(i));
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

    private void imprimeMenuCriterios() {
        System.out.println("### Menu de Busca de Pets ###");
        System.out.println("1. Buscar por Nome ou Sobrenome");
        System.out.println("2. Buscar por Sexo");
        System.out.println("3. Buscar por Idade");
        System.out.println("4. Buscar por Peso");
        System.out.println("5. Buscar por Raça");
        System.out.println("6. Buscar por Endereço");
        System.out.println("7. Sair");
        System.out.print("Escolha o critério de busca: ");
    }


    private ArrayList<Pet> filtrarPorTipo(ArrayList<Pet> lista, int opcao) {
        ArrayList<Pet> filtrados = new ArrayList<>();
        PetType tipo = (opcao == 1) ? PetType.GATO : PetType.CACHORRO;
        for (Pet pet : lista) {
            if (pet.getType() == tipo) {
                filtrados.add(pet);
            }
        }
        return filtrados;
    }

    private ArrayList<Pet> filtrarPorNome(ArrayList<Pet> lista) {
        System.out.print("Deseja buscar por qual nome? ");
        String nomeCriterio = sc.nextLine();
        ArrayList<Pet> filtrados = new ArrayList<>();
        String nomeFormatado = formatador(nomeCriterio).toLowerCase();
        for (Pet pet : lista) {
            if (formatador(pet.getNome()).toLowerCase().contains(nomeFormatado)) {
                filtrados.add(pet);
            }
        }
        return filtrados;
    }

    private ArrayList<Pet> filtrarPorSexo(ArrayList<Pet> lista) {
        System.out.println("Digite 1 para buscar por MACHO e 2 para buscar por FEMEA");
        int opcao = validator.lerUmDois();
        PetGender genero = (opcao == 1) ? PetGender.MACHO : PetGender.FEMEA;
        ArrayList<Pet> filtrados = new ArrayList<>();
        for (Pet pet : lista) {
            if (pet.getGender() == genero) {
                filtrados.add(pet);
            }
        }
        return filtrados;
    }

    private ArrayList<Pet> filtrarPorIdade(ArrayList<Pet> lista) {
        System.out.print("Deseja buscar por qual idade? ");
        String criterio = sc.nextLine();
        ArrayList<Pet> filtrados = new ArrayList<>();
        String criterioFormatado = formatador(criterio).toLowerCase();
        for (Pet pet : lista) {
            if (formatador(pet.getIdade()).toLowerCase().contains(criterioFormatado)) {
                filtrados.add(pet);
            }
        }
        return filtrados;
    }

    private ArrayList<Pet> filtrarPorPeso(ArrayList<Pet> lista) {
        System.out.print("Deseja buscar por qual peso? ");
        String criterio = sc.nextLine();
        ArrayList<Pet> filtrados = new ArrayList<>();
        String criterioFormatado = formatador(criterio).toLowerCase();
        for (Pet pet : lista) {
            if (formatador(pet.getPeso()).toLowerCase().contains(criterioFormatado)) {
                filtrados.add(pet);
            }
        }
        return filtrados;
    }

    private ArrayList<Pet> filtrarPorRaca(ArrayList<Pet> lista) {
        System.out.print("Deseja buscar por qual raca? ");
        String nomeCriterio = sc.nextLine();
        ArrayList<Pet> filtrados = new ArrayList<>();
        String nomeFormatado = formatador(nomeCriterio).toLowerCase();
        for (Pet pet : lista) {
            if (formatador(pet.getRaca()).toLowerCase().contains(nomeFormatado)) {
                filtrados.add(pet);
            }
        }
        return filtrados;
    }

    private ArrayList<Pet> filtrarPorEndereco(ArrayList<Pet> lista) {
        System.out.print("Deseja buscar por qual endereço? ");
        String criterio = sc.nextLine();
        ArrayList<Pet> filtrados = new ArrayList<>();
        String criterioFormatado = formatador(criterio).toLowerCase();
        for (Pet pet : lista) {

            String enderecoCompleto = pet.getPetAddress().getStreet() + " " + pet.getPetAddress().getNumHouse() + " " + pet.getPetAddress().getCity();
            if (formatador(enderecoCompleto).toLowerCase().contains(criterioFormatado)) {
                filtrados.add(pet);
            }
        }
        return filtrados;
    }


    private String formatador(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}

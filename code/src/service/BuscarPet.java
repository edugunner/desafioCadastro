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
        }

        ArrayList<Pet> petsCadastrados = new ArrayList<>();

        File[] arquivos = pasta.listFiles();

        if (arquivos == null) {
            System.out.println("A pasta está vazia.");
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

    public void listarPets() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        for (int i = 0; i < petsCadastrados.size(); i++) {
            System.out.println(petsCadastrados.get(i));
        }
    }

    public void menuCriterio() {
        while (true) {
            System.out.println("### Menu de Busca de Pets ###");
            System.out.println("1. Buscar por Nome ou Sobrenome");
            System.out.println("2. Buscar por Sexo");
            System.out.println("3. Buscar por Tipo");
            System.out.println("4. Buscar por Idade");
            System.out.println("5. Buscar por Peso");
            System.out.println("6. Buscar por Raça");
            System.out.println("7. Buscar por Endereço");
            System.out.println("8. Sair");
            System.out.print("Escolha o critério de busca: ");

            try {
                int opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 1:

                        listaPorNome();
                        break;
                    case 2:
                        listaPorSexo();
                        break;
                    case 3:
                        listaPorTipo();
                        break;
                    case 4:
                        listaPorIdade();
                        break;
                    case 5:
                        listaPorPeso();
                        break;
                    case 6:
                        listaPorRaca();
                        break;
                    case 7:
                        listaPorEndereco();
                        break;
                    case 8:
                        System.out.println("Saindo..");
                        return;
                }
            } catch (InputMismatchException e) {
                System.err.println("Opção inválida. Digite apenas números");
                sc.nextLine();
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

    public void listaPorNome() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        ArrayList<Pet> resultados = new ArrayList<>();
        System.out.print("Deseja buscar por qual nome?");
        String nomeCriterio = sc.nextLine();
        for (Pet pet : petsCadastrados) {
            if (formatador(pet.getNome()).toLowerCase().contains(formatador(nomeCriterio).toLowerCase())) {
                resultados.add(pet);
            }
        }
        imprimeResultados(resultados);
    }

    public void listaPorSexo() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        ArrayList<Pet> resultados = new ArrayList<>();

        System.out.println("Digite 1 para buscar por MACHO e 2 para buscar por FEMEA");
        int opcao = validator.lerUmDois();

        for (Pet pet : petsCadastrados) {
            if (opcao == 1) {
                if (pet.getGender() == PetGender.MACHO) {
                    resultados.add(pet);
                }
            }
            if (opcao == 2) {
                if (pet.getGender() == PetGender.FEMEA) {
                    resultados.add(pet);
                }
            }
        }
        imprimeResultados(resultados);
    }

    public void listaPorTipo() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        ArrayList<Pet> resultados = new ArrayList<>();

        System.out.println("Digite 1 para buscar por GATO e 2 para buscar por CACHORRO");
        int opcao = validator.lerUmDois();

        for (Pet pet : petsCadastrados) {
            if (opcao == 1) {
                if (pet.getType() == PetType.GATO) {
                    resultados.add(pet);
                }
            }
            if (opcao == 2) {
                if (pet.getType() == PetType.CACHORRO) {
                    resultados.add(pet);
                }
            }
        }
        imprimeResultados(resultados);
    }

    public void listaPorIdade() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        ArrayList<Pet> resultados = new ArrayList<>();
        System.out.print("Deseja buscar por qual idade?");
        String criterio = sc.nextLine();
        for (Pet pet : petsCadastrados) {
            if (formatador(pet.getIdade()).toLowerCase().contains(formatador(criterio).toLowerCase())) {
                resultados.add(pet);
            }
        }
        imprimeResultados(resultados);
    }

    public void listaPorPeso() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        ArrayList<Pet> resultados = new ArrayList<>();
        System.out.print("Deseja buscar por qual peso?");
        String criterio = sc.nextLine();
        for (Pet pet : petsCadastrados) {
            if (formatador(pet.getPeso()).toLowerCase().contains(formatador(criterio).toLowerCase())) {
                resultados.add(pet);
            }
        }
        imprimeResultados(resultados);
    }

    public void listaPorRaca() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        ArrayList<Pet> resultados = new ArrayList<>();
        System.out.print("Deseja buscar por qual raca?");
        String nomeCriterio = sc.nextLine();
        for (Pet pet : petsCadastrados) {
            if (formatador(pet.getRaca()).toLowerCase().contains(formatador(nomeCriterio).toLowerCase())) {
                resultados.add(pet);
            }
        }
        imprimeResultados(resultados);
    }

    public void listaPorEndereco() {
        ArrayList<Pet> petsCadastrados = buscarPets();
        ArrayList<Pet> resultados = new ArrayList<>();
        System.out.print("Deseja buscar por qual endereço?");
        String criterio = sc.nextLine();
        for (Pet pet : petsCadastrados) {
            if (formatador(String.valueOf(pet.getPetAddress())).toLowerCase().contains(formatador(criterio).toLowerCase())) {
                resultados.add(pet);
            }
        }
        imprimeResultados(resultados);
    }


    private String formatador(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}



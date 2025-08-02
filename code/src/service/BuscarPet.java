package service;

import model.Pet;
import model.PetAddress;
import model.PetGender;
import model.PetType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BuscarPet {
    static Path path = Paths.get("petsCadastrados");
    File pasta = new File(path.toFile().getAbsolutePath());

    public ArrayList<Pet> buscarPet() {

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
                try (FileReader fr = new FileReader(arquivo);
                     BufferedReader br = new BufferedReader(fr)) {
                    String nome = br.readLine().trim();

                    String tipo = br.readLine().replaceAll("[^a-zA-ZáàâãéèêíïóôõúüçÁÀÂÃÉÈÊÍÏÓÔÕÚÜÇ]", "");

                    String genero = br.readLine().replaceAll("[^a-zA-ZáàâãéèêíïóôõúüçÁÀÂÃÉÈÊÍÏÓÔÕÚÜÇ]", "");
                    System.out.println(genero);

                    String endereco = br.readLine();

                    String idade = br.readLine();

                    String peso = br.readLine();

                    String raca = br.readLine();

                    Pet pet = new Pet();
                    PetAddress petAddress = new PetAddress();

                    String[] enderecoCompleto = endereco.split(",");
                    petAddress.setStreet(enderecoCompleto[0].trim());
                    petAddress.setNumHouse(enderecoCompleto[1].trim());
                    petAddress.setCity(enderecoCompleto[2].trim());
                    pet.setPetAddress(petAddress);


                    pet.setNome(nome);
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
        for (int i = 0; i < petsCadastrados.size(); i++) {
            System.out.println(petsCadastrados.get(i));
        }
        return petsCadastrados;
    }
}

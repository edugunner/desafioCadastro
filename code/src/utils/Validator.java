package utils;

import model.Pet;
import model.PetAddress;
import model.PetGender;
import model.PetType;
import service.Answers;


public class Validator {
    Pet pet = new Pet();
    PetAddress petAddress = new PetAddress();


    public void validaName(String nome, String sobrenome) {
        String regex = "[(a-zA-Z\\s)]+";
        if (sobrenome.isBlank() || nome.isBlank()) {
            throw new IllegalArgumentException("Digite o nome completo do Pet");

        }
        if (!nome.matches(regex) || !sobrenome.matches(regex)) {
            System.out.println("O Nome não deve conter Números ou Carácteres Especiais, Tente Novamente");
            Answers answers = new Answers();
            answers.name();
        }
        String nome_completo = nome + sobrenome;
        nome_completo = nome_completo.trim();
        regex = "[a-zA-Z ]+";
        if (!nome_completo.matches(regex)) {
            System.out.println("O Nome não deve conter Números ou Carácteres Especiais, Tente Novamente");
            Answers answers = new Answers();
            answers.name();
        } else {
            pet.setNome(nome);
            pet.setSobrenome(sobrenome);
        }
    }

    public void validaType(String petType) {
        if (petType.equalsIgnoreCase("Cachorro")) {
            petType = String.valueOf(PetType.CACHORRO);
            System.out.println(petType);
        } else if (petType.equalsIgnoreCase("Gato")) {
            petType = String.valueOf(PetType.GATO);
            System.out.println(petType);

        } else {
            System.out.println("Opção inválida. Digite \"Gato\" ou \"Cachorro\"");
            Answers answers = new Answers();
            answers.type();
        }
    }

    public void validaGender(String gender) {
        if (gender.equalsIgnoreCase("Macho")) {
            String petGender = String.valueOf(PetGender.MACHO);
            System.out.println(petGender);
        } else if (gender.equalsIgnoreCase("Femea")) {
            String petGender = String.valueOf(PetGender.FEMEA);
            System.out.println(petGender);
        } else {
            System.out.println("Opção inválida, tente novamente");
            Answers answers = new Answers();
            answers.gender();
        }
    }

    public void validaAddress(String numHouse, String city, String street) {
        if (!numHouse.matches("[\\d]+")) {
            System.out.println("Endereço inválido. Tente novamente");
            Answers answers = new Answers();
            answers.address();
        } else {
            petAddress.setNumHouse(numHouse);
            petAddress.setCity(city);
            petAddress.setStreet(street);
            System.out.println(petAddress.getAddress());
        }
    }

    public void validaIdade(String idade) {
        if (!idade.matches("^[0-9.,]+$")) {
            System.out.println("Idade Inválida. Tente Novamente");
            Answers answers = new Answers();
            answers.age();
        }
        if (!idade.matches(",")) {
            idade = idade.replace(',', '.');
            pet.setIdade(idade);
            System.out.println(pet.getIdade());
        } else {
            pet.setIdade(idade);
            System.out.println(pet.getIdade());
        }
        double parseDouble = Double.parseDouble(idade);
        if (parseDouble > 20) {
            throw new IllegalArgumentException("Idade superior a vinte");
        }
    }

    public void validaPeso(String peso) {
        if (!peso.matches("^[0-9.,]+$")) {
            System.out.println("Peso Inválido. Tente Novamente");
            Answers answers = new Answers();
            answers.age();
        }
        if (!peso.matches(",")) {
            peso = peso.replace(',', '.');
            pet.setPeso(peso);
            System.out.println(pet.getPeso());
        } else {
            pet.setPeso(peso);
            System.out.println(pet.getPeso());
        }
        double parsePeso = Double.parseDouble(peso);
        if (parsePeso > 60.00 || parsePeso < 0.5) {
            throw new IllegalArgumentException("Peso inválido");
        }
    }

    public void validaRaca(String raca) {
        String regex = "[(a-zA-Z\\s)]+";
        if (!raca.matches(regex)) {
            System.out.println("Raça não pode conter números ou caracteres especiais");
            Answers answers = new Answers();
            answers.race();
        }
        raca = raca.trim();
        regex = "[a-zA-Z ]+";
        if (!raca.matches(regex)) {
            System.out.println("Raça não pode conter números ou caracteres especiais");
            Answers answers = new Answers();
            answers.race();
        } else {
            pet.setRaca(raca);
            System.out.println(pet.getRaca());

        }
    }
}

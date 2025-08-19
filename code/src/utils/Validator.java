package utils;


import model.Pet;
import model.PetAddress;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validator {
    Pet pet = new Pet();
    Scanner sc = new Scanner(System.in);
    PetAddress petAddress = new PetAddress();

    public String validaNome() {
        String regex = "[(a-zA-Z\\s)]+";
        Scanner sc = new Scanner(System.in);
        String nome = sc.nextLine();

        if (nome.isBlank()) {
            System.err.println("O pet precisa ter um nome e um sobrenome. Tente novamente");
        }

        nome = nome.trim();
        regex = "[a-zA-Z ]+";
        if (!nome.matches(regex)) {
            System.out.println("O nome ou sobrenome não deve conter números ou carácteres especiais, tente novamente");
            nome = sc.nextLine();
        } else {
            return nome;
        }
        return nome;
    }


    public int lerUmDois() {
        try {
            System.out.print("Digite um número: ");
            int numValido = sc.nextInt();
            if (numValido == 1 || numValido == 2) {
                sc.nextLine();
                return numValido;
            } else {
                System.out.println("Entrada inválida! Digite um número valido.");
                return lerUmDois();
            }

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite um número valido.");
            sc.nextLine();
            return lerUmDois();
        }
    }


    public PetAddress validaAddress() {
        System.out.println("Por favor, insira o endereço do pet.");

        String numHouse;
        while (true) {
            System.out.println("Número da casa:");
            numHouse = sc.nextLine();
            if (numHouse.matches("\\d+")) {
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, digite apenas números.");
            }
        }


        System.out.println("Cidade:");
        String city = sc.nextLine();

        System.out.println("Rua:");
        String street = sc.nextLine();

        PetAddress petAddress = new PetAddress();
        petAddress.setNumHouse(numHouse);
        petAddress.setCity(city);
        petAddress.setStreet(street);

        return petAddress;
    }

    public String validaIdade() {
        String idade = sc.next();
        double parseDouble;
        try {
            if (!idade.matches("^[0-9.,\\s]+$")) {
                System.out.println("Idade Inválida. Tente Novamente");
                validaIdade();
            }
            if (!idade.matches(",")) {
                idade = idade.replace(',', '.');
                parseDouble = Double.parseDouble(idade);
            } else {
                parseDouble = Double.parseDouble(idade);
            }
            if (parseDouble > 20.0) {
                System.err.println("Idade Inválida. Tente Novamente");
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro. Tente novamente" + e.getMessage());
        }
        return idade;
    }

    public String validaPeso() {
        String peso = sc.next();
        double parseDouble;
        if (!peso.matches("^[0-9.,\\s]+$")) {
            System.out.println("Peso Inválido. Tente Novamente");
            validaPeso();
        }
        if (!peso.matches(",")) {
            peso = peso.replace(',', '.');
        }
        if (peso.matches("\\s")) {
            return peso;
        } else {
            parseDouble = Double.parseDouble(peso);
        }
        if (parseDouble > 60 || parseDouble < 0.5) {
            throw new IllegalArgumentException("Peso inválido");
        }
        return peso;
    }

    public String validaRaca() {
        String raca = sc.next();
        if (!raca.matches("[(a-zA-Z\\s)]+")) {
            System.out.println("Raça inválida. Digite apenas letras.");
            validaRaca();
        } else {
            return raca;
        }
        return raca;
    }
}

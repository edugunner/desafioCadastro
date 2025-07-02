package service;

import model.Pet;
import utils.Validator;

import java.util.Scanner;

public class Answers {
    Pet pet = new Pet();


    Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();

    public void name() {
        String nome = sc.nextLine();
        String sobrenome = sc.nextLine();
        pet.setNome(nome);
        pet.setSobrenome(sobrenome);
        System.out.println(pet.getNome_completo());


    }

    public void type() {
        String petType = sc.nextLine();
        validator.validaType(petType);
    }

    public void gender() {
        String gender = sc.nextLine();
        validator.validaGender(gender);
    }

    public void address() {
        System.out.println("Número da casa:");
        String numHouse = sc.nextLine();
        System.out.println("Cidade:");
        String city = sc.nextLine();
        System.out.println("Rua:");
        String street = sc.nextLine();
        validator.validaAddress(numHouse, city, street);

    }

    public void age() {
        String idade = sc.nextLine();
        validator.validaIdade(idade);
    }

    public void weight() {
        String peso = sc.nextLine();
        validator.validaPeso(peso);
    }

    public void race() {
        String raca = sc.nextLine();
        validator.validaRaca(raca);
    }
}


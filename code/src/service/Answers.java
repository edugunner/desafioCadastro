package service;

import repository.FileRepository;
import utils.Validator;

import java.util.Scanner;

public class Answers {
    Validator validator = new Validator();
    FileRepository fileRepository = new FileRepository();

    public void name() {
        fileRepository.readLineSpecific(1);
    }

    public void type() {
        fileRepository.readLineSpecific(2);
        System.out.println("Digite \"1\" se for Cachorro e \"2\" se for Gato");

    }

    public void gender() {
        fileRepository.readLineSpecific(3);
        System.out.println("Digite \"1\" se for Macho e \"2\" se for Femêa");
    }

    public void address() {
        fileRepository.readLineSpecific(4);
        validator.validaAddress();
    }

    public void age() {
        fileRepository.readLineSpecific(5);
        validator.validaIdade();
    }

    public void weight() {
        fileRepository.readLineSpecific(6);
        validator.validaPeso();
    }

    public void race() {
        fileRepository.readLineSpecific(7);
        validator.validaRaca();
    }

}


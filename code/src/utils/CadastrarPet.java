package utils;

import model.Pet;
import repository.FileRepository;

public class CadastrarPet {
    Validator validator = new Validator();
    FileRepository fileRepository = new FileRepository();
    Pet pet = new Pet();

    public void cadastrarPet() {
        fileRepository.readLineSpecific(1);
        pet.setNome(validator.validaNome());
        pet.setSobrenome(validator.validaNome());
        System.out.println(pet.getNome());
        System.out.println(pet.getSobrenome());



        fileRepository.readLineSpecific(2);
        System.out.println("Digite \"1\" se for Cachorro e \"2\" se for Gato");
        pet.setTypePet(validator.lerUmDois());
        System.out.println(pet.getType());

        fileRepository.readLineSpecific(3);
        System.out.println("Digite \"1\" se for Macho e \"2\" se for Femêa");
        pet.setGenderPet(validator.lerUmDois());
        System.out.println(pet.getGender());

        fileRepository.readLineSpecific(4);
        pet.setPetAddress(validator.validaAddress());
        System.out.println(pet.getPetAddress());

        fileRepository.readLineSpecific(5);
        pet.setIdade(validator.validaIdade());
        System.out.println(pet.getIdade());

        fileRepository.readLineSpecific(6);
        pet.setPeso(validator.validaPeso());
        System.out.println(pet.getPeso());

        fileRepository.readLineSpecific(7);
        pet.setRaca(validator.validaRaca());
        System.out.println(pet.getRaca());

        pet.savePet();
    }
}

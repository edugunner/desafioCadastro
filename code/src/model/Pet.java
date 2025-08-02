package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pet {
    public String nome;
    public String sobrenome;
    public String idade;
    public String peso;
    public String raca;
    PetType type;
    PetGender gender;
    public PetAddress petAddress = new PetAddress();

    static String NAO_INFORMADO = "Não Informado";

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        if (idade.isBlank()) {
            idade = NAO_INFORMADO;
        }
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        if (peso.isBlank()) {
            peso = NAO_INFORMADO;
        }
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        if (raca.isBlank()) {
            raca = NAO_INFORMADO;
        }
        this.raca = raca;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public PetGender getGender() {
        return gender;
    }

    public void setGender(PetGender gender) {
        this.gender = gender;
    }

    public void setTypePet(int opcao) {
        if (opcao == 1) {
            setType(PetType.CACHORRO);
        }
        if (opcao == 2) {
            setType(PetType.GATO);
        }
    }

    public void setGenderPet(int opcao) {
        if (opcao == 1) {
            setGender(PetGender.MACHO);
        }
        if (opcao == 2) {
            setGender(PetGender.FEMEA);
        }
    }


    public PetAddress getPetAddress() {
        return petAddress;
    }

    public void setPetAddress(PetAddress petAddress) {
        this.petAddress = petAddress;
    }

    @Override
    public String toString() {
        return
                "1- " + nome + " " + sobrenome + '\n' +
                        "2- " + type + '\n' +
                        "3- " + gender + '\n' +
                        "4- " + petAddress + '\n' +
                        "5- " + idade + '\n' +
                        "6- " + peso + '\n' +
                        "7- " + raca + '\n';
    }

    public void savePet() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dataHoraFormatada = now.format(formatter);
        String nomeCompletoPet = this.nome.toUpperCase() + this.sobrenome.toUpperCase();


        File diretorioPets = new File("petsCadastrados");

        try {

            if (!diretorioPets.exists()) {
                diretorioPets.mkdirs();
            }
        } catch (SecurityException e) {

            System.err.println("Erro de segurança ao criar o diretório: " + e.getMessage());
            throw new RuntimeException("Não foi possível criar o diretório para salvar o pet. Verifique as permissões.", e);
        } catch (Exception e) {
            System.err.println("Erro inesperado ao criar o diretório: " + e.getMessage());
            throw new RuntimeException("Erro inesperado ao criar o diretório para salvar o pet.", e);
        }


        String nomeArquivo = dataHoraFormatada + "-" + nomeCompletoPet + ".TXT";

        File filePet = new File(diretorioPets, nomeArquivo);

        try (FileWriter fw = new FileWriter(filePet);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(this.toString());
            System.out.println("Pet salvo com sucesso em: " + filePet.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo do pet: " + e.getMessage());
            throw new RuntimeException("Não foi possível salvar as informações do pet.", e);
        }

    }
}

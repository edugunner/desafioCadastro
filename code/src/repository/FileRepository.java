package repository;


import model.Pet;
import utils.Validator;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileRepository {
    static File formularioDiretorio = new File("code/src/data");
    static File source = new File(formularioDiretorio, "formulario.txt");
    Validator validator = new Validator();
    Pet pet = new Pet();

    public void createFile() {
        formularioDiretorio.mkdirs();

        try (FileWriter fw = new FileWriter(source);
             BufferedWriter bw = new BufferedWriter(fw)) {
            source.createNewFile();
            bw.write("1 - Qual o nome e sobrenome do pet?\n2 - Qual o tipo do pet (Cachorro/Gato)\n3 - Qual o sexo do animal?\n4 - Qual endereço e bairro que ele foi encontrado?\n5 - Qual a idade aproximada do pet?\n6 - Qual o peso aproximado do pet?\n7 - Qual a raça do pet?");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void readFile() {
        File file = new File(String.valueOf(source));
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            String linha = br.readLine();

            System.out.println(linha);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readLineSpecific(int linha) {
        int contador = 0;
        File file = new File(String.valueOf(source));
        try (FileReader fr = new FileReader(file);
             BufferedReader br = new BufferedReader(fr)) {
            while (contador < 7) {
                String answer = br.readLine();
                contador++;
                if (contador == linha) {
                    System.out.println(answer);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}



package service;

import model.Pet;
import utils.Validator;

import java.io.*;
import java.util.Scanner;

public class Questions {
    File questions = new File("C:\\Users\\edusa\\Desktop\\desafioCadastro\\desafioCadastro\\code\\src\\data\\formulario.txt");
    Pet pet = new Pet();
    Answers answers = new Answers();
    Validator validator = new Validator();



    public void printPerguntas() {
        try (FileReader fr = new FileReader(questions);
             BufferedReader br = new BufferedReader(fr)){
            Scanner sc = new Scanner(System.in);
            String linha;

            linha = br.readLine(); // Pergunta 1
            System.out.println(linha);
            answers.race();
            answers.name();

            linha = br.readLine();//Pergunta 2
            System.out.println(linha);
            answers.type();


            linha = br.readLine(); //Pergunta 3
            System.out.println(linha);
            answers.gender();

            linha = br.readLine();
            System.out.println(linha);//Pergunta 4
            answers.address();

            linha = br.readLine(); //Pergunta 5
            System.out.println(linha);
            answers.age();

            linha = br.readLine(); //Pergunta 6
            System.out.println(linha);
            answers.weight();


            linha = br.readLine(); //Pergunta 7
            System.out.println(linha);
            answers.race();

        } catch (IOException e) {
            System.out.println("Arquivo formulario não existe");
            throw new RuntimeException(e);
        }
    }
}

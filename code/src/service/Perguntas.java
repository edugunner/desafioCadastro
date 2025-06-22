package service;

import java.io.*;

public class Perguntas {
    File perguntas = new File("C:\\Users\\edusa\\Desktop\\spring\\desafioCadastro\\code\\src\\data\\formulario.txt");

    public void printPerguntas() {
        try (FileReader fr = new FileReader(perguntas);
             BufferedReader br = new BufferedReader(fr)){
            String linha;
            while ((linha = br.readLine()) != null){
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Arquivo formulario não existe");
            throw new RuntimeException(e);
        }
    }
}

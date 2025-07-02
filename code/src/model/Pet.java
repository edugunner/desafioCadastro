package model;


import service.Answers;

public class Pet {
    private String nome;
    private String sobrenome;
    private String idade;
    private String peso;
    private String raca;
    PetAddress petAddress;
    PetGender petGender;
    PetType petType;
    public static final String NAO_INFORMADO = "Não Informado";

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty() || nome.equalsIgnoreCase(NAO_INFORMADO)) {
            this.nome = NAO_INFORMADO;
            return;
        }
        nome = nome.concat(" ");
        this.nome = nome;
    }


    public String getSobrenome() {

        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        if (sobrenome == null || sobrenome.trim().isEmpty() || sobrenome.equalsIgnoreCase(NAO_INFORMADO)) {
            this.sobrenome = NAO_INFORMADO;
            return;
        }
        this.sobrenome = sobrenome;
    }


    public String getNome_completo() {
        String nome_completo = nome + sobrenome;
        nome_completo = nome_completo.trim();
        String regex = "[a-zA-Z ]+";
        if (!nome_completo.matches(regex)) {
            System.out.println("O Nome não deve conter Números ou Carácteres Especiais, Tente Novamente");
            Answers answers = new Answers();
            answers.name();
        }
        return nome_completo;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        if (idade == null || idade.trim().isEmpty() || idade.equalsIgnoreCase(NAO_INFORMADO)) {
            this.idade = NAO_INFORMADO;
            return;
        }
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        if (peso == null || peso.trim().isEmpty() || peso.equalsIgnoreCase(NAO_INFORMADO)) {
            this.peso = NAO_INFORMADO;
            return;
        }
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        if (raca == null || raca.trim().isEmpty() || raca.equalsIgnoreCase(NAO_INFORMADO)) {
            this.raca = NAO_INFORMADO;
            return;
        }
        this.raca = raca;
    }
}

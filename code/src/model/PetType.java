package model;

public enum PetType {
    CACHORRO("Cachorro"),
    GATO("Gato");


    private final String descricao;

    PetType(String descricao) {
        this.descricao = descricao;
    }
}

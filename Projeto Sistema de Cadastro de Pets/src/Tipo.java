public enum Tipo {
    CACHORRO(1, "Cachorro"),
    GATO(2, "Gato");


    private int valor;
    private String tipo;
    Tipo(int valor, String tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

}

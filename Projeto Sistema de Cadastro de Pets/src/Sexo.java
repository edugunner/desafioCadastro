public enum Sexo {
    MACHO(1, "Macho"),
    FEMEA(2, "Femea");


    private int valor;
    private String sexo;
    Sexo(int valor, String sexo) {
        this.valor = valor;
        this.sexo = sexo;
    }
}

package model;


public class PetAddress {
    private String numHouse;
    private String city;
    private String street;
    static String NAO_INFORMADO = "Não Informado";


    public String getNumHouse() {
        return numHouse;
    }

    public void setNumHouse(String numHouse) {
        if (numHouse.isBlank()) {
            numHouse = NAO_INFORMADO;
        }
        this.numHouse = numHouse;
    }

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        if (city.isBlank()) {
            city = NAO_INFORMADO;
        }
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street.isBlank()) {
            street = NAO_INFORMADO;
        }
        this.street = street;
    }

    @Override
    public String toString() {
        return
                "Rua " + street + ", " + numHouse + ", "+ city;
    }
}

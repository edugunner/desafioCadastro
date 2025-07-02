package model;

import service.Answers;

public class PetAddress {
    private String numHouse;
    private String city;
    private String street;
    private String address;


    public String getNumHouse() {
        return numHouse;
    }

    public void setNumHouse(String numHouse) {
        if (!numHouse.matches("[\\d]+")){
            System.out.println("Endereço inválido. Tente novamente");
            Answers answers = new Answers();
            answers.address();
            return;
        }
        this.numHouse = numHouse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        city = city.concat(" ");
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        street = street.concat(" ");
        this.street = street;
    }

    public String getAddress() {
        return getStreet() + getCity() + getNumHouse();
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

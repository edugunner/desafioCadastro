package service;


import model.Pet;

import java.io.File;

public class SavePet {
    Pet pet = new Pet();
    File file = new File(pet.getNome()+pet.getSobrenome()+".txt");



}
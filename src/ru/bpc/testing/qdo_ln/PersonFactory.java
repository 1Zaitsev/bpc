package ru.bpc.testing.qdo_ln;

import java.util.HashMap;

public class PersonFactory {
    private HashMap<String, Person> kingdomPopulation;
    private King king;

    public HashMap<String, Person> getKingdomPopulation() {
        return kingdomPopulation;
    }

    public void setKingdomPopulation(HashMap<String, Person> kingdomPopulation) {
        this.kingdomPopulation = kingdomPopulation;
    }

    public King getKing() {
        return king;
    }

    public PersonFactory(King king) {
        this.kingdomPopulation = new HashMap<String, Person>(32);
        this.king = king;
    }

    public Person createPerson(String name){
        if(!kingdomPopulation.containsKey(name)){
            Person person = new PersonWithSlaves(name);
            kingdomPopulation.put(name, person);
            king.getSlaves().add(person);
        }
        return kingdomPopulation.get(name);
    }

    public void addSlave(Person person, String slaveName){
        if(!kingdomPopulation.containsKey(slaveName)){
            Person slave = new PersonWithSlaves(slaveName);
            kingdomPopulation.put(slaveName, slave);
            person.getSlaves().add(slave);
        }
        else{
            Person slave = kingdomPopulation.get(slaveName);
            king.getSlaves().remove(slave);
            person.getSlaves().add(slave);
        }
    }
}

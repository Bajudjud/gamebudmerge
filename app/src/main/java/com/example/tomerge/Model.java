package com.example.tomerge;

public class Model {
    private String name;
    private String equipment;
    private String collection;

    public Model(String name, String equipment, String collection) {
        this.name = name;
        this.equipment = equipment;
        this.collection = collection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
